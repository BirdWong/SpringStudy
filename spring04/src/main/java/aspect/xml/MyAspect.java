package aspect.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.ThrowsAdvice;

/**
 * @author h4795
 * @className MyAspect
 * @description TODD
 * @date 2019/3/6 0006 20:08
 */
public class MyAspect {
	/**
	 * 前置通知，使用JoinPoint接口作为参数获得目标对象信息
	 */
	public void before(JoinPoint jp){
		System.out.println("前置通知，模拟权限控制");
		System.out.println("，目标对象："+jp.getTarget()
		+ ", 被增强处理的方法：" + jp.getSignature().getName());
	}

	/**
	 * 后置返回通知
	 */
	public void afterReturning(JoinPoint jp){
		System.out.println("后置返回通知：" + "模拟删除临时文件");
		System.out.println(", 被增强处理的方法：" + jp.getSignature().getName());
	}

	/**
	 * 环绕通知
	 * ProceedingJoinPoint是JoinPoint的子接口，代表可以执行的目标方法
	 * 返回值的类型必须是Object
	 * 必须一个参数是ProceedingJoinPoint类型
	 * 必须throwsThrowable
	 */
	public Object around(ProceedingJoinPoint pjp)throws Throwable{
		//开始
		System.out.println("环绕开始： 执行目标方法前，模拟开启事务");
		//执行当前目标方法
		Object obj = pjp.proceed();
		//结束
		System.out.println("环绕结束：执行目标方法后，模拟关闭事物");

		return obj;
	}

	/**
	 * 异常通知
	 */
	public void except(Throwable e){
		System.out.println("异常通知：" + "程序执行异常" + e.getMessage());
	}

	/**
	 * 后置（最终）通知
	 */
	public void after(){
		System.out.println("最终通知：模拟示范资源");
	}

}

