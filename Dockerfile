# 基础镜像使用java
FROM java:8
# 作者
MAINTAINER slcp
# VOLUME 指定临时文件目录为/tmp，在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为myblog.jar
ADD myblog.jar myblog.jar
# 运行jar包
RUN bash -c 'touch /myblog.jar'
ENTRYPOINT ["java","-jar","/myblog.jar"]
#暴露6001端口作为微服务
EXPOSE 80
