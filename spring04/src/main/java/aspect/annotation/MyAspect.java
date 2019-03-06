package aspect.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author h4795
 * @className MyAspect
 * @description TODD
 * @date 2019/3/6 0006 20:41
 */
//对应<aop:aspect ref="myAspect">
@Aspect
//对应<bean id="myAspect" class="aspectj.xml.MyAspect"/>
@Component
public class MyAspect {
	/**
	 * 定义切入点
	 */
	@Pointcut("execution(* dynamic.jdk.*.*(..))")
	private void myPointCut(){
		//对应  <aop:pointcut id="myPointCut" expression="execution(* dynamic.jdk.*.*(..))"/>
	}

	/**
	 * 前置通知，使用Joinpoint接口作为参数获得目标对象信息
	 */
	@Before("myPointCut())")
	//对应             <aop:before method="before" pointcut-ref="myPointCut" />
	public void before(JoinPoint jp){
		System.out.println("前置通知：模拟权限控制");
		System.out.println(", 目标类对象：" + jp.getTarget()
		+ ", 被增强处理的方法，" + jp.getSignature().getName());
	}


	/**
	 * 后置返回通知
	 * @param jp
	 */
	@AfterReturning("myPointCut()")
	//对应         <aop:after-returning method="afterReturning" pointcut-ref="myPointCut"/>
	public void afterReturning(JoinPoint jp){
		System.out.println("后置返回通知：" + "模拟删除临时文件");
		System.out.println(". 被增强处理的方法:" + jp.getSignature().getName());
	}


	/**
	 * 环绕通知*ProceedingJoinPoint是J o i n P o工n t的子接口，代表可以执行的目标方法
	 * 返回值的类型必须是Object
	 * 必须一个参数是ProceedingJoinPoint类型
	 * 必须throwsThrowable
	 */
	@Around("myPointCut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		//开始
		System.out.println("环绕开始：执行目标方法前，模拟开启事物");
		//执行当前目标方法
		Object obj = pjp.proceed();
		//结束
		System.out.println("环绕结束：执行目标方法后，模拟关闭事物");
		return obj;
	}

	/**
	 * 异常通知
	 */
	@AfterThrowing(value = "myPointCut()", throwing = "e")
	public void except(Throwable e){
		System.out.println("异常通知：" + "程序执行异常" + e.getMessage());
	}

	/**
	 * 后置（最终）通知
	 */
	@After("myPointCut()")
	public void after(){
		System.out.println("最终通知：模拟释放资源");
	}


}

