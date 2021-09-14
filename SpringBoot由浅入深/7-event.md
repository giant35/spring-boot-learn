
# 二十四、SpringBoot中初始化加载的四种方式



## 01、目标

SpringBoot中初始化加载的四种方式

## 02、分析

在平时的业务模块开发过程中,平常我们经常会有一些需求是项目启动时候加载一下预置数据，难免会需要做一些全局的任务、缓存、线程等等的初始化工作，那么如何解决这个问题呢？方法有多种，但具体又要怎么选择呢？

![img](.\images.assets\kuangstudy073a9bf7-cb8a-4a3d-8b16-66271c6c1195.png)

**按照前面的分析，Spring-boot容器启动流程总体可划分为2部分：**

- 执行注解：扫描指定范围下的bean、载入自动配置类对应的bean加载到IOC容器。
- main方法中具体SpringAppliocation.run()，全流程贯穿SpringApplicationEvent(经典的spring事件驱动模型),有6个子类：
  ApplicationFailedEvent.class
  ApplicationPreparedEvent.class
  ApplicationReadyEvent.class
  ApplicationStartedEvent.class
  ApplicationStartingEvent.class
  SpringApplicationEvent.class

## 03、加载方式一、实现InitializingBean 接口

```java
public class InitSequenceBean implements InitializingBean {   
    public InitSequenceBean() {   
       System.out.println("InitSequenceBean: constructor");   
    }   
    @PostConstruct  
    public void postConstruct() {   
       System.out.println("InitSequenceBean: postConstruct");   
    }   
    public void initMethod() {   
       System.out.println("InitSequenceBean: init-method");   
    }   
    @Override  
    public void afterPropertiesSet() throws Exception {   
       System.out.println("InitSequenceBean: afterPropertiesSet");   
    }   
}
```

InitializingBean 是 Spring 提供的一个接口，只包含一个方法 afterPropertiesSet()。凡是实现了该接口的类，当其对应的 Bean 交由 Spring 管理后，当其必要的属性全部设置完成后，Spring 会调用该 Bean 的 afterPropertiesSet()。在Bean在实例化的过程中执执行顺序为：`Constructor > @PostConstruct > InitializingBean > init-method`

## 04、加载方式二、[@PostConstruct](https://github.com/PostConstruct)注解

```java
＠Component
public Class AAA {    
    @Autowired    
    private BBB b;   
    public AAA() {        
        System.out.println("此时b还未被注入: b = " + b);    
    }    
    @PostConstruct    
    private void init() {        
        System.out.println("此时b已经被注入: b = " + b);    
    }
}
```

