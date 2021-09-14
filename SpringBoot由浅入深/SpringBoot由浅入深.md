# 一、为什么选择SpringBoot

## 01、目标

了解和掌握springboto项目

## 02、概述

SpringBoot是随着spring4.0诞生的，它于2014年4月，发布了SpringBoot1.0.0。
SpringBoot是一个内嵌Web容器（tomcat/jetty）的可执行程序(jar)的框架。
你在开发web应用程序的时候，不需要将项目打成war包部署到web容器中。而是作为一个可执行的程序jar即可。
启动的时候把web服务器配置好，加载起来即可运行。

### 传统的spring项目存在的问题

- 大量的xml文件，配置相当繁琐
- 整合第三方框架的配置相当复杂
- 低效的开发效率和部署效率等问题
- 依赖外部的web服务器
- 日志管理需要依赖
- 一堆的依赖在maven中pom.xml中

### springboot解决了什么问题

- 创建独立的Spring应用程序
- 直接嵌入Tomcat、Jetty或Undertow（无需部署WAR文件）
- 提供固执己见的“启动程序”依赖项以简化构建配置
- 尽可能自动配置Spring和第三方库
- 提供生产准备功能，如度量、运行状况检查和外部化配置
- 完全没有代码生成，也不需要XML配置

### 帮助文档

https://docs.spring.io/spring-boot/docs/2.4.6/reference/htmlsingle/



# 二、使用SpringBoot快速搭建一个单体架构

## 01、目标

快速构建一个springboot项目

## 02、步骤

- 1、使用idea快速构建一个springboot工程
- 2、认识springboot的目录结构
- 3、运行springboot项目

## 03、具体实现

### 03-01、使用idea快速构建一个springboot工程

![img](.\images.assets\kuangstudy28155ad5-f47c-4c2b-b6f4-ea13b2060777.png)

![img](.\images.assets\kuangstudy48090756-361c-4f97-bdee-75096e9e8e74.png)

![img](.\images.assets\kuangstudy9ee53dda-f705-4d3f-b046-a35b49646bbc.png)

![img](.\images.assets\kuangstudy23181fb1-8ffc-45e6-9926-ae495a799e74.png)

### 03-02、认识springboot的目录结构

![img](.\images.assets\kuangstudy67064ed4-28bb-4475-ba70-2ec6190be382.png)

#### 核心配置pom.xml

```xml
<!--web的依赖：包括spring,日志、json、自动配置、容器等-->
<dependency>    
    <groupId>org.springframework.boot</groupId> 
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!--测试启动-->
<dependency>    
    <groupId>org.springframework.boot</groupId>    
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
```

#### 核心配置applicaiton.properties

默认是空的。但是springboot提供了很多的默认值比如：
默认端口和访问路径是：`8080`和`/`

#### 核心的配置类

`KuangstudyBootApplication.java` 程序从这个地方进行访问和启动。

```java
package com.kuangstudy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KuangstudyBootApplication {   
    public static void main(String[] args) {
        SpringApplication.run(KuangstudyBootApplication.class, args);    
    }
}
```

#### 静态资源处理

- staitc：存放静态资源css/js/images/fonts
- templates：freemarker或者thymeleaf存放模板html页面的位置

### 03-03、运行springboot项目

- 定义一个HelloWorldContorller.java

```java
package com.kuangstudy.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {   
    @GetMapping("/hello")   
    public String test() {        
        return "Hello xueixangban!!!";    
    }
}
```

- 直接运行：`KuangstudyBootApplication.java` 即可
- 访问：`http://localhost:8080/hello`





# 三、SpringBoot项目搭建说明&starter机制



## 01、目标

掌握和了解springboot环境搭建和starter机制

## 02、分析

### 依赖环境

Spring Boot 2.4.6需要Java 8和兼容Java 16(包括)。Spring Framework 5.3.7或以上也是必需的。

- maven3.5+
- jdk1.8+
- tomcat9.0

### 内置的starter

starter是一组方便的依赖项描述符，您可以将其包含在您的应用程序中。您可以一站式地获得所需的所有Spring和相关技术，而不必遍历示例代码和复制-粘贴加载依赖关系描述符。例如，如果您希望开始使用Spring和JPA访问数据库，则需要在项目中包含Spring -boot-starter-data- JPA依赖项。
启动器包含大量的依赖项，您需要这些依赖项来快速启动和运行一个项目，并且具有一组一致的、受支持的托管传递依赖项

starter的命名规范：

- 官方的是`spring-boot-starter-xxxx`
- 第三方的名的是 `xxxx-boot-starter`

更多从参考：https://docs.spring.io/spring-boot/docs/2.4.6/reference/htmlsingle/#using-boot-starter



# 四、SpringBoot常见配置说明



## 01、目标

- 掌握和了解springboot的常见配置

## 02、具体实现

### 02-01、修改默认端口

```
server.port=9999
```

- 修改随机端口

```
#随机端口server.port=${random.int[8080,8999]}
```

随机端口的好处就是：
1：如果在同一台服务器上多个服务如果用同一个端口会造成端口冲突
2：在微服务项目和开发中，开发人员是不会去记住ip和端口的。我们一般在真实的开发环境下，设置一个随机端口，就不用去管理端口了。也不会造成端口的冲突。





# 五、SpringBoot中yml配置说明

