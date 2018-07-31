# maven学习
在本地使用maven时，一般不会自己构建中央库（就是搭建一个环境（Nexus）），都是从网络上查找的。这时注意查找一个可用的maven镜像地址。如下是我使用的(目前来看是可以使用的):
```xml
<mirror>
		<id>mvnrepository.com</id>
		<mirrorOf>central</mirrorOf>
		<name>repo in china</name>
		<url>http://central.maven.org/maven2/</url>
	 </mirror>
```
当我们使用一个错误的镜像，或者镜像网络不通的话，创建maven工程会出现问题的，就算是之前的项目打包也会出现问题（如下图）。  
![image](./wikiImg/maven-mirror-error.PNG) 