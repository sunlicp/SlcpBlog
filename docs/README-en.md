# SlcpBlog
🌍
*[English](README-en.md) ∙ [简体中文](README.md)*
A blog system based on`SpringBoot` and `vue`

## Main Features

- Articles, categories, labels, music addition, deletion, editing, etc.
- Support the function of listening to songs.
- Support photo album function.
- Support article search.
- Sidebar function, latest articles, hottest articles, labels, etc.
- Article editing support`Markdown`
- It supports`Redis`caching and is implemented based on annotations.
- Integrated drawing bed function.
- Complete comment function, including reply comments and email reminders of comments.
- Integrate third-party QQ login.
- Back office integration Vue (recently, semi-finished products).

## Basic environment

### Windows Environmental requirements

**back-end**

~~~
JDK = 1.8
MySQL >= 5.7
Maven >= 3.0
Redis >= 6.0
IntelliJ IDEA
Xshell
MySQL图形化操作工具
~~~

**front end**（Optional）

~~~
Node.js
VsCode 前端工具
VUE
~~~

> reminder:
>
> This front-end option is optional. If you don't integrate Vue, you can directly skip the front-end (the background management system based on Vue, which is an abnormal attendance project I used to play, has not completely replaced the background management of this project at present, mainly because the background of this project is too ugly! I don't know what I thought when I was a freshman at that time 🤡)


- JDK = 1.8

  Download the latest version of version 1.8 on the official website and install it after downloading.

  Download ：https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html

  ```
  #环境变量配置(现在默认会自动配置好)
  略
  
  #打开CMD命令行工具，检查是否配置成功
  java -version
  ```

- Maven >= 3.0

  Download the latest version of the compressed package from `Maven` official website.

  Download ：https://maven.apache.org/download.cgi

  ```
  #环境变量配置
  略
  
  #打开CMD命令行工具，检查是否配置成功
  mvn -v
  ```

- Mysql >= 5.7(new)

  Installation tutorial：https://www.runoob.com/mysql/mysql-install.html

  Download ：https://dev.mysql.com/downloads/mysql/

  > Visit MySQL's visual database development tool: the installation tutorial of `Navicat premium` tool can be searched by Baidu.

- Redis

  Download ：https://redis.io/download/

  Installation tutorial：解压，配置(密码，默认无密码)

  ~~~bash
  #快捷启动命令.bat
  redis-server.exe redis.windows.conf
  pause
  ~~~

- IntelliJ IDEA

  Installation tutorial：[https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123891067](https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123891067)

- Xshell

  Installation tutorial：https://blog.csdn.net/Sunshine_Mr_Sun/article/details/124320972

- VSCode

  Download ：https://code.visualstudio.com/

- Node.js

  Installation tutorial：https://www.runoob.com/nodejs/nodejs-install-setup.html


## RUN

**Back end startup steps**

1. Create DB
2. Import project
3. Configure yaml
4. Successful startup

> reminder:
>
> 1. The SQL file has been provided and only needs to be executed` myblog. sql`
>
> 2. Import the project into idea (the tool used in this project is idea. If you use ecplise, you can use it as well. After all, Baidu can't do anything.)
>
> 3. Remember to modify the configuration of the connection database before starting
>
> 4. If the console prints the access path, it is OK



**Front end startup steps * * (optional)

```bash
#打开windows命令行窗口，进入项目目录
cd 路径/topicUI

#强烈建议不要用直接使用 cnpm 安装，会有各种诡异的 bug，可以通过重新指定 registry 来解决 npm 安装速度慢的问题。
npm install --registry=https://registry.npm.taobao.org

#启动项目
npm run dev
```

Open the browser and enter: http://localhost:8080 (the default account / password has two 'admin/1')

>If the login page can be displayed correctly and successfully, and the menu and page display are normal, it indicates that the project is successfully launched

## Server deployment

> Linux deployment must first obtain jars and dists based on win operation, and then operate. If there is no ECs, here-[download VMware](https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123206789)

**Linux environment requirements**

~~~tex
docker
docker-compose
nginx
~~~

- docker

  Installation tutorial：https://blog.csdn.net/Sunshine_Mr_Sun/article/details/123206789

- nginx

  Download：http://nginx.org/download/



**Deployment steps**

1. Package the front and rear end projects respectively (jar, dist)



2. Put dist into nginx and configure nginx

3. Modify dockerfile and docker compose YML file (file provided)

4. Execute docker build -t myblog:1.0

5. Docker images check whether the image is generated

6. Docker compose up (check whether it is started successfully. After that, start it in the background -d)

7. View and login



## Problem related

If you have any questions, please mention issue, or send the problem description to my email` 1206128610@qq.com `. I will answer as soon as possible It is recommended to submit issue

---



## To all of you 🙋‍♀️🙋‍♂️

If this project helps you, please [here](https://github.com/ShawSun1/SpringBootBlog/issues/2)Leave your website for more people to see.

Your reply will be the driving force for me to continue updating and maintaining.

