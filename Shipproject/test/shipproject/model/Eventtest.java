package shipproject.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class Eventtest {
	Events event;
	EventsErrorMsgs errorMsg;

	@Before
	public void setUp() throws Exception {
		event=new Events();
		errorMsg=new EventsErrorMsgs();
	}
	@Test
	@FileParameters("test/shipproject/model/event_test_cases1.csv")
	public void test(int testcaseNo, String action,String eventname,String location,String capacity,String duration,String type,String managerid,
		String id_event,String idcreate,String date,String time,String estCap,String cdate,String ctime,String cestcap,String ccorrid,String error,String esterror,String dateerror,String timeError,String corerror) throws ParseException {
		String OLD_FORMAT = "MM/dd/yyyy";
		String NEW_FORMAT = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		SimpleDateFormat dsf = new SimpleDateFormat(OLD_FORMAT);
		if(!date.equals("")) {
		Date d = sdf.parse(date);
		sdf.applyPattern(NEW_FORMAT);
		date = sdf.format(d);
		}
		if(!cdate.equals("")) {
		Date d2 = dsf.parse(cdate);
		dsf.applyPattern(NEW_FORMAT);
		cdate=sdf.format(d2);
		}
		
		if(action.equals("Eventmanagermodifyevent")) {
			event.setEvent(eventname, location, capacity, duration, type, date, managerid, time, Integer.parseInt(id_event), Integer.parseInt(idcreate), estCap);
			event.validateEventModify(action, event, errorMsg, cdate, ctime, cestcap);
			assertTrue(error.equals(errorMsg.getErrorMsg()));
			assertTrue(esterror.equals(errorMsg.getEstCap()));
			assertTrue(dateerror.equals(errorMsg.getDate()));
			assertTrue(timeError.equals(errorMsg.getTime()));
			assertTrue(corerror.equals(errorMsg.getManager()));
		}
		else if(action.equals("Eventmanagerassignevent")) {
			event.setEvent(eventname, location, capacity, duration, type, date, managerid, time, Integer.parseInt(id_event), Integer.parseInt(idcreate), estCap);
			event.validateEventCor(action, event, errorMsg, ccorrid);
			System.out.println(errorMsg.getManager()+" divide "+corerror);
			assertTrue(error.equals(errorMsg.getErrorMsg()));
			assertTrue(esterror.equals(errorMsg.getEstCap()));
			assertTrue(dateerror.equals(errorMsg.getDate()));
			assertTrue(timeError.equals(errorMsg.getTime()));
			assertTrue(corerror.equals(errorMsg.getManager()));
		}
		else {
			event.setDate(date);
			event.setTime(time);
			event.validateEvent(action, event, errorMsg);
			assertTrue(error.equals(errorMsg.getErrorMsg()));
		}
	}

}
