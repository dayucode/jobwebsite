<h2>招聘网代码解析</h2>

---

系统整体难点解析可概括为 

`一、资源权限认证  二、前后端分离   三、数据库及业务操作  四、简历生成`  

---

**一：资源权限认证**

本系统采用springboot整合SpringSecurity和JWT完成认证授权

**面向系统解释**：细分角色和权限，并将用户、角色、权限和资源均采用数据库存储，并且自定义过滤器，代替原有的FilterSecurityInterceptor过滤器

**面向用户解释：**前端发起的任意请求访问后端受到SpringSecurity安全框架的制约，每一个请求都被该框架拦截，拦截后操作有两种

1. 对请求放行（针对一般资源类似css、js、图片等静态资源及初始访问接口获取验证码/登录/注册等接口）
2. 对请求拦截，针对前端访问后端业务接口(即CRDU及其他面向系统的业务操作)

![image](https://github.com/dayucode/jobwebsite/blob/master/pic/code1.png)

具体的认证流程为（登录或者注册统一称为登录）

SpringSecurity拦截器：

![image](https://github.com/dayucode/jobwebsite/blob/master/pic/code2.png)

（1）进入登录页面自发请求后端服务器获取验证码，登录请求带上验证码请求后端，后端通过判断验证码是否存在判断是否进入验证码拦截器。进入验证码拦截器后通过用户名及密码验证用户是否存在，若存在生成token(jwt生成由uuid加自定义字符组成)返回给用户并存储到redis数据库。jwt了解（https://www.jianshu.com/p/5fb8918a6b49）

![image](https://github.com/dayucode/jobwebsite/blob/master/pic/code3.png)

（2）用户在访问其他业务接口时带上token，后端请求时通过判断请求是否带token进入登录拦截器，若存在，通过存储在redis数据库的token进行匹配验证token是否合法（是否存在及是否失效）验证成功后放行接口进行业务操作。

![image](https://github.com/dayucode/jobwebsite/blob/master/pic/code4.png)

注意的是每次token验证完后都会为系统注入Authentication（封装用户权限及基本信息）对象，以供开发者在全局随时随地获取当前请求用户信息

 **二、前后端分离** 

所谓前后端分离即相较传统的前后端一体大致区别有两点1、前后端分离开，后端服务器侧重点趋向于后端数据+业务，不过度关心前端页面如何展示。同时更加利在团队开发中前后端职责分离。2、前后端分离更加有利于数据的保密措施。

桥接：前后端的桥接通过标题（一）中生成的token。前端通过带token的访问方式进行资源权限认证后操作后端接口。

**三、数据库及业务操作** 

本系统采用mybatis动态操作数据库。

结合mybatis-plus-Service和mybatis-plus-Mapper

mybatis-plus 提供两种包含预定义增删改查操作的接口：

com.baomidou.mybatisplus.core.mapper.BaseMapper
com.baomidou.mybatisplus.extension.service.IService
Mybatis-plus提供了2个接口1个类：

从分层角度来解释

1、BaseMapper是DAO层的CRUD封装，而IService是业务业务逻辑层的CRUD封装，所以多了批量增、删、改的操作封装；

 2、IService是对BaseMapper的扩展，从BaseMapper、IService、ServiceImpl三者的类关系可以看出；
 此外，还有一个原因，就是IService和BaseMapper提供的是两种实现方式：
 如果继承BaseMapper，则不需要去实现其内部方法，依靠mybatis的动态代理即可实现CRUD操作；

总结：这样选择的优点，可以更加方便的操作数据库，根据不同的需求用不同的操作方式的数操作据库。更加方便和高效。

**四、简历生成** 

简历生成的流程依次为

前端填写简历信息-->点击生成简历-->后端查找简历信息+简历模板-->生成docx文件-->转化成pdf文件-->点击预览-->查看后端生成的pdf简历

1、通过调用github上的开源工具包 poi-tl（https://github.com/Sayi/poi-tl）生成docx，具体的流程就是查询简历信息到实体中，将实体set到工具包中，设置生成文档路径。

![image](https://github.com/dayucode/jobwebsite/blob/master/pic/code5.png)

2、找到生成的doxc简历文件，设置生成pdf路径，将docx转化为pdf文件并保存。

![image](https://github.com/dayucode/jobwebsite/blob/master/pic/code6.png)

注意的是：生成的简历命名方式  resume_+'用户的userId'+docx/pdf,用户查看简历也是通过拼接指定路径（pdf生成路径）+自己的id+文件后缀查看简历文件

