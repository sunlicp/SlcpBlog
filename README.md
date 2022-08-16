# SlcpBlog
🌍
*[English](/docs/README.en.md) ∙ [简体中文](README.md)*

基于 `vue`和`SpringBoot`，实现的博客系统

## 主要功能：
### 前端
1. 后台管理系统采用`Vue`开发。
2. 文章模块，支持文章内容、分类、标签对文章进行快速搜索，并且文本编辑支持`Markdown`。
3. 首页模块，支持`侧边栏`模块、`最新`文章以及`最热`文章展示。
4. 悠闲娱乐模块，支持`听歌`、`相册`等。
5. `在线算法`模块，可进行在线刷题以及题型分享功能等。
6. `评论`模块，所有文章都可进行评论，以及评论的邮件提醒。
7. 实现`留言板`和`友链`展示，以及`图库`(给爱摄影的小伙伴们)。
8. 集成了`动态诗歌`，生活不能没有诗意。
9. 还有...
### 后端
1. 后端是采用`Spring boot`实现。
2. 拥有 `Restful` 风格的 Api，并且后台接口采用`优美`代码写法。
3. 拥有`图床`功能，并且实现获取随机图片(当你在编写文章忘记赋予图片时,会给你温馨的添上)。
4. 拥有发送`QQ邮箱`功能，给你的爱人每天定时发送`彩虹屁`哈哈哈。
5. 拥有获取`随机诗词`功能，生活不能没有诗意。
6. 拥有`评论`功能，当然后端功能是实现的哦，不过我最终改为了插件～
7. 集成QQ登录以及支付宝支付功能，不过感觉后来用不上，代码就留在那了。
8. 支持`Redis`缓存(基于注解实现，代码值得一看哈，说不定就因此涨薪了)。
9. 还有...

## 基本环境

### windows环境需求
**后端**
~~~
JDK = 1.8
MySQL >= 5.7
Maven >= 3.0
Redis >= 6.0
IntelliJ IDEA
Xshell
MySQL图形化操作工具
~~~

**前端**

~~~
Node.js
VsCode 前端工具
VUE
~~~
> 温馨提示:
> 该前端选项是可选，若不整合vue，可直接跳过前端(基于vue的后台管理系统，是我以前玩的出勤异常项目，目前还未完全替换本项目的后台管理，主要是本项目的后台太丑了! 我也不知道当时大一的我是怎么想的🤡)

> 必选，后台已经完美整合vue~

- JDK = 1.8

  在官方网站上下载1.8版本中的最新版，下载完成后安装即可。

  下载地址：https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

  ```
  #环境变量配置(现在默认会自动配置好)
  略
  
  #打开CMD命令行工具，检查是否配置成功
  java -version
  ```

- Maven >= 3.0

  从Maven官方网站上下载最新版的压缩包。

  下载地址：https://maven.apache.org/download.cgi

  ```
  #环境变量配置
  略
  
  #打开CMD命令行工具，检查是否配置成功
  mvn -v
  ```

- Mysql >= 5.7(最新版即可)

  安装教程：https://www.runoob.com/mysql/mysql-install.html

  下载地址：https://dev.mysql.com/downloads/mysql/

  访问Mysql的可视化数据库开发工具： Navicat Premium      工具的安装教程可自行百度搜索。

- Redis

  下载地址：https://redis.io/download/
  
  安装教程：解压，配置(密码，默认无密码)
  ~~~bash
  #快捷启动命令.bat
  redis-server.exe redis.windows.conf
  pause
  ~~~

- IntelliJ IDEA

  安装教程：[https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123891067](https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123891067)
  
- Xshell

  安装教程：https://blog.csdn.net/Sunshine_Mr_Sun/article/details/124320972

- VSCode

  下载地址：https://code.visualstudio.com/

- Node.js

  安装教程：https://www.runoob.com/nodejs/nodejs-install-setup.html


## 运行

**后端启动步骤**

1. 创建数据库
2. 导入项目
3. 配置yaml
4. 启动成功

> 温馨提示：
>
> 1.sql文件已提供，只需执行即可。`myblog.sql`
>
> 2.将项目导入idea(本项目使用的工具是idea，如果你用的ecplise一样可以使用，毕竟百度无所不能)
>
> 3.启动之前记得修改连接数据库的配置
>
> 4.若控制台打印出访问路径，即OK



**前端启动步骤**（可选）

```bash
#打开windows命令行窗口，进入项目目录
cd 路径/topicUI

#强烈建议不要用直接使用 cnpm 安装，会有各种诡异的 bug，可以通过重新指定 registry 来解决 npm 安装速度慢的问题。
npm install --registry=https://registry.npm.taobao.org

#启动项目
npm run dev
```

打开浏览器，输入：http://localhost:81

>若能正确展示登录页面，并能成功登录，菜单及页面展示正常，则表明项目启动成功

## 服务器部署

> Linux部署肯定是先基于win操作的前提下获取jar以及dist，再进行操作的，若没有云服务器，here-[下载VMware](https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123206789)

**Linux环境需求**

~~~tex
docker
docker-compose
nginx
~~~

- docker

  安装教程：https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123206789

- nginx

  下载地址：http://nginx.org/download/

**部署步骤**

1.分别将前后端项目打包(jar、dist)

2.将dist丢到nginx里，并配置nginx

3.修改Dockerfile以及docker-compose.yml文件（文件已提供）

4.执行docker build -t myblog:1.0 .

5.docker images 查看镜像是否生成

6.docker-compose up (查看是否启动成功，成功后可后台启动  -d)

7.查看并登录

---

## 问题相关

有任何问题欢迎提Issue,或者将问题描述发送至我邮箱 `sun8880402@163.com`.我会尽快解答.推荐提交`Issues`方式.

---

## 致大家🙋‍♀️🙋‍♂️

如果本项目帮助到了您，请在[这里](https://github.com/sunlicp/SpringBootBlog/issues/2)留下您的网址，让更多的人看到。
您的回复将会是我继续更新维护下去的动力。

---


## 捐赠

如果您觉得本项目对您有所帮助，欢迎您请我喝杯咖啡，当然我更希望您三连足矣哦，您的支持是我最大的动力。

![image-20220816111802875](https://img.slcp.top/image-20220816111802875.png)

### 支付宝：

<div>    
<img src="https://img.slcp.top/zhifupay.jpg" width="150" height="150" />
</div>  



### 微信：

<div>    
<img src="https://img.slcp.top/wechatpay.jpg" width="150" height="150" />
</div>

---

## 更新说明
**2022.7.13**
后台管理系统，已整合vue，并且抛弃原有的界面。(因此，vue成了必不可少的环节了)

**2022.7.15**
前台整合thymeleaf

**2022.7.19**
前台样式整理

---
温馨提示：该项目仅供学习使用。未经本人允许请勿商用，并标明出处哦~
