# SpringMVCѧϰ
�ڴ���������У���ʹ�������µ����ã�  
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