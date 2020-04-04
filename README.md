# myproject2
2020.3.13

在Linux环境上运行。

配置好java环境，tomcat环境，mysql环境，docker环境，docker环境拉取 hzucc/alpine-oj 镜像。

然后确保装好gcc，g++，jdk,jre,python3,golang等编译器。

拉取代码，运行项目即可。
__________________________________________________________________________________




数据库文件myproject2.sql在项目里，【登录账号密码】。

这是一个自己开发的OJ网站。

优点：运行代码安全，运行时间/运行内存计算精确，支持多语言且易扩展。界面使用layui简洁大方，色调协调。

一个兴趣使然的OJ系统，主要用于提供题库管理，判题等OJ业务。
（比如也可以用于在线笔试系统，或者是用于面试的机试系统）
提供一个对程序设计题目的题库管理子系统。
提供一个用于评测题目的判题子系统。
提供一个简单的用户管理系统（Spring Security实现权限管理）。
支持多用户实时评测，多线程评测代码（队列实现）。
支持C/C++，java，python3，go等多语言提交代码，且易扩展新语言。
使用layui做前端ui，界面简洁大方，色调协调搭配，AJAX分离前后端。
使用MVC设计模式，springboot + mysql + mybatis等Web端技术实现后端。
使用Linux作为服务器，用户代码的运行时间/运行内存计算方法精确，使用Linux底层库或系统调用实现。
评测安全方面，使用docker自搭建以alpine为基础镜像的镜像来构造沙盒评测环境，保证用户代码运行于封闭docker容器环境中，同时对docker容器和外界系统进行交互的cpu，内存，io资源进行限制，来保证外部系统安全。
使用工厂模式管理docker容器的生产消费，将docker容器的创建销毁开销剥离于判题流程。

OJ判题核心详细描述链接：https://blog.csdn.net/qq_36335313/article/details/102675037
DockerFile地址：https://github.com/hzucc/hzucc_alpine_oj
hzucc/alpine-oj镜像源码：https://github.com/hzucc/hzucc_alpine_oj/tree/master/src




