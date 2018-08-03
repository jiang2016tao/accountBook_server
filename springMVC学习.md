# SpringMVCѧϰ
## ��ʶorg.springframework.web.servlet.DispatcherServlet
�ο�[DispatcherServlet](http://sishuok.com/forum/blogPost/list/5188.html)  
��web.xml�е�����
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
load-on-startup����ʾ��������ʱ��ʼ����Servlet��  
url-pattern����ʾ��Щ���󽻸�Spring Web MVC���� ��/�� ����������Ĭ��servletӳ��ġ�Ҳ�����硰*.html����ʾ����������htmlΪ��չ��������    
 ��ѡ����   | ���� 
------------- | ------------- 
contextClass  | ����ʵ����WebApplicationContext�ӿڵ��ࡣ�������ʼ����servlet����Ҫ�õ��������Ķ�Ѯ��Ĭ������£���ܻ�ʹ��һ��XmlWebApplicationContext���� 
contextConfigLocation  | һ��ָ��������������·�����ַ�������ֵ�ᱻ�����contextClass��ָ����������ʵ�������ľ��ַ����ڿ��԰�������ַ������ַ�������Զ��ŷָ����Դ�֧������ж�������ĵ����á��ڶ�����������ظ������bean���������ص�bean����Ϊ׼ 
namespace  | WebApplicationContext�������ռ䡣Ĭ����[servlet-name]-servlet   
----------
1.SpringMVC�����Ĵ
    1.1  ��web.xml�ļ���������á�
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
2.�ڴ���������У���ʹ�������µ����ã�  
```xml
<!--����IEִ��AJAXʱ������JSON���������ļ� -->
	 <bean id="mappingJacksonHttpMessageConverter"
	  class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
	  <property name="supportedMediaTypes">
	   <list>
	    <value>text/html;charset=UTF-8</value>
	   </list>
	  </property>
	 </bean>
	 <!-- ����SpringMVC��ע�⹦�ܣ���������ע��POJO��ӳ�� -->
	 <bean
	  class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
	  <property name="messageConverters">
	   <list>
	    <ref bean="mappingJacksonHttpMessageConverter" /> <!-- JSONת���� -->
	   </list>
	  </property>
	 </bean>
```
�ڷ�����д�Ľӿڵ�Url��ʱ��ҳ�汨���ˣ�����ͼ����  
![image](./wikiImg/spring_mvc_1.PNG)
ԭ����jar��û�����ã���Ҫ����һ��jar����  
jackson-core-asl.jar��jackson-mapper-asl.jar��pom�ļ���ʽ���£�  
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

# spring ��hessian������
��web.xml�ļ�������hessian�Ĺ������ã