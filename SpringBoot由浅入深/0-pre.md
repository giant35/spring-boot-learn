# 说明
网上找的培训材料整理而来  

[[_TOC_]]


---
# D1

## [第一篇 基本项目创建及常见注解解析](1-start.md)
* 一、为什么选择SpringBoot
* 二、使用SpringBoot快速搭建一个单体架构
* 三、SpringBoot项目搭建说明&starter机制
* 【补充】演示 1-hello
  1. 创建过程
  1. 项目分析 @GetMapping @RestController
  1. devtools
  1. debugger thread stack
  1. 打包 
* 【补充】演示 1-todo
    1. 更多注解
    1. 单元测试

## [第二篇 配置](2-config.md)
* 四、SpringBoot常见配置说明
* 五、SpringBoot中yml配置说明
* 六、自定义配置&@Value注入属性
* 七、@ConfigurationProperties注入属性
* 【补充】 Profile

!!! D1问卷


----
# D2

## [第三篇 自动生成 getter setter 与日志](3-lombok-log.md)
* 八、SpringBoot中使用lombok&注意事项
* 【删】 03、番外 - 为什么要放弃lombok
* 九、SpringBoot日志存储路径和设置日志格式
* 十、SpringBoot中的异步处理框架@Async

## [第四篇 swagger 配置](4-swagger.md)
* 【删】 十一、SpringBoot和在线文档Swagger的整合
* 【补充】新的 Swagger 整合方式 https://github.com/springfox/springfox


## [第五篇 统一处理返回与验证](5-advise-validate.md)
* 十二、SpringBoot统一接口返回的标准格式R.java
* 十三、SpringBoot封装全局异常处理器
* 十四、SpringBoot的参数校验器-Validator
* 十五、SpringBoot-Assert和自定义ValidatorUtils

----
# D3

## [第六篇 mybatis 访问数据库](6-mybatis.md)
* 十六、SpringBoot快速整合Mybatis&MybatisPlus

## [第七篇 事件](7-event.md)
* 二十五、Spring实战系列（一）-监听器模式开发



-----
# 删减的部分
* 十七、SpringBootApplication启动原理
* 十八、剖析@SpringBootConfiguration源码
* 十九、剖析@ComponentScan源码
* 二十、剖析@EnableAutoConfiguration注解
* 二十一、剖析解密spring.factories原理
* 二十二、自定义实现Spring.factories文件&自定义starter机制
* 二十三、SpringBoot中的@Primary 和 @Qualifier
* 二十四、SpringBoot中初始化加载的四种方式
* 二十六、Spring实战系列（二）-有关属性配置文件的加载和使用
* 二十七、Spring实战系列（三）-BeanPostProcessor的妙用
* 二十八、Spring实战系列（四）-动态注入接口Bean
* 二十九、Spring实战系列（五） - Spring中的InitializingBean接口的使用
* 三十、Spring中Bean 的完整生命周期