在具体Bean的实例化过程中执行，[@PostConstruct](https://github.com/PostConstruct)注解的方法，会在构造方法之后执行，顺序为`Constructor > @Autowired > @PostConstruct > 静态方法`，所以这个注解就避免了一些需要在构造方法里使用依赖组件的尴尬（与之对应的还有[@PreDestroy](https://github.com/PreDestroy)，在对象消亡之前执行，原理差不多）使用特点如下：
-只有一个非静态方法能使用此注解
-被注解的方法不得有任何参数
-被注解的方法返回值必须为void
-被注解方法不得抛出已检查异常
-此方法只会被执行一次

## 05、加载方式三、实现ApplicationRunner接口

```java
@Component
@Order(0)
public class ApplicationRunnerTest implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.printf("*****************************applicationContext ***********");
        System.out.printf("***");
    }
}
```

## 06、加载方式四、实现CommandLineRunner接口或者实现CommandLineRunner接口

由上可知，我们只要实现这两个中的任何一个接口便可以完成我们的资源初始化任务，可以看到它们的加载是在容器完全启动之前。它两的区别是：前者的run方法参数是String…args，直接传入字符串，后者的参数是ApplicationArguments，对参数进行了封装。功能上是一样的。同时也可以使用 [@Order](https://github.com/Order)注解来实现资源加载的先后顺序，值越小，优先级越高。实例如下：

```java
@Component
@Order(1)
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("...init resources by implements CommandLineRunner");
    }
}
@Component
@Order(2)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("...init resources by implements ApplicationRunner");
    }
}
```

## 06、加载方式四、ApplicationListener

ApplicationListener 就是spring的监听器，能够用来监听事件，典型的观察者模式。如果容器中有一个ApplicationListener Bean，每当ApplicationContext发布ApplicationEvent时，ApplicationListener Bean将自动被触发。这种事件机制都必须需要程序显示的触发。其中spring有一些内置的事件，当完成某种操作时会发出某些事件动作。比如监听ContextRefreshedEvent事件，当所有的bean都初始化完成并被成功装载后会触发该事件，实现ApplicationListener接口可以收到监听动作，然后可以写自己的逻辑。同样事件可以自定义、监听也可以自定义，完全根据自己的业务逻辑来处理。所以也能做到资源的初始化加载！

**定义事件处理类**

```java
package com.kuangstudy.service.event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 15:36
 */
public class EmailEvent extends ApplicationContextEvent {
    private String userid;
    private String email;
    public EmailEvent(ApplicationContext context,String userid, String email) {
        super(context);
        this.userid = userid;
        this.email = email;
    }
    public void sendEmail(String email) {
        System.out.println(userid + "：发送邮件!!!!" + email);
    }
}
```

**定义事件监听**

```java
package com.kuangstudy.service.event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class EmailListerner implements ApplicationListener<EmailEvent> {//ContextRefreshedEvent为启动事件
    @Override
    public void onApplicationEvent(EmailEvent event) {
        System.out.println("初始化数据");
        event.sendEmail("xuechengfeifei");
    }
}
```

**进行事件发布**

```java
@Autowired
private ApplicationContext applicationContext;
// 事件发布
applicationContext.publishEvent(new EmailEvent(applicationContext,"1","xuechengfeifei@163.com"));
```



# 二十五、Spring实战系列（一）-监听器模式开发

> “对于Spring框架，现实公司使用的非常广泛，但是由于业务的复杂程度不同，了解到很多小伙伴们利用Spring开发仅仅是利用了Spring的IOC，即使是AOP也很少用，但是目前的Spring是一个大家族，形成了一个很大的生态，覆盖了我们平时开发的方方面面，抛开特殊的苛刻要求之外，Spring的生态其实已经很全面了，所以在此开个系列来研究下Spring提供给我们的一些平时不太却又很实用的内容。”

**说明：**
我们平时在开发的时候都在讲高内聚低耦合，而且目前开发业务的时候业务一般在一个工程内部，但是为了考虑后期的可维护性，所以内部又以包名划分出了多个业务模块，但是有时不可避免的出现多个模块之间业务调用的情况。尤其是在采用RPC框架的时候，我们可能抽象出了一些服务接口，但是在同一个工程内部，RPC服务接口互相调用总觉得怪怪的，有没有相对优雅的方式来处理呢？答案是有的，就是利用Spring的事件监听模式。

> 定义一个场景：用户签到并获取积分，用户签到，记录签到信息，然后给用户添加对应的签到积分。
> 根据描述我们需要划分一下模块：积分模块，签到模块。下面开始设计代码
> 1、积分模块

1.1、积分业务接口

```java
public interface PointBizService {
    /**
     * 处理用户积分
     *
     * @param source
     * @return
     */
    public long handleUserPoint(int source);
}
```

1.2、积分业务实现类

```java
@Service
public class PointBizServiceImpl implements PointBizService {
    @Override
    public long handleUserPoint(int source) {
        return 0;
    }
}
```

1.3、积分事件

```java
public class PointEvent<PointHandleVO> extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public PointEvent(PointHandleVO source) {
        super(source);
    }
```

2、签到模块

2.1、签到业务接口

```java
public interface SignBizService {
    /**
     * @param uid
     * @return
     */
    public boolean userSign(long uid);
}
```

2.2、签到业务实现类

```java
@Service
public class SignBizServiceImpl implements SignBizService {
    @Autowired
    private ApplicationContextUtils applicationContextUtils;
    @Override
    public boolean userSign(long uid) {
        //....处理原有的业务
        applicationContextUtils.getApplicationContext().publishEvent(new PointEvent(new PointHandleVO(2)));
        return false;
    }
}
```

3、工具类

```java
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
```

以上是整体业务的核心代码，简单梳理下监听器的流程：1、用户处理完原有的签到业务后，通过工具类获取到ApplicationContext类，然后调用publishEvent(ApplicationEvent event)方法发布事件，然后PointEventListener事件监听器类监听到自己的PointEvent事件，进行业务处理，这样的实现避免了业务服务的直接调用，看着简洁些，但是这个也有个缺点:这种写法只能在同一个java进程中使用。如果是在分布式环境下利用好集群的特性，可以引入消息队列等第三方中间件来帮助实现业务的解耦。