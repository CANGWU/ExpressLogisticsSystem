package Junit_test;

import org.junit.Test;

import mocklog.MockLogInfo;

public class LogListTest {

	@Test
	public void test() {
		//fail("Not yet implemented");
		MockLogInfo loginfo=new MockLogInfo();
		loginfo.createLog("张三，快递员，鼓楼营业厅，订单输入，2015/11/11");
	}

}
