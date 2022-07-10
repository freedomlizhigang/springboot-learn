# springboot-learn
学习springboot
0. spring
   1. 框架基础IOC&DI，bean生命周期
   2. SpringMVC如何快速上手web项目
   3. AOP切片实现Filter：可实现用户登录检查，权限检查，日志记录，异常处理，统一返回值等操作
   4. mybatis数据库操作
   5. redis操作
   6. 文件上传，本地+阿里云
   7. 异步处理，定时任务，队列
   8. spring security等其他框架
1. springboot(web/test/configuration-processor/jdbc/mysql-connector/security)
    1. 统一返回与异常处理：https://segmentfault.com/a/1190000020052492 ， https://blog.csdn.net/ZHWang102107/article/details/82931584
2. mybatis,mybatis plus,数据库操作，代码生成
    0. 整合：https://segmentfault.com/a/1190000017211657#22-service-%E5%B1%82
    1. mybatis plus：https://mp.baomidou.com/guide/#%E7%89%B9%E6%80%A7
    2. 代码生成器：https://juejin.im/post/5db694e3e51d452a2e25ba45
3. lombok:数据注解等功能，简化get/set
    1. 工具包：https://www.hutool.cn/docs/#/
    2. lombok简化entity信息
4. 开始rbac
    1. menu，权限菜单，基本结束
        1. 数据校验：https://juejin.im/post/5dafcdcb6fb9a04ddc6258f9
        2. 安全认证：https://segmentfault.com/a/1190000011450413 ， shiro：https://www.xncoding.com/2017/07/07/spring/sb-shiro.html
        3. 树形菜单：https://blog.csdn.net/LDY1016/article/details/85784001
    2. section，部门管理，基本结束
    3. role，权限分配，基本结束
    4. admin，部门、角色分配，大部分完成
    5. log，aop切面，队列(RabbitMQ太麻烦，用redis订阅模型处理)写入，redis服务不能停~，MongoDB数据结构
        0. aop切面：https://juejin.im/post/5be0dd17e51d45304c3c7a75
        1. redis,jedis连接池：https://blog.csdn.net/dingse/article/details/80572783
        2. redis 队列：https://juejin.im/post/5d463bc1e51d4561c75f27a9
        3. redis 集群：https://segmentfault.com/a/1190000016370939
        4. redis 延时队列：https://blog.csdn.net/qq330983778/article/details/99341671
        5. MongoDB：https://juejin.im/post/5dc3ad1cf265da4d26043fb5
    6. jwt，Redis + aop权限验证，参数签名&加密
        0. shiro 单个已经完成，参数RSA加密解密，多个shiro
        1. shiro：https://www.cnblogs.com/ealenxie/p/10610741.html ，https://www.xncoding.com/2017/07/09/spring/sb-jwt.html ，多身份（user/admin）https://blog.csdn.net/xiangwanpeng/article/details/54802509
    7. 数据迁移，定时备份
        1. flyway 迁移：https://blog.csdn.net/feinifi/article/details/103010627
        2. 定时备份：https://my.oschina.net/u/4345490/blog/4183796
    8. 文件上传，本地+阿里云oss
        1. 目录管理，文件上传：https://www.hutool.cn/docs/#/http/Http%E5%AE%A2%E6%88%B7%E7%AB%AF%E5%B7%A5%E5%85%B7%E7%B1%BB-HttpUtil
        1. 普通处理上传：https://juejin.im/post/5c9e57e2f265da307a160328
        2. base64图片：https://juejin.im/post/5caef6b2e51d456e51614a66
        3. 阿里云oss：https://www.cnblogs.com/smallSevens/p/11728749.html ， https://help.aliyun.com/document_detail/32008.html?spm=a2c4g.11174283.6.769.5aaf7da2Sz5cVj
    9. 阿里云短信服务
        1. 短信服务：https://blog.csdn.net/qq_15071263/article/details/80526226
    10. 微信SDK，登录+支付
        1. sdk：https://github.com/Wechat-Group/WxJava