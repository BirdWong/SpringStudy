package cn.jijiking51.instance;

/**
 * @author h4795
 * @className BeanStaticFactory
 * @description TODD
 * @date 2019/3/5 0005 14:44
 */
public class BeanStaticFactory {

	private static BeanClass beanInstance = new BeanClass("调用静态工厂方法实例化Bean");

	public static BeanClass createInstance(){
		return beanInstance;
	}
}
