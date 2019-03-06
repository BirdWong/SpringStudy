package spring.proxyfactorybean;

import dynamic.jdk.TestDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author h4795
 * @className ProxyFactoryBeanTest
 * @description TODD
 * @date 2019/3/6 0006 19:16
 */
public class ProxyFactoryBeanTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestDao testDao = (TestDao) applicationContext.getBean("testDaoProxy");

		testDao.save();
		testDao.nodify();
		testDao.delete();
	}
}
