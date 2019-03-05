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
		BeanClass b1 = (BeanClass) applicationContext.getBean("constructorInstance");
		BeanClass b2 = (BeanClass) applicationContext.getBean("constructorInstance");
		System.out.println(b1==b2);
	}
}
