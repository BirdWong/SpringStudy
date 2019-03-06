package dynamic.jdk;

import dynamic.jdk.impl.TestDaoImple;

/**
 * @author h4795
 * @className JDKDynamicTest
 * @description TODD
 * @date 2019/3/6 0006 16:41
 */
public class JDKDynamicTest {

	public static void main(String[] args) {
		//创建代理对象
		JDKDynarnicProxy jdkDynarnicProxy = new JDKDynarnicProxy();
		//创建目标对象
		TestDao testDao = new TestDaoImple();
		/**
		 * 从代理对象中获取增强后的目标对象，该对象是一个被代理的对象，它会进入代理的逻辑方法invoke中
		 */
		TestDao testDaoAdvice = (TestDao) jdkDynarnicProxy.createProxy(testDao);

		testDaoAdvice.save();
		System.out.println("=============");
		testDaoAdvice.nodify();
		System.out.println("=============");
		testDaoAdvice.delete();
	}
}
