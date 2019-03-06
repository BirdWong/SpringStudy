package dynamic.cglib;

/**
 * @author h4795
 * @className CglibDynamicTest
 * @description TODD
 * @date 2019/3/6 0006 17:09
 */
public class CglibDynamicTest {
	public static void main(String[] args) {
		//创建代理对象
		CglibDynamicProxy cdp = new CglibDynamicProxy();

		//创建目标对象
		TestDao testDao = new TestDao();

		//获取增强后的目标对象
		TestDao testDaoAdvice = (TestDao) cdp.createProxy(testDao);

		//执行方法
		testDaoAdvice.save();
		System.out.println("=============");
		testDaoAdvice.nodify();
		System.out.println("=============");
		testDaoAdvice.delete();

	}
}
