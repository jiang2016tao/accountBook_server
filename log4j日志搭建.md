# log4j日志搭建  
在项目中日志的记录是我们定位和维护线上问题的有利工具，是每个项目的必需。  

- 引入日志所需的依赖包。  
```xml
<dependency>
	    <groupId>org.slf4j</groupId>
	    <artifactId>slf4j-log4j12</artifactId>
	    <version>1.7.25</version>
	</dependency>
```
- 需要log4j.properties的配置文件  
[配置文件参看](./src/main/resources/log4j.properties)  
## 配置文件的部分说明  
log4j.rootLogger=DEBUG,stdout,D
> 日志打印的级别配置，这里是DEBUG。即只要是DEBUG级别或比DEBUG级别高的日志都可以打印。后面的D是append的部分。   
> D代表的是按照这一部分配置输出日志（这只是配置文件的部分）
> ```xml
		#输出至debug日志文件
		log4j.appender.D=org.apache.log4j.DailyRollingFileAppender
		log4j.appender.D.File=../logs/log4j/debug.log
		log4j.appender.D.Append=true
		log4j.appender.D.Threshold=DEBUG
		log4j.appender.D.layout=org.apache.log4j.PatternLayout
		log4j.appender.D.layout.ConversionPattern=%d{yyyy-MM-dd HH:mm:ss,SSS} [%t] [%c] [%p] - %m%n
> 这里有指定的输出格式和输出的目录文件。   

- 在web.xml文件里加载配置文件   
```xml
<context-param>
	<param-name>log4jConfigLocation</param-name>
	<param-value>/WEB-INF/classes/log4j.properties</param-value>  	
  </context-param>
  <listener>
  	<listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
  </listener>
```

这样日志基本就搭建了，但是这样你会发现debug,info,warn,error级别的日志都打印在一个文件里了，并没有实现我预期的，日志文件根据日志的级别分别写入
不同的文件里。  
实现预期的希望就需要修改上面的部分代码。  
> 修改配置，不同级别对应不同的输出日志文件。  
> ```xml log4j.rootLogger=DEBUG,D,I,W,E```
> 重写配置文件里配置的DailyRollingFileAppender类，log4j.appender.I=org.apache.log4j.DailyRollingFileAppender  

