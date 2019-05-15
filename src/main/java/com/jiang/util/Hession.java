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
