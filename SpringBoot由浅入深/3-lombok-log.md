

# 八、SpringBoot中使用lombok&注意事项

## 01、目标

掌握lombok得基本用发

## 02、步骤

我们编写pojo时，经常需要编写构造函数和gettersetter方法，属性多的时候，就非常浪费时间，使用lombok插件可以解决这个问题：

### 02-01、在idea中安装lombok插件

![img](.\images.assets\kuangstudy0be0904f-d4a3-4e9c-ba37-e146d05b88c5.png)

### 02-02、在pom.xml引入依赖

```xml
 <dependency>   
     <groupId>org.projectlombok</groupId>  
     <artifactId>lombok</artifactId>    
     <version>1.18.20</version>
</dependency>
```

### 02-03、然后在bean增加相关注解

- [@Data](https://github.com/Data) ：自动提供getter和setter、hashCode、equals、toString等方法
- [@Getter](https://github.com/Getter)：自动提供getter方法
- [@Setter](https://github.com/Setter)：自动提供setter方法
- [@Slf4j](https://github.com/Slf4j) and [@Log4j](https://github.com/Log4j)：自动在bean中提供log变量，在需要打印日志的类中使用，项目中使用slf4j、log4j日志框架
- [@ToString](https://github.com/ToString) ：生成toString方法
- [@NonNull](https://github.com/NonNull) ：这个注解可以用在**成员方法**或者**构造方法的参数**前面，会自动产生一个关于此参数的非空检查，如果参数为空，则抛出一个空指针异常。
- [@Cleanup](https://github.com/Cleanup) 注解用于确保已分配的资源被释放（IO的连接关闭）。
- [@Synchronized](https://github.com/Synchronized) 注解自动添加到同步机制，生成的代码并不是直接锁方法,而是锁代码块， 作用范围是方法上。
- [@Builder](https://github.com/Builder) 可以实现属性连续赋值
- [@NoArgsConstructor](https://github.com/NoArgsConstructor): 自动生成无参数构造函数。
- [@AllArgsConstructor](https://github.com/AllArgsConstructor): 自动生成全参数构造函数。
- [@Value](https://github.com/Value)注解和[@Data](https://github.com/Data)类似，区别在于它会把所有成员变量默认定义为private final修饰，并且不会生成set方法。
- [@Accessors](https://github.com/Accessors) [@Accessors](https://github.com/Accessors)批注用于配置lombok如何生成和查找getter和setter。标注到类上，chain属性设置为true时，类的所有属性的setter方法返回值将为this，用来支持setter方法的链式写法

```java
new User().setUsername("riemann").setPassword("123");
```

### 02-04、案例

```java
package com.itbooking.pojo;
import lombok.*;
@ToString@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {  
    private Integer id; 
    private String bookname; 
    private Long price;   
    private String pic;   
    private String bookdesc;}

```






# 九、SpringBoot日志存储路径和设置日志格式

 创建时间：2021/05/19 00:02 [字体](javascript:void(0);) [皮肤](javascript:void(0);)最后修改于： 2021/06/25 14:21

## 01、目标

学习springboot日志的框架，以及日志级别、设置日志的存储路径、设置日志的格式等等。

## 02、分析

在springboot的底层日志结构中对应：`spring-boot-starter-logging`可以看出，它依赖了三个框架分别是：

- sl4j
- logback
- log4j

![img](.\images.assets\kuangstudy3d4bfc45-dfc9-481f-969c-8cfc6e6a23ae.png)

### 03、sl4j、logback和log4j三者的关系

1、logbck和log4j是日志实现框架，就是实现怎么记录日志的。
2、sl4j：提供了java所有的日志框架的简单抽象（使用了日志的门面设计模式），说白了就是一个日志API（`没有实现类`）, 它不能单独使用：必须要结合logback和log4j日志框架来实现结合使用。

## 04、springboot的日志搭配

springboot2.x以后默认采用了：sl4j+logback的日志搭配。
在开发过程中，我们可以采用sl4j的api去记录日志，底层的实现就是根据配置来决定使用logback还是log4j日志框架。

如：

```
package com.kuangstudy.controller;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RestController;/** * @description: * @author: xuke * @time: 2021/6/1 19:58 */@RestControllerpublic class LogController {    private static final Logger log = LoggerFactory.getLogger(LogController.class);    @GetMapping("/log")    public void console(){        log.trace("----------trace--------");        log.debug("----------debug--------");        log.info("----------info--------");        log.warn("----------warn--------");        log.error("----------error--------");    }}
```

通过浏览器访问：`http://localhost:9999/log` 只输入了如下内容：

```
 [nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------info-------- [nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------warn-------- [nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------error--------
```

为什么值打印了：`info`、`warn`、`error`呢？
因为：springboot默认的日志级别是：`info`

### 04-01、修改springboot的日志级别

打开`application.yml`配置文件:

```
# 打开com.kuangstudy目录的日志级别logging:  level:    com:      kuangstudy: trace#如果你想全部打开可以配置：#logging:#  level:#    root: debug
```

打印日志结果如下：

```
[nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------trace--------[nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------debug--------[nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------info--------[nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------warn--------[nio-9999-exec-1] com.kuangstudy.controller.LogController  : ----------error--------
```

日志级别：总共有TRACE < DEBUG < INFO < WARN < ERROR < FATAL ，且级别是逐渐提供，如果日志级别设置为INFO，则意味TRACE和DEBUG级别的日志都看不到。

### 04-02、日志输出和日志格式

```
server:  port: 9999logging:  level:    root: info  file:    # 默认情况下：会在项目的根目录下生成/output/logs/spring.log，默认的日志名是：spring.log    path: output/logs    # 如果不想把日志存放在logging.file.path目录下，可以采用name来重新定义存储的位置和日志文件的名称    #name: d:/output/logs/console.log  pattern:    console: "%d{yyyy/MM/dd-HH:mm:ss} [%thread] %-5level %logger{50}- %msg%n"    file: "%d{yyyy/MM/dd-HH:mm:ss} ---- [%thread] %-5level %logger{50}- %msg%n"
%c 输出logger名称%C 输出类名%d{HH:mm:ss.SSS} 表示输出到毫秒的时间%t 输出当前线程名称%-5level 输出日志级别，-5表示左对齐并且固定输出5个字符，如果不足在右边补0%logger 输出logger名称，因为Root Logger没有名称，所以没有输出%msg 日志文本%n 换行其他常用的占位符有：%F 输出所在的类文件名，如Log4j2Test.java%L 输出行号%M或%method 输出所在方法名%l 输出完整的错误位置, 包括类名、方法名、文件名、行数%p 该条日志的优先级%replace{pattern}{regex}{substitution} 将pattern的输出结果pattern按照正则表达式regex替换成substitution
```




