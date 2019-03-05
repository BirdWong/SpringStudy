package cn.jijiking51.instance;

/**
 * @author h4795
 * @className BeanInstanceFactory
 * @description TODD
 * @date 2019/3/5 0005 14:44
 */
public class BeanInstanceFactory {

	public BeanClass createBeanClassInstance(){
		return new BeanClass("调用工厂实例化Bean");
	}
}
