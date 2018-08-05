package com.jiang.util;

import static org.springframework.util.Assert.notNull;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class HessianServerScannerConfigurer implements BeanDefinitionRegistryPostProcessor,InitializingBean,BeanNameAware,ApplicationContextAware {

	private String beanName;
	private String basePackge;

	private Class<? extends Annotation> annotationClass;
	private boolean includeAnnotationConfig=true;
	private ApplicationContext applicationContext;
	private Map<String, String> implClassContextName = new HashMap<String, String>();
	private BeanNameGenerator beanNameGenerator=new AnnotationBeanNameGenerator(){

		@Override
		protected String buildDefaultBeanName( BeanDefinition definition ) {
			AnnotationMetadata metadata=((ScannedGenericBeanDefinition)definition).getMetadata();
			Map<String, Object> annotationAttributes=metadata.getAnnotationAttributes( annotationClass.getName() );
			String uri=( String )annotationAttributes.get( "uri" );
			if(!StringUtils.isEmpty( uri ))
				return "/"+uri;
			
			int index=metadata.getClassName().lastIndexOf( "." );
			return "/"+metadata.getClassName().substring( index+1 );
		}
		
	};

	@Override
	public void setBeanName( String name ) {
		this.beanName=name;
	}
	@Override
	public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory ) throws BeansException {
		// TODO Auto-generated method stub

	}


	@Override
	public void postProcessBeanDefinitionRegistry( BeanDefinitionRegistry registry ) throws BeansException {
		System.out.println( "postProcessBeanDefinitionRegistry" );
		HessianClassPathScanner scan=new HessianClassPathScanner(registry);
		scan.setResourceLoader( this.applicationContext );
		scan.setBeanNameGenerator( beanNameGenerator );
		scan.setIncludeAnnotationConfig( includeAnnotationConfig );
		scan.registerFilters();
		String[] basePackages=StringUtils.tokenizeToStringArray( basePackge, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS );
		scan.scan( basePackages );
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println( "afterPropertiesSet" );
		notNull(this.basePackge,"Property 'basePackge' is required "+beanName);
		notNull( this.annotationClass,"Property 'annotationClass' is required "+beanName );
		
		XmlWebApplicationContext xmlContext=( XmlWebApplicationContext )applicationContext;
		do{
			DefaultListableBeanFactory beanfactory=( DefaultListableBeanFactory )xmlContext.getAutowireCapableBeanFactory();
			/*
			 * ReflectionUtils��spring�ṩ�ķ��乤����
			 * ͨ�������ȡorg.springframework.beans.factory.support.DefaultSingletonBeanRegistry���singletonObjects�ֶ�
				private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);
				DefaultSingletonBeanRegistry��DefaultListableBeanFactory�ĸ��࣬����һ���鿴
			 */
			Field findField=ReflectionUtils.findField( beanfactory.getClass(), "singletonObjects" );
			ReflectionUtils.makeAccessible( findField );
			//��ȡsingletonObjects�ڶ����ϵ�ֵ
			@SuppressWarnings( "unchecked" )
			Map<String, Object> field=( Map<String, Object> )ReflectionUtils.getField( findField, beanfactory );
			for(Entry<String, Object> entry:field.entrySet()){
				System.out.println( "key : "+entry.getKey() );
				System.out.println( "value : "+entry.getValue().getClass().getName() );
				//�������ﻹû��ʹ��aop����������������Ĵ��룬�����ڸ��Ĺ���
//				Class<?>[] interfaces=AopUtils.getTargetClass( entry.getValue() ).getInterfaces();
				Class<?>[] interfaces=entry.getValue().getClass().getInterfaces();
				
				for(Class<?> interfaceClass:interfaces){
					System.out.println( interfaceClass.getName() );
					Annotation annotation=interfaceClass.getAnnotation( annotationClass );
					System.out.println( annotation );
					if(annotation!=null){
						implClassContextName.put( interfaceClass.getName(), entry.getKey() );
					}
				}
			}
		}while((xmlContext=( XmlWebApplicationContext )xmlContext.getParentBeanFactory())!=null);
	}
	@Override
	public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {

		
		this.applicationContext=applicationContext;
	}
	
	public boolean isIncludeAnnotationConfig() {
	
		return includeAnnotationConfig;
	}
	
	public void setIncludeAnnotationConfig( boolean includeAnnotationConfig ) {
	
		this.includeAnnotationConfig = includeAnnotationConfig;
	}
	public String getBasePackge() {
	
		return basePackge;
	}
	
	public void setBasePackge( String basePackge ) {
	
		this.basePackge = basePackge;
	}
	
	public Class<? extends Annotation> getAnnotationClass() {
	
		return annotationClass;
	}
	
	public void setAnnotationClass( Class<? extends Annotation> annotationClass ) {
	
		this.annotationClass = annotationClass;
	}

	private class HessianClassPathScanner extends ClassPathBeanDefinitionScanner {

		public HessianClassPathScanner( BeanDefinitionRegistry registry ) {
			super( registry );
		}


		@Override
		protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
			Set<BeanDefinitionHolder> beanDefinitionHolders=super.doScan(basePackages);
			if(beanDefinitionHolders.isEmpty()){
				System.out.println("No hessian.");
			}
			else{
				for(BeanDefinitionHolder beanDefinitionHolder:beanDefinitionHolders){
					GenericBeanDefinition genericBeanDefinition=(GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
					System.out.println("Creating HessianServiceExporter with name '"+beanDefinitionHolder.getBeanName()+"' and '"
					+genericBeanDefinition.getBeanClassName()+"' serviceInterface");
					
					/**
					 * ����Ĵ�������൱���������ļ���������������á�
					 * <bean id = "basicService" class="example.impl.BasicImpl"/>

					    <bean name="/basicHessianService" class="org.springframework.remoting.caucho.HessianServiceExporter">
					        <property name="service" ref="basicService"/>
					        <property name="serviceInterface" value="example.Basic"/>
					    </bean>
					 */
					genericBeanDefinition.getPropertyValues().add("serviceInterface", genericBeanDefinition.getBeanClassName());
					String beanNameRef=implClassContextName.get( genericBeanDefinition.getBeanClassName() );
					genericBeanDefinition.getPropertyValues().add( "service", new RuntimeBeanReference( beanNameRef ) );
					genericBeanDefinition.setBeanClass( HessianServiceExporter.class );
				}
			}
			return beanDefinitionHolders;
		}

		@Override
		protected boolean checkCandidate( String beanName, BeanDefinition beanDefinition ) throws IllegalStateException {

			String implBeanName=implClassContextName.get( beanDefinition.getBeanClassName() );
			System.out.println( "implBeanName : "+implBeanName );
			if(!StringUtils.isEmpty( implBeanName )&&super.checkCandidate( implBeanName, beanDefinition ))
				return true;
			else{
				System.out.println( "Skipping HessianServiceExpoter with name '"+beanName+"' and '"+beanDefinition.getBeanClassName()
				+"' serviceInterface. Bean already defined with the same name." );
				return false;
			}
		}

		//ע�������-��֤��ȷ���౻ɨ��ע��
		public void registerFilters( ) {

			boolean acceptAllInterfaces=true;
			if(HessianServerScannerConfigurer.this.annotationClass !=null){
				addIncludeFilter( new AnnotationTypeFilter( HessianServerScannerConfigurer.this.annotationClass ) );
				acceptAllInterfaces=false;
			}
			
			if(acceptAllInterfaces){
				addIncludeFilter( new TypeFilter() {
					
					@Override
					public boolean match( MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory ) throws IOException {
						
						return true;
						
					}
				} );
			}
			
			addExcludeFilter( new TypeFilter() {
				
				@Override
				public boolean match( MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory ) throws IOException {
					
					String className=metadataReader.getClassMetadata().getClassName();
					return className.endsWith( "package-info" );
				}
			} );
				
		}

		//ԭ�����������ж��Ƿ�Ϊ��������Ƿ��������ࣨ���ӿڻᱻ�ų���-����������Ҫ���ӿڼӽ�����������Ҫ���Ǹ÷�����
		@Override
		protected boolean isCandidateComponent( AnnotatedBeanDefinition beanDefinition ) {

			
			return (beanDefinition.getMetadata().isInterface()&&beanDefinition.getMetadata().isIndependent());
				
		}

	}
	
	


	

}
