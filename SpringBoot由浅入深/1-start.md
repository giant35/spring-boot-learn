# 一、为什么选择SpringBoot

## 01、目标

了解和掌握springboto项目

## 02、概述

SpringBoot是随着spring4.0诞生的，它于2014年4月，发布了SpringBoot1.0.0。
SpringBoot是一个内嵌Web容器（tomcat/jetty）的可执行程序(jar)的框架。
你在开发web应用程序的时候，不需要将项目打成war包部署到web容器中。而是作为一个可执行的程序jar即可。
启动的时候把web服务器配置好，加载起来即可运行。

<del>

### 传统的spring项目存在的问题

- 大量的xml文件，配置相当繁琐
- 整合第三方框架的配置相当复杂
- 低效的开发效率和部署效率等问题
- 依赖外部的web服务器
- 日志管理需要依赖
- 一堆的依赖在maven中pom.xml中

</del>

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
【补充】https://spring.io/guides/gs/rest-service/

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

