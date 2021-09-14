

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

