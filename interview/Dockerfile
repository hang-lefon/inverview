# 基础镜像
FROM registry.cn-beijing.aliyuncs.com/lefon/myopenjdk:8.6

# 设置工作目录
WORKDIR /opt
# 拷贝jar包到工作目录
COPY target/*.jar app.jar
RUN  ls
# 设置暴漏的端口
EXPOSE 8080
# 启动jar包
#ENTRYPOINT ["java","-jar","app.jar"]

CMD java ${JAVA_TOOL_OPTIONS} -jar app.jar