[![img](.\images.assets\132.jpeg) 学相伴-飞哥 ](https://www.kuangstudy.com/user/aaa284f6f98146d4a927e0d42dacb01b) 分类：[学习笔记](https://www.kuangstudy.com/bbs?cid=4) 创建时间：2021/06/01 16:56 [字体](javascript:void(0);) [皮肤](javascript:void(0);)最后修改于： 2021/06/01 19:40

## 01、目标

学习和掌握yml配置

## 02、分析

yml是YAML（YAML Ain’t MarkUp Language）语言的配置文件。以数据为中心，以键值的方式存在，更适合做配置文件。

- 如果properties和yml都配置：properties的优先级要高于yml配置。
- 如果两者不同的地方，取并集，相同的部分properties覆盖yml中的配置

## 03、具体实现

### 03-01、在src/resources目录下新建application.yml

```
server:  port: 9999alipay:  appid: wx2e43sdf23433231111  email: xucnengsdfs@323.comuser:  names: zhangsan,xiaoxie,lisi,xiaoguo  friends: zhangsan,xiaoxie,lisi,xiaoguo
```

> 注意：key和value之间，两个空格或者用tab键即可。

### 03-02、测试如下

http://localhost:9999/value

```json
{  
    "port": 9999,
    "appid": "wx2e43sdf23433231111",
    "email": "xucnengsdfs@323.com", 
    "names": [    "zhangsan",    "xiaoxie",    "lisi",    "xiaoguo"  ],  
    "friends": [    "zhangsan",    "xiaoxie",    "lisi",    "xiaoguo"  ]
}
```





# 六、自定义配置&@Value注入属性

[![img](.\images.assets\132.jpeg) 学相伴-飞哥 ](https://www.kuangstudy.com/user/aaa284f6f98146d4a927e0d42dacb01b) 分类：[学习笔记](https://www.kuangstudy.com/bbs?cid=4) 创建时间：2021/06/13 13:25 [字体](javascript:void(0);) [皮肤](javascript:void(0);)最后修改于： 2021/06/13 13:30

## 01、目标

使用[@Value](https://github.com/Value)的方式完成属性的注入和配置

## 02、具体实现

**第一：在appliation.properties中自定义属性配置如下:**

```properties
# 普通属性
alipay.appid=24sdfkja23423
alipay.email=xuchengfeifei@q163.com
# 数组
user.names=zhangsan,xiaoxie,lisi,xiaoguo
# 集合 List<String>
user.friends=zhangsan,xiaoxie,lisi,xiaoguo
```

**第二：定义属性配置类**

> 提示：在任意配置类，springioc容器管理的类都可以通过[@Value](https://github.com/Value)获取配置文件中的属性，如下：

```java
package com.kuangstudy.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
/** 
* @author 飞哥 
* @Title: 学相伴出品 
* @Description: 我们有一个学习网站：https://www.kuangstudy.com 
* @date 2021/6/1 16:31 
*/
@Component
public class KsdWexinPayConfig { 
    @Value("${server.port}")    
    private int port;    
    @Value("${alipay.appid}")    
    private String appid;    
    @Value("${alipay.email}")    
    private String email;    
    @Value("${user.names}")    
    private String[] names;    
    @Value("${user.friends}")    
    private List<String> friends;    
    public int getPort() {        
        return port;    
    }    
    public void setPort(int port) { 
        this.port = port;    
    }    
    public String getAppid() {     
        return appid;    
    }    
    public void setAppid(String appid) { 
        this.appid = appid;    
    }    
    public String getEmail() { 
        return email;    
    }    
    public void setEmail(String email) {
        this.email = email;    
    }    
    public String[] getNames() {
        return names;    
    }   
    public void setNames(String[] names) {  
        this.names = names;   
    }   
    public List<String> getFriends() {   
        return friends;    
    }    
    public void setFriends(List<String> friends) {  
        this.friends = friends;    
    }
}
```

**第三：定义属性配置测试**

```java
package com.kuangstudy.controller;
import com.kuangstudy.config.KsdWexinPayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/** 
* @author 飞哥 
* @Title: 学相伴出品 
* @Description: 我们有一个学习网站：https://www.kuangstudy.com 
* @date 2021/6/1 15:44 
*/
@RestControllerpublic 
class ValueConfigurationController {  
    @Autowired    
    private KsdWexinPayConfig ksdWexinPayConfig;  
    @GetMapping("/value")    
    public KsdWexinPayConfig value() {  
        return ksdWexinPayConfig;   
    }
}
```

**第四：测试访问**

http://localhost:9999/value

```java
{    "port": 9999,    "appid": "wx2e43sdf23433231111",    "email": "xuchengfeifei@q163.com",    "names": [    "zhangsan",    "xiaoxie",    "lisi",    "xiaoguo"],"friends": [    "zhangsan",    "xiaoxie",    "lisi",    "xiaoguo"]}
```



# 七、@ConfigurationProperties注入属性

## 01、目标

完成属性配置的注入

## 02、具体操作

### 02-01、在application.yml配置如下：

```yaml
kuangstudy:alipay:    
key: 12352342    
appid: wx2e43sdf23433231111    
email: xuchengfeifei@163.com    
mcid: 1823923932
```

### 02-02、定义配置类：

```java
package com.kuangstudy.config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
@ConfigurationProperties(prefix = "kuangstudy.alipay")
@SpringBootConfiguration
@EnableConfigurationProperties(AlipayProperties.class)
public class AlipayProperties {    
    // 获取支付得信息    
    private String appid ;    
    // 邮箱    
    private String email;    
    // 私钥    
    private String key;    
    // 商户id    
    private String mcid;    
    public String getAppid() {      
        return appid;    
    }    
    public void setAppid(String appid) { 
        this.appid = appid;    
    }    
    public String getEmail() {  
        return email;    
    }    
    public void setEmail(String email) {
        this.email = email;    
    }   
    public String getKey() {  
        return key;    
    }    
    public void setKey(String key) {
        this.key = key;   
    }    
    public String getMcid() {  
        return mcid;   
    }    
    public void setMcid(String mcid) {   
        this.mcid = mcid;    
    }    
    @Override    
    public String toString() {    
        return "AlipayProperties{" +    
            "appid='" + appid + '\'' +   
            ", email='" + email + '\'' +      
            ", key='" + key + '\'' +          
            ", mcid='" + mcid + '\'' +       
            '}';   
    }}

@SpringBootConfiguration：申明当前是一个配置类@ConfigurationProperties(prefix = "kuangstudy.alipay") - 标记当前类是一个属性配置类，并且自定义前缀`kuangstudy.alipay`@EnableConfigurationProperties(AlipayProperties.class) - 注册到springboot的属性配置中，让其可以自动提示
```

### 02-03、属性配置类自动提示依赖：

```xml
<dependency>    
    <groupId>org.springframework.boot</groupId>
    <artifactId>
        spring-boot-configuration-processor
    </artifactId>
</dependency>
```

配置好以后，记得：`mvn clean package` 才可以生效

### 02-04、访问测试：

```
##访问http://localhost:9999/value2##结果如下：AlipayProperties{appid='wx2e43sdf23433231111', email='xuchengfeifei@163.com', key='12352342', mcid='1823923932'}
```



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

## 03、番外 - 为什么要放弃lombok

来源参考：https://zhuanlan.zhihu.com/p/146659383

如果您正在阅读此文，想必您对Project Lombok已经有了一段时间的了解。您是否正准备拥抱Lombok?还是正准备将如此酷炫的项目推荐给你的团队？如果您准备那么做，不妨听听我在使用Lombok一年后的一些感受。

我承认，Lombok是一个很不错的Java库，它可以让你在少写代码的同时耍耍酷，简单的几个注解，就可以干掉一大片模板代码。但是，所有的源代码很多时候是用来阅读的，只有很少的时间是用来执行的(你可以细品这句话)。一年以前，我和大多数人都认为Lombok的出现会让Java的编码体验会更好，并极力的在我的团队中推荐使用Lombok。一年以后，我开始对此产生顾虑，尤其是在我准备为开源的博客系统Una-Boot升级Java版本时，我才意识到Lombok自己掉入了一个戏法陷阱。在我进一步分析其源代码并理解相关注解的工作原理后，发现我并不需要使用一个非标准的第三方库将Java转换为一个精巧而酷炫的语言。引入Lombok让我的项目一时爽，但一时爽的代价是随着项目推进，技术债务开始累积。接下来，我将用几个大家耳熟能详的场景，重演自己是如何掉入Lombok的戏法陷阱。

### 爱的开始，恨的起源

面对Lombok提供的诸多“神走位”，你并不会介意在IDE上新增一个插件。对于IntelliJ IDEA玩家而言，只需搜索“Lombok Plugin”便可找到这款神器并安装上它。爱上Lombok从安装Lombok插件开始，恨也从此萌芽。

没使用Lombok之前，我们的源代码看起来是这一的：

```java
public class MyObject{   
    private Long id;  
    private String name; 
    private int age;   
    private int gender;    
    public Long getId(){       
        return id;    }    
    public void setId(Long id){  
        this.id = id;    
    }    
    public String getName(){ 
        return name;   
    }   
    public void setName(String name){ 
        this.name = name;    
    }    
    public int getAge(){    
        return age;   
    }    
    public void setAge(int age){ 
        this.age = age;    
    }    
    public int getGender(){   
        return gender;   
    }    
    public void setGender(int gender){ 
        this.gender = gender;   
    }    
    @Override  
    public boolean equals(Object o){ 
        if(this == o){       
            return true;      
        }       
        if(o == null || getClass() != o.getClass()){    
            return false;      
        }       
        MyObject obj = (MyObject) o; 
        return age = obj.age &&   gender = obj.gender && Objects.equals(id,obj.id) &&  Objects.queals(name,obj.name);    
    }    
    @Override    
    public int hashCode(){
        return Objects.hash(id,name,age,gender); 
    }    
    @Override   
    public String toString(){ 
        return "MyObject{"+  "id="+id+"name="+name+ "age="+age+"gender="+gander+"}";    
    }
}
```

每个JavaBean都会充斥着如上述getter，setter，equals，hashCode和toString的模板代码，这看起来像一个偏胖的人（不得不承认Java是一个有缺陷的编程语言）。当我们安装好Lombok插件后，IDE便可以识别其酷炫的注解，使用Lombok的[@Getter](https://github.com/Getter)和[@Setter](https://github.com/Setter)注解后，代码会像下面这样看起来很苗条：

```java
@Getter
@Setter
public class MyObject{  
    private Long id;    
    private String name;    
    private int age;    
    private int gender;   
    @Override    
    public boolean equals(Object o){  
        if(this == o){           
            return true;        }  
        if(o == null || getClass() != o.getClass())
        {            
            return false;      
        }        
        MyObject obj = (MyObject) o;  
        return age = obj.age &&  
            gender = obj.gender && 
            Objects.equals(id,obj.id) && 
            Objects.queals(name,obj.name);    
    }    
    @Override    
    public int hashCode(){ 
        return Objects.hash(id,name,age,gender);  
    }    
    @Override  
    public String toString(){
        return "MyObject{"+            "id="+id+            "name="+name+            "age="+age+            "gender="+gander+            "}";    }}

```

现在的代码是否看起来爽多了？但这还不是最爽的时候。既然其他方法都替换掉了，那把toString方法也一起拿掉吧.如你所愿，可以使用[@ToString](https://github.com/ToString)注解去掉对于的方法：

```java
@Getter
@Setter
@EqualsAndHashCode
public class MyObject{  
    private Long id;
    private String name; 
    private int age;  
    private int gender;  
    @Override    
    public String toString(){  
        return "MyObject{"+            "id="+id+            "name="+name+            "age="+age+            "gender="+gander+            "}";    }
}
```

经过Lombok的戏法之后，相比一开始的代码，看起来是不是很酷炫，很苗条，很性感？你以为到此为止了？远不止于此。你会发现类名上一大坨注解看起来好别扭，Lombok提供了一个组合注解[@Data](https://github.com/Data)，可以替换掉类名头上那坨像翔一样的东西：

```
@Datapublic class MyObject{    private Long id;    private String name;    private int age;    private int gender;}
```

现在，Lombok是否让你的对象成为了你心目中完美的样子?魔鬼的“身材”，酷炫精炼。Lombok还有其他一些注解，如[@Slf4j](https://github.com/Slf4j)，[@NoArgsConstructor](https://github.com/NoArgsConstructor)，[@AllArgsConstructor](https://github.com/AllArgsConstructor)等等，介绍Lombok用法不是本文重点。

以上代码行数的变化过程，也许是无数程序员爱上Lombok的主要原因吧，这就像一个肥胖的人逐渐变成一个身材苗条的人。同时也让你看到了一个现象：你以为程序员很懒吗？其他有些时候他们比你想象中的还要懒。在爽的同时，也为代码种下了祸根。

### 扭曲的审美，爱的隐患

扭曲的审美，导致了被审视的对象处于亚健康状态。使用Lombok插件之后，我们的代码也处于“亚健康”状态。还是回归一开始的那句话：所有的源代码很多时候是用来阅读的，只有很少的时间是用来执行的。本质上讲，我们都追求减少程序中的样板代码以使其代码更精炼简洁，从而提高代码的可读性和可维护性。但Lombok并没有达到我们所追求的这一愿景，它仅仅是利用Java语言在编译时的空档期，使用一种很取巧的方式，将我们所需要的方法注入（写入）到当前的类中，这种过程很像在hack我们的代码，只是一种看起来酷炫的把戏。这种把戏并不智能和安全，反而会破坏Java代码现有的特性以及代码的可读性。下面，结合我自己使用Lombok之后的感受，谈谈Lombok带来的几大痛点。

### 03-01、JDK版本问题

当我想要将现有项目的JDK从Java 8升级到Java 11时，我发现Lombok不能正常工作了。于是我不得不将所有的Lombok注解从项目源代码中清除，并使用IDE自带的功能生成getter/setter，equals，hashCode，toString以及构造器等方法，你也可以使用Delombok工具完成这一过程。但这终究会消耗你很多的时间。

### 03-02、胁迫使用

当你的源代码中使用了Lombok，恰好你的代码又被其他的人所使用，那么依赖你代码的人，也必须安装Lombok插件(不管他们喜不喜欢)，同时还要花费时间去了解Lombok注解的使用情况，如果不那么做，代码将无法正常运行。使用过Lombok之后，我发现这是一种很流氓的行为。

### 03-03、可读性差

Lombok隐藏了JavaBean封装的细节，如果你使用[@AllArgsConstructor](https://github.com/AllArgsConstructor)注解，它将提供一个巨型构造器，让外界有机会在初始化对象时修改类中所有的属性。首先，这是极其不安全的，因为类中某系属性我们是不希望被修改的；另外，如果某个类中有几十个属性存在，就会有一个包含几十个参数的构造器被Lombok注入到类中，这是不理智的行为；其次，构造器参数的顺序完全由Lombok所控制，我们并不能操控，只有当你需要调试时才发现有一个奇怪的“小强”在等着你；最后，在运行代码之前，所有JavaBean中的方法你只能想象他们长什么样子，你并不能看见。

### 03-04、代码耦合度增加

当你使用Lombok来编写某一个模块的代码后，其余依赖此模块的其他代码都需要引入Lombok依赖，同时还需要在IDE中安装Lombok的插件。虽然Lombok的依赖包并不大，但就因为其中一个地方使用了Lombok，其余所有的依赖方都要强制加入Lombok的Jar包，这是一种入侵式的耦合，如果再遇上JDK版本问题，这将是一场灾难。

### 03-05、得不偿失

使用Lombok，一时觉得很爽，但它却污染了你的代码，破坏了Java代码的完整性，可读性和安全性，同时还增加的团队的技术债务，这是一种弊大于利，得不偿失的操作。如果你确实想让自己的代码更加精炼，同时又兼顾可读性和编码效率，不妨使用主流的Scala或Kotlin这一基于JVM的语言。

![img](.\images.assets\kuangstudyc288a5d0-cc42-43e3-a64f-91ce4dca05c4.png)

### 03-06、总结

Lombok本身是一个优秀的Java代码库，它采用了一种取巧的语法糖，简化了Java的编码，为Java代码的精简提供了一种方式，但在使用此代码库时，需要了解到Lombok并非一个标准的Java库。使用Lombok，会增加团队的技术债务，降低代码的可读性，增大代码的耦合度和调式难度。虽然在一定程度上Lombok减少了样板代码的书写，但也带来了一些未知的风险。如果你正在参与一个团队项目（或大型项目）,考虑到后续的升级与扩展，是否使用Lombok，请与你的团队多沟通和三思。





# 九、SpringBoot日志存储路径和设置日志格式

[![img](.\images.assets\132.jpeg) 学相伴-飞哥 ](https://www.kuangstudy.com/user/aaa284f6f98146d4a927e0d42dacb01b) 分类：[学习笔记](https://www.kuangstudy.com/bbs?cid=4) 创建时间：2021/05/19 00:02 [字体](javascript:void(0);) [皮肤](javascript:void(0);)最后修改于： 2021/06/25 14:21

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







# 十、SpringBoot中的异步处理框架@Async


## 01、目标

掌握和了解springboot的异步处理和[@Async](https://github.com/Async)

## 02、为什么使用异步框架

在SpringBoot的日常开发中，一般都是同步调用的，但经常有特殊业务需要做异步来处理。比如：注册用户、需要送积分、发短信和邮件、或者下单成功、发送消息等等。

- 第一个原因：容错问题，如果送积分出现异常，不能因为送积分而导致用户注册失败。
- 第二个原因：提升性能，比如注册用户花了30毫秒，送积分划分50毫秒，如果同步的话一共耗时：70毫秒，用异步的话，无需等待积分，故耗时是：30毫秒就完成了业务。

## 03、代码实现

在SpringBoot中使用[@Async](https://github.com/Async)注解即可实现异步调用。如下：

### 03-01、定义异步调用任务类

```
package com.kuangstudy.service;import lombok.extern.slf4j.Slf4j;import org.springframework.scheduling.annotation.Async;import org.springframework.stereotype.Service;/** * @description: * @author: xuke * @time: 2021/6/1 21:03 */@Service@Slf4jpublic class RegSyncService {    @Async    public void sendMsg(){        // todo :模拟耗时5秒        try {            Thread.sleep(5000);            log.info("---------------发送消息--------");        }catch (Exception ex){            ex.printStackTrace();        }    }    @Async    public void addScore(){        // todo :模拟耗时5秒        try {            Thread.sleep(5000);            log.info("---------------处理积分--------");        }catch (Exception ex){            ex.printStackTrace();        }    }}
```

### 03-02、开启异步线程的支持

```
package com.kuangstudy;import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.scheduling.annotation.EnableAsync;@SpringBootApplication@EnableAsync //开启异步执行public class KuangstudyBootHelloApplication {    public static void main(String[] args) {        SpringApplication.run(KuangstudyBootHelloApplication.class, args);    }}
```

### 03-03、定义业务执行

```
package com.kuangstudy.controller.reg;import com.kuangstudy.service.RegSyncService;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RestController;/** * @description: * @author: xuke * @time: 2021/6/1 21:17 */@RestController@Slf4jpublic class RegController {    @Autowired    RegSyncService regSyncService;    @GetMapping("/sync1")    public String reguser1(){        log.info("---------注册用户---------");        // 异步发送消息        regSyncService.sendMsg();        return "ok";    }    @GetMapping("/sync2")    public String reguser2(){        log.info("---------注册用户---------");        // 异步添加积分        regSyncService.addScore();        return "ok";    }}
```

**测试结果：**
访问：http://localhost:9999/sync1
访问：http://localhost:9999/sync2
无论访问上面那个链接，都很快就响应注册结果，同时过5秒以后执行对应的发送消息和添加积分的业务。后台的日志中也很清晰的看到，是重新开启了新的线程执行对应的任务。

![img](.\images.assets\kuangstudy5e4d3b65-9172-4afb-8c9e-39b0c29d1f17.png)

## 04、给[@Async](https://github.com/Async)自定义一个线程池

[@Async](https://github.com/Async)注解默认情况下用的是`SimpleAsyncTaskExecutor`线程池。该线程池不是真正意义上的线程池，因为线程不重用，每次调用都会新建一个新的线程。通过上面的日志分析获得结论：【task-1】,【task-2】,【task-3】….递增。
[@Async](https://github.com/Async)注解异步框架提供多种线程机制：

- SimpleAsyncTaskExecutor：简单的线程池，这个类不重用线程，每次调用都会创建一个新的线程。
- SyncTaskExecutor：这个类没实现异步调用，只是一个同步操作，只适合用于不需要多线程的地方。
- ConcurrentTaskExecutor：Executor的适配类，不推荐使用.。
- ThreadPoolTaskScheduler：可以和cron表达式使用。
- ThreadPoolTaskExecutor：最常用，推荐，其本质就是：java.util.concurrent.ThreadPoolExecutor的包装

```
package com.kuangstudy.config;import org.springframework.boot.SpringBootConfiguration;import org.springframework.context.annotation.Bean;import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;import java.util.concurrent.ThreadPoolExecutor;/** * @description: * @author: xuke * @time: 2021/6/1 21:32 */@SpringBootConfigurationpublic class SyncThreadPoolConfiguration {//    @Primary    @Bean(name="threadPoolTaskExecutor")    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();        // 1: 创建核心线程数        threadPoolTaskExecutor.setCorePoolSize(10);        // 2：线程池维护线程的最大数量，只有在缓存队列满了之后才会申请超过核心线程数的线程        threadPoolTaskExecutor.setMaxPoolSize(100);        // 3：缓存队列        threadPoolTaskExecutor.setQueueCapacity(50);        // 4：线程的空闲事件，当超过了核心线程数之外的线程在达到指定的空闲时间会被销毁        threadPoolTaskExecutor.setKeepAliveSeconds(200);        // 5：异步方法内部线的名称        threadPoolTaskExecutor.setThreadNamePrefix("ksdsysn-thread-");        // 6：缓存队列的策略        /* 当线程的任务缓存队列已满并且线程池中的线程数量已经达到了最大连接数，如果还有任务来就会采取拒绝策略，         * 通常有四种策略：         *ThreadPoolExecutor.AbortPolicy：丢弃任务并抛出异常：RejectedExcutionException异常         *ThreadPoolExecutor.DiscardPolicy：丢弃任务，但是不抛出异常         *ThreadPoolExecutor.DiscardOldestPolicy: 丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）         *ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用execute()方法，直到成功。        * */        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());        threadPoolTaskExecutor.initialize();        return threadPoolTaskExecutor;    }}
```

## 05、小结和思考

在想啥的互联网中开发项目，针对高并发的请求，一般的做法是高并发接口异步线程池隔离处理：
比如：修改用户信息接口，刷新用户redis的缓存
比如：修改文章信息，同步redis缓存和索引库
等，都可以实现异步+线程池的方式很多的解决和达到高性能的效果。



# 十一、SpringBoot和在线文档Swagger的整合

## 01、目标

掌握swagger的配置以及相关参数命令

## 02、概述

> 相信无论是前端还是后端开发，都或多或少地被接口文档折磨过。前端经常抱怨后端给的接口文档与实际情况不一致。后端又觉得编写及维护接口文档会耗费不少精力，经常来不及更新。其实无论是前端调用后端，还是后端调用后端，都期望有一个好的接口文档。但是这个接口文档对于程序员来说，就跟注释一样，经常会抱怨别人写的代码没有写注释，然而自己写起代码起来，最讨厌的，也是写注释。所以仅仅只通过强制来规范大家是不够的，随着时间推移，版本迭代，接口文档往往很容易就跟不上代码了。

**Swagger是什么？它能干什么？**
发现了痛点就要去找解决方案。解决方案用的人多了，就成了标准的规范，这就是Swagger的由来。通过这套规范，你只需要按照它的规范去定义接口及接口相关的信息。再通过Swagger衍生出来的一系列项目和工具，就可以做到生成各种格式的接口文档，生成多种语言的客户端和服务端的代码，以及在线接口调试页面等等。这样，如果按照新的开发模式，在开发新版本或者迭代版本的时候，只需要更新Swagger描述文件，就可以自动生成接口文档和客户端服务端代码，做到调用端代码、服务端代码以及接口文档的一致性。

但即便如此，对于许多开发来说，编写这个yml或json格式的描述文件，本身也是有一定负担的工作，特别是在后面持续迭代开发的时候，往往会忽略更新这个描述文件，直接更改代码。久而久之，这个描述文件也和实际项目渐行渐远，基于该描述文件生成的接口文档也失去了参考意义。所以作为Java届服务端的大一统框架Spring，迅速将Swagger规范纳入自身的标准，建立了Spring-swagger项目，后面改成了现在的Springfox。通过在项目中引入Springfox，可以扫描相关的代码，生成该描述文件，进而生成与代码一致的接口文档和客户端代码。这种通过代码生成接口文档的形式，在后面需求持续迭代的项目中，显得尤为重要和高效。

## 03、具体实现

### 03-01、引入swagger对应依赖

```xml
<!-- Swagger -->
<dependency>   
    <groupId>io.springfox</groupId> 
    <artifactId>springfox-swagger-ui</artifactId>  
    <version>2.9.2</version>
</dependency>
<dependency>    
    <groupId>io.springfox</groupId>  
    <artifactId>springfox-swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<!-- 文档 -->
<dependency>   
    <groupId>io.springfox</groupId>   
    <artifactId>springfox-swagger2</artifactId>  
    <version>2.9.2</version>  
    <exclusions>       
        <exclusion>      
            <groupId>io.swagger</groupId>  
            <artifactId>swagger-models</artifactId> 
        </exclusion>     
        <exclusion>       
            <groupId>com.google.guava</groupId>   
            <artifactId>guava</artifactId>  
        </exclusion>  
    </exclusions></dependency>
<dependency>    
    <groupId>io.swagger</groupId>
    <artifactId>swagger-models</artifactId> 
    <version>1.5.21</version>
</dependency>
<dependency>    
    <groupId>io.springfox</groupId> 
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>   
    <groupId>com.github.xiaoymin</groupId>   
    <artifactId>swagger-bootstrap-ui</artifactId>  
    <version>1.8.5</version>
</dependency>
```

### 03-02、定义和开启swagger的配置类

```java
/**
 * itbooking系统平台<br/>
 * com.itbooking.config<br/>
 * SweggerConfiguration.java<br/>
 * 创建人:mofeng <br/>
 * 时间：2018年9月24日-下午5:35:07 <br/>
 * 2018itbooking-版权所有<br/>
 */
package com.kuangstudy.config;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/5/20 13:16
 */
@SpringBootConfiguration
@EnableSwagger2
public class SwaggerConfiguration {
    /**
     * 在完成上述配置之后，其实就已经可以产生帮助文档了，但是这样的文档主要针对请求本身，而描述主要来源于函数等命名产生。
     * 对用户体验不好，我们通常需要自己增加一些说明来丰富文档内容。如果：
     * 加入
     *
     * @ApiIgnore 忽略暴露的 api
     * @ApiOperation(value = "查找", notes = "根据用户 ID 查找用户")
     * 添加说明
     * <p>
     * <p>
     * 其他注解：
     * @Api ：用在类上，说明该类的作用
     * @ApiImplicitParams ：用在方法上包含一组参数说明
     * @ApiResponses ：用于表示一组响应
     * 完成上述之后，启动springboot程序，
     * 旧访问：http://localhost:8080/swagger-ui.html
     * 新访问：http://localhost:8080/doc.html
     * @ApiOperation() 用于方法；表示一个http请求的操作
     * value用于方法描述
     * notes用于提示内容
     * tags可以重新分组（视情况而用）
     * @ApiParam() 用于方法，参数，字段说明；表示对参数的添加元数据（说明或是否必填等）
     * name–参数名
     * value–参数说明
     * required–是否必填
     * @ApiModel()用于类 ；表示对类进行说明，用于参数用实体类接收
     * value–表示对象名
     * description–描述
     * 都可省略
     * @ApiModelProperty()用于方法，字段； 表示对model属性的说明或者数据操作更改
     * value–字段说明
     * name–重写属性名字
     * dataType–重写属性类型
     * required–是否必填
     * example–举例说明
     * hidden–隐藏
     * @ApiIgnore()用于类或者方法上，可以不被swagger显示在页面上 比较简单, 这里不做举例
     * @ApiImplicitParam() 用于方法
     * 表示单独的请求参数
     * @ApiImplicitParams() 用于方法，包含多个 @ApiImplicitParam
     * name–参数ming
     * value–参数说明
     * dataType–数据类型
     * paramType–参数类型
     * example–举例说明
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kuangstudy.controller"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("学相伴APP项目数据接口")
                .description("学相伴APP项目数据接口，在线体验文档")
                .termsOfServiceUrl("https://api.kuangstudy.com/api")
                .contact("徐柯，阿超，狂神")
                .version("1.0")
                .build();
    }
}
```

### 03-03、开始体验swagger

**bootstrapui版本：http://localhost:9999/doc.html**
![img](.\images.assets\kuangstudy3ba69a88-fb2c-47f1-a254-d3ab414c4602.png)

**旧版本：http://localhost:9999/swagger-ui.html**

![img](.\images.assets\kuangstudy7a793d45-85eb-459e-b256-72bc54934a61.png)

### 03-04、体验接口的执行

定义用户保存接口：

```java
package com.kuangstudy.controller.result;
import com.kuangstudy.entity.User;
import com.kuangstudy.vo.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/5/20 12:35
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @ApiOperation("保存用户信息")
    @PostMapping("/save")
    public R saveUser() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setNickname("学相伴用户_" + i);
            user.setPassword("123456" + i);
            user.setSex(i % 2);
            userList.add(user);
        }
        return R.ok().data("userList", userList);
    }
}
```

![img](.\images.assets\kuangstudydbaba911-f3f1-4188-b577-c7d3e231b6b3.png)

## 04、总结

其实归根到底，使用Swagger，就是把相关的信息存储在它定义的描述文件里面（yml或json格式），再通过维护这个描述文件可以去更新接口文档，以及生成各端代码。而Springfox-swagger,则可以通过扫描代码去生成这个描述文件，连描述文件都不需要再去维护了。所有的信息，都在代码里面了。代码即接口文档，接口文档即代码。

更多注解参考：https://www.kuangstudy.com/bbs/1399753439654756353



# 十二、SpringBoot统一接口返回的标准格式R.java

## 01、目标

完成统一返回以及统一返回的意义和项目开发的作用

## 02、具体实现

### 02-01、定义统一返回类R

```java
package com.kuangstudy.common.base;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
/**
 * @ClassName BaseController
 * @Author ：xuke
 * @Date ：2021/1/17 13:01
 * @Description：通用数据返回格式
 * @Version: 1.0
 */
@Data
@ToString
public class R implements Serializable {
    private static final long serialVersionUID = 986823857621547280L;
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;
    private R() {
    }
    public static R ok() {
        R r = new R();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }
    public static R ok(Object data) {
        R r = new R();
        r.setSuccess(ResultCodeEnum.SUCCESS.getSuccess());
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }
    public static R error() {
        R r = new R();
        r.setSuccess(ResultCodeEnum.UNKNOWN_REASON.getSuccess());
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }
    public static R setResult(ResultCodeEnum resultCodeEnum) {
        R r = new R();
        r.setSuccess(resultCodeEnum.getSuccess());
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }
    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
    public R message(String message) {
        this.setMessage(message);
        return this;
    }
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }
    public R data(Object o) {
        this.setData(o);
        return this;
    }
}
```

### 02-02、定义枚举

```java
package com.kuangstudy.ksdstate.vo;
import lombok.Getter;
@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 20000,"成功"),
    UNKNOWN_REASON(false, 20001, "未知错误"),
    BAD_SQL_GRAMMAR(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确");
    private Boolean success;
    private Integer code;
    private String message;
    private ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
```

### 02-03、具体使用

```java
@GetMapping("/user")
public R getUser() {
    return R.ok().data("1").message("xxxxx").code(133);
}
```

## 03、优化和改进

看到这里，应该很多同学都应该觉得这样的封装还不错，甚至会觉得很完美。
但是其实这个是很大的问题和弊端，因为你每写一个接口或者方法，都需要进行R返回，还是蛮累的。
如果你写这种代码在公司里推广给整个公司的人去使用，估计会被吐槽。原因开发太过于强制和依赖R类。并不是什么好事，怎么优化和处理呢？
可以使用spring提供的结果拦截增强处理机制来解决这个问题,如下：
采用springboot提供的`ResponseBodyAdvice`l处理即可。
**ResponseBodyAdvice的作用是：拦截Controller方法的返回值，统一处理返回值到响应体中，一般来做response的统一格式，加密，签名等。如下：**

- 在使用的时候在controller的包上增加basePackages=”com.kuangstudy”，因为如果不加的话，可能给整个系统的产生冲突影响比如：如果你使用了`swagger`时会出现空白异常。

```java
package com.kuangstudy.config.handler;
import com.kuangstudy.common.base.ErrorHandler;
import com.kuangstudy.common.base.R;
import com.kuangstudy.utils.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 11:16
 */
@ControllerAdvice(basePackages = "com.kuangstudy")
public class ResultResponseHandler implements ResponseBodyAdvice<Object> {
    /**
     * 是否支持advice功能，true是支持 false是不支持
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorHandler) {
            ErrorHandler errorHandler = (ErrorHandler) o;
            return R.error().code(errorHandler.getStatus()).message(errorHandler.getMessage());
        } else if (o instanceof String) { //因为如果返回是string的话默认会调用string的处理器会直接返回，所以要进行处理
            return JsonUtil.obj2String(R.ok(o));
        }
        return R.ok(o);
    }
}
```

- 因为如果返回是string的话默认会调用string的处理器直接返回所以要进行处理

这样在开发的时候，我们开发还是按照正常的开发和返回即可。不用在强迫小伙伴一定返回R类。

# 十三、SpringBoot封装全局异常处理器

## 01、目标

- 为什么Springboot需要《全局异常处理》
- 编码实现springboot的全局异常配置
- 自定义异常，并集成自定义异常处理器
- 统一返回&异常返回进行结合处理

## 02、分析

### 02-01、为什么Springboot需要《全局异常处理》

全局异常处理就是指把整个系统的异常统一自动处理，程序员可以做到不用些try/catch就能进行处理项目中出现的异常。
**为什么需要全局异常处理呢？**

- **第一个原因：不用强制写try/catch,异常交由统一异常的处理机制进行捕获。**

```java
@GetMapping("/error1")
    public String error1() {
    int i = 1 / 0;
    return "success";
}
```

在开发中，如果不用try/catch进行捕获的话。客户端就会跳转到springboot默认的异常页面。报出500的错误信息。
在开发中遇见了异常一般的程序开发者都会使用try/catch来进行捕获处理,如下：

```java
  @GetMapping("/error2")
  public String error2() {
      try {
          int i = 1 / 0;
      } catch (Exception ex) {
          log.info("出现了异常:{}", ex);
          return "no";
      }
      return "success";
  }
```

有没有更好的处理解决方案呢？答案是：有的。可以采用全局异常处理器来解决这个问题。

- **第二个原因：自定义异常：只能用全局异常来捕获。**

```java
@GetMapping("/error4")
public void error4() {
   throw  new RuntimeException("用户名和密码有误!!!");
}
```

上面的方式就没办法在try/catch进行处理。而且直接返回这些错误信息，用户是看不懂这些信息的，所以统一异常处理是非常有必要的事情！如何做呢？

- **第三个原因：JSR303的参数验证器，参数校验不通过会抛出异常，也是无法通过try/catch进行直接捕获处理的。**

### 02-02、编码实现springboot的全局异常配置

#### 步骤1：统一封装异常处理枚举类

所有的运行时异常都用枚举类来进行定义即可：

```java
package com.kuangstudy.exception;
import lombok.Getter;
/**
 * @Author xuke
 * @Description 专门处理异常
 * @Date 21:14 2021/6/25
 * @Param 
 * @return 
**/
@Getter
public enum ResultCodeEnum {
    UNKNOWN_REASON(false, 20001, "未知错误"),
    SERVER_ERROR(false, 500, "服务器忙，请稍后在试"),
    ORDER_CREATE_FAIL(false, 601, "订单下单失败");
    private Boolean success;
    private Integer code;
    private String message;
    private ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
```

#### 步骤2：封装controller的异常结果处理

```java
package com.kuangstudy.common.base;
import lombok.*;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:32
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ErrorHandler {
    // 异常的状态码，从枚举中获得
    private Integer status;
    // 异常的消息，写用户看得懂的异常，从枚举中得到
    private String message;
    // 异常的名字
    private String exception;
    /**
     * 对异常处理进行统一封装
     *
     * @param resultCodeEnum
     * @param throwable
     * @param message
     * @return
     */
    public static ErrorHandler fail(ResultCodeEnum resultCodeEnum, Throwable throwable, String message) {
        ErrorHandler errorHandler = ErrorHandler.fail(resultCodeEnum, throwable);
        errorHandler.setMessage(message);
        return errorHandler;
    }
    /**
     * 对异常枚举进行封装
     *
     * @param resultCodeEnum
     * @param throwable
     * @return
     */
    public static ErrorHandler fail(ResultCodeEnum resultCodeEnum, Throwable throwable) {
        ErrorHandler errorHandler = new ErrorHandler();
        errorHandler.setMessage(resultCodeEnum.getMessage());
        errorHandler.setStatus(resultCodeEnum.getCode());
        errorHandler.setException(throwable.getClass().getName());
        return errorHandler;
    }
}
```

#### 步骤3：定义一个全局异常处理器

```java
package com.kuangstudy.config.handler;
import com.kuangstudy.common.base.ErrorHandler;
import com.kuangstudy.common.base.ResultCodeEnum;
import com.kuangstudy.config.exception.BusinessException;
import com.kuangstudy.config.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:40
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 对服务器端出现500异常进行统一处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorHandler makeExcepton(Throwable e, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.SERVER_ERROR, e);
        log.error("请求的地址是：{},出现的异常是：{}", request.getRequestURL(), e);
        return errorHandler;
    }
}
```

- makeExcepton方法的作用是：把运行时异常封装为ErrorHandler对象进行统一捕获处理。
- [@RestControllerAdvice](https://github.com/RestControllerAdvice)和[@ControllerAdvice](https://github.com/ControllerAdvice)它是对controller的增强扩展处理，而全局异常就是一种扩展能力之一。
- [@ExceptionHandler](https://github.com/ExceptionHandler)(Throwable.class) ：统一处理某一类型异常，从而减少代码的出现异常的复杂度和重复率，
- [@ResponseStatus](https://github.com/ResponseStatus)(HttpStatus.INTERNAL_SERVER_ERROR)：指定客户端收到的http状态码，这里配置的是500，就显示成500错误。不指定也是没问题的。因为返回是根据自己的枚举进行处理了。

#### 步骤3：运行查看结果

http://localhost:9999/error1
http://localhost:9999/error2
http://localhost:9999/error3
http://localhost:9999/error4 ：

执行四个请求的异常处理都是：

```java
{  "status": 500,  "message": "服务器忙，请稍后在试",  "exception": "java.lang.RuntimeException"}
```

都被拦截成到全局异常处理了。访问`error4`并没有显示对应的自定义异常处理信息，如何进行处理呢？答案是：自定义异常统一处理

### 02-03、自定义异常，并集成自定义异常处理器

> **自定义异常的好处是：可以根据自定异常的信息快速的定位错误出现的模块以及方便记录日志文件，快速进行错误的分析和判定。**

#### 步骤1：添加自定义异常

```java
package com.kuangstudy.config.exception;
import com.kuangstudy.common.base.ErrorHandler;
import com.kuangstudy.common.base.ResultCodeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:40
 */
@Data
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;
    public BusinessException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
```

``` java
package com.kuangstudy.config.exception;
import com.kuangstudy.common.base.ErrorHandler;
import com.kuangstudy.common.base.ResultCodeEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:40
 */
@Data
public class OrderException extends RuntimeException {
    private Integer code;
    private String message;
    public OrderException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }
    public OrderException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
```



#### 步骤2：添加自定义异常处理方法

```java
package com.kuangstudy.config.handler;
import com.kuangstudy.common.base.ErrorHandler;
import com.kuangstudy.common.base.ResultCodeEnum;
import com.kuangstudy.config.exception.BusinessException;
import com.kuangstudy.config.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:40
 */
@RestControllerAdvice(basePackages = "com.kuangstudy")
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 对服务器端出现500异常进行统一处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorHandler makeExcepton(Throwable e, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.SERVER_ERROR, e);
        log.error("请求的地址是：{},出现的异常是：{}", request.getRequestURL(), e);
        return errorHandler;
    }
    /**
     * 对自定义异常进行统一处理
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorHandler handlerBusinessException(BusinessException e, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.builder()
                .status(e.getCode())
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.error("请求的地址是：{},BusinessException出现异常：{}", request.getRequestURL(), e);
        return errorHandler;
    }
    /**
     * 对自定义异常进行统一处理
     */
    @ExceptionHandler(OrderException.class)
    public ErrorHandler handlerOrderException(OrderException e, HttpServletRequest request) {
        ErrorHandler errorHandler = ErrorHandler.builder()
                .status(e.getCode())
                .message(e.getMessage())
                .exception(e.getClass().getName())
                .build();
        log.error("请求的地址是：{},OrderException出现异常：{}", request.getRequestURL(), e);
        return errorHandler;
    }
}
```

#### 步骤3：定义方法进行测试

```java
 @GetMapping("/error5")
 public void error5() {
     throw new BusinessException(ResultCodeEnum.LOGIN_CODE_FAIL_ERROR);
 }
@GetMapping("/error6")
public void error6() {
    throw new OrderException(ResultCodeEnum.LOGIN_PHONE_ERRROR);
}
```

访问：http://localhost:9999/error5

```json
{  "status": 20007,  "message": "短信验证码失效，请重新发送",  "exception": "com.kuangstudy.config.exception.BusinessException"}
```

访问：http://localhost:9999/error6

```json
{  "status": 20002,  "message": "手机号码不能为空",  "exception": "com.kuangstudy.config.exception.OrderException"}
```

### 02-04、统一返回&异常返回进行结合处理

因为显示统一异常处理和统一返回处理存在两种格式的返回，这个时候如果是接口的调用者就会出现凌乱的感觉。我们可以使用Controller的结果返回拦截处理进行结果处理在返回。

**统一异常和统一结果返回结合处理：**

```java
package com.kuangstudy.config.handler;
import com.kuangstudy.common.base.ErrorHandler;
import com.kuangstudy.common.base.R;
import com.kuangstudy.utils.JsonUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 11:16
 */
@ControllerAdvice
public class ResultResponseHandler implements ResponseBodyAdvice<Object> {
    /**
     * 是否支持advice功能，true是支持 false是不支持
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorHandler) {
            ErrorHandler errorHandler = (ErrorHandler) o;
            return R.error().code(errorHandler.getStatus()).message(errorHandler.getMessage());
        } else if (o instanceof String) {
            return JsonUtil.obj2String(R.ok(o));
        }
        return R.ok(o);
    }
}
```



# 十四、SpringBoot的参数校验器-Validator

[![img](.\images.assets\132.jpeg) 学相伴-飞哥 ](https://www.kuangstudy.com/user/aaa284f6f98146d4a927e0d42dacb01b) 分类：[学习笔记](https://www.kuangstudy.com/bbs?cid=4) 创建时间：2021/06/01 09:30 [字体](javascript:void(0);) [皮肤](javascript:void(0);)最后修改于： 2021/06/02 16:09

## 01、目标

- 为什么要做Validator参数校验器
- 编码实现对用户名、密码、邮箱、手机号码、身份证等的校验器
- 实现一个手机号码的自定义验证器
- 把验证器和全局异常处理结合

## 02、分析

### 02-01、为什么要使用validator参数校验器

因为在日常的开发中，服务端对象的校验是非常重要的一个环节，比如：注册的时候：校验用户名，密码，身份证，邮箱等信息是否为空，以及格式是否正确，但是这种在日常的开发中进行校验太繁琐了，代码繁琐而且很多。
Validator框架应运而生，它的出现就是为了解决开发人员在开发的时候减少代码的，提升开发效率。它专门用来做接口的参数校验，比如：密码长度、是否为空等等。

spring的validator校验框架遵守的是JSR-303的验证规范（参数校验规范），JSP全称：Java Specification Requests缩写。
在默认情况下：SpringBoot会引入Hibernate Validatorr机制来支持JSR-303验证规范。

SpringBoot的validator校验框架支持如下特征：

- JSR303特征：JSR303是一项标准，只提供规范不提供实现。规定一些校验规范即校验注解。比如：[@Null](https://github.com/Null)、[@NotNull](https://github.com/NotNull)、[@Pattern](https://github.com/Pattern)。这些类都位于：`javax.validation.constraints`包下。
- hibernate validation特征：hibernate validation是对JSR303规范的实现并且进行了增强和扩展。并增加了注解：[@Email](https://github.com/Email)、[@Length](https://github.com/Length)、[@Range](https://github.com/Range)等等。
- spring Validation：Spring Validation是对Hibernate Validation的二次封装。在SpringMvc模块中添加了自动校验。并将校验信息封装到特定的类中。

**常用注解列表**

``` java
JSR提供的校验注解：         
@Null   被注释的元素必须为 null    
@NotNull    被注释的元素必须不为 null    
@AssertTrue     被注释的元素必须为 true    
@AssertFalse    被注释的元素必须为 false    
@Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
@Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
@DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值    
@DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值    
@Size(max=, min=)   被注释的元素的大小必须在指定的范围内    
@Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内    
@Past   被注释的元素必须是一个过去的日期    
@Future     被注释的元素必须是一个将来的日期    
@Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式    
Hibernate Validator提供的校验注解：  
@NotBlank(message =)   验证字符串非null，且trim后长度必须大于0    
@Email  被注释的元素必须是电子邮箱地址    
@Length(min=,max=)  被注释的字符串的大小必须在指定的范围内    
@NotEmpty   被注释的字符串的必须非空    
@Range(min=,max=,message=)  被注释的元素必须在合适的范围内
```

## 03、实战开发

### 03-01、在pom.xml添加依赖

springboot天然就支持了validator数据对象校验

```xml
<dependency>    
    <groupId>org.springframework.boot</groupId>   
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### 03-02、新建用户实体并结合验证注解

```java
package com.kuangstudy.vo;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.*;
import java.util.Date;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 14:29
 */
@Data
public class UserVo {
    @NotNull(message = "用户id不能为空")
    private Long userId;
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20, message = "用户名不能超过20个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "用户昵称限制：最多20字符，包含文字、字母和数字")
    private String username;
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String mobile;
    @NotBlank(message = "联系邮箱不能为空")
    @Email(message = "邮箱格式不对")
    private String email;
    @Future(message = "时间必须是将来时间")
    private Date createTime;
}
```

### 03-03、添加UserValidationController进行校验

```java
package com.kuangstudy.controller.validator;
import com.kuangstudy.common.base.R;
import com.kuangstudy.vo.UserVo;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 14:46
 */
@RestController
@Api(description = "用户校验")
@RequestMapping("/user")
public class UserValiatorController {
    @PostMapping("/valiator/reg")
    public UserVo createUser(@RequestBody @Validated UserVo userVo) {
        return userVo;
    }
}
```

`@Validated`注解记得一定要加上，它的作用是用来校验UserVo的参数是否正确的注解。

### 03-04、进行校验测试

http://localhost:9999/doc.html
**测试数据：**

```json
{    
    "createTime": "",   
    "email": "xxxxxx@qq.com",   
    "mobile": "15074816437",    
    "userId": 0,   
    "username": "xxxx"
}
```

**验证错误的信息**

```json
{  
    "success": false, 
    "code": 500, 
    "message": "服务器忙，请稍后在试",   
    "error": "Validation failed for argument [0] in public com.kuangstudy.vo.UserVo com.kuangstudy.controller.validator.UserValiatorController.createUser(com.kuangstudy.vo.UserVo) with 2 errors: [Field error in object 'userVo' on field 'mobile': rejected value [15074816x437]; codes [Pattern.userVo.mobile,Pattern.mobile,Pattern.java.lang.String,Pattern]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userVo.mobile,mobile]; arguments []; default message [mobile],[Ljavax.validation.constraints.Pattern$Flag;@475daaf7,^[1][3,4,5,6,7,8,9][0-9]{9}$]; default message [手机号格式有误]] [Field error in object 'userVo' on field 'email': rejected value [xxxxxxqq.com]; codes [Email.userVo.email,Email.email,Email.java.lang.String,Email]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [userVo.email,email]; arguments []; default message [email],[Ljavax.validation.constraints.Pattern$Flag;@354723d6,.*]; default message [邮箱格式不对]] ",  
    "data": null}
```

**如果填写正确的信息是：**

```json
{  
    "success": true, 
    "code": 20000, 
    "message": "成功", 
    "error": null, 
    "data": {   
        "userId": 0, 
        "username": "xxxx",
        "mobile": "15074816437",
        "email": "xxxxxx@qq.com",
        "createTime": null  
    }
}
```

但是这种信息在开发中，其实还是存在很大的不友好，怎么处理这种问题？可以结合统一异常处理即可。

## 04、把validator异常加入到《全局异常处理器中》

因为上述返回的异常根本没办法理解和看明白。所以对验证出现的信息返回可以结合全局异常处理器进行统一处理。
因为验证失败，springboot内部是以异常的方式进行报错和返回，这个时候我们可以捕捉这个异常进行处理

```java
package com.kuangstudy.config.handler;
import com.kuangstudy.common.base.ErrorHandler;
import com.kuangstudy.common.base.ResultCodeEnum;
import com.kuangstudy.config.exception.BusinessException;
import com.kuangstudy.config.exception.OrderException;
import com.kuangstudy.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 10:40
 */
@RestControllerAdvice(basePackages = "com.kuangstudy.controller")
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 对验证的统一异常进行统一处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorHandler handlerValiator(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<Map<String, String>> mapList = toValidatorMsg(fieldErrors);
        ErrorHandler errorHandler = ErrorHandler.fail(ResultCodeEnum.PARAM_ERROR, e, JsonUtil.obj2String(mapList));
        errorHandler.setErrors("");
        return errorHandler;
    }
    /**
     * 对验证异常进行统一处理
     * @param fieldErrorList
     * @return
     */
    private List<Map<String, String>> toValidatorMsg(List<FieldError> fieldErrorList) {
        List<Map<String, String>> mapList = new ArrayList<>();
        for (FieldError fieldError : fieldErrorList) {
            Map<String, String> map = new HashMap<>();
            map.put("field", fieldError.getField());
            map.put("msg", fieldError.getDefaultMessage());
            mapList.add(map);
        }
        return mapList;
    }
}
```

**测试数据**

```json
{   
    "createTime": "",  
    "email": "xxxxxxqq.com", 
    "mobile": "1507481x6437",  
    "userId": 0,  
    "username": "xxxx"
}
```

**错误的测试结果**

```json
{  
    "success": false, 
    "code": 21003, 
    "message": "[{\"msg\":\"邮箱格式不对\",\"field\":\"email\"},{\"msg\":\"手机号格式有误\",\"field\":\"mobile\"}]",  
    "error": "",  
    "data": null
}
```

## 05、自定义校验器

在开发中有时候有些验证需求满足不了我们的业务，我们可以进行扩展进行处理验证、springboot的扩展validation的时候做了接口。具体实现如下:
**步骤1：首先定义验证异常注解**

```java
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package com.kuangstudy.common.ano;
import com.kuangstudy.config.handler.PhoneValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "手机格式不正确!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        Phone[] value();
    }
}
```

**步骤2：定义具体的验证处理类**

```java
package com.kuangstudy.config.handler;
import com.kuangstudy.common.ano.Phone;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 15:54
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public boolean isValid(String phonevalue, ConstraintValidatorContext constraintValidatorContext) {
        // 1: 如果用户没输入直接返回不校验，因为空的判断交给@NotNull去做就行了
        if (phonevalue == null && phonevalue.length() == 0) {
            return true;
        }
        Pattern p = Pattern.compile("^(13[0-9]|14[5|7|9]|15[0|1|2|3|5|6|7|8|9]|17[0|1|6|7|8]|18[0-9])\\d{8}$");
        // 2：如果校验通过就返回true,否则返回false;
        Matcher matcher = p.matcher(phonevalue);
        return matcher.matches();
    }
    @Override
    public void initialize(Phone constraintAnnotation) {
    }
}
```

**步骤3：在实体vo中使用**

```java
@NotBlank(message = "手机号2不能为空") 
@Phone 
private String phone;
```

**步骤4：测试结果**

```json
{  
    "success": true, 
    "code": 20000, 
    "message": "成功",
    "error": null,  
    "data": {   
        "userId": 0,  
        "username": "xxxxxxx",   
        "mobile": "13453432143",  
        "phone": "13453432143x", 
        "email": "xxxx@qq.com",  
        "createTime": null 
    }
}
```

**验证错误返回**

```json
{  
    "success": false, 
    "code": 21003, 
    "message": "[{\"msg\":\"手机格式不正确!\",\"field\":\"phone\"}]",  "error": "", 
    "data": null
}
```

**验证正确返回**

```json
{  
    "success": true, 
    "code": 20000, 
    "message": "成功", 
    "error": null,  
    "data": {   
        "userId": 0, 
        "username": "xxxxxxx", 
        "mobile": "13453432143", 
        "phone": "13453432143",   
        "email": "xxxx@qq.com",  
        "createTime": null  
    }
}
```

# 十五、SpringBoot-Assert和自定义ValidatorUtils



## 01、目标

- 如何使用Assert参数校验
- 为什么用了Validator参数校验，还必须再用Assert参数校验呢？
- 自定义属于自己的ValdatorUtils校验器

## 02、Assert参数校验

`Assert`是断言的意思，它是Spring提供的一个工具类，`org.springframework.util.Assert`
Web 应用在接受表单提交的数据后都需要对其进行合法性检查，如果表单数据不合法，请求将被驳回。类似的，当我们在编写类的方法时，也常常需要对方法入参进行合法性检查，如果入参不符合要求，方法将通过抛出异常的方式拒绝后续处理。

核心方法：

```java
import java.util.Collection;
import java.util.Map;
public abstract class Assert
{
  public static void isTrue(boolean expression, String message)
  {
    if (!(expression))
      throw new IllegalArgumentException(message);
  }
  public static void isTrue(boolean expression)
  {
    isTrue(expression, "[Assertion failed] - this expression must be true");
  }
  public static void isNull(Object object, String message)
  {
    if (object != null)
      throw new IllegalArgumentException(message);
  }
  public static void isNull(Object object)
  {
    isNull(object, "[Assertion failed] - the object argument must be null");
  }
  public static void notNull(Object object, String message)
  {
    if (object == null)
      throw new IllegalArgumentException(message);
  }
  public static void notNull(Object object)
  {
    notNull(object, "[Assertion failed] - this argument is required; it must not be null");
  }
  public static void hasLength(String text, String message)
  {
    if (!(StringUtils.hasLength(text)))
      throw new IllegalArgumentException(message);
  }
  public static void hasLength(String text)
  {
    hasLength(text, 
      "[Assertion failed] - this String argument must have length; it must not be null or empty");
  }
  public static void hasText(String text, String message)
  {
    if (!(StringUtils.hasText(text)))
      throw new IllegalArgumentException(message);
  }
  public static void hasText(String text)
  {
    hasText(text, 
      "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
  }
  public static void doesNotContain(String textToSearch, String substring, String message)
  {
    if ((StringUtils.hasLength(textToSearch)) && (StringUtils.hasLength(substring)) && 
      (textToSearch.indexOf(substring) != -1))
      throw new IllegalArgumentException(message);
  }
  public static void doesNotContain(String textToSearch, String substring)
  {
    doesNotContain(textToSearch, substring, 
      "[Assertion failed] - this String argument must not contain the substring [" + substring + "]");
  }
  public static void notEmpty(Object[] array, String message)
  {
    if (ObjectUtils.isEmpty(array))
      throw new IllegalArgumentException(message);
  }
  public static void notEmpty(Object[] array)
  {
    notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
  }
  public static void noNullElements(Object[] array, String message)
  {
    if (array != null)
      for (int i = 0; i < array.length; ++i)
        if (array[i] == null)
          throw new IllegalArgumentException(message);
  }
  public static void noNullElements(Object[] array)
  {
    noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
  }
  public static void notEmpty(Collection collection, String message)
  {
    if (CollectionUtils.isEmpty(collection))
      throw new IllegalArgumentException(message);
  }
  public static void notEmpty(Collection collection)
  {
    notEmpty(collection, 
      "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
  }
  public static void notEmpty(Map map, String message)
  {
    if (CollectionUtils.isEmpty(map))
      throw new IllegalArgumentException(message);
  }
  public static void notEmpty(Map map)
  {
    notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
  }
  public static void isInstanceOf(Class clazz, Object obj)
  {
    isInstanceOf(clazz, obj, "");
  }
  public static void isInstanceOf(Class type, Object obj, String message)
  {
    notNull(type, "Type to check against must not be null");
    if (!(type.isInstance(obj)))
      throw new IllegalArgumentException(message + 
        "Object of class [" + "null" + 
        "] must be an instance of " + type);
  }
  public static void isAssignable(Class superType, Class subType)
  {
    isAssignable(superType, subType, "");
  }
  public static void isAssignable(Class superType, Class subType, String message)
  {
    notNull(superType, "Type to check against must not be null");
    if ((subType == null) || (!(superType.isAssignableFrom(subType))))
      throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
  }
  public static void state(boolean expression, String message)
  {
    if (!(expression))
      throw new IllegalStateException(message);
  }
  public static void state(boolean expression)
  {
    state(expression, "[Assertion failed] - this state invariant must be true");
  }
}
```

## 03、为什么有Validator参数校验还需要Assert参数校验呢？

**原因1：因为validator只解决了参数自身的数据校验，解决不了参数和业务数据直接的校验，比如判断一个对象是否为null**
**原因2：比如判断两个值是否相等不相等，如：密码和确认密码等等**
比如：

**新增逻辑判断方法**

```java
@PostMapping("/valiator/reg2")
public UserVo createUser() {
    //UserVo userVo = userService.getById(id);
    UserVo userVo = null;
    if (userVo == null) {
        throw new IllegalArgumentException("用户不存在!!!");
    }
    return userVo;
}
@PostMapping("/valiator/reg3")
public UserVo createUser3() {
    UserVo userVo = null;
    Assert.isNull(userVo,"Assert用户不存在!!!");
    return userVo;
}
```

**增加统一异常拦截处理**

```java
 /**
 * assert异常校验处理
 */
 @ExceptionHandler(IllegalArgumentException.class)
 public ErrorHandler handlerIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
     ErrorHandler errorHandler = ErrorHandler.builder()
     .status(4000)
     .message(e.getMessage())
     .exception(e.getClass().getName())
     .build();
     log.error("请求的地址是：{},IllegalArgumentException出现异常：{}", request.getRequestURL(), e);
     return errorHandler;
 }
```

**测试结果**

```
{  "success": false,  "code": 4000,  "message": "用户不存在!!!",  "error": null,  "data": null}
```

## 03、自定义验证器

借鉴Assert的思想，封装属于自己的验证器

```java
package com.kuangstudy.utils;
import org.springframework.util.Assert;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @description: 校验工具类
 * @author: xuke
 * @time: 2020/9/26 1:19
 */
public class ValidatorUtil extends Assert {
    /**
     * @return boolean
     * @Author xuke
     * @Description 验证手机号码
     * @Date 0:33 2020/9/26
     * @Param [phone]
     **/
    public static void isValidatorPhone(String phone) {
        String regex = "(^0?1[3|4|5|7|6|8|9][0-9]\\d{8}$)";
        if (phone.length() != 11) {
            throw new IllegalArgumentException("手机号码长度必须是11位");
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        if (!isMatch) {
            throw new IllegalArgumentException("请正确输入手机号码!");
        }
    }
    /**
     * 利用正则表达式判断字符串是否是数字
     *
     * @param str
     * @return
     */
    public static void isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            throw new IllegalArgumentException("请输入数字");
        }
    }
}
```

**参考工具类**

```java
package com.kuangstudy.utils;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
/**
 * 常用的一些验证，如手机、移动号码、联通号码、电信号码、密码、座机、 邮政编码、邮箱、年龄、身份证、URL、QQ、汉字、字母、数字等
 */
public class ValidateUtil {
    /**
     * 手机号规则
     */
    public static final String MOBILE_PATTERN = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))(\\d{8})$";
    /**
     * 中国电信号码格式验证 手机段： 133,153,180,181,189,177,1700,173
     **/
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|53|7[37]|8[019])\\d{8}$)|(?:^(?:\\+86)?1700\\d{7}$)";
    /**
     * 中国联通号码格式验证 手机段：130,131,132,155,156,185,186,145,176,1707,1708,1709,175
     **/
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[5]|5[56]|7[56]|8[56])\\d{8}$)|(?:^(?:\\+86)?170[7-9]\\d{7}$)";
    /**
     * 中国移动号码格式验证 手机段：134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     **/
    private static final String CHINA_MOVE_PATTERN = "(?:^(?:\\+86)?1(?:3[4-9]|4[7]|5[0-27-9]|7[8]|8[2-478])\\d{8}$)|(?:^(?:\\+86)?1705\\d{7}$)";
    /**
     * 密码规则（6-16位字母、数字）
     */
    public static final String PASSWORD_PATTERN = "^[0-9A-Za-z]{6,16}$";
    /**
     * 固号（座机）规则
     */
    public static final String LANDLINE_PATTERN = "^(?:\\(\\d{3,4}\\)|\\d{3,4}-)?\\d{7,8}(?:-\\d{1,4})?$";
    /**
     * 邮政编码规则
     */
    public static final String POSTCODE_PATTERN = "[1-9]\\d{5}";
    /**
     * 邮箱规则
     */
    public static final String EMAIL_PATTERN = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    /**
     * 年龄规则 1-120之间
     */
    public static final String AGE_PATTERN = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    /**
     * 身份证规则
     */
    public static final String IDCARD_PATTERN = "^\\d{15}|\\d{18}$";
    /**
     * URL规则，http、www、ftp
     */
    public static final String URL_PATTERN = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
    /**
     * QQ规则
     */
    public static final String QQ_PATTERN = "^[1-9][0-9]{4,13}$";
    /**
     * 全汉字规则
     */
    public static final String CHINESE_PATTERN = "^[\u4E00-\u9FA5]+$";
    /**
     * 全字母规则
     */
    public static final String STR_ENG_PATTERN = "^[A-Za-z]+$";
    /**
     * 整数规则
     */
    public static final String INTEGER_PATTERN = "^-?[0-9]+$";
    /**
     * 正整数规则
     */
    public static final String POSITIVE_INTEGER_PATTERN = "^\\+?[1-9][0-9]*$";
    /**
     * @param mobile 手机号码
     * @return boolean
     * @Description: 验证手机号码格式
     */
    public static boolean validateMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(MOBILE_PATTERN);
    }
    /**
     * 验证是否是电信手机号,133、153、180、189、177
     *
     * @param mobile 手机号
     * @return boolean
     */
    public static boolean validateTelecom(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(CHINA_TELECOM_PATTERN);
    }
    /**
     * 验证是否是联通手机号 130,131,132,155,156,185,186,145,176,1707,1708,1709,175
     *
     * @param mobile 电话号码
     * @return boolean
     */
    public static boolean validateUnionMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(CHINA_UNICOM_PATTERN);
    }
    /**
     * 验证是否是移动手机号
     *
     * @param mobile 手机号 134,135,136,137,138,139,150,151,152,157,158,159,182,183,184,187,188,147,178,1705
     * @return boolean
     */
    public static boolean validateMoveMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return Boolean.FALSE;
        }
        return mobile.matches(CHINA_MOVE_PATTERN);
    }
    /**
     * @param pwd 密码
     * @return boolean
     * @Description: 验证密码格式  6-16 位字母、数字
     */
    public static boolean validatePwd(String pwd) {
        if (StringUtils.isEmpty(pwd)) {
            return Boolean.FALSE;
        }
        return Pattern.matches(PASSWORD_PATTERN, pwd);
    }
    /**
     * 验证座机号码，格式如：58654567,023-58654567
     *
     * @param landline 固话、座机
     * @return boolean
     */
    public static boolean validateLandLine(final String landline) {
        if (StringUtils.isEmpty(landline)) {
            return Boolean.FALSE;
        }
        return landline.matches(LANDLINE_PATTERN);
    }
    /**
     * 验证邮政编码
     *
     * @param postCode 邮政编码
     * @return boolean
     */
    public static boolean validatePostCode(final String postCode) {
        if (StringUtils.isEmpty(postCode)) {
            return Boolean.FALSE;
        }
        return postCode.matches(POSTCODE_PATTERN);
    }
    /**
     * 验证邮箱（电子邮件）
     *
     * @param email 邮箱（电子邮件）
     * @return boolean
     */
    public static boolean validateEamil(final String email) {
        if (StringUtils.isEmpty(email)) {
            return Boolean.FALSE;
        }
        return email.matches(EMAIL_PATTERN);
    }
    /**
     * 判断年龄，1-120之间
     *
     * @param age 年龄
     * @return boolean
     */
    public static boolean validateAge(final String age) {
        if (StringUtils.isEmpty(age)) {
            return Boolean.FALSE;
        }
        return age.matches(AGE_PATTERN);
    }
    /**
     * 身份证验证
     *
     * @param idCard 身份证
     * @return boolean
     */
    public static boolean validateIDCard(final String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return Boolean.FALSE;
        }
        return idCard.matches(IDCARD_PATTERN);
    }
    /**
     * URL地址验证
     *
     * @param url URL地址
     * @return boolean
     */
    public static boolean validateUrl(final String url) {
        if (StringUtils.isEmpty(url)) {
            return Boolean.FALSE;
        }
        return url.matches(URL_PATTERN);
    }
    /**
     * 验证QQ号
     *
     * @param qq QQ号
     * @return boolean
     */
    public static boolean validateQq(final String qq) {
        if (StringUtils.isEmpty(qq)) {
            return Boolean.FALSE;
        }
        return qq.matches(QQ_PATTERN);
    }
    /**
     * 验证字符串是否全是汉字
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validateChinese(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(CHINESE_PATTERN);
    }
    /**
     * 判断字符串是否全字母
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validateStrEnglish(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(STR_ENG_PATTERN);
    }
    /**
     * 判断是否是整数，包括负数
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validateInteger(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(INTEGER_PATTERN);
    }
    /**
     * 判断是否是大于0的正整数
     *
     * @param str 字符串
     * @return boolean
     */
    public static boolean validatePositiveInt(final String str) {
        if (StringUtils.isEmpty(str)) {
            return Boolean.FALSE;
        }
        return str.matches(POSITIVE_INTEGER_PATTERN);
    }
}
```

# 十六、SpringBoot快速整合Mybatis&MybatisPlus

[![img](.\images.assets\132.jpeg) 学相伴-飞哥 ](https://www.kuangstudy.com/user/aaa284f6f98146d4a927e0d42dacb01b) 分类：[学习笔记](https://www.kuangstudy.com/bbs?cid=4) 创建时间：2021/06/01 09:30 [字体](javascript:void(0);) [皮肤](javascript:void(0);)最后修改于： 2021/06/02 22:45

## 01、目标

整合mybatis和mybatis-plus实现数据库的增删改查

## 02、学习参考

https://www.kuangstudy.com/course/detail/1321004290617901058

## 03、具体实现

### 03-01、引入mybatis-plus依赖

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.17</version>
</dependency>
<!--mybatis-plus-->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.0</version>
</dependency>
```

### 03-02、在application.yml进行配置

**application.yml**

```yaml
spring:
  profiles:
    active: prod
```

**application-prod.yml**

```yaml
server:
  compression:
    enabled: true
    mime-types: text/html,text/xml,text/plain,text/css,text/javascript,application/javascript,application/json,application/xml
    min-response-size: 1024
  tomcat:
    accept-count: 1000
    threads:
      max: 800
      min-spare: 100
    max-http-form-post-size: 0
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/kssindexdb?serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=utf-8&useSSL=false
    username: root
    password: mkxiaoer1986
    hikari:
      connection-timeout: 60000
      validation-timeout: 3000
      idle-timeout: 60000
      login-timeout: 5
      max-lifetime: 60000
      maximum-pool-size: 30
      minimum-idle: 10
      read-only: false
  application:
    name: edu-front-web
  thymeleaf:
    cache: false
    prefix: classpath:/templates/
    mode: HTML
    encoding: UTF-8
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
    locale: zh_CN
    generator:
      write-numbers-as-strings: true
      write-bigdecimal-as-plain: true
  servlet:
    content-type: text/html
    multipart:
      max-file-size: 2MB
      max-request-size: 2MB
  mvc:
    servlet:
      load-on-startup: 1
  session:
    store-type: redis
    timeout: 1800
  main:
    allow-bean-definition-overriding: true
mybatis-plus:
  mapper-locations: classpath*:/mapper/*.xml
  type-aliases-package: com.kuangstudy.entity
```

### 03-03、增mybatis-plus扫描和分页处理

```java
package com.kuangstudy.config;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@MapperScan("com.kuangstudy.mapper")
public class MyBatisPlusConfig {
    //逻辑删除插件
//    @Bean
//    public ISqlInjector sqlInjector() {
//        return new LogicSqlInjector();
//    }
    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
```

### 03-04、Mybatis-plus对日期时间的处理

```java
package com.kuangstudy.config;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;
@Slf4j
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
```

### 03-05、定义Entity

```java
package com.kuangstudy.entity;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.Serializable;
import java.util.Date;
/**
 * <p>
 *
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-09-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("kss_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户唯一id")
    @TableId(value = "userid", type = IdType.ID_WORKER)
    private Long userid;
    @ApiModelProperty(value = "用户的唯一账号")
    private String usernum;
    @ApiModelProperty(value = "用户昵称")
    private String nickname;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    @ApiModelProperty(value = "用户手机号")
    private String mobile;
    @ApiModelProperty(value = "微信登录openid")
    private String openid;
    @ApiModelProperty(value = "0未禁用 1禁用")
    private Integer disabled;
    @ApiModelProperty(value = "VIP到期时间")
    private Date vipEndTime;
    @ApiModelProperty(value = "用户头像")
    private String avatar;
    @ApiModelProperty(value = "用户级别")
    private Integer level;
    @ApiModelProperty(value = "用户签名")
    private String sign;
    @ApiModelProperty(value = "性别：0女 1男")
    private Integer sex;
    @ApiModelProperty(value = "生日")
    private Date birthday;
    @ApiModelProperty(value = "地址")
    private String address;
}
```

### 03-06、定义UserMapper接口

```java
package com.kuangstudy.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kuangstudy.entity.User;
/*
 * @Author 徐柯
 * @Description 
 * @Date 19:21 2021/6/2
 * @Param
 * @return 
 **/
public interface UserMapper extends BaseMapper<User> {
}
```

### 03-07、定义UserService和UserServiceImpl

```java
package com.kuangstudy.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kuangstudy.entity.User;
/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-09-05
 */
public interface UserService extends IService<User> {
}
```

``` java
package com.kuangstudy.service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kuangstudy.entity.User;
import com.kuangstudy.mapper.UserMapper;
import org.springframework.stereotype.Service;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 19:25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
```



### 03-07、定义MybatisUserController

```java
package com.kuangstudy.controller.user;
import com.kuangstudy.entity.User;
import com.kuangstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/2 19:31
 */
@RestController
public class MybatisUserController {
    @Autowired
    private UserService userService;
    @GetMapping("/getuser")
    public User getUser(Integer id) {
        return userService.getById(id);
    }
}
```

### 03-08、访问接口

http://localhost:9999/getuser?id=1

```json
{
  "success": true,
  "code": "20000",
  "message": "成功",
  "error": null,
  "data": {
    "userid": "1",
    "usernum": "999981",
    "nickname": "琪琪怪怪",
    "password": "155bd5d2deaedd02eb7ecf1284e07f8c",
    "email": null,
    "mobile": "18681986901",
    "openid": "fff8fea78c7f4765824feb828ed06a5a",
    "disabled": "0",
    "vipEndTime": null,
    "avatar": "/assert/images/avatar/4.jpg",
    "level": "0",
    "sign": "这个人很懒,什么都没有写",
    "sex": "1",
    "birthday": null,
    "address": "中国"
  }
}
```



# 十七、SpringBootApplication启动原理



## 01、目标

- 学习springboot的核心注解[@SpringBootApplication](https://github.com/SpringBootApplication)
- 掌握[@SpringBootApplication](https://github.com/SpringBootApplication)的原理

## 02、剖析[@SpringBootApplication](https://github.com/SpringBootApplication)源码

分析[@SpringBootApplication](https://github.com/SpringBootApplication)源码如下：

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication {
    @AliasFor(annotation = EnableAutoConfiguration.class)
    Class<?>[] exclude() default {};
    @AliasFor(annotation = EnableAutoConfiguration.class)
    String[] excludeName() default {};
    @AliasFor(annotation = ComponentScan.class, attribute = "basePackages")
    String[] scanBasePackages() default {};
    @AliasFor(annotation = ComponentScan.class, attribute = "basePackageClasses")
    Class<?>[] scanBasePackageClasses() default {};
    @AliasFor(annotation = ComponentScan.class, attribute = "nameGenerator")
    Class<? extends BeanNameGenerator> nameGenerator() default BeanNameGenerator.class;
    @AliasFor(annotation = Configuration.class)
    boolean proxyBeanMethods() default true;
}
```

**注解的核心部分说明：**

- [@Target](https://github.com/Target)(ElementType.TYPE) 注解的目标位置：接口、类、枚举
- [@Retention](https://github.com/Retention)(RetentionPolicy.RUNTIME)：注解会在class字节码文件中存在，在运行时可以通过反射获得
- [@Documented](https://github.com/Documented) 用于生成javadoc文档。
- [@Inherited](https://github.com/Inherited)：在类继续关系中，如果子类要继承父类的注解，那么要该注解必须被[@Inherited](https://github.com/Inherited)修改注解

以上是常规的注解，剩下的三个是springboot的核心注解了！
**[@SpringBootApplication](https://github.com/SpringBootApplication)注解是一个复合注解，包括如下三个注解：**

- [@SpringBootConfiguration](https://github.com/SpringBootConfiguration)
- [@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)
- [@ComponentScan](https://github.com/ComponentScan)

以上三个注解在内部只做一件事情`把bean注册到springioc容器`。

## 03、[@SpringBootConfiguration](https://github.com/SpringBootConfiguration)分析

**[@SpringBootConfiguration](https://github.com/SpringBootConfiguration)**： 负责把[@Configuration](https://github.com/Configuration)+[@Bean](https://github.com/Bean)的方式的bean注入到ioc容器中。
[@ComponentScan](https://github.com/ComponentScan)：负责将指定包下含有特定注解的类，（比如：[@Service](https://github.com/Service)、[@Component](https://github.com/Component)、[@Repository](https://github.com/Repository)、[@Controller](https://github.com/Controller)等），通过扫描放入到ioc容器中。
[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)：通过`spring.factories`的配置，来实现bean的注册到springioc容器中。

![img](.\images.assets\kuangstudy9a7663dc-f828-473e-b336-79a198e25fd7.png)

## 04、课后练习

1、尝试把[@SpringBootApplication](https://github.com/SpringBootApplication)注解删除
2、然后用上面的三个注解进行替代，在启动和运行看效果。



# 十八、剖析@SpringBootConfiguration源码



## 01、目标

- 剖析[@SpringBootConfiguration](https://github.com/SpringBootConfiguration)源码，掌握[@SpringBootConfiguration](https://github.com/SpringBootConfiguration)的原理
- 分析[@Configuration](https://github.com/Configuration)的原理，并实践
- 分析[@Bean](https://github.com/Bean)的原理，并实践

## 02、剖析[@SpringBootConfiguration](https://github.com/SpringBootConfiguration)源码

### 02-01、分析:[@SpringBootConfiguration](https://github.com/SpringBootConfiguration)

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
public @interface SpringBootConfiguration {
    @AliasFor(annotation = Configuration.class)
    boolean proxyBeanMethods() default true;
}
```

[@SpringBootConfiguration](https://github.com/SpringBootConfiguration)其实就是一个[@Configuration](https://github.com/Configuration)，说明当前是一个`配置类`。

### 02-02、分析[@Configuration](https://github.com/Configuration)

从Spring3.0以后，[@Configuration](https://github.com/Configuration)用于定义配置类，可替换xml配置文件。被注解的类内部包含一个或者多个`@bean`注解的方法。这个方法讲会被 `AnnotationConfigApplicationContext` 或 `AnnotationConfigWebApplicationContext` 类进行扫描。并用户构建bean的定义，初始化spring容器。

- `@Configuration`标注在类上，`@Bean`标注在方式上，等价于spring的xml文件配置的`<bean></bean>`节点。
- 而[@Configuration](https://github.com/Configuration)就相当于一个`applicationContext.xml`文件。

**原理分析1：定义一个配置类**

```java
package com.kuangstudy.source;
import org.springframework.context.annotation.Configuration;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 0:30
 */
@Configuration // 当前是一个配置类
public class KsdMyConfiguration {
}
```

这里就相当于定义了一个`applicationContext.xml`配置文件<beans></beans>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc-4.3.xsd
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd">
</beans>
```

**步骤2：定义一个main函数去加载和启动配置类**

```java
package com.kuangstudy.source;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 0:30
 */
public class KsdApplicationTest {
    public static void main(String[] args){
        // 1： 加载一个配置类
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(KsdMyConfiguration.class);
    }
}
```

## 03、如何把一个对象加载到spring的ioc容器中

在配置类用[@bean](https://github.com/bean)来标记初始化。
[@bean](https://github.com/bean)的作用是：带有[@bean](https://github.com/bean)的注解方法将返回一个对象，该对象被注册到spring的ioc容器中。

**步骤1：创建一个bean**

```java
package com.kuangstudy.source;
import lombok.*;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 0:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserBean {
    private String nickname;
    private String password;
}
```

**步骤2：在配置类中注册bean**

```java
package com.kuangstudy.source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 0:30
 */
@Configuration // 当前是一个配置类
public class KsdMyConfiguration {
    /**
     * @Author xuke
     * @Description 初始化bean
     * @Date 0:40 2021/6/3
     * @Param []
     * @return com.kuangstudy.source.UserBean
    **/
    @Bean
    public UserBean getUserBean() {
        UserBean userBean = new UserBean();
        userBean.setNickname("feige");
        userBean.setPassword("1345323");
        return userBean;
    }
}
```

类似于以前的：

```xml
<bean id="userbean" class="com.kuangstudy.source.UserBean">
    <property name="nickname" value="feige"></property>
    <property name="password" value="12154545"></property>
</bean>
```

**步骤3：运行测试类**

```java
package com.kuangstudy.source;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 0:30
 */
public class KsdApplicationTest {
    public static void main(String[] args){
        // 1： 加载一个配置类
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(KsdMyConfiguration.class);
        // 2：获取通过配置类加载到ioc,然后通过getBean方法从ioc容器中获取bean对象出来
        UserBean bean = applicationContext.getBean(UserBean.class);
        System.out.println(bean);
    }
}
```

打印结果如下：

```
UserBean(nickname=feige, password=1345323)
```

## 04、小结

说明：使用传统的xml方式还是基于注解的方式，其结论都是一样：都会通过不同的方式机制把对象加载到ico容器中，让ioc容器去管理对象。

## 05、作业和思考

```java
package com.kuangstudy.source;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 0:46
 */
public class UserBeanService {
    /**
     * @Author xuke
     * @Description 根据id查询用户信息
     * @Date 0:47 2021/6/3
     * @Param [id]
     * @return com.kuangstudy.source.UserBean
    **/
    public UserBean getUserBeanById(Long id) {
        UserBean userBean = new UserBean();
        userBean.setNickname("feige");
        userBean.setPassword("1345323");
        return userBean;
    }
}
```

把上面的`UserBeanService` 通过配置类和[@bean](https://github.com/bean)的方式加载到ioc容器中，并且调用getUserBeanById方法进行返回查看结果，然后得出结论？



# 十九、剖析@ComponentScan源码

## 01、目标

- 理解springboot的[@ComponentScan](https://github.com/ComponentScan)注解的作用
- 学会使用[@ComponentScan](https://github.com/ComponentScan)注解

## 02、剖析springboot的[@ComponentScan](https://github.com/ComponentScan)注解

``` java
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
```

- excludeFilters：过滤不需要扫描的类型
- [@Filter](https://github.com/Filter):过滤不需要扫描的注解，FilterType.CUSTOM过滤类型为自定义规则，即指定特定的class进行过滤。
- classes：过滤指定的class，即剔除了TypeExcludeFilter和AutoConfigurationExcludeFilter

**从上面的源码，我们可以得出如下结论：**
1、`@SpringBootApplication`的源码包含[@ComponentScan](https://github.com/ComponentScan),只要`@SpringBootApplication`注解所在的包及其子孙包，都将它们下面的class扫描并装入到springioc容器中

2、如果自定义的springbean。不在`@SpringBootApplication`注解所在的包及其子孙包中，都必须手动加上[@ComponentScan](https://github.com/ComponentScan)注解并指定对应扫描指定bean所在的包。

## 03、为什么要使用[@ComponentScan](https://github.com/ComponentScan),它解决了什么问题？

在开发过程中，我们一般定义一个class或者说bean，都是在类上加注解`@Service,@Controller或者@Conponent`就可以了，但是spring的ioc怎么知道在哪里去加载这些类？就必须告诉spring去哪里找到这些加了这些加了注解的`bean`的。而[@ComponentScan](https://github.com/ComponentScan)：就是告诉spring容器去哪里找到这些bean的一种机制

- **[@ComponentScan](https://github.com/ComponentScan)的作用：告诉spring容器去扫描[@ComponentScan](https://github.com/ComponentScan)指定包下所有的注解类，然后将扫描的类装入到ioc容器中。**

> 默认情况下：[@ComponentScan](https://github.com/ComponentScan)加载的包是[@SpringBootApplication](https://github.com/SpringBootApplication)的所在的包及其子孙包，将会将其下面所有符合条件的bean加入到springioc容器中。

## 04、实践和体验[@ComponetScan](https://github.com/ComponetScan)机制

**步骤1：新建一个service**
注意：在启动类包以外地方创建，如下：

``` java
package com.test;
import org.springframework.stereotype.Service;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 1:11
 */
@Service
public class OrderService {
    public void listorder() {
        System.out.println("查询订单!!!!");
    }
}
```

**步骤2：在启动类中尝试获取OrderService**

``` java
package com.kuangstudy;
import com.test.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableAsync //开启异步执行
@EnableTransactionManagement
@MapperScan("com.kuangstudy.mapper")
public class KuangstudyBootHelloApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(KuangstudyBootHelloApplication.class, args);
        OrderService orderService = run.getBean(OrderService.class);
        orderService.listorder();
    }
}
```

出现异常

``` java
Exception in thread "main" org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.test.OrderService' available
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:351)
    at org.springframework.beans.factory.support.DefaultListableBeanFactory.getBean(DefaultListableBeanFactory.java:342)
    at org.springframework.context.support.AbstractApplicationContext.getBean(AbstractApplicationContext.java:1172)
    at com.kuangstudy.KuangstudyBootHelloApplication.main(KuangstudyBootHelloApplication.java:18)
```

异常的信息是：没有找到OrderService这个bean。

**步骤3：可以使用[@ComponentScan](https://github.com/ComponentScan)来解决**

```java
package com.kuangstudy;
import com.test.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableAsync //开启异步执行
@EnableTransactionManagement
@MapperScan("com.kuangstudy.mapper")
@ComponentScan(basePackages = {"com.test"})
public class KuangstudyBootHelloApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(KuangstudyBootHelloApplication.class, args);
        OrderService orderService = run.getBean(OrderService.class);
        orderService.listorder();
    }
}
```

在启动类上：增加[@ComponentScan](https://github.com/ComponentScan)(basePackages = {“com.test”})即可，解决异常并且加入到ioc容器中去了。
上面会出现异常和问题：`@ComponentScan(basePackages = {"com.test"})` 会覆盖默认的`@SpringBootApplication`的机制，也就是默认扫描主类包及其子孙包下面的类的机制。

**步骤4：解决覆盖问题**

```java
package com.kuangstudy;
import com.test.OrderService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@SpringBootApplication
@EnableAsync //开启异步执行
@EnableTransactionManagement
@MapperScan("com.kuangstudy.mapper")
@ComponentScan(basePackages = {"com.test","com.kuangstudy"})
public class KuangstudyBootHelloApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(KuangstudyBootHelloApplication.class, args);
        OrderService orderService = run.getBean(OrderService.class);
        orderService.listorder();
    }
}
```

## 05、课后作业

在课程的基础上新建一个controller如下：

```java
package com.test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @description:
 * @author: xuke
 * @time: 2021/6/3 1:22
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}
```

然后使用`@ComponentScan`指定包，进行扫描装配，然后在浏览器访问:`http://localhost:9999/test` 得到结果：`ok`说明没有问题。



# 二十、剖析@EnableAutoConfiguration注解



## 01、目标

- 剖析[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)注解
- 学习[@Import](https://github.com/Import)又什么作用，编写实现一个[@Import](https://github.com/Import)列子
- 默认[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)的原理，自己设计一个[@Enable](https://github.com/Enable)*的开关注解
- 自定义starter

## 02、剖析[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)注解

[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)源码如下：

``` java
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
    String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
    Class<?>[] exclude() default {};
    String[] excludeName() default {};
}
```

上面中最关键的是：`@Import(AutoConfigurationImportSelector.class)` 先来分析:[@Import](https://github.com/Import)

## 03：[@Import](https://github.com/Import)有什么作用

> **[@Import](https://github.com/Import)作用：就是将其指定的类实例注入到springioc容器中。它是一种搭载机制**

我们用代码的方式来解密[@Import](https://github.com/Import)机制：
**步骤1：创建一个bean**

```java
package com.kuangstudy.controller.econfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 10:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCourseBean {
    private String nickname="yykk";
    private String password="12356";
}
```

**步骤2：采用[@Import](https://github.com/Import)注入到springioc中**

```java
package com.kuangstudy.controller.econfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Service;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 10:26
 */
@Service
@Import(UserCourseBean.class)
public class UserCourseService {
}
```

**步骤3：定义测试类**

```java
package com.kuangstudy.controller.econfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 10:26
 */
public class TestConfiguration {
    public static void main(String[] args) {
        // 1:初始化bean
        ApplicationContext context = new AnnotationConfigApplicationContext(UserCourseService.class);
        // 2: 获取userbean
        UserCourseService userCourseService = context.getBean(UserCourseService.class);
        // 3：测试userCoursebean是否已经装配到容器中
        UserCourseBean userCourseBean = context.getBean(UserCourseBean.class);
        System.out.println(userCourseService);
        System.out.println(userCourseBean);
    }
}
```

打印结果：

```
com.kuangstudy.controller.econfig.UserCourseService@346d61beUserCourseBean(nickname=yykk, password=12356)
```

说明：usercoursebean也被装配到了ioc容器中.（**你可以通过注释 `@Import(UserCourseBean.class)` 来测试得出结论。**）

## 04、面试题分析：Spring的ImportSelector接口有什么用处?

**源码分析**

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
    String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";
    Class<?>[] exclude() default {};
    String[] excludeName() default {};
}
```

**AutoConfigurationImportSelector**
AutoConfigurationImportSelector是一个核心类，专门负责和装配EnableAutoConfiguration的配置类。源码如下：

```java
public class AutoConfigurationImportSelector implements DeferredImportSelector, BeanClassLoaderAware, ResourceLoaderAware, BeanFactoryAware, EnvironmentAware, Ordered {
}
```

从源码中可以找到6个核心接口：最核心的是`DeferredImportSelector`接口。

- **DeferredImportSelector**：实现`String[] selectImports(AnnotationMetadata var1);`方法，其作用是：去收集需要的class注册到springioc容器中。
- **BeanClassLoaderAware：**允许一个获取它的classLoader(即当前bean factory加载bean类使用的class loader)的回调类，实现了void setBeanClassLoader(ClassLoader classLoader);
- **ResourceLoaderAware：**ResourceLoaderAware回调接口负责将ResourceLoader对象注入到当前的受管Bean实例中，其定义如下。当受管Bean获得ResourceLoader对象后，它便能够通过它获得各种资源。
- **BeanFactoryAware**：通过实现`void setBeanFactory(BeanFactory var1) throws BeansException;` 获取BeanFactory对象。可以获取springioc容器的beanfactory对象操作和获取bean。
- **EnvironmentAware**：通过实现`void setEnvironment(Environment var1);` 获取上下文环境对象。这个对象可以拿到底层系统的参数配置信息
- **Ordered**: `int getOrder();` 决定springioc加载实现类的顺序。越小越先加载

**分析：DeferredImportSelector**

```java
package org.springframework.context.annotation;
import java.util.function.Predicate;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.Nullable;
public interface ImportSelector {
    String[] selectImports(AnnotationMetadata var1);
    @Nullable
    default Predicate<String> getExclusionFilter() {
        return null;
    }
}
```

**DeferredImportSelector作用：`定义了一个selectImports接口方法，它的作用是去收集需要的class注册到springioc容器中。`
`ImportSelector`的实现类（比如：AutoConfigurationImportSelector）会和`@Import`结合搭配使用。会把实现类中返回成数组。都注入到ioc容器中。**

```
package org.springframework.boot.autoconfigure;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
}
```

## 05、面试题分析：动手编写一个[@Enable](https://github.com/Enable)开关类

是springboot中存在大量的开关注解类比如：`@EnableTransactionManagement`、`@EnableAsync`等。
`@Enable*`的含义是：开启一项功能，起到了开关的作用。而这些开关的原理是：底层通过`@Import+ImportSelector`接口来实现的。

**步骤1：定义两个需要被ioc容器加载的bean**

```java
package com.kuangstudy.controller.selector;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 11:22
 */
public class PersonBean {
}
```

``` java
package com.kuangstudy.controller.selector;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 11:22
 */
public class RoleBean {
}
```



**步骤2：定义selector类**

```java
package com.kuangstudy.controller.selector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import java.util.function.Predicate;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 11:22
 */
public class PersonRoleImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                "com.kuangstudy.controller.selector.RoleBean",
                "com.kuangstudy.controller.selector.PersonBean"
        };
    }
}
```

**步骤3：自定义一个enable的开关类**

```java
package com.kuangstudy.controller.selector;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 11:24
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(PersonRoleImportSelector.class)
public @interface EnablePersonRole {
}
```

作用是：`告诉springioc要不要去加载PersonRoleImportSelector中的bean注入。`

**步骤4：定义配置类，使用enable开关**

```java
package com.kuangstudy.controller.selector;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 11:22
 */
@EnablePersonRole
public class PersonRoleConfiguration {
}
```

**步骤5：编写测试类**

```
package com.kuangstudy.controller.selector;import org.springframework.context.ApplicationContext;import org.springframework.context.annotation.AnnotationConfigApplicationContext;/** * @author 飞哥 * @Title: 学相伴出品 * @Description: 我们有一个学习网站：https://www.kuangstudy.com * @date 2021/6/3 10:26 */public class TestImportSelector {    public static void main(String[] args) {        // 1:初始化bean        ApplicationContext context = new AnnotationConfigApplicationContext(PersonRoleConfiguration.class);        PersonBean personBean = context.getBean(PersonBean.class);        RoleBean roleBean = context.getBean(RoleBean.class);        System.out.println(personBean);        System.out.println(roleBean);    }}
```

**最终结果：**

```
com.kuangstudy.controller.selector.PersonBean@7a419da4com.kuangstudy.controller.selector.RoleBean@14555e0a
```

**课后作业**
自定模仿设计一个开关。看是否有需要进行用开关的方式进行设计。

## 06、面试题分析：自己动手写一个Spring.factories`

```java
spring常用的几个aware bean接口
BeanNameAware
作用：让Bean获取自己在BeanFactory配置中的名字（根据情况是id或者name）。
Spring自动调用。并且会在Spring自身完成Bean配置之后，且在调用任何Bean生命周期回调（初始化或者销毁）方法之前就调用这个方法。换言之，在程序中使用BeanFactory.getBean(String beanName)之前，Bean的名字就已经设定好了。
BeanFactoryAware
要直接在自己的代码中读取spring的bean,我们除了根据常用的set外,也可以通过spring的BeanFactoryAware接口实现,只要实现setBeanFactory方法就可以。
private BeanFactory  beanFactory;
public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
this.beanFactory = beanFactory;
}
这样我们就可以直接拿东西用了，如
Object  object = beanFactory.getBean(beanName);
我们既然可以通过set来拿到我们要的对象，为什么还要用这个beanFactory呢，道理很简单，因为有些情况是需要动态的去获取对象的，比如说我有10个银行的处理对象，他们都继承了我的BankService对象，但是具体处理的时候要哪家银行的对象呢？这个依赖于用户的选择。你可以注入10个BankService实例，然后用if --else来搞，不过那样太坨了。每增加一家银行你都需要改代码。
通过beanFactory的话，那就一行代码搞定，只要给beanName就OK了，动点脑筋吧beanName配置的有规律点，然后根据用户的银行选择，凑出个beanName，大功告成了！
简单一句话的理解是：beanFactory让你可以不依赖注入方式，随意的读取IOC容器里面的对象，不过beanFactory本身还是要注入的
ApplicationContextAware
当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean。换句话说，就是这个类可以直接获取spring配置文件中，所有有引用到的bean对象。
public class LocalUtil implements ApplicationContextAware{
  private static ApplicationContext applicationcontext;
  @Override
  public void setApplicationContext(ApplicationContext applicationcontext)throws BeansException {
this.applicationcontext = applicationcontext;
  }
  public static ApplicationContext getApplicationContext()throws BeansException {
return applicationcontext;
  }
}
ResourceLoaderAware
ResourceLoaderAware回调接口负责将ResourceLoader对象注入到当前的受管Bean实例中，其定义如下。当受管Bean获得ResourceLoader对象后，它便能够通过它获得各种资源。
public interface ResourceLoaderAware {
    void setResourceLoader(ResourceLoader resourceLoader); 
} 
ServletContextAware
在spring中，凡是实现ServletContextAware接口的类，都可以取得ServletContext。
BeanClassLoaderAware
 允许一个获取它的classLoader(即当前bean factory加载bean类使用的class loader)的回调类，实现了void setBeanClassLoader(ClassLoader classLoader);
InitializingBean
接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候会执行该方法。
```





# 二十一、剖析解密spring.factories原理



## 01、目标

1、搞明白Spring的ImportSelector的原理和Spring的`spring.facories`文件是用来干嘛的？
2、[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)是如何通过`spring.facories`来实现bean的注册?
3、动手编码练习，自定义一个`spring.factories`文件。

## 02、解密Spring的`spring.facories`的原理

**步骤1：在[@EnableAutoConfiguration](https://github.com/EnableAutoConfiguration)对应的开关类中的AutoConfigurationImportSelector.java找到selectImports方法**

```java
public Iterable<Entry> selectImports() {
            if (this.autoConfigurationEntries.isEmpty()) {
                return Collections.emptyList();
            } else {
                Set<String> allExclusions = (Set)this.autoConfigurationEntries.stream().map(AutoConfigurationImportSelector.AutoConfigurationEntry::getExclusions).flatMap(Collection::stream).collect(Collectors.toSet());
                // 这里就是去拿配置的class，把所以的 spring.factories的配置类全部加载到springioc容器中
                Set<String> processedConfigurations = (Set)this.autoConfigurationEntries.stream().map(AutoConfigurationImportSelector.AutoConfigurationEntry::getConfigurations).flatMap(Collection::stream).collect(Collectors.toCollection(LinkedHashSet::new));
                processedConfigurations.removeAll(allExclusions);
                return (Iterable)this.sortAutoConfigurations(processedConfigurations, this.getAutoConfigurationMetadata()).stream().map((importClassName) -> {
                    return new Entry((AnnotationMetadata)this.entries.get(importClassName), importClassName);
                }).collect(Collectors.toList());
            }
        }
```

重点核心代码分析：

```java
// 这里就是去拿配置的class，把所以的 spring.factories的配置类全部加载到springioc容器中
Set<String> processedConfigurations = (Set)this.autoConfigurationEntries.stream().map(AutoConfigurationImportSelector.AutoConfigurationEntry::getConfigurations).flatMap(Collection::stream).collect(Collectors.toCollection(LinkedHashSet::new));
```

- `this.autoConfigurationEntries` 它是一个arrayList集合。`private final List<AutoConfigurationImportSelector.AutoConfigurationEntry> autoConfigurationEntries = new ArrayList();`

**步骤2：debug启动springboot项目**

![img](.\images.assets\kuangstudy57681dc2-3797-4828-910a-cb4913a12861.png)

很清楚的看到集合装配了很多配置类。问题来了？
**autoConfigurationEntries是怎么赋值的?**
找到这个类中的`process`方法,如下：

```
@Override
public void process(AnnotationMetadata annotationMetadata, DeferredImportSelector deferredImportSelector) {
    Assert.state(deferredImportSelector instanceof AutoConfigurationImportSelector,
        () -> String.format("Only %s implementations are supported, got %s",
    AutoConfigurationImportSelector.class.getSimpleName(),
    deferredImportSelector.getClass().getName()));
    // 这里就是把springioc容器中加载好的配置类筛选出来。然后装配给集合
    AutoConfigurationEntry autoConfigurationEntry = ((AutoConfigurationImportSelector) deferredImportSelector)
        .getAutoConfigurationEntry(annotationMetadata);
    // 在这里赋值的    
    this.autoConfigurationEntries.add(autoConfigurationEntry);
    for (String importClassName : autoConfigurationEntry.getConfigurations()) {
    this.entries.putIfAbsent(importClassName, annotationMetadata);
}
}
```

**AutoConfigurationEntry又是如何而来的？**

```java
/**
     * Return the {@link AutoConfigurationEntry} based on the {@link AnnotationMetadata}
     * of the importing {@link Configuration @Configuration} class.
     * @param annotationMetadata the annotation metadata of the configuration class
     * @return the auto-configurations that should be imported
     */
    protected AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
        if (!isEnabled(annotationMetadata)) {
            return EMPTY_ENTRY;
        }
         AnnotationAttributes attributes = getAttributes(annotationMetadata);
        // 1: 收集要注册的springioc中的class
        List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
        // 2：去除重复的classp配置
        configurations = removeDuplicates(configurations);
        // 3:把加载出来的class进行过滤处理。因为set的特性就是不重复元素。相同元素会过滤。
        Set<String> exclusions = getExclusions(annotationMetadata, attributes);
        checkExcludedClasses(configurations, exclusions); 
        configurations.removeAll(exclusions);
        configurations = getConfigurationClassFilter().filter(configurations);
        fireAutoConfigurationImportEvents(configurations, exclusions);
        return new AutoConfigurationEntry(configurations, exclusions);
    }
```

**分析getCandidateConfigurations()方法：**

```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) { 
        // 这里就是去加载spring.factories文件的方法，这个文件中定义了springboot中所有的配置类。
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass(),
                getBeanClassLoader());
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you "
                + "are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
```

**分析：SpringFactoriesLoader.loadFactoryNames**

```java
public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
        ClassLoader classLoaderToUse = classLoader;
        if (classLoader == null) {
            classLoaderToUse = SpringFactoriesLoader.class.getClassLoader();
        }
        String factoryTypeName = factoryType.getName();
        // 这里是真正加载`spring.factories`的地方
        return (List)loadSpringFactories(classLoaderToUse).getOrDefault(factoryTypeName, Collections.emptyList());
    }
```

**分析:loadSpringFactories方法：**

```java
 private static Map<String, List<String>> loadSpringFactories(ClassLoader classLoader) {
        Map<String, List<String>> result = (Map)cache.get(classLoader);
        if (result != null) {
            return result;
        } else {
            HashMap result = new HashMap();
            try {
               // 找到了核心的位置：META-INF/spring.factories,并把类路径下的spring.factories进行匹配获取加载到内存中
                Enumeration urls = classLoader.getResources("META-INF/spring.factories");
                // 开始循环遍历文件中的信息
                while(urls.hasMoreElements()) {
                    URL url = (URL)urls.nextElement();
                    UrlResource resource = new UrlResource(url);
                    // 把文件加载出来，转换成properties，实质就是一个：Map
                    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                    // 开始遍历和循环每一项
                    Iterator var6 = properties.entrySet().iterator();
                    while(var6.hasNext()) {
                        Entry<?, ?> entry = (Entry)var6.next();
                        // 获取对应配置文件中的没一项的key。
                        String factoryTypeName = ((String)entry.getKey()).trim();
                        // 获取对应配置文件中的没一项的值。
                        String[] factoryImplementationNames = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                        String[] var10 = factoryImplementationNames;
                        int var11 = factoryImplementationNames.length;
                        // 然后存放于Map的中。    
                        for(int var12 = 0; var12 < var11; ++var12) {
                            String factoryImplementationName = var10[var12];
                            ((List)result.computeIfAbsent(factoryTypeName, (key) -> {
                                return new ArrayList();
                            })).add(factoryImplementationName.trim());
                        }
                    }
                }
                result.replaceAll((factoryType, implementations) -> {
                    return (List)implementations.stream().distinct().collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
                });
                cache.put(classLoader, result);
                return result;
            } catch (IOException var14) {
                throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", var14);
            }
        }
    }
```

**spring.factories，这个文件在哪里找到**

![img](.\images.assets\kuangstudyfeb3645f-5d21-4c75-96ee-f1bd27fbbd2d.png)

> `spring.factories` 是springboot的解耦扩展机制，这种机制是模仿java的SPI机制来进行实现的。关于什么是Java的SPI可以参考如下地址：

**spring.factories ：内容是什么样子的？**

```
# Initializersorg.springframework.context.ApplicationContextInitializer=\org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer,\org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener# Application Listenersorg.springframework.context.ApplicationListener=\org.springframework.boot.autoconfigure.BackgroundPreinitializer# Auto Configuration Import Listenersorg.springframework.boot.autoconfigure.AutoConfigurationImportListener=\org.springframework.boot.autoconfigure.condition.ConditionEvaluationReportAutoConfigurationImportListener# Auto Configuration Import Filtersorg.springframework.boot.autoconfigure.AutoConfigurationImportFilter=\org.springframework.boot.autoconfigure.condition.OnBeanCondition,\org.springframework.boot.autoconfigure.condition.OnClassCondition,\org.springframework.boot.autoconfigure.condition.OnWebApplicationCondition# Auto Configureorg.springframework.boot.autoconfigure.EnableAutoConfiguration=\org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\org.springframework.boot.autoconfigure.cassandra.CassandraAutoConfiguration,\org.springframework.boot.autoconfigure.context.ConfigurationPropertiesAutoConfiguration,\org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration,\org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration,\org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration,\org.springframework.boot.autoconfigure.couchbase.CouchbaseAutoConfiguration,\org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration,\org.springframework.boot.autoconfigure.data.cassandra.CassandraDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.cassandra.CassandraReactiveRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.cassandra.CassandraRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.couchbase.CouchbaseDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.couchbase.CouchbaseReactiveRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.couchbase.CouchbaseRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.elasticsearch.ReactiveElasticsearchRestClientAutoConfiguration,\org.springframework.boot.autoconfigure.data.jdbc.JdbcRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.ldap.LdapRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.neo4j.Neo4jReactiveDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.neo4j.Neo4jReactiveRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.solr.SolrRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.r2dbc.R2dbcDataAutoConfiguration,\org.springframework.boot.autoconfigure.data.r2dbc.R2dbcRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration,\org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration,\org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration,\org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration,\org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration,\org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchRestClientAutoConfiguration,\org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration,\org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration,\org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration,\org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration,\org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration,\org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration,\org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration,\org.springframework.boot.autoconfigure.hazelcast.HazelcastJpaDependencyAutoConfiguration,\org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration,\org.springframework.boot.autoconfigure.http.codec.CodecsAutoConfiguration,\org.springframework.boot.autoconfigure.influx.InfluxDbAutoConfiguration,\org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration,\org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration,\org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration,\org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,\org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration,\org.springframework.boot.autoconfigure.jdbc.JndiDataSourceAutoConfiguration,\org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration,\org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration,\org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration,\org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration,\org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration,\org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration,\org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration,\org.springframework.boot.autoconfigure.jersey.JerseyAutoConfiguration,\org.springframework.boot.autoconfigure.jooq.JooqAutoConfiguration,\org.springframework.boot.autoconfigure.jsonb.JsonbAutoConfiguration,\org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration,\org.springframework.boot.autoconfigure.availability.ApplicationAvailabilityAutoConfiguration,\org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapAutoConfiguration,\org.springframework.boot.autoconfigure.ldap.LdapAutoConfiguration,\org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,\org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration,\org.springframework.boot.autoconfigure.mail.MailSenderValidatorAutoConfiguration,\org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration,\org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration,\org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration,\org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration,\org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration,\org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,\org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration,\org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration,\org.springframework.boot.autoconfigure.r2dbc.R2dbcTransactionManagerAutoConfiguration,\org.springframework.boot.autoconfigure.rsocket.RSocketMessagingAutoConfiguration,\org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration,\org.springframework.boot.autoconfigure.rsocket.RSocketServerAutoConfiguration,\org.springframework.boot.autoconfigure.rsocket.RSocketStrategiesAutoConfiguration,\org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration,\org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration,\org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration,\org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration,\org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration,\org.springframework.boot.autoconfigure.security.rsocket.RSocketSecurityAutoConfiguration,\org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyAutoConfiguration,\org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration,\org.springframework.boot.autoconfigure.session.SessionAutoConfiguration,\org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration,\org.springframework.boot.autoconfigure.security.oauth2.client.reactive.ReactiveOAuth2ClientAutoConfiguration,\org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration,\org.springframework.boot.autoconfigure.security.oauth2.resource.reactive.ReactiveOAuth2ResourceServerAutoConfiguration,\org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration,\org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration,\org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration,\org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration,\org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration,\org.springframework.boot.autoconfigure.transaction.jta.JtaAutoConfiguration,\org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration,\org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration,\org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration,\org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration,\org.springframework.boot.autoconfigure.web.reactive.ReactiveWebServerFactoryAutoConfiguration,\org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration,\org.springframework.boot.autoconfigure.web.reactive.error.ErrorWebFluxAutoConfiguration,\org.springframework.boot.autoconfigure.web.reactive.function.client.ClientHttpConnectorAutoConfiguration,\org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration,\org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration,\org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration,\org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration,\org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration,\org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration,\org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration,\org.springframework.boot.autoconfigure.websocket.reactive.WebSocketReactiveAutoConfiguration,\org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration,\org.springframework.boot.autoconfigure.websocket.servlet.WebSocketMessagingAutoConfiguration,\org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration,\org.springframework.boot.autoconfigure.webservices.client.WebServiceTemplateAutoConfiguration# Failure analyzersorg.springframework.boot.diagnostics.FailureAnalyzer=\org.springframework.boot.autoconfigure.data.redis.RedisUrlSyntaxFailureAnalyzer,\org.springframework.boot.autoconfigure.diagnostics.analyzer.NoSuchBeanDefinitionFailureAnalyzer,\org.springframework.boot.autoconfigure.flyway.FlywayMigrationScriptMissingFailureAnalyzer,\org.springframework.boot.autoconfigure.jdbc.DataSourceBeanCreationFailureAnalyzer,\org.springframework.boot.autoconfigure.jdbc.HikariDriverConfigurationFailureAnalyzer,\org.springframework.boot.autoconfigure.r2dbc.ConnectionFactoryBeanCreationFailureAnalyzer,\org.springframework.boot.autoconfigure.session.NonUniqueSessionRepositoryFailureAnalyzer# Template availability providersorg.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider=\org.springframework.boot.autoconfigure.freemarker.FreeMarkerTemplateAvailabilityProvider,\org.springframework.boot.autoconfigure.mustache.MustacheTemplateAvailabilityProvider,\org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAvailabilityProvider,\org.springframework.boot.autoconfigure.thymeleaf.ThymeleafTemplateAvailabilityProvider,\org.springframework.boot.autoconfigure.web.servlet.JspTemplateAvailabilityProvider
```

**为什么返回的是：Map<String,List<String>>**
因为`spring.factories`的文件对应每种key有很多种，每种key对应的value也很多。当然是map<string,list>类型来装载，看上面的源代码就可以得知。

[01、目标](https://www.kuangstudy.com/zl/1392498023556202497#header1)[02、解密Spring的spring.facories的原理](https://www.kuangstudy.com/zl/1392498023556202497#header2)

https://www.kuangstudy.com/bbs/1324199621287600130)

# 二十二、自定义实现Spring.factories文件&自定义starter机制



## 01、目标

自定义starter机制

## 02、分析

**步骤1：在/src/main/resources目录下新建一个,META/Spring.factoires文件**

![img](.\images.assets\kuangstudy4f43fc58-2882-40df-bb2f-9cce3d250776.png)

**步骤2：新建一个配置类和一个bean**
注意包名：是不同的包哦。当然你可以放在不同的工程下即可。

```java
package com.kuang.message;
import java.sql.Connection;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 14:20
 */
public class MessageTemplate {
    public Connection getConnection() {
        System.out.println("我是一个链接方法....");
        return null;
    }
}
```

``` java
package com.kuang.message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/3 14:20
 */
@Configuration
public class MessageTemplateConfiguration {
    @Bean
    public MessageTemplate messageTemplate() {
        return new MessageTemplate();
    }
}
```





**步骤3：然后在配置文件`spring.factories`中去装配MessageConfiguration**

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.kuang.message.MessageTemplateConfiguration
```

**步骤4：体验`MessageTemplate`是否加载到ioc容器中**

```java
package com.kuangstudy;
import com.kuang.message.MessageTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.sql.Connection;
@SpringBootApplication
@EnableAsync //开启异步执行
@EnableTransactionManagement
@MapperScan("com.kuangstudy.mapper")
public class KuangstudyBootHelloApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(KuangstudyBootHelloApplication.class, args);
        MessageTemplate bean = applicationContext.getBean(MessageTemplate.class);
        Connection connection = bean.getConnection();
    }
}
```

打印结果：`我是一个链接方法....` 说明以及被加载到ioc容器中去了。
通过断点查看如下：

![img](.\images.assets\kuangstudy8c697475-f4a3-4797-a6f4-bb87ae81e725.png)

![img](.\images.assets\kuangstudyac30bc49-b99d-40f4-b9ec-2e4136ff61a4.png)

## 03、课程作业

尝试使用`spring.factories`去封装一个RedisTemplate。



# 二十三、SpringBoot中的@Primary 和 @Qualifier



## 01、目标

掌握SpringBoot中的[@Primary](https://github.com/Primary) 和 [@Qualifier](https://github.com/Qualifier)

## 02、问题

当一个接口有2个不同实现时,使用[@Autowired](https://github.com/Autowired)注解时会报org.springframework.beans.factory.NoUniqueBeanDefinitionException异常信息。
**解决**
（1）使用Qualifier注解，选择一个对象的名称,通常比较常用
（2）Primary可以理解为默认优先选择,不可以同时设置多个,内部实质是设置BeanDefinition的primary属性

| 注解                                       | 备注                                                         |
| ------------------------------------------ | ------------------------------------------------------------ |
| [@Primary](https://github.com/Primary)     | 优先方案，被注解的实现，优先被注入                           |
| [@Qualifier](https://github.com/Qualifier) | 先声明后使用，相当于多个实现起多个不同的名字，注入时候告诉我你要注入哪个 |

## 03、[@Qualifier](https://github.com/Qualifier)

**接口**

```java
public interface EmployeeService {
    public EmployeeDto getEmployeeById(Long id);
}
```

**接口对应的两个实现类：EmployeeServiceImpl和EmployeeServiceImpl1：接口对应的两个实现类：EmployeeServiceImpl和EmployeeServiceImpl1：**

```java
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    public EmployeeDto getEmployeeById(Long id) {
                return new EmployeeDto();
     }
}
```

``` java
@Service("employeeService1")
public class EmployeeServiceImpl1 implements EmployeeService {
    public EmployeeDto getEmployeeById(Long id) {
         return new EmployeeDto();
    }
}
```



**这个时候就要用到[@Qualifier](https://github.com/Qualifier)注解了，qualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的，我们修改调用代码，添加[@Qualifier](https://github.com/Qualifier)注解，需要注意的是[@Qualifier](https://github.com/Qualifier)的参数名称必须为我们之前定义[@Service](https://github.com/Service)注解的名称之一！**

```java
@Controller
@RequestMapping("/emplayee.do")
public class EmployeeInfoControl {
    @Autowired
    @Qualifier("employeeService")
    EmployeeService employeeService;
    @RequestMapping(params = "method=showEmplayeeInfo")
    public void showEmplayeeInfo(HttpServletRequest request, HttpServletResponse response, EmployeeDto dto) {
            //#略
    }
}
```

## 04、[@Primary](https://github.com/Primary)

[@Primary](https://github.com/Primary)：和[@Qualifier](https://github.com/Qualifier) 一样，[@Primary](https://github.com/Primary)也一样，使用场景经常是：在spring 中使用注解，常使用[@Autowired](https://github.com/Autowired)， 默认是根据类型Type来自动注入的。但有些特殊情况，对同一个接口，可能会有几种不同的实现类，而默认只会采取其中一种的情况下 [@Primary](https://github.com/Primary) 的作用就出来了。

**接口**

```java
public interface Singer {   
    String sing(String lyrics);
}
```

**有下面的两个实现类:**

```java
@Component // 加注解，让spring识别
public class MetalSinger implements Singer{
    @Override
    public String sing(String lyrics) {
        return "I am singing with DIO voice: "+lyrics;
    }
}
```

``` java
@Primary
@Component
public class OperaSinger implements Singer{
    @Override
    public String sing(String lyrics) {
        return "I am singing in Bocelli voice: "+lyrics;
    }
}
```

``` java
@Controller
@RequestMapping("/emplayee.do")
public class EmployeeInfoControl {
    @Autowired
    Singer singer; // 最终会把OperaSinger注入给它
    @RequestMapping(params = "method=showEmplayeeInfo")
    public void showEmplayeeInfo(HttpServletRequest request, HttpServletResponse response, EmployeeDto dto) {
            //#略
    }
}

```

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

# 二十六、Spring实战系列（二）-有关属性配置文件的加载和使用



“对于Spring框架，现实公司使用的非常广泛，但是由于业务的复杂程度不同，了解到很多小伙伴们利用Spring开发仅仅是利用了Spring的IOC，即使是AOP也很少用，但是目前的Spring是一个大家族，形成了一个很大的生态，覆盖了我们平时开发的方方面面，抛开特殊的苛刻要求之外，Spring的生态其实已经很全面了，所以在此开个系列来研究下Spring提供给我们的一些平时不太却又很实用的内容。”

说明：

我们平时使用Spring进行开发的时候经常会需要加载配置文件，而且Spring加载配置文件的方式又提供了很多种，我这次先把一些普通的加载方式说明下，然后再就一些比较特殊的情况的加载进行说明。

1、常用加载方式：
1.1、xml中进行配置文件引入

```xml
<context:property-placeholder location="classpath:config/*.properties"  file-encoding="utf-8"/>
    这个还有个原始的配置是配置 一个PropertyPlaceholderConfigurer
<bean id="propertyConfigurer"class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
     <property name="location">
       <value>conf/jdbc.properties</value>
     </property>
      <property name="fileEncoding">
       <value>UTF-8</value>
    </property>
</bean>
```

1.2、通过注解进行导入

[@PropertySource](https://github.com/PropertySource)(value = “properties/config.properties”)
以上是导入一个配置文件，如果需要导入多个，value是支持数组的，还有一个注解是[@PropertySources](https://github.com/PropertySources),他是支持[@PropertySource](https://github.com/PropertySource)的数组格式。根据需要进行选用。

1.3、通过定义PropertyPlaceholderConfigurer的Bean来进行加载

```java
@Bean
public PropertyPlaceholderConfigurer initPropertyPlaceholderConfigurer() {
    PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
    placeholderConfigurer.setLocation(new ClassPathResource("properties/*.properties"));
    placeholderConfigurer.setFileEncoding("UTF-8");
    return placeholderConfigurer;
}
```

以上是加载配置文件的几种方式，springboot是内部已经配好了扫描跟路径下的application.properties文件。
接下来进入我们今天的重点，如何使用已经导入配置文件中的属性。

2、使用配置文件中的属性
2.1、xml中使用—占位符

```xml
<mongo:mongo-client id="dataMongoConnection" replica-set="${mongo.data.hostport}" credentials="${mongo.data.username}:${mongo.data.password}@admin">
    <!--<mongo:mongo-client id="dataMongoConnection" replica-set="${mongo.data.hostport}">-->
        <mongo:client-options connections-per-host="${mongo.data.connectionsPerHost}" threads-allowed-to-block-for-connection-multiplier="${mongo.data.threadsAllowedToBlockForConnectionMultiplier}"
                              connect-timeout="${mongo.data.connectTimeout}"
                              max-wait-time="${mongo.data.maxWaitTime}"
                              socket-keep-alive="${mongo.data.socketKeepAlive}"
                              socket-timeout="${mongo.data.socketTimeout}"/>
</mongo:mongo-client>
```

2.2、注解的使用

```java
@Value("${jdbc.username}")
private String username;
```

以上是经常使用的方式，接下来将一个不常用的方式，有时候我们在引入Spring的第三方工具包的时候，有时在采用注解配置第三方核心配置类的时候发现采用注解引入属性值的时候发现不起作用，观察这些配置类的时候会发现他们经常会实现Spring的一些顶级接口类，例如：BeanDefinitionRegistryPostProcessor。因为PropertyPlaceholderConfigurer的初始化级别是最低的，当在注册这些顶级Bean的时候，我们的属性值是还没有被赋值的，所以导致属性值都是初始化值，如何解决这种情况呢，有2种方式。

方式一：

在Spring调用加载配置文件的时候，有一个通用类可以获取到当前环境的属性文件上下文Environment，我们可以直接通过注解直接引入这个类。

```java
@Autowired
private Environment environment;
在获取到这个类之后我们就可以自由的操作属性值了，操作如下：
environment.resolvePlaceholders("${dataSource.url}");
        environment.resolvePlaceholders("${dataSource.username}");
        environment.resolvePlaceholders("${dataSource.password}");
```

这时候就解决[@Value](https://github.com/Value)不可用的问题，其实代码environment.resolvePlaceholders(“${dataSource.url}”);这个被调用后返回的是解析后的字符串值，这点也要注意下。

方式二：

这个方式比较笨拙，就是将这种顶级配置类单独放到一个配置类中进行配置，而且仅限该类没有属性需要配置的情况下。这种情况使用很特殊，大家可以忽略。
以上便是我今天要分享的实战内容，虽然知识点不多，但是也是实际开发中遇到的，希望对大家有所帮助。



# 二十七、Spring实战系列（三）-BeanPostProcessor的妙用



## 01、概述

“对于Spring框架，现实公司使用的非常广泛，但是由于业务的复杂程度不同，了解到很多小伙伴们利用Spring开发仅仅是利用了Spring的IOC，即使是AOP也很少用，但是目前的Spring是一个大家族，形成了一个很大的生态，覆盖了我们平时开发的方方面面，抛开特殊的苛刻要求之外，Spring的生态其实已经很全面了，所以在此开个系列来研究下Spring提供给我们的一些平时不太却又很实用的内容。”

说明：

对于Spring开发时，我们有时会遇到同一个接口有多个实现类，为了避免错误，我们通常在具体调用的地方通过ApplicationContext根据业务的需要来选择不同的接口实现类，虽然可以在抽象出一个工厂方法，但是还是感觉不够优雅，如果通过[@Autowired](https://github.com/Autowired)直接引入接口,则需要在某个实现类上标注[@Primary](https://github.com/Primary),否则会报错。那么书归正传如何优雅的解决上述的问题呢，此处就介绍一种利用Spring的BeanPostProcessor来处理。话不多说先上接口

示例：

1、声明接口

```java
public interface HelloService {
    public void sayHello();
}
```

2、对应的接口实现类1：

```java
@Service
public class HelloServiceImpl1 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl1");
    }
}
```

3、对应接口实现类2：

```java
@Service
public class HelloServiceImpl2 implements HelloService{
    @Override
    public void sayHello() {
        System.out.println("你好我是HelloServiceImpl2");
    }
}
```

4、自定义注解：

```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RountingInjected {
    String value() default "helloServiceImpl1";
}
```

5、自定义BeanPostProcessor实现类：

```java
@Component
public class HelloServiceInjectProcessor implements BeanPostProcessor {
    @Autowired
    private ApplicationContext applicationContext;
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetCls = bean.getClass();
        Field[] targetFld = targetCls.getDeclaredFields();
        for (Field field : targetFld) {
            //找到制定目标的注解类
            if (field.isAnnotationPresent(RountingInjected.class)) {
                if (!field.getType().isInterface()) {
                    throw new BeanCreationException("RoutingInjected field must be declared as an interface:" + field.getName()
                            + " @Class " + targetCls.getName());
                }
                try {
                    this.handleRoutingInjected(field, bean, field.getType());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
    /**
     * @param field
     * @param bean
     * @param type
     * @throws IllegalAccessException
     */
    private void handleRoutingInjected(Field field, Object bean, Class type) throws IllegalAccessException {
        Map<String, Object> candidates = this.applicationContext.getBeansOfType(type);
        field.setAccessible(true);
        if (candidates.size() == 1) {
            field.set(bean, candidates.values().iterator().next());
        } else if (candidates.size() == 2) {
            String injectVal = field.getAnnotation(RountingInjected.class).value();
            Object proxy = RoutingBeanProxyFactory.createProxy(injectVal, type, candidates);
            field.set(bean, proxy);
        } else {
            throw new IllegalArgumentException("Find more than 2 beans for type: " + type);
        }
    }
```

6、对应的代理实现类：

```java
public class RoutingBeanProxyFactory {
    private final static String DEFAULT_BEAN_NAME = "helloServiceImpl1";
    public static Object createProxy(String name, Class type, Map<String, Object> candidates) {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setInterfaces(type);
        proxyFactory.addAdvice(new VersionRoutingMethodInterceptor(name, candidates));
        return proxyFactory.getProxy();
    }
    static class VersionRoutingMethodInterceptor implements MethodInterceptor {
        private Object targetObject;
        public VersionRoutingMethodInterceptor(String name, Map<String, Object> beans) {
            this.targetObject = beans.get(name);
            if (this.targetObject == null) {
                this.targetObject = beans.get(DEFAULT_BEAN_NAME);
            }
        }
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            return invocation.getMethod().invoke(this.targetObject, invocation.getArguments());
        }
    }
}
```

7、结果测试类

```java
@Component
public class HelloServiceTest {
    @RountingInjected(value = "helloServiceImpl2")
    private HelloService helloService;
    public void testSayHello() {
        helloService.sayHello();
    }
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("colin.spring.basic.advanced.bbp");
        HelloServiceTest helloServiceTest = applicationContext.getBean(HelloServiceTest.class);
        helloServiceTest.testSayHello();
    }
```

上述是整个解决方案的示例流程，其核心思想就是根据自定义注解拦截要注入的接口实现类，运用java反射和代理的知识点来进行有效的实现类注入。
再次补充下BeanPostProcessor的一些知识点，
BeanPostProcessor接口作用：

如果我们想在Spring容器中完成bean实例化、配置以及其他初始化方法前后要添加一些自己逻辑处理。我们需要定义一个或多个BeanPostProcessor接口实现类，然后注册到Spring IoC容器中。

Spring中Bean的实例化过程图示：

![img](.\images.assets\kuangstudy87cb6a2b-4cb1-4cf1-b00d-f611cf7c6455.png)

注意：
1、接口中的两个方法都要将传入的bean返回，而不能返回null，如果返回的是null那么我们通过getBean方法将得不到目标。
2、BeanFactory和ApplicationContext对待bean后置处理器稍有不同。ApplicationContext会自动检测在配置文件中实现了BeanPostProcessor接口的所有bean，并把它们注册为后置处理器，然后在容器创建bean的适当时候调用它，因此部署一个后置处理器同部署其他的bean并没有什么区别。而使用BeanFactory实现的时候，bean 后置处理器必须通过代码显式地去注册，在IoC容器继承体系中的ConfigurableBeanFactory接口中定义了注册方法

```java
 * Add a new BeanPostProcessor that will get applied to beans created  
 * by this factory. To be invoked during factory configuration.  
 * <p>Note: Post-processors submitted here will be applied in the order of  
 * registration; any ordering semantics expressed through implementing the  
 * {@link org.springframework.core.Ordered} interface will be ignored. Note  
 * that autodetected post-processors (e.g. as beans in an ApplicationContext)  
 * will always be applied after programmatically registered ones.  
 * @param beanPostProcessor the post-processor to register   
void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
```

另外，不要将BeanPostProcessor标记为延迟初始化。因为如果这样做，Spring容器将不会注册它们，自定义逻辑也就无法得到应用。假如你在<beans />元素的定义中使用了’default-lazy-init’属性，请确信你的各个BeanPostProcessor标记为’lazy-init=”false”‘。

InstantiationAwareBeanPostProcessor
InstantiationAwareBeanPostProcessor是BeanPostProcessor的子接口，可以在Bean生命周期的另外两个时期提供扩展的回调接口， 即实例化Bean之前（调用postProcessBeforeInstantiation方法）和实例化Bean之后（调用postProcessAfterInstantiation方法）， 该接口定义如下：

```java
package org.springframework.beans.factory.config;    
import java.beans.PropertyDescriptor;    
import org.springframework.beans.BeansException;    
import org.springframework.beans.PropertyValues;    
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {    
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;    
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;    
    PropertyValues postProcessPropertyValues(    
            PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName)    
            throws BeansException;    
}
```

其使用方法与上面介绍的BeanPostProcessor接口类似，只时回调时机不同。

如果是使用ApplicationContext来生成并管理Bean的话则稍有不同，使用ApplicationContext来生成及管理Bean实例的话，在执行BeanFactoryAware的setBeanFactory()阶段后，若Bean类上有实现org.springframework.context.ApplicationContextAware接口，则执行其setApplicationContext()方法，接着才执行BeanPostProcessors的ProcessBeforeInitialization()及之后的流程。



# 二十八、Spring实战系列（四）-动态注入接口Bean



## 01、概述

“对于Spring框架，现实公司使用的非常广泛，但是由于业务的复杂程度不同，了解到很多小伙伴们利用Spring开发仅仅是利用了Spring的IOC，即使是AOP也很少用，但是目前的Spring是一个大家族，形成了一个很大的生态，覆盖了我们平时开发的方方面面，抛开特殊的苛刻要求之外，Spring的生态其实已经很全面了，所以在此开个系列来研究下Spring提供给我们的一些平时不太却又很实用的内容。”

上一篇我们分析了BeanPostProcessor的基本使用，接下来我们分析下如何使用该类实现动态的接口注入，示例说明：在BeetlSQL框架中，在使用自动扫描注入时，我们通常只需要配置上要扫描的包路径，然后在该路径下声明对应的Dao接口类，这些接口类都默认继承BaseMapper接口类，然后我们在使用这些Dao类的时候，直接根据类型注入（[@Autowired](https://github.com/Autowired)）即可使用，这个其实和Mybatis的那一套相似，也和Spring自身的Spring-data框架也类似。这个经常用于框架的开发，那么我就该部分的实现做相应的解释，这三个框架具体实现可能有差距，感兴趣的小伙伴自行去查看源码，我会以一个很简单的例子来讲解大概的实现逻辑。

问题描述：

继承Spring框架，实现声明某个自定义接口（UserMapper）,改接口继承通用接口BaseMapper，（通用接口BaseMapper有默认的实现类），实现通过类型注入UserMapper类，然后通过Spring框架的上下文类（ApplicationContext实现类）的getBean（）方法拿到UserMapper类来调用内部提供的方法。

1、声明BaseMapper接口类

```java
  public interface BaseMapper {
    /**
     * @param value
     */
    public void add(String value);
    /**
     * @param key
     */
    public void remove(String key);
}
```

2、默认BaseMapper实现类：CustomBaseMapper

```java
 public class CustomBaseMapper implements BaseMapper {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private List<String> dataList = new CopyOnWriteArrayList<>();
    /**
     * @param value
     */
    @Override
    public void add(String value) {
        logger.info("添加数据:" + value);
        dataList.add(value);
    }
    /**
     * @param key
     */
    @Override
    public void remove(String key) {
        if (dataList.isEmpty())
            throw new IllegalArgumentException("Can't remove because the list is Empty!");
    }
}
```

接下来是继承Spring的核心代码

3、首先我们要先定义一个扫描某路径下的类，该类继承ClassPathBeanDefinitionScanner，自定义扫描类：DefaultClassPathScanner

```java
  public class DefaultClassPathScanner extends ClassPathBeanDefinitionScanner {
    private final String DEFAULT_MAPPER_SUFFIX = "Mapper";
    public DefaultClassPathScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }
    private String mapperManagerFactoryBean;
    /**
     * 扫描包下的类-完成自定义的Bean定义类
     *
     * @param basePackages
     * @return
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        // 如果指定的基础包路径中不存在任何类对象，则提示
        if (beanDefinitions.isEmpty()) {
            logger.warn("系统没有在 '" + Arrays.toString(basePackages) + "' 包中找到任何Mapper，请检查配置");
        } else {
            processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }
    /**
     * 注册过滤器-保证正确的类被扫描注入
     */
    protected void registerFilters() {
        addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                    throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                //TODO 这里设置包含条件-此处是个扩展点，可以根据自定义的类后缀过滤出需要的类
                return className.endsWith(DEFAULT_MAPPER_SUFFIX);
            }
        });
        addExcludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                    throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }
    /**
     * 重写父类的判断是否能够实例化的组件-该方法是在确认是否真的是isCandidateComponent
     * 原方法解释：
     * 确定给定的bean定义是否有资格成为候选人。
     * 默认实现检查类是否不是接口，也不依赖于封闭类。
     * 以在子类中重写。
     *
     * @param beanDefinition
     * @return
     */
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        // 原方法这里是判断是否为顶级类和是否是依赖类（即接口会被排除掉-由于我们需要将接口加进来，所以需要覆盖该方法）
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }
    /**
     * 扩展方法-对扫描到的含有BeetlSqlFactoryBean的Bean描述信息进行遍历
     *
     * @param beanDefinitions
     */
    void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            String mapperClassName = definition.getBeanClassName();
            // 必须在这里加入泛型限定，要不然在spring下会有循环引用的问题
            definition.getConstructorArgumentValues().addGenericArgumentValue(mapperClassName);
            //依赖注入
            definition.getPropertyValues().add("mapperInterface", mapperClassName);
            // 根据工厂的名称创建出默认的BaseMapper实现
            definition.getPropertyValues().add("mapperManagerFactoryBean", new RuntimeBeanReference(this.mapperManagerFactoryBean));
            definition.setBeanClass(BaseMapperFactoryBean.class);
            // 设置Mapper按照接口组装
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
            logger.info("已开启自动按照类型注入 '" + holder.getBeanName() + "'.");
        }
    }
    public void setMapperManagerFactoryBean(String mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }
}
```

4、核心的接口实现类：BaseMapperFactoryBean

```java
 public class BaseMapperFactoryBean<T> implements FactoryBean<T>, InitializingBean, ApplicationListener<ApplicationEvent>, ApplicationContextAware {
    /**
     * 要注入的接口类定义
     */
    private Class<T> mapperInterface;
    /**
     * Spring上下文
     */
    private ApplicationContext applicationContext;
    //也因该走工厂方法注入得来
    private BaseMapper mapperManagerFactoryBean;
    public BaseMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
    @Override
    public T getObject() throws Exception {
        //采用动态代理生成接口实现类，核心实现
        return (T) Proxy.newProxyInstance(applicationContext.getClassLoader(), new Class[]{mapperInterface}, new MapperJavaProxy(mapperManagerFactoryBean, mapperInterface));
    }
    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 判断属性的注入是否正确-如mapperInterface判空
        if (null == mapperInterface)
            throw new IllegalArgumentException("Mapper Interface Can't Be Null!!");
    }
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //TODO 可依据事件进行扩展
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }
    public void setMapperManagerFactoryBean(BaseMapper mapperManagerFactoryBean) {
        this.mapperManagerFactoryBean = mapperManagerFactoryBean;
    }
}
```

5、定义默认的BaseMapper的FactoryBean-MapperManagerFactoryBean

```java
  public class MapperManagerFactoryBean implements FactoryBean<BaseMapper>, InitializingBean, ApplicationListener<ApplicationEvent> {
    @Override
    public BaseMapper getObject() throws Exception {
        return new CustomBaseMapper();
    }
    @Override
    public Class<?> getObjectType() {
        return BaseMapper.class;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        //TODO 判断属性的注入是否正确
    }
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }
}
```

6、核心的java动态代理类：MapperJavaProxy

```java
 public class MapperJavaProxy implements InvocationHandler {
    private BaseMapper baseMapper;
    private Class<?> interfaceClass;
    public MapperJavaProxy(BaseMapper baseMapper, Class<?> interfaceClass) {
        this.baseMapper = baseMapper;
        this.interfaceClass = interfaceClass;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("mapperInterface is not interface.");
        }
        if (baseMapper == null) {
            baseMapper = new CustomBaseMapper();
        }
        return method.invoke(baseMapper, args);
    }
}
```

7、调用时的核心配置类：DefaultClassRegistryBeanFactory

```java
 public class MapperJavaProxy implements InvocationHandler {
    private BaseMapper baseMapper;
    private Class<?> interfaceClass;
    public MapperJavaProxy(BaseMapper baseMapper, Class<?> interfaceClass) {
        this.baseMapper = baseMapper;
        this.interfaceClass = interfaceClass;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!interfaceClass.isInterface()) {
            throw new IllegalArgumentException("mapperInterface is not interface.");
        }
        if (baseMapper == null) {
            baseMapper = new CustomBaseMapper();
        }
        return method.invoke(baseMapper, args);
    }
}
```

8、调用测试

8.1、假设你在包目录：colin.spring.basic.advanced.inject.dao下声明自定义的类UserMapper

```java
public interface UserMapper extends BaseMapper {}
```

8.2、声明配置类：ClassRegistryBeanScannerConfig

```java
@Configuration
public class ClassRegistryBeanScannerConfig {
    @Bean(name = "mapperManagerFactoryBean")
    public MapperManagerFactoryBean configMapperManagerFactoryBean() {
        MapperManagerFactoryBean mapperManagerFactoryBean = new MapperManagerFactoryBean();
        return mapperManagerFactoryBean;
    }
    @Bean
    public DefaultClassRegistryBeanFactory configDefaultClassRegistryBeanFactory() {
        DefaultClassRegistryBeanFactory defaultClassRegistryBeanFactory = new DefaultClassRegistryBeanFactory();
        defaultClassRegistryBeanFactory.setScanPackage("colin.spring.basic.advanced.inject.dao");
        defaultClassRegistryBeanFactory.setMapperManagerFactoryBean("mapperManagerFactoryBean");
        return defaultClassRegistryBeanFactory;
    }
}
```

8.3、测试调用

```java
  public static void main(String[] args) {
        AnnotationConfigApplicationContext acApplicationCOntext = new AnnotationConfigApplicationContext("colin.spring.basic.advanced.inject");
        UserMapper userMapper = acApplicationCOntext.getBean(UserMapper.class);
        userMapper.add("lalaldsf");
        acApplicationCOntext.stop();
    }
