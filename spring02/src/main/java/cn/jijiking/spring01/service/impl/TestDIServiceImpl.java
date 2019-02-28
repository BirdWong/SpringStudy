package cn.jijiking.spring01.service.impl;

import cn.jijiking.spring01.dao.TestDIDao;
import cn.jijiking.spring01.service.TestDIService;

/**
 * @author h4795
 * @className TestDIServiceImpl
 * @description TODD
 * @date 2019/2/28 0028 10:41
 */
public class TestDIServiceImpl implements TestDIService {

	private TestDIDao testDIDao;

	public TestDIServiceImpl(TestDIDao diDao){
		super();
		this.testDIDao = diDao;
	}

//	public void setTestDIDao(TestDIDao testDIDao){
//		this.testDIDao = testDIDao;
//	}

	public void sayHello() {
		testDIDao.sayHello();
	}
}
