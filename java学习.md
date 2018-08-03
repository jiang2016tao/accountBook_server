# java学习
参考：https://blog.csdn.net/briblue/article/details/73824058
## java 注解
我们讲解一下如何编写自定义的Annotations。
J2SE5.0版本在 java.lang.annotation提供了四种元注解，专门注解其他的注解：  
@Documented –注解是否将包含在JavaDoc中  
@Retention –什么时候使用该注解  
@Target? –注解用于什么地方  
@Inherited – 是否允许子类继承该注解    
@Repeatable 自然是可重复的意思。@Repeatable 是 Java 1.8 才加进来的，所以算是一个新的特性。  
这里主要理解Retention和Target的使用，另外两个可以了解就行了。  
### @Documented
一个简单的Annotations标记注解，表示是否将注解信息添加在java文档中。  
### @Retention
RetentionPolicy.SOURCE – 在编译阶段丢弃。这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码。@Override, @SuppressWarnings都属于这类注解。  
RetentionPolicy.CLASS – 在类加载的时候丢弃。在字节码文件的处理中有用。注解默认使用这种方式。  
RetentionPolicy.RUNTIME– 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。  
需要注意的是，如果一个注解要在运行时被成功提取，那么 @Retention(RetentionPolicy.RUNTIME) 是必须的。
### @Target
如果不明确指出，该注解可以放在任何地方。以下是一些可用的参数。需要说明的是：属性的注解是兼容的，如果你想给7个属性都添加注解，仅仅排除一个属性，那么你需要在定义target包含所有的属性。  
ElementType.TYPE:用于描述类、接口或enum声明  
ElementType.FIELD:用于描述实例变量  
ElementType.METHOD  
ElementType.PARAMETER  
ElementType.CONSTRUCTOR  
ElementType.LOCAL_VARIABLE  
ElementType.ANNOTATION_TYPE 另一个注释  
ElementType.PACKAGE 用于记录java文件的package信息    
### @Inherited 
 定义该注释和子类的关系  
需要注意的是，在注解中定义属性时它的类型必须是 8 种基本数据类型外加 类、接口、注解及它们的数组。  
注解中属性可以有默认值，默认值需要用 default 关键值指定
下面是一个例子：
```java
package com.jiang.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//用于描述类、接口或enum声明  
@Retention(RetentionPolicy.RUNTIME)//始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息。我们自定义的注解通常使用这种方式。
public @interface Hession {
	String description() default "";
	String uri() default "";
}
```
## 注解与反射
注解只有通过反射来提取。  
首先可以通过 Class 对象的 isAnnotationPresent() 方法判断它是否应用了某个注解  
```java
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {}
```
然后通过 getAnnotation() 方法来获取 Annotation 对象。  
```java
public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {}
```
或者是 getAnnotations() 方法。
```java
public Annotation[] getAnnotations() {}
```
前一种方法返回指定类型的注解，后一种方法返回注解到这个元素上的所有注解。
其实，我就想知道在我们实际项目中如何去判断使用这个注解。  