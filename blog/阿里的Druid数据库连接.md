# druid数据库连接  
## 配置属性  
[DruidDataSource配置属性列表](https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8)  
可以对照里的代码对比进行学习。这里主要是了解其中一种报错信息的处理。
```java
[ERROR][Druid-ConnectionPool-Create-2000239237][2019-04-17 17:06:21,512]DruidDataSource$CreateConnectionThread.run line:1682 : create connection error
com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure
The last packet successfully received from the server was 0 milliseconds ago. The last packet sent successfully to the server was 0 milliseconds ago.
at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:57)
at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
at java.lang.reflect.Constructor.newInstance(Constructor.java:525)
at com.mysql.jdbc.Util.handleNewInstance(Util.java:408)
at com.mysql.jdbc.SQLError.createCommunicationsException(SQLError.java:1137)
at com.mysql.jdbc.MysqlIO.reuseAndReadPacket(MysqlIO.java:3697)

```
这个错误网上有很多解决方案，自己可以了解和尝试解决。但是我在项目中遇到的问题是应用启动的时候就报这个错（其实怀疑是大量的高并发访问数据库造成的），显然与网上的大多说法不是一样的。但是自己的找不到问题的所在，目前就是优化代码，尽量减少对数据库的操作处理。  
还有配置项中注意：如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。