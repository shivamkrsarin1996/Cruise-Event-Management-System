package shipproject.model;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class usertest {
	user usr;
	userErrorMsgs usrerror;
	
	@Before
	public void setUp() throws Exception {
		usr=new user();
		usrerror=new userErrorMsgs();
	}
	
	@Test
	@FileParameters("test/shipproject/model/User_test_cases.csv")
	public void test(int testcaseNo, String action,String id_user,String username,String first_name, String last_name, String password,String cpassword,String role, String phone,String email,String memtype,String room_number,String deck_number,
			String cfirst,String clast,String cpass,String cemail,String cmem,String cphone,String cdeck,String croom,
			String errorMsg,String usernameError,String first_nameError,String last_nameError,String passwordError,String cpasswordError,String phoneError,String emailError,String room_numberError,String deck_numberError) {
		usr.setUser(username, first_name, last_name, password, role, phone, email, memtype, room_number, deck_number);
		 if(!action.equals("updateProfile")) {
			usr.setCpassword(cpassword);
			usr.validateUser(action, usr, usrerror);
			assertTrue(errorMsg.equals(usrerror.getErrorMsg()));
			assertTrue(usernameError.equals(usrerror.getUsernameError()));
			assertTrue(first_nameError.equals(usrerror.getFirst_nameError()));
			assertTrue(last_nameError.equals(usrerror.getLast_nameError()));
			assertTrue(passwordError.equals(usrerror.getPasswordError()));
			assertTrue(cpasswordError.equals(usrerror.getCpasswordError()));
			assertTrue(phoneError.equals(usrerror.getPhoneError()));
			assertTrue(emailError.equals(usrerror.getEmailError()));
			assertTrue(room_numberError.equals(usrerror.getRoom_numberError()));
			assertTrue(deck_numberError.equals(usrerror.getDeck_numberError()));
		}
		else {
			usr.validateChange(action, usr, usrerror, cfirst, clast, cpass, cemail, cmem, cphone, cdeck, croom);
			assertTrue(errorMsg.equals(usrerror.getErrorMsg()));
			assertTrue(cpasswordError.equals(usrerror.getCpasswordError()));
		}
	}
}
