# javaѧϰ
�ο���https://blog.csdn.net/briblue/article/details/73824058
## java ע��
���ǽ���һ����α�д�Զ����Annotations��
J2SE5.0�汾�� java.lang.annotation�ṩ������Ԫע�⣬ר��ע��������ע�⣺  
@Documented �Cע���Ƿ񽫰�����JavaDoc��  
@Retention �Cʲôʱ��ʹ�ø�ע��  
@Target? �Cע������ʲô�ط�  
@Inherited �C �Ƿ���������̳и�ע��    
@Repeatable ��Ȼ�ǿ��ظ�����˼��@Repeatable �� Java 1.8 �żӽ����ģ���������һ���µ����ԡ�  
������Ҫ���Retention��Target��ʹ�ã��������������˽�����ˡ�  
### @Documented
һ���򵥵�Annotations���ע�⣬��ʾ�Ƿ�ע����Ϣ�����java�ĵ��С�  
### @Retention
RetentionPolicy.SOURCE �C �ڱ���׶ζ�������Щע���ڱ������֮��Ͳ������κ����壬�������ǲ���д���ֽ��롣@Override, @SuppressWarnings����������ע�⡣  
RetentionPolicy.CLASS �C ������ص�ʱ���������ֽ����ļ��Ĵ��������á�ע��Ĭ��ʹ�����ַ�ʽ��  
RetentionPolicy.RUNTIME�C ʼ�ղ��ᶪ����������Ҳ������ע�⣬��˿���ʹ�÷�����ƶ�ȡ��ע�����Ϣ�������Զ����ע��ͨ��ʹ�����ַ�ʽ��  
��Ҫע����ǣ����һ��ע��Ҫ������ʱ���ɹ���ȡ����ô @Retention(RetentionPolicy.RUNTIME) �Ǳ���ġ�
### @Target
�������ȷָ������ע����Է����κεط���������һЩ���õĲ�������Ҫ˵�����ǣ����Ե�ע���Ǽ��ݵģ���������7�����Զ����ע�⣬�����ų�һ�����ԣ���ô����Ҫ�ڶ���target�������е����ԡ�  
ElementType.TYPE:���������ࡢ�ӿڻ�enum����  
ElementType.FIELD:��������ʵ������  
ElementType.METHOD  
ElementType.PARAMETER  
ElementType.CONSTRUCTOR  
ElementType.LOCAL_VARIABLE  
ElementType.ANNOTATION_TYPE ��һ��ע��  
ElementType.PACKAGE ���ڼ�¼java�ļ���package��Ϣ    
### @Inherited 
 �����ע�ͺ�����Ĺ�ϵ  
��Ҫע����ǣ���ע���ж�������ʱ�������ͱ����� 8 �ֻ�������������� �ࡢ�ӿڡ�ע�⼰���ǵ����顣  
ע�������Կ�����Ĭ��ֵ��Ĭ��ֵ��Ҫ�� default �ؼ�ֵָ��
������һ�����ӣ�
```java
package com.jiang.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//���������ࡢ�ӿڻ�enum����  
@Retention(RetentionPolicy.RUNTIME)//ʼ�ղ��ᶪ����������Ҳ������ע�⣬��˿���ʹ�÷�����ƶ�ȡ��ע�����Ϣ�������Զ����ע��ͨ��ʹ�����ַ�ʽ��
public @interface Hession {
	String description() default "";
	String uri() default "";
}
```
## ע���뷴��
ע��ֻ��ͨ����������ȡ��  
���ȿ���ͨ�� Class ����� isAnnotationPresent() �����ж����Ƿ�Ӧ����ĳ��ע��  
```java
public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {}
```
Ȼ��ͨ�� getAnnotation() ��������ȡ Annotation ����  
```java
public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {}
```
������ getAnnotations() ������
```java
public Annotation[] getAnnotations() {}
```
ǰһ�ַ�������ָ�����͵�ע�⣬��һ�ַ�������ע�⵽���Ԫ���ϵ�����ע�⡣
��ʵ���Ҿ���֪��������ʵ����Ŀ�����ȥ�ж�ʹ�����ע�⡣  