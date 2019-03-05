# Sprig Bean

## Bean的配置
|属性或子元素名称|描述|
|---|---|
|id| Bean在BeanFactory中的唯一标示，在代码中通过BeanFactory获取Bean实例需要以此作为索引名称|
|class| Bean的具体实现类，使用类的名|
|scope| 指定Bean实例的作用域|
|<constructor-arg>| <bean<元素的子元素，使用构造方法注入，指定构造方法的参数，改元素的index属性指定参数的序号，ref属性指定对BeanFactory中其他Bean的引用关系，type属性指定参数类型，value属性指定参数的常量值|
|<property>|<bean>元素的子元素，用于设置一个属性。该元素的name属性指定Bean实例中相应的属性名称，value属性指定Bean的属性值，ref属性指定属性对BeanFactory中其他Bean的引用关系|
|\<map\>|<property元素的子元素，用于封装Map类型的依赖注入|
|\<list\>|<property元素的子元素，用于封装List类型的依赖注入|
|\<set\>|<property>元素的子元素，用于封装Set类型的依赖注入|
|\<entry\>|<map>元素的子类，用于设置一个键值对时|


## Bean的实例化

文件目录
```text
spring03
│  pom.xml
│  README.md
│ 
├─src
│  ├─main
│  │  ├─java
│  │  │  └─cn
│  │  │      └─jijiking51
│  │  │          ├─instance
│  │  │          │      BeanClass.j
│  │  │          │      BeanInstanc
│  │  │          │      BeanStaticF
│  │  │          │
│  │  │          └─test
│  │  │                  TestInstanc
│  │  │
│  │  └─resources
│  │          application-context.xml
```


### 构造方法实例化


1. 创建BeanClass

```java
package cn.jijiking51.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author h4795
 * @className BeanClass
 * @description TODD
 * @date 2019/3/5 0005 14:43
 */
@Data
@AllArgsConstructor
public class BeanClass {
	public String message;

	public BeanClass(){
		this.message = "sucess!";
	}
}


```


2. 创建配置文件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">


        <bean id="constructorInstance" class="cn.jijiking51.instance.BeanClass"/>

</beans>
```

3. 创建测试类
```java
package cn.jijiking51.test;

import cn.jijiking51.instance.BeanClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author h4795
 * @className TestInstance
 * @description TODD
 * @date 2019/3/5 0005 14:44
 */
public class TestInstance {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
		BeanClass constructorInstance = (BeanClass) applicationContext.getBean("constructorInstance");
		System.out.println(constructorInstance.getMessage());
	}
}

```


### 静态工厂实例化

1. 创建工厂类BeanStaticFactory
```java
package cn.jijiking51.instance;

/**
 * @author h4795
 * @className BeanStaticFactory
 * @description TODD
 * @date 2019/3/5 0005 14:44
 */
public class BeanStaticFactory {

	private static BeanClass beanInstance = new BeanClass("调用静态工厂方法实例化Bean");

	public static BeanClass createInstance(){
		return beanInstance;
	}
}

```

2. 修改配置文件
```xml
<bean id="staticFactoryInstance" class="cn.jijiking51.instance.BeanStaticFactory" factory-method="createInstance"/>
```

3. 添加测试代码
```java
        BeanClass bean = (BeanClass) applicationContext.getBean("staticFactoryInstance");
		System.out.println(bean.getMessage());
```

### 实例工厂实例化
1. 创建工厂类BeanInstanceFactory
```java
package cn.jijiking51.instance;

/**
 * @author h4795
 * @className BeanInstanceFactory
 * @description TODD
 * @date 2019/3/5 0005 14:44
 */
public class BeanInstanceFactory {

	public BeanClass createBeanClassInstance(){
		return new BeanClass("调用工厂实例化Bean");
	}
}

```

2. 修改配置文件
```xml
        <bean id="myFactory" class="cn.jijiking51.instance.BeanInstanceFactory"/>

        <!--使用factory-bean属性指定配置工厂，使用factory-method属性指定使用工厂中的那个方法实例化bean-->
        <bean id="instanceFactoryInstance" factory-bean="myFactory" factory-method="createBeanClassInstance"/>
```

3. 修改测试代码
```java
	BeanClass bean = (BeanClass) applicationContext.getBean("instanceFactoryInstance");
    System.out.println(bean.getMessage());
	
