# 秒杀系统
## 依赖

```shell
## Java 8
java -version

## 设置 Java 的环境，供 Maven 用
export JAVA_HOME="$(/usr/libexec/java_home -v 1.8)"

## maven
mvn -version
```

## 快速开始


```shell
# maven 运行
## mavne 命令
mvn spring-boot:run -Dspring.profiles.active=dev


# IDEA 运行
## 需要设置运行时的参数
## 运行 Application.java 中的 main 方法

# 编译运行
mvn package
java -jar xxxx.jar --spring.profiles.active=dev

```