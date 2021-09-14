
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