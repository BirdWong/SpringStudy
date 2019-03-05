package cn.jijiking51.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author h4795
 * @className BeanClass
 * @description TODD
 * @date 2019/3/5 0005 14:43
 */
@Data
@AllArgsConstructor
public class BeanClass {
	public String message;

	public BeanClass(){
		this.message = "sucess!";
	}
}
