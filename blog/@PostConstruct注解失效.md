# @PostConstruct注解失效
【问题描述】：在项目中我们经常在应用启动时来做一些初始化的事情，但是我使用的@PostConstruct注解的方法在启动的时候并没有去执行。  
代码：[TestPostConstruct](https://github.com/jiang2016tao/accountBook_server/blob/master/src/main/java/com/jiang/util/TestPostConstruct.java)  
这样启动应用，发现对应的注解方法根本就没有被执行。   
查看配置文件发现com.jiang.util.TestPostConstruct这个文件并没有放在配置文件里使用注解进行扫描。  
```xml
<context:component-scan base-package="com.jiang.service.impl"></context:component-scan>
```
这是spring-mybatis.xml文件里配置扫描的包，TestPostConstruct类根本不在这个包里面，注解根本没有被解析，那怎么可能执行了。添加配置信息。   
```xml
<context:component-scan base-package="com.jiang.service.impl;com.jiang.util"></context:component-scan>
```
这样后@PostConstruct注解的方法得到执行。  
```java
2019-07-27 16:26:14,058 [main] [com.jiang.util.TestPostConstruct] [WARN] - @PostConstruct init
```
*注意：我们在配置的过程中一定要是在spring的配置文件里，不要配置在了spring-mvc的配置文件里了*  
在我们的项目里有spring-mybatis.xml和spring-mvc.xml这两个配置文件。在web.xml里的使用：
```xml
<context-param>
  	<param-name>contextConfigLocation</param-name>
  	<param-value>classpath:/spring-mybatis.xml</param-value>
  </context-param>
```
```xml
<!-- Spring Mvc Start -->
 <servlet>
  <servlet-name>dispatcher</servlet-name>
  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
  <init-param>
   <param-name>contextConfigLocation</param-name>
   <param-value>classpath:/spring-mvc.xml</param-value>
  </init-param>
 </servlet>
```
上面是两个文件在web.xml里的配置，这个是有去区别的[springmvc在tomcat中的执行过程](https://www.cnblogs.com/hantalk/p/6652967.html?utm_source=itdadao&utm_medium=referral)  
tomcat容器启动根据web.xml里的配置来的，显然在配置里我们先加载的是spring的容器管理，然后才是dispatcherservlet初始化（即springmvc的控制流程）  
[@PostConstruct和@PreDestroy注解](https://www.cnblogs.com/landiljy/p/5764515.html)这里有实例可以参考，结合实例理解注解在Servlet中的位置，及整个生命周期里执行流程顺序。  
@PostConstruct和@PreDestroy，这两个注解被用来修饰一个非静态的void（）方法。  
- @PostConstruct说明   
被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器调用一次，类似于Serclet的inti()方法。被@PostConstruct修饰的方法会在构造函数之后，init()方法之前运行。  
- @PreDestroy说明  
 被@PreDestroy修饰的方法会在服务器卸载Servlet的时候运行，并且只会被服务器调用一次，类似于Servlet的destroy()方法。被@PreDestroy修饰的方法会在destroy()方法之后运行，在Servlet被彻底卸载之前。（详见下面的程序实践）   

如果想在生成对象时候完成某些初始化操作，而偏偏这些初始化操作又依赖于依赖注入，那么就无法在构造函数中实现。为此，可以使用@PostConstruct注解一个方法来完成初始化，@PostConstruct注解的方法将会在依赖注入完成后被自动调用。  
在spring注解中执行顺序：Constructor >> @Autowired >> @PostConstruct  
这就说明为啥在项目中初始化，有些地方可以直接使用static{}块代码端直接初始化，有需要依赖的地方要用@PostConstruct注解。  