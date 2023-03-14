# Plan & Record

# TODO

- [ ] httpClient
- [ ] 如何携带 csrf token
- [ ] 服务端通信细节：将请求体绑定到对象的过程
- [ ] 其他需求
- [ ] Unit Test

# 20230314

完成了最基本的用户表和注册登录模块，使用 Spring Security 和基于 `UserDetailsService` 的用户存储。对已有的各代码文件中的关键点做一些记录。

## Domain & Data

`BlogUser` 类是领域类，注解 `@Entity` 使 JPA 将此类作为一个数据表进行持久化。`@Data` 自动生成属性方法，指定生成一个私有的无参构造函数和一个所有域的构造函数。这是因为 JPA 实体类需要一个无参构造函数，而其他模块中需要使用有参构造函数构造此类的对象。

`BlogUserRepo` 声明了 `getByUsername` 方法，是提供给 `UserDetailsService` 使用的。它只是一个接口，Spring 将自动生成它的实现并注入 IOC 容器，所以其他模块可以直接获取这个类型的 Bean。

## Security

定义一个实现 `UserDetailsService` 接口的类 `BlogUserUserDetailsService`，此类借助 `BlogUserRepo` 覆盖所需的方法。用 `@Service` 标注，将注入 IOC。

`BlogSecurityConfig` 作为组件式的安全配置。其中 `getAuthenticationManager()` 方法将自动获取 IOC 中的 UserDetailsService 和 PasswordEncoder Bean 进行配置。

当显式配置 `HttpSecurity` 时，即使使用默认登录页也必须写出 `formLogin(Customizer.withDefaults())`。Security 默认启用 csrf 防护，这意味着 POST 等请求必须携带 csrf token（否则会出现 403 forbidden 错误），如果在测试时不希望使用 csrf token，则此处应该禁用。

## Controller

为了实现用户注册，定义一个 RestController，接受 POST 请求，使用请求中的数据创建 BlogUser，然后插入数据库。在开发过程中发现，如果直接使用 User 作为客户端和服务端通信时，服务端直接绑定的对象，则 User 无法成功创建。解决办法是定义一个专门用于通信的类 `BlogUserForm`，用 lombok 配置一个无参的构造函数和一个全部参数的构造函数。服务端的 Controller 将请求体中的数据绑定到这个简单类的对象中，然后再创建真正的 User。

为了模拟客户端注册新用户，定义一个 RestController 接受 GET 请求，获取 GET 请求的参数创建 JSON 对象作为请求体，与请求头一起组建 POST 请求，然后发送给上边的 API。客户端不应该直接使用服务端中定义的内容，所以此 GET 请求响应体类型设为 String。

参考：[RestTemplate Post Request with JSON | Baeldung](https://www.baeldung.com/spring-resttemplate-post-json) 

# 20230311

Firstly, need to create a table for user:

+ Enable data jpa configuration and schema
+ Data jpa rest controller
+ Security
