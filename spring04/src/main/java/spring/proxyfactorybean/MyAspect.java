package spring.proxyfactorybean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author h4795
 * @className MyAspect
 * @description TODD
 * @date 2019/3/6 0006 19:02
 */
public class MyAspect implements MethodInterceptor {




	public void check(){
		System.out.println("模拟权限控制");
	}
	public void except(){
		System.out.println("模拟异常处理");
	}
	public void log(){
		System.out.println("模拟日志记录");
	}
	public void monitor(){
		System.out.println("性能监控");
	}


	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		//增强方法
		check();
		except();
		//执行目标方法
		Object obj = methodInvocation.proceed();
		//增强方法
		log();
		monitor();
		return obj;
	}

}
