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