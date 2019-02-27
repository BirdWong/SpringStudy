# Spring 01——创建项目

1. 通过maven引入spring依赖
```xml
<dependencies>
                <dependency>
                    <groupId>junit</groupId>
                    <artifactId>junit</artifactId>
                    <version>4.8</version>
                </dependency>



                <dependency>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring-context</artifactId>
                    <version>5.0.2.RELEASE</version>
                </dependency>



                <!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
                <dependency>
                    <groupId>commons-logging</groupId>
                    <artifactId>commons-logging</artifactId>
                    <version>1.2</version>
                </dependency>

        </dependencies>
```

其中spring的expression、aop、context、core四个主要部分我们只导入了spring-context，因为spring-context就已经包含了其他的依赖
```xml
......

<dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aop</artifactId>
      <version>5.0.2.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>5.0.2.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>5.0.2.RELEASE</version>
      <scope>compile</scope>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-expression</artifactId>
      <version>5.0.2.RELEASE</version>
      <scope>compile</scope>
    </dependency>
...

```
以上是spring-context-5.0.2.RELEASE.pom中的部分依赖，我们可以看到，已经自动引入。

接下来，我们开始编写文件，首先看我们的文件结构目录：
```text
├─spring01
│  │  pom.xml
│  │  README.md
│  │  spring01.iml
│  │
│  ├─.idea
│  │  │  compiler.xml
│  │  │  misc.xml
│  │  │  workspace.xml
│  │  │
│  │  └─inspectionProfiles
│  │          Project_Default.xml
│  │
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─cn
│  │  │  │      └─jijiking
│  │  │  │          └─spring01
│  │  │  │              │  Test.java
│  │  │  │              │
│  │  │  │              └─dao
│  │  │  │                  │  TestDao.java
│  │  │  │                  │
│  │  │  │                  └─impl
│  │  │  │                          TestDaoImpl.java
│  │  │  │
│  │  │  └─resources
│  │  │          applicationContext.xml
│  │  │
│  │  └─test
│  │      └─java
│  └─target
│      ├─classes
│      │  │  applicationContext.xml
│      │  │
│      │  └─cn
│      │      └─jijiking
│      │          └─spring01
│      │              │  Test.class
│      │              │
│      │              └─dao
│      │                  │  TestDao.class
│      │                  │
│      │                  └─impl
│      │                          TestDaoImpl.class
│      │
│      └─generated-sources
│          └─annotations
```

第一个编写的是我们的TestDao文件

TestDao.java
```java
public interface TestDao {

	public String sayHello();
}
```
其中只有一个方法，就是sayHello， 接下来我们要编写他的实现类TestDaoImpl

TestDaoImpl.java
```java
public class TestDaoImpl implements TestDao {
	public String sayHello() {
		return "Hello world";
	}
}
```
TestDaoImpl中我们实现了TestDao的sayHello方法，返回了一个"Hello World"

接下来我们要编写对它的测试类Test

Test.java，下面展示的是测试1
```java
public class Test {

	public static void main(String[] args) {
		TestDao dao = new TestDaoImpl();
		String s = dao.sayHello();
		System.out.println(s);


//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		TestDao testDao = applicationContext.getBean("testDao", TestDao.class);
//		String s = testDao.sayHello();
//		System.out.println(s);
	}
}
```
我们现在使用的是通过new一个对象然后调用他的实现方法，这是我们之前学习java时通常使用的方式。

接下来我们进行测试2， 使用spring的方式调用sayHello，在此之前我们需要先配置applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean id="testDao" class="cn.jijiking.spring01.dao.impl.TestDaoImpl">
        </bean>
                
</beans>
```
这是一个基础配置的模板，我们可以在下载的spring文档中找到他

`Spring4.2.4/spring-framework-4.2.4/docs/spring-framework-reference/html/beans.html#beans-factory-metadata`

其中该id属性是一个字符串，用于标识单个bean定义。该class属性定义bean的类型并使用完全限定的类名。id属性的值指的是协作对象。

配置好applicationContext.xml后我们编写Test测试类

Test.java
```java
public class Test {

	public static void main(String[] args) {
//		TestDao dao = new TestDaoImpl();
//		String s = dao.sayHello();
//		System.out.println(s);


		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestDao testDao = applicationContext.getBean("testDao", TestDao.class);
		String s = testDao.sayHello();
		System.out.println(s);
	}
}
```
通过ClassPathXmlApplicationContext读取我们的xml配置文件，其中的参数是填写我们的xml路径
然后通过getBean方式将TestDaoImpl注入到TestDao中，紧接着我们调用sayHello

在这里，我们可以看到，打印结果与上面有所不同， 我们这里出现了部分日志记录，我们来看一下，第二条信息

`Loading XML bean definitions from class path resource [applicationContext.xml]`

翻译过来就是：从类路径资源[applicationContext.xml]加载XML中定义的bean

自此，我们的第一步，创建项目就完成了