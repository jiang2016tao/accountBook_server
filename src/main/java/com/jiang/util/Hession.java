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
