


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
