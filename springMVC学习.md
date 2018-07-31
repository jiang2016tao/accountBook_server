# SpringMVC学习
在搭建环境过程中，我使用了如下的配置：  
```xml
<!--避免IE执行AJAX时，返回JSON出现下载文件 -->
	 <bean id="mappingJacksonHttpMessageConverter"
	  class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
	  <property name="supportedMediaTypes">
	   <list>
	    <value>text/html;charset=UTF-8</value>
	   </list>
	  </property>
	 </bean>
	 <!-- 启动SpringMVC的注解功能，完成请求和注解POJO的映射 -->
	 <bean
	  class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	  <property name="messageConverters">
	   <list>
	    <ref bean="mappingJacksonHttpMessageConverter" /> <!-- JSON转换器 -->
	   </list>
	  </property>
	 </bean>
```
在访问我写的接口的Url的时候页面报错了（如下图）：  
![image](./wikiImg/spring_mvc_1.PNG)
原来是jar包没有引用，需要引用一下jar包：  
jackson-core-asl.jar和jackson-mapper-asl.jar，pom文件格式如下：  
```xml
<dependency>
	   <groupId>org.codehaus.jackson</groupId>
	   <artifactId>jackson-core-asl</artifactId>
	   <version>1.9.13</version>
	  </dependency>
	  <dependency>
	   <groupId>org.codehaus.jackson</groupId>
	   <artifactId>jackson-mapper-asl</artifactId>
	   <version>1.9.13</version>
	  </dependency>
``` 