```

总结：
此处对于BeanPostProcessor接口的调用应该属于高级应用了，该思路常用来解决扩展或集成Spring框架，其核心的思路可以分为以下几步：
1、自定义实现类路径扫描类，决定哪些类应该被注入进Spring容器。
2、采用Java动态代理来动态实现对于声明接口类的注入。
3、实现BeanDefinitionRegistryPostProcessor，在Spring初始化初期将需要扫描导入Spring容器的类进行注入。





# 二十九、Spring实战系列（五） - Spring中的InitializingBean接口的使用



## 01、分析

InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
测试，如下：

```java
import org.springframework.beans.factory.InitializingBean;
public class TestInitializingBean implements InitializingBean{
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ceshi InitializingBean");        
    }
    public void testInit(){
        System.out.println("ceshi init-method");        
    }
}
```

定义一个配置类如下：

```java
package com.kuangstudy.initializingbean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * @author 飞哥
 * @Title: 学相伴出品
 * @Description: 我们有一个学习网站：https://www.kuangstudy.com
 * @date 2021/6/25 11:16
 */
@Configuration
public class TestInitializingBeanConfiguration {
    @Bean(initMethod = "testInit")
    public TestInitializingBean getTestInitializingBean() {
        return new TestInitializingBean();
    }
}
```

运行程序，得出结果：

```
ceshi InitializingBeanceshi init-method
```

从结果可以看出，在Spring初始化bean的时候，如果该bean实现了InitializingBean接口，并且同时在配置文件中指定了init-method，系统则是先调用afterPropertieSet()方法，然后再调用init-method中指定的方法。

那么这种方式在spring中是怎么实现的呢，通过查看Spring加载bean的源码类AbstractAutowiredCapableBeanFactory可以看出其中的奥妙，AbstractAutowiredCapableBeanFactory类中的invokeInitMethods说的非常清楚，如下：

```java
protected void invokeInitMethods(String beanName, final Object bean, RootBeanDefinition mbd) throws Throwable {
    //判断该bean是否实现了实现了InitializingBean接口，如果实现了InitializingBean接口，则只掉调用bean的afterPropertiesSet方法
    boolean isInitializingBean = (bean instanceof InitializingBean);
    if (isInitializingBean && (mbd == null || !mbd.isExternallyManagedInitMethod("afterPropertiesSet"))) {
        if (logger.isDebugEnabled()) {
            logger.debug("Invoking afterPropertiesSet() on bean with name '" + beanName + "'");
        }
        if (System.getSecurityManager() != null) {
            try {
                AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                    public Object run() throws Exception {
                        //直接调用afterPropertiesSet
                        ((InitializingBean) bean).afterPropertiesSet();
                        return null;
                    }
                },getAccessControlContext());
            } catch (PrivilegedActionException pae) {
                throw pae.getException();
            }
        }                
        else {
            //直接调用afterPropertiesSet
            ((InitializingBean) bean).afterPropertiesSet();
        }
    }
    if (mbd != null) {
        String initMethodName = mbd.getInitMethodName();
        //判断是否指定了init-method方法，如果指定了init-method方法，则再调用制定的init-method
        if (initMethodName != null && !(isInitializingBean && "afterPropertiesSet".equals(initMethodName)) &&
                !mbd.isExternallyManagedInitMethod(initMethodName)) {
            //进一步查看该方法的源码，可以发现init-method方法中指定的方法是通过反射实现
            invokeCustomInitMethod(beanName, bean, mbd);
        }
    }
}
```

## 02、使用场景

- 在SpringMVC中AbstractHandlerMethodMapping就实现了InitializingBean接口，当一个RequestMappingHandlerMapping的实例创建完成后会接着调用afterPropertiesSet方法，扫描所有的controller完成所有的handler method的注册。
- 在mybatis中的SqlSessionFactoryBean中，用来初始化sqlSessionFactory，以及断言判断有没有数据源等。
- 

## 03、总结

1、Spring为bean提供了两种初始化bean的方式，实现InitializingBean接口，实现afterPropertiesSet方法，或者在配置文件中通过init-method指定，两种方式可以同时使用。
2、实现InitializingBean接口是直接调用afterPropertiesSet方法，比通过反射调用init-method指定的方法效率要高一点，但是init-method方式消除了对spring的依赖。
3、如果调用afterPropertiesSet方法时出错，则不调用init-method指定的方法。



# 三十、Spring中Bean 的完整生命周期



https://www.cnblogs.com/zrtqsk/p/3735273.html

在传统的Java应用中，bean的生命周期很简单，使用Java关键字 new 进行Bean 的实例化，然后该Bean 就能够使用了。一旦bean不再被使用，则由Java自动进行垃圾回收。

相比之下，Spring管理Bean的生命周期就复杂多了，正确理解Bean 的生命周期非常重要，因为Spring对Bean的管理可扩展性非常强，下面展示了一个Bean的构造过程

![img](.\images.assets\kuangstudyefff3255-ce06-4d2a-9974-94bc70c5675b.png)

如上图所示，Bean 的生命周期还是比较复杂的，下面来对上图每一个步骤做文字描述:

- Spring启动，查找并加载需要被Spring管理的bean，进行Bean的实例化
- Bean实例化后对将Bean的引入和值注入到Bean的属性中
- 如果Bean实现了BeanNameAware接口的话，Spring将Bean的Id传递给setBeanName()方法
- 如果Bean实现了BeanFactoryAware接口的话，Spring将调用setBeanFactory()方法，将BeanFactory容器实例传入
- 如果Bean实现了ApplicationContextAware接口的话，Spring将调用Bean的setApplicationContext()方法，将bean所在应用上下文引用传入进来
- 如果Bean实现了BeanPostProcessor接口，Spring就将调用他们的postProcessBeforeInitialization()方法。
- 如果Bean 实现了InitializingBean接口，Spring将调用他们的afterPropertiesSet()方法。类似的，如果bean使用init-method声明了初始化方法，该方法也会被调用
- 如果Bean 实现了BeanPostProcessor接口，Spring就将调用他们的postProcessAfterInitialization()方法。
- 此时，Bean已经准备就绪，可以被应用程序使用了。他们将一直驻留在应用上下文中，直到应用上下文被销毁。
- 如果bean实现了DisposableBean接口，Spring将调用它的destory()接口方法，同样，如果bean使用了destory-method 声明销毁方法，该方法也会被调用。

**摘自org.springframework.beans.factory.BeanFactory， 全部相关接口如下，上述已有的就不用着重标注，把额外的相关接口着重标注下**

![img](.\images.assets\kuangstudy80f559c2-3ce6-40d3-97e1-4cdc1719ceae.png)

————————————初始化————————————

- BeanNameAware.setBeanName() 在创建此bean的bean工厂中设置bean的名称，在普通属性设置之后调用，在InitializinngBean.afterPropertiesSet()方法之前调用
- BeanClassLoaderAware.setBeanClassLoader(): 在普通属性设置之后，InitializingBean.afterPropertiesSet()之前调用
- BeanFactoryAware.setBeanFactory() : 回调提供了自己的bean实例工厂，在普通属性设置之后，在InitializingBean.afterPropertiesSet()或者自定义初始化方法之前调用
- EnvironmentAware.setEnvironment(): 设置environment在组件使用时调用
- EmbeddedValueResolverAware.setEmbeddedValueResolver(): 设置StringValueResolver 用来解决嵌入式的值域问题
- ResourceLoaderAware.setResourceLoader(): 在普通bean对象之后调用，在afterPropertiesSet 或者自定义的init-method 之前调用，在 ApplicationContextAware 之前调用。
- ApplicationEventPublisherAware.setApplicationEventPublisher(): 在普通bean属性之后调用，在初始化调用afterPropertiesSet 或者自定义初始化方法之前调用。在 ApplicationContextAware 之前调用。
- MessageSourceAware.setMessageSource(): 在普通bean属性之后调用，在初始化调用afterPropertiesSet 或者自定义初始化方法之前调用，在 ApplicationContextAware 之前调用。
- ApplicationContextAware.setApplicationContext(): 在普通Bean对象生成之后调用，在InitializingBean.afterPropertiesSet之前调用或者用户自定义初始化方法之前。在ResourceLoaderAware.setResourceLoader，ApplicationEventPublisherAware.setApplicationEventPublisher，MessageSourceAware之后调用。
- ServletContextAware.setServletContext(): 运行时设置ServletContext，在普通bean初始化后调用，在InitializingBean.afterPropertiesSet之前调用，在 ApplicationContextAware 之后调用注：是在WebApplicationContext 运行时
- BeanPostProcessor.postProcessBeforeInitialization() : 将此BeanPostProcessor 应用于给定的新bean实例 在任何bean初始化回调方法(像是InitializingBean.afterPropertiesSet或者自定义的初始化方法）之前调用。这个bean将要准备填充属性的值。返回的bean示例可能被普通对象包装，默认实现返回是一个bean。
- BeanPostProcessor.postProcessAfterInitialization() : 将此BeanPostProcessor 应用于给定的新bean实例 在任何bean初始化回调方法(像是InitializingBean.afterPropertiesSet或者自定义的初始化方法)之后调用。这个bean将要准备填充属性的值。返回的bean示例可能被普通对象包装
- InitializingBean.afterPropertiesSet(): 被BeanFactory在设置所有bean属性之后调用(并且满足BeanFactory 和 ApplicationContextAware)。

————————————销毁————————————
在BeanFactory 关闭的时候，Bean的生命周期会调用如下方法:

- DestructionAwareBeanPostProcessor.postProcessBeforeDestruction(): 在销毁之前将此BeanPostProcessor 应用于给定的bean实例。能够调用自定义回调，像是DisposableBean 的销毁和自定义销毁方法，这个回调仅仅适用于工厂中的单例bean(包括内部bean)
- 实现了自定义的destory()方法

