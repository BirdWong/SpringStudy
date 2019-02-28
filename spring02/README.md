# Spring 02——依赖注入

依赖注入我们尝试用两种方式注入，构造方法注入和set方法注入，首先看这次的结构目录
```text
G:.
│  pom.xml
│  README.md
│
├─.idea
│  │  compiler.xml
│  │  misc.xml
│  │  uiDesigner.xml
│  │  workspace.xml
│  │
│  └─inspectionProfiles
│          Project_Default.xml
│
├─src
│  ├─main
│  │  ├─java
│  │  │  └─cn
│  │  │      └─jijiking
│  │  │          └─spring01
│  │  │              │  Test.java
│  │  │              │
│  │  │              ├─dao
│  │  │              │  │  TestDIDao.java
│  │  │              │  │
│  │  │              │  └─impl
│  │  │              │          TestDIDaoImpl.java
│  │  │              │
│  │  │              └─service
│  │  │                  │  TestDIService.java
│  │  │                  │
│  │  │                  └─impl
│  │  │                          TestDIServiceImpl.java
│  │  │
│  │  └─resources
│  │          applicationContext.xml
│  │
│  └─test
│      └─java
└─target
    ├─classes
    │  │  applicationContext.xml
    │  │
    │  └─cn
    │      └─jijiking
    │          └─spring01
    │              │  Test.class
    │              │
    │              ├─dao
    │              │  │  TestDIDao.class
    │              │  │
    │              │  └─impl
    │              │          TestDIDaoImpl.class
    │              │
    │              └─service
    │                  │  TestDIService.class
    │                  │
    │                  └─impl
    │                          TestDIServiceImpl.class
    │
    └─generated-sources
        └─annotations
```

首先我们创建一个TestDIDao，里面我们只有一个方法sayHello
```java
public interface TestDIDao {

	public void sayHello();
}
```

接下来写他的实现类TestDIDaoImpl
```java
public class TestDIDaoImpl implements TestDIDao {
	public void sayHello() {
		System.out.println("TestDI:Hello world!");
	}
}
```

然后我们在service包下创建一个TestDIService
```java
public interface TestDIService {
	public void sayHello();
}
```

写出他的实现类TestDIServiceImpl
```java
public class TestDIServiceImpl implements TestDIService {

	private TestDIDao testDIDao;

//	public TestDIServiceImpl(TestDIDao diDao){
//		super();
//		this.testDIDao = diDao;
//	}

	public void setTestDIDao(TestDIDao testDIDao){
		this.testDIDao = testDIDao;
	}

	public void sayHello() {
		testDIDao.sayHello();
	}
}
```

## set方法注入
首先尝试的是set方法注入，我们写好我们的配置文件applicationConttext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean id="myDIDao" class="cn.jijiking.spring01.dao.impl.TestDIDaoImpl">
                <!-- collaborators and configuration for this bean go here -->
        </bean>

        <!--<bean id="testDIService" class="cn.jijiking.spring01.service.impl.TestDIServiceImpl">-->
                <!--<constructor-arg index="0" ref="myDIDao"/>-->
        <!--</bean>-->

        <bean id="testDIService" class="cn.jijiking.spring01.service.impl.TestDIServiceImpl">
                <property name="testDIDao" ref="myDIDao"/>
        </bean>

</beans>
```
这里我们看到了一个新的标签\<property\>,这是官方的定义：

`Bean定义可以具有零个或多个属性。属性元素对应于bean类公开的JavaBean setter方法。Spring支持原语，对相同或相关工厂中的其他bean的引用，列表，映射和属性。`

## 构造方法注入
接下来实现我们的构造方法注入，修改TestDIServiceImpl
```java
public class TestDIServiceImpl implements TestDIService {

	private TestDIDao testDIDao;

	public TestDIServiceImpl(TestDIDao diDao){
		super();
		this.testDIDao = diDao;
	}

//	public void setTestDIDao(TestDIDao testDIDao){
//		this.testDIDao = testDIDao;
//	}

	public void sayHello() {
		testDIDao.sayHello();
	}
}
```

同时修改applicationContext.xml中的配置
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

        <bean id="myDIDao" class="cn.jijiking.spring01.dao.impl.TestDIDaoImpl">
                <!-- collaborators and configuration for this bean go here -->
        </bean>

        <bean id="testDIService" class="cn.jijiking.spring01.service.impl.TestDIServiceImpl">
                <constructor-arg index="0" ref="myDIDao"/>
        </bean>

        <!--<bean id="testDIService" class="cn.jijiking.spring01.service.impl.TestDIServiceImpl">-->
                <!--<property name="testDIDao" ref="myDIDao"/>-->
        <!--</bean>-->

</beans>
```

编写我们的测试类
```java
public class Test {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestDIService testDIService = (TestDIService) applicationContext.getBean("testDIService");
		testDIService.sayHello();
	}
}
```