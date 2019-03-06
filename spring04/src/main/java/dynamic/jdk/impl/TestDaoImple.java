package dynamic.jdk.impl;

import dynamic.jdk.TestDao;
import org.springframework.stereotype.Repository;

import javax.xml.ws.RespectBinding;

/**
 * @author h4795
 * @className TestDaoImple
 * @description TODD
 * @date 2019/3/6 0006 16:06
 */
@Repository("testDao")
public class TestDaoImple implements TestDao {

	public void save() {
		System.out.println("保存");
	}

	public void nodify() {
		System.out.println("修改");
	}

	public void delete() {
		System.out.println("删除");
	}
}
