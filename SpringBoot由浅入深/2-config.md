

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

 创建时间：2021/06/13 13:25 [字体](javascript:void(0);) [皮肤](javascript:void(0);)最后修改于： 2021/06/13 13:30

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


# 【补充】 Profile
不同环境不同配置