package cn.jijiking.spring01;

import cn.jijiking.spring01.dao.TestDIDao;
import cn.jijiking.spring01.service.TestDIService;
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


		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		TestDIService testDIService = (TestDIService) applicationContext.getBean("testDIService");
		testDIService.sayHello();


	}
}
