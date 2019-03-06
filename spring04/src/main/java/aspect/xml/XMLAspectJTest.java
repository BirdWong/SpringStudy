package aspect.xml;

import dynamic.jdk.TestDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author h4795
 * @className XMLAspectJTest
 * @description TODD
 * @date 2019/3/6 0006 20:25
 */
public class XMLAspectJTest {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestDao testDao = (TestDao) applicationContext.getBean("testDao");
		testDao.save();
	}
}
