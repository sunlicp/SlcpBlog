# SlcpBlog
🌍
*[English](/docs/README-en.md) ∙ [简体中文](README.md)*


基于`SpringBoot` 和 `vue`，实现的博客系统

## 主要功能：

- 文章，分类，标签，音乐的添加，删除，编辑等。
- 支持文章搜索、听歌、相册功能。
- 侧边栏功能，最新文章，最热文章，标签等。
- 文章编辑支持`Markdown`
- 支持`Redis`缓存，基于注解实现。
- 集成了图床功能。
- 完整的评论功能，包括发表回复评论，以及评论的邮件提醒。
- 集成第三方QQ登录。
- 后台整合vue(最近在弄，半成品)。

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

**前端**（可选）

~~~
Node.js
VsCode 前端工具
VUE
~~~
> 温馨提示:
> 该前端选项是可选，若不整合vue，可直接跳过前端(基于vue的后台管理系统，是我以前玩的出勤异常项目，目前还未完全替换本项目的后台管理，主要是本项目的后台太丑了! 我也不知道当时大一的我是怎么想的🤡)


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

## 问题相关

有任何问题欢迎提Issue,或者将问题描述发送至我邮箱 `1206128610@qq.com`.我会尽快解答.推荐提交Issue方式.

---

## 致大家🙋‍♀️🙋‍♂️

如果本项目帮助到了你，请在[这里](https://github.com/ShawSun1/SpringBootBlog/issues/2)留下你的网址，让更多的人看到。
您的回复将会是我继续更新维护下去的动力。


## 捐赠

如果您觉得本项目对您有所帮助，欢迎您请我喝杯咖啡，您的支持是我最大的动力，您可以扫描下方二维码为我付款，谢谢。

### 支付宝：

<div>    
<img src="https://img.slcp.top/zhifupay.jpg" width="150" height="150" />
</div>  



### 微信：

<div>    
<img src="https://img.slcp.top/wechatpay.jpg" width="150" height="150" />
</div>

---

## 交流群

QQ交流群：648742271 [点击加入](https://jq.qq.com/?_wv=1027&k=45H977HM)

备注：slcp，邀您加入群聊