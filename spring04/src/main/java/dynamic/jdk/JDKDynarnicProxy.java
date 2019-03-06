package dynamic.jdk;

import aspect.MyAspect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author h4795
 * @className JDKDynarnicProxy
 * @description TODD
 * @date 2019/3/6 0006 16:20
 */
public class JDKDynarnicProxy implements InvocationHandler {

	private TestDao testDao;

	public Object createProxy(TestDao testDao){
		this.testDao = testDao;

		//1. 类加载器
		ClassLoader cld = JDKDynarnicProxy.class.getClassLoader();

		//2. 被代理对象实现的所有借口
		Class[] clazz = testDao.getClass().getInterfaces();

		//3. 使用代理类增强，返回代理后的对象
		return Proxy.newProxyInstance(cld,clazz,this);
	}


	/*
	 * 代理的逻辑方法，所有动态代理类的方法调用都交给该方法处理
	 * proxy是被代理对象
	 * method是将要被执行的方法
	 * args是执行方法时市耍的参数
	 * return指返回代理结果
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//1. 创建一个切面
		MyAspect myAspect = new MyAspect();
		//2. 前增强
		myAspect.check();
		myAspect.except();

		//3. 在目标类上调用方法并传入参数，相当于调用TestDao中的方法
		Object obj = method.invoke(testDao, args);

		//4. 后增强
		myAspect.log();
		myAspect.monitor();
		return obj;
	}




}
