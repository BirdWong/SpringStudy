package cn.jijiking.spring01;

import cn.jijiking.spring01.dao.TestDao;
import cn.jijiking.spring01.dao.impl.TestDaoImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import java.io.File;

/**
 * @author h4795
 * @className Test
 * @description TODD
 * @date 2019/2/27 0027 15:32
 */
public class Test {

	public static void main(String[] args) {
//		TestDao dao = new TestDaoImpl();
//		String s = dao.sayHello();
//		System.out.println(s);


//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		TestDao testDao = applicationContext.getBean("testDao", TestDao.class);
//		String s = testDao.sayHello();
//		System.out.println(s);


		BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource(new File("G:\\WorkProjeckts\\IdeaProjects\\spring\\spring01\\src\\main\\resources\\applicationContext.xml")));
		TestDao testDao = beanFactory.getBean("testDao", TestDao.class);
		System.out.println(testDao.sayHello());
	}
}
