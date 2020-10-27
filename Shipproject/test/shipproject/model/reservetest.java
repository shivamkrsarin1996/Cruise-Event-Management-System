package shipproject.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class reservetest {
	reserve reserve;
	reserveErrorMsgs reserveErrorMsgs;
	
	@Before
	public void setUp() throws Exception {
		reserve=new reserve();
		reserveErrorMsgs=new reserveErrorMsgs();
	}
	@Test
	@FileParameters("test/shipproject/model/reserve_test_cases.csv")
	public void test(int testcaseNo, String action,String userid,String reserveid,String createid,String error){
		reserve.setId_user(Integer.parseInt(userid));
		reserve.setIdreserve(Integer.parseInt(reserveid));
		reserve.setIdcreate(Integer.parseInt(createid));
		reserve.validatereservation(action, reserve, reserveErrorMsgs);
		assertTrue(error.equals(reserveErrorMsgs.getErrorMsg()));
	}

}
