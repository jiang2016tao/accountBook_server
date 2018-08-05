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
����Ŀ�о�������ʹ�����contextConfigLocation��namespace  
>
```xml
<!--����������ø����ʼ��Root WebApplicationContext-->
<servlet>
  <servlet-name>dispatcher</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
   <param-name>contextConfigLocation</param-name>
   <param-value>classpath:/spring-mvc.xml</param-value>
  </init-param>
 </servlet>
 
 <!--����������ø����ʼ��Servlet WebApplication-->
 <servlet>
  	<servlet-name>hessian</servlet-name>
  	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  	<init-param>
	   <param-name>namespace</param-name>
	   <param-value>classpath:/hessian-servlet</param-value>
	  </init-param>
	  <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
  	<servlet-name>hessian</servlet-name>
  	<url-pattern>/hessian/*</url-pattern>
  </servlet-mapping>
```
namespace ��contextConfigLocation������:namespaceָ�����ļ���spring-mvc ��ȥWEB-INF/��ȥѰ�ң����üӺ�׺;contextConfigLocation����ָ����������ļ����ö��ŷֿ���   
�ο�[DispatcherServlet ���� namespace contextConfigLocatoin����]��https://blog.csdn.net/weixin_36210698/article/details/72629259��

## ��ʶorg.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
�ο�[Spring����bean�Ĺ���](http://blog.gavinzh.com/2017/11/17/Spring-create-bean/)
�ο�[Spring��չ���ܽ�](http://blog.gavinzh.com/2017/11/20/spring-develop-summary/)
## ��ʶorg.springframework.context.annotation.ClassPathBeanDefinitionScanner
�ο�[Springɨ��basePackagesע��](https://blog.csdn.net/unix21/article/details/52163789)
�ο�[��̬ע��ӿ�Bean](https://blog.csdn.net/geekjoker/article/details/80497913)
��spring������ע��Ľ��������У����ཫ��ɨ�衣
```xml
<context:component-scan base-package="com.jiang.web.controller"></context:component-scan>
```
�����ͻ����ClassPathBeanDefinitionScanner��doScan������  
![image](./wikiImg/ClassPathBeanDefinitionScanner_1.png)
���к��߲������丸�෽��org.springframework.context.annotation.ClasPathScanningCandidateComponentProvider.findCandidateComponents(String),ͨ���÷������������.  
![image](./wikiImg/ClassPathBeanDefinitionScanner_2.png)
���к��߲��ַ���org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider.isCandidateComponent(AnnotatedBeanDefinition)���Ƕ�bean�����һ�����ˡ�Դ������ͼ  
![image](./wikiImg/ClassPathBeanDefinitionScanner_3.png)
 ԭ�����������ж��Ƿ�Ϊ��������Ƿ��������ࣨ���ӿڻᱻ�ų���-����������Ҫ���ӿڼӽ�����������Ҫ���Ǹ÷�������������������Ŀ��ɨ����ǽӿڣ�������Ҫ��д���������  
�丸��ClassPathScanningCandidateComponentProvider������������ ���ο�[ClassPathScanningCandidateComponentProvider](https://blog.csdn.net/duzm200542901104/article/details/78909668) 
```java
private finalList<TypeFilter>includeFilters=newLinkedList<TypeFilter>();
private finalList<TypeFilter>excludeFilters=newLinkedList<TypeFilter>();
```
һ���ǰ����Ĺ�������һ�����ų��Ĺ���������ɨ���ʱ�����Ƚ����ų���������ƥ�䣬���ƥ���˽�����оͲ��������࣬�������������������ƥ�䣬���ƥ���˾Ͱ������ࡣ  
ClassPathScanningCandidateComponentProvider���Դ��resetFilters������������������ֵ�ġ����£�
```java
/**
	 * Reset the configured type filters.
	 * @param useDefaultFilters whether to re-register the default filters for
	 * the {@link Component @Component}, {@link Repository @Repository},
	 * {@link Service @Service}, and {@link Controller @Controller}
	 * stereotype annotations
	 * @see #registerDefaultFilters()
	 */
	public void resetFilters(boolean useDefaultFilters) {
		this.includeFilters.clear();
		this.excludeFilters.clear();
		if (useDefaultFilters) {
			registerDefaultFilters();
		}
	}
```

## ��ʶorg.springframework.beans.factory.annotation.AnnotatedBeanDefinition
## ��ʶorg.springframework.beans.factory.InitializingBean
InitializingBean�ӿ�Ϊbean�ṩ�˳�ʼ�������ķ�ʽ����ֻ����afterPropertiesSet���������Ǽ̳иýӿڵ��࣬�ڳ�ʼ��bean��ʱ���ִ�и÷�����  
�ο�[InitializingBean������](https://blog.csdn.net/maclaren001/article/details/37039749)
## ��ʶorg.springframework.web.context.support.XmlWebApplicationContext
��XML�����ļ���װ��ģ�����WEB������ApplicationContextʵ���࣬������ {@link org.springframework.web.context.support.XmlWebApplicationContext}��  
- getParentBeanFactory()��ȡ������bean����
- getAutowireCapableBeanFactory ��ȡһ��ע����bean�ļ��Ϲ���

## ��ʶorg.springframework.beans.factory.support.DefaultListableBeanFactory
�ο�[spring beansԴ����֮ ioc����֮ʼ��--DefaultListableBeanFactory](https://www.cnblogs.com/davidwang456/p/4187012.html)  
���Լ�����⣩��װspring�������Ѿ�ע��õ�bean�ļ����ࡣ
- �̳���AbstractAutowireCapableBeanFactory�ķ���  

>�ṩbean�Ĵ��� (��construct����), ����עֵ, �� (�����Զ���)�ͳ�ʼ��.��������ʱbean����, ��������ļ���, ���ó�ʼ��������

## ��ʶorg.springframework.aop.support.AopUtils
�����spring��һ�������ࣺ
>public static Class<?> getTargetClass(Object candidate)���������ͨ��Aop�Ĵ�����ҵ�Ŀ���ࣨ������ִ���߼����ࣩ��  
�ο�[getTargetClass˵����ʹ��](http://norther.iteye.com/blog/269763)

## ��ʶorg.springframework.beans.factory.config.RuntimeBeanReference.RuntimeBeanReference(String)
�ο�[Spring Bean�Ľ��� RuntimeBeanReference](https://blog.csdn.net/jerryai1/article/details/52980239)  
��Spring�У�Bean�Ľ����׶Σ����xml�����е�<bean>��ǩ������Spring�е�BeanDefinition��������֪��һ��bean������Ҫ����������bean����XML�����п��Ա���Ϊ  
```xml
<bean class="foo.bar.xxx">
   <property name="referBeanName" ref="otherBeanName" />
</bean>
```
��Spring�Ľ����Σ���ʵ��������û��������Bean��ʵ������ˣ���ô���������������Bean�����BeanDefinition�б�ʾ�أ�  
�𰸾���RuntimeBeanReference���ڽ�����������Bean��ʱ�����������������bean��name����һ��RuntimeBeanReference���񣬽�����������BeanDefinition��MutablePropertyValues�С�  
���磺�����е�����bean�ᱻ������  
```java
//����֪��foo.bar.xxx ������Ϊһ��beanDefiniton������ΪxxxBeanDefinition
reference = new RuntimeBeanReference("otherBeanName");
xxxBeanDefinition.getPropertyValues().addPropertyValue("referBeanName", reference);
```
## ��ʶorg.springframework.beans.factory.annotation.AnnotatedBeanDefinition
�ο�[AnnotatedBeanDefinition](https://www.cnblogs.com/davidwang456/p/4199459.html)  
AnnotationMetadata�ﲿ�ַ���˵����   
boolean isInterface()���ظ����Ƿ��ǽӿڡ�  
boolean isAbstract()���ظ����Ƿ�Ϊ�����ࡣ  
boolean isConcrete()���ظ����Ƿ�Ϊ�����ࡣ  
boolean isFinal()���ظ����Ƿ�Ϊfinal�ࡣ  
Ϊʲô�¶�û����Ŀ��ʹ�õ�beanDefinition.getMetadata().isIndependent()   
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

![image](./wikiImg/spring_mvc_1.png)
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
# spring����  
- ����Ŀ�б�д��һ���������ࣺ  
�ο�[The type javax.servlet.ServletContext cannot be resolved. It is indirectly referenced from required](https://blog.csdn.net/lurao/article/details/50237253)
```java
package com.jiang.util;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringContext extends XmlWebApplicationContext{
	private XmlBeanDefinitionReader beanDefinitionReader;

	@Override
	protected void initBeanDefinitionReader( XmlBeanDefinitionReader beanDefinitionReader ) {

		
		super.initBeanDefinitionReader( beanDefinitionReader );
		this.beanDefinitionReader=beanDefinitionReader;
			
	}

	
	public XmlBeanDefinitionReader getBeanDefinitionReader() {
	
		return beanDefinitionReader;
	}
	
}

```
��������ʾ����The type javax.servlet.ServletContext cannot be resolved. It is indirectly referenced from  	 required .class files����������ȱ��servlet��  
��������������Ϳ�����:
```xml
<dependency>
	  	<groupId>javax</groupId>
	  	<artifactId>javaee-api</artifactId>
	  	<version>7.0</version>
	  </dependency>
```
- bean��ע������  
��java��ʹ�����淽ʽ����bean��ע��
```java
@Autowired
	private UserService userService;
```
�������ǳ����ˣ���ע��ʧ��

> org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'testControll': Injection of autowired dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Could not autowire field: private com.jiang.service.UserService com.jiang.web.controller.TestControll.userService; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type [com.jiang.service.UserService] found for dependency: expected at least 1 bean which qualifies as autowire candidate for this dependency. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
	at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessPropertyValues(AutowiredAnnotationBeanPostProcessor.java:292)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1185)
	
�������Լ�û�ж�service.impl���µ�ʵ������н�����������spring������û��������bean�������Ҿ���spring-mybatis,xml�ļ������ɨ�����á����£�
```xml
<context:component-scan base-package="com.jiang.service.impl"></context:component-scan>
```	
Ȼ��ͳ����ˣ���������⡣
- ��web.xml�������ˣ�
```xml
<context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>classpath:/spring-mybatis.xml</param-value>
  </context-param>
```
����������Ӧ�õ�ʱ��,spring����û��ȥ��������ļ�,��ʱ�������.ԭ����Ҫ��.xml�ļ������spring�ļ��������£�
```xml
<listener>
 	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 </listener>
```