# SpringMVC学习
## 认识org.springframework.web.servlet.DispatcherServlet
参考[DispatcherServlet](http://sishuok.com/forum/blogPost/list/5188.html)  
在web.xml中的配置
```xml
<servlet>
        <servlet-name>chapter2</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>chapter2</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```
load-on-startup：表示启动容器时初始化该Servlet；  
url-pattern：表示哪些请求交给Spring Web MVC处理， “/” 是用来定义默认servlet映射的。也可以如“*.html”表示拦截所有以html为扩展名的请求。    
 可选参数   | 解释 
------------- | ------------- 
contextClass  | 任意实现了WebApplicationContext接口的类。这个类会初始化该servlet所需要用到的上下文对旬。默认情况下，框架会使用一个XmlWebApplicationContext对象 
contextConfigLocation  | 一个指定了上下文配置路径的字符串，该值会被传入给contextClass所指定的上下文实例对象。文具字符串内可以包含多个字符串，字符串这间以逗号分隔，以此支持你进行多个上下文的配置。在多个上下文中重复定义的bean，以最后加载的bean定义为准 
namespace  | WebApplicationContext的命名空间。默认是[servlet-name]-servlet   
----------
1.SpringMVC环境的搭建
    1.1  在web.xml文件里进行配置。
    ```xml
     <servlet>
	  <servlet-name>dispatcher</servlet-name>
	  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	  <init-param>
	   <param-name>contextConfigLocation</param-name>
	   <param-value>classpath:/spring-mvc.xml</param-value>
	  </init-param>
	 </servlet>
	 <servlet-mapping>
	  <servlet-name>dispatcher</servlet-name>
	  <url-pattern>*.do</url-pattern>
	 </servlet-mapping>
    ```
2.在搭建环境过程中，我使用了如下的配置：  
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

# spring 与hessian的整合
在web.xml文件中配置hessian的过滤配置：