```

## Bean的作用域
|作用域名称|描述|
|---|---|
|singleton|默认的作用域，使用singleton定义的Bean在Spring容器中只有一个Bean实例|
|prototype|Spring容器每次获取prototype定义的bean，容器都将创建一个新的Bean实例|
|request|在一次Http请求中容器将返回yigeBean实例，不同的Http请求返回不同的Bean实例，仅在WebSpring应用程序上下文中使用|
|session|在一个Http Session中，容器将返回同一个Bean实例，仅在WebSpring应用程序上下文中使用|
|application|为每个ServletContext对象创建一个实例，即同一个应用共享一个Bean实例。仅在WebSpring应用程序上下文中使用|
|websocket|为每个WebSocket对象创建一个Bean实例。jinzaiWebSpring应用程序上下文中使用|

### singleton作用域

1. 将bean设置singleton
```xml
    <!--默认配置-->
    <bean id="constructorInstance" class="cn.jijiking51.instance.BeanClass"/>
    <!--手动配置-->
    <bean id="constructorInstance" class="cn.jijiking51.instance.BeanClass" scope="singleton"/>
```


2. 编写测试代码
```java
    public static void main(String[] args) {
    		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
    		BeanClass b1 = (BeanClass) applicationContext.getBean("constructorInstance");
    		BeanClass b2 = (BeanClass) applicationContext.getBean("constructorInstance");
    		System.out.println(b1==b2);
    }
```

`结论：在使用id或者name获取Bean实例时，IOC容器仅放回一个Bean实例`


### prototype作用域
1. 修改配置文件
```xml
        <bean id="constructorInstance" class="cn.jijiking51.instance.BeanClass" scope="prototype"/>
```

2. 运行测试文件
`结论： 在使用id或name两次获取Bean实例时，Ioc容器将返回两个不同的Bean实例`


## Bean的生命周期
Bean的生命周期的整个过程如下：
(1）根据Bean的配置情况实例化一个Bean。<br>
(2）根据Spring上下文对实例化的Bean进行依赖注入，即对Bean的属性进行初始化。<br>
(3）如果Bean实现了BeanNameAware接口，将调用它实现的setBeanName(Stringbeanld）方法，此处参数传递的是Spring配置文件中Bean的id。<br>
(4）如果Bean实现了BeanFactoryAware接口，将调用它实现的setBeanFactory方法，此处参数传递的是当前Spring工厂实例的引用。<br>
(5）如果Bean实现了ApplicationContextAware接口，将调用它实现的setApplicationContext(ApplicationContext）方法，此处参数传递的是Spring下文实例的引用。<br>
(6）如果Bean关联了BeanPostProcessor接口，将调用初始化方法postProcessBeforelnitialization(Objectobj,Strings）对Bean进行操作。<br>
(7）如果Bean实现了InitializingBean接口，将调用afterPropertiesSet方法。<br>
(8）如果Bean在Spring配置文件中配置了init-method属性，将自动调用其配置的初始化方法。<br>
(9）如果Bean关联了BeanPostProcessor接口，将调用postProcessAfterinitialization(Objectobj,Strings）方法，由于是在Bean初始化结束时调用A武er方法，也可用于内存或缓存技术。<br>
(10）当Bean不再需要时将进入销毁阶段，如果Bean实现了DisposableBean接口，则调用其实现的destroy方法将Spring中的Bean销毁。<br>
(11）如果在配置文件中通过destroy-method属性指定了Bean的销毁方法，将调用其配置的销毁方法进行销毁。<br>

## Bean的装配方式

1. XML配置
2. 注解配置

    `一定要配置包扫描`
    ```xml
           <context:component-scan base></context:component-scan base-package="Bean所在的包路径"/>
    ```
    1. @Component
        一个泛化的概念，仅仅表示一个组件对象，可以作用在任何层次上
    2. @Repository
        用于访问层（DAO）的类标识Bean
    3. @Service
        用于标注一个业务逻辑组件类（service层）
    4. @Controller
        标注一个控制器组件类（Spring MVC的Controller）
    5. @Resource
        注解与＠Autowired的功能一样，区别在于该注解默认是按照名称来装配注入的，只有当找不到与名称匹配的Bean时才会按照、类型来装配注入；而＠Autowired默认按照Bean的类型进行装配，如果想按照名称来装配注入，则需要和＠Qualifier注解一起使用。@Resource注解有两个属性一－name和type。name属性指定Bean实例名称，即按照、名称来装配注入；可pe属性指定Bean类型，即按照Bean的类型进行装配。
    6. @Qualifler
        注解与＠Autowired注解配合使用。当＠Autowired注解需要按照名称来装配注入时需要和该注解一起使用，Bean的实例名称由＠Qualifier注解的参数指定。
