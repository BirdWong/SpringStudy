package dynamic.cglib;

import aspect.MyAspect;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author h4795
 * @className CglibDynamicProxy
 * @description TODD
 * @date 2019/3/6 0006 17:01
 */
public class CglibDynamicProxy implements MethodInterceptor {

	/**
	 * 创建代理的方法，生成CGLIB代理对象
	 * target是目标对象，需要增强的西乡
	 * 返回目标对象的CGLIB代理对象
	 * @param target
	 * @return
	 */
	public Object createProxy(Object target){
		//创建一个动态类对象，即增强类对象
		Enhancer enhancer = new Enhancer();
		//确定需要增强的类，设置其父类
		enhancer.setSuperclass(target.getClass());
		//确定代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor的方法
		enhancer.setCallback(this);
		return enhancer.create();
	}

	/**
	 * @param o
	 * @param method
	 * @param objects
	 * @param methodProxy
	 * @return
	 * @throws Throwable
	 */
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		//创建一个切面
		MyAspect myAspect = new MyAspect();
		//前增强
		myAspect.check();
		myAspect.except();
		//目标方法执行，返回代理结果
		Object obj = methodProxy.invokeSuper(o,objects);
		//后增强
		myAspect.log();
		myAspect.monitor();
		return obj;
	}
}
