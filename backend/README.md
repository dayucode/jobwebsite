### 目录结构

```
PEAR-ADMIN-PRO\BACKEND
│  pom.xml————————maven  pom.xml配置文件
│  
├─sql
│      pear-admin-pro.sql————————数据库文件
│      
└─src
    └─main
        ├─java
        │  └─com
        │      └─pearadmin
        │          └─pro
        │              │  EntranceApplication.java————————项目启动类
        │              │  
        │              ├─common————————公共包
        │              │  ├─aop————————aop定义实现
        │              │  │  │  ExcelAspect.java————————Excel实现AOP
        │              │  │  │  LogAspect.java————————Log 实现 Aop
        │              │  │  │  
        │              │  │  ├─annotation————————自定义注解包
        │              │  │  │      Excel.java
        │              │  │  │      Log.java
        │              │  │  │      
        │              │  │  └─enums————————自定义枚举包
        │              │  │          Action.java
        │              │  │          Model.java
        │              │  │          
        │              │  ├─cache————————缓存定义包
        │              │  │  │  BaseCache.java
        │              │  │  │  
        │              │  │  └─impl————————BaseCache的具体实现
        │              │  │          AllocationCache.java————————全局配置缓存
        │              │  │          DictionaryCache.java————————全局字典缓存
        │              │  │          
        │              │  ├─configure————————公共配置包
        │              │  │      CoreConfig.java————————核心配置
        │              │  │      QuartzConfig.java————————定时任务配置
        │              │  │      SecurityConfig.java————————安全核心配置类
        │              │  │      SwaggerConfig.java————————swagger文档配置
        │              │  │      
        │              │  ├─constant————————公共常量包
        │              │  │      CacheNameConstant.java————————Redis 缓存区分前缀
        │              │  │      ControllerConstant.java————————Controller 静态配置（API前缀）
        │              │  │      GenerateConstant.java————————Generate 静态配置（代码生成器，暂时空）
        │              │  │      SecurityConstant.java————————Security 静态配置
        │              │  │      SystemConstant.java————————System 静态配置
        │              │  │      TenantConstant.java————————Tenant 多租户静态配置
        │              │  │      TokenConstant.java————————Json Web Token 静态配置
        │              │  │      
        │              │  ├─context
        │              │  │      BaseContext.java————————base上下文
        │              │  │      BeanContext.java————————Bean上下文（Bean操作）
        │              │  │      DataContext.java————————数据源上下文（数据源操作）
        │              │  │      UserContext.java————————用户上下文（登录用户信息与操作）
        │              │  │      
        │              │  ├─quartz————————定时任务相关
        │              │  │  │  QuartzExecute.java
        │              │  │  │  QuartzService.java
        │              │  │  │  QuartzStarted.java
        │              │  │  │  
        │              │  │  └─base
        │              │  │          BaseQuartz.java————————定时任务原始接口
        │              │  │          
        │              │  ├─secure————————Security安全框架相关
        │              │  │  ├─captcha————————验证码相关
        │              │  │  │      SecureCaptcha.java
        │              │  │  │      SecureCaptchaRest.java
        │              │  │  │      SecureCaptchaService.java
        │              │  │  │      SecureCaptchaSupport.java
        │              │  │  │      
        │              │  │  ├─process————————登录权限相关
        │              │  │  │      SecureLoginFailureHandler.java
        │              │  │  │      SecureLoginSuccessHandler.java
        │              │  │  │      SecureLogoutSuccessHandler.java
        │              │  │  │      SecureNoAuthenticationHandler.java
        │              │  │  │      SecureNoPermissionHandler.java
        │              │  │  │      
        │              │  │  ├─services————————用户实体
        │              │  │  │      SecureUser.java
        │              │  │  │      SecureUserService.java
        │              │  │  │      
        │              │  │  └─uutoken————————token验证相关
        │              │  │          SecureUserToken.java
        │              │  │          SecureUserTokenService.java
        │              │  │          SecureUserTokenSupport.java
        │              │  │          
        │              │  ├─tools————————公共工具
        │              │  │  ├─core
        │              │  │  │      ExcelUtil.java
        │              │  │  │      MathUtil.java
        │              │  │  │      PatternUtil.java
        │              │  │  │      ServletUtil.java
        │              │  │  │      StringUtil.java
        │              │  │  │      TokenUtil.java
        │              │  │  │      
        │              │  │  └─support
        │              │  │      └─server————————服务器信息相关
        │              │  │          │  Server.java
        │              │  │          │  
        │              │  │          └─server
        │              │  │                  Cpu.java
        │              │  │                  Disk.java
        │              │  │                  Jvm.java
        │              │  │                  Mem.java
        │              │  │                  Sys.java
        │              │  │                  
        │              │  └─web
        │              │      ├─base
        │              │      │  │  BaseController.java————————基础controller
        │              │      │  │  BaseService.java————————基础service
        │              │      │  │  
        │              │      │  ├─domain
        │              │      │  │      BaseDomain.java————————基础domain
        │              │      │  │      TreeDomain.java————————基础树domain
        │              │      │  │      
        │              │      │  └─page
        │              │      │          Pageable.java————————分页工具类
        │              │      │          PageRequest.java————————分页参数
        │              │      │          PageResponse.java————————分页结果
        │              │      │          
        │              │      ├─domain
        │              │      │      Result.java————————Ajax 响 应 实 体
        │              │      │      ResultCode.java————————Ajax 响应类型
        │              │      │      ResultController.java————————Ajax 响 应 处 理
        │              │      │      
        │              │      ├─exception
        │              │      │  │  GlobalExceptionHandler.java————————全局异常
        │              │      │  │  ValidationExceptionHandler.java
        │              │      │  │  
        │              │      │  ├─auth
        │              │      │  │  ├─captcha————————验证码异常
        │              │      │  │  │      CaptchaException.java
        │              │      │  │  │      CaptchaExpiredException.java
        │              │      │  │  │      CaptchaValidationException.java
        │              │      │  │  │      
        │              │      │  │  └─token————————token异常
        │              │      │  │          TokenException.java
        │              │      │  │          TokenExpiredException.java
        │              │      │  │          TokenValidationException.java
        │              │      │  │          
        │              │      │  └─base
        │              │      │          BusinessException.java————————业务异常
        │              │      │          
        │              │      └─interceptor————————各种拦截器
        │              │          │  BoundSqlSqlSource.java
        │              │          │  DataScopeInterceptor.java
        │              │          │  DomainInterceptor.java
        │              │          │  InterceptorConfiguration.java
        │              │          │  InvocationHandler.java
        │              │          │  SubmissionInterceptor.java
        │              │          │  TenantInterceptor.java
        │              │          │  
        │              │          ├─annotation
        │              │          │      DataScope.java
        │              │          │      DataScopeRule.java
        │              │          │      Submission.java
        │              │          │      TenantIgnore.java
        │              │          │      
        │              │          └─enums
        │              │                  Scope.java
        │              │                  
        │              └─modules
        │                  ├─job————————定时任务
        │                  │              
        │                  ├─not————————系统公告
        │                  │              
        │                  ├─oss————————oss文件
        │                  │              
        │                  ├─sns————————短信
        │                  │              
        │                  └─sys————————系统业务
        │                      │      SysConfig————————系统配置
        │                      │      SysDataSource————————多数据源
        │                      │      SysDept————————部门
        │                      │      SysDict————————数据字段类型
        │                      │      SysDictData————————数据字典
        │                      │      SysLog————————系统日志
        │                      │      SysMail————————邮箱发送
        │                      │      SysNotice————————消息实体
        │                      │      SysPost————————岗位
        │                      │      SysPower————————权限
        │                      │      SysRole————————角色实体
        │                      │      SysRoleDept————————角色部门实体
        │                      │      SysRolePower————————角色权限
        │                      │      SysTenant————————租户相关
        │                      │      SysTenantPower————————租户权限实体
        │                      │      SysUser————————用户
        │                      │      SysUserRole————————用户角色
        │                                  
        └─resources
                application-dev.yml————————开发配置
                application-pro.yml————————上线配置
                application.yml————————基础配置
                banner.txt————————项目启动banner
```

