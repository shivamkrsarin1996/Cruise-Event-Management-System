package shipproject.model;

import java.io.Serializable;
import java.util.ArrayList;

import shipproject.data.eventsDAO;
import shipproject.data.reserveDAO;

public class reserve implements Serializable{
	private static final long serialVersionUID = 3L;
	private int id_user;
	private int idreserve;
	private int idcreate;
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getIdreserve() {
		return idreserve;
	}
	public void setIdreserve(int idreserve) {
		this.idreserve = idreserve;
	}
	public int getIdcreate() {
		return idcreate;
	}
	public void setIdcreate(int idcreate) {
		this.idcreate = idcreate;
	}
//validation 
	
	public void validatereservation(String action,reserve reserve,reserveErrorMsgs errormsg) {
			System.out.println("reach");
			errormsg.setErrorMsg(validatetypecap(reserve.getId_user(),reserve.getIdcreate()));
			if(errormsg.getErrorMsg().equals("")) {
				errormsg.setErrorMsg(validateCap(reserve.getIdcreate()));
				if(errormsg.getErrorMsg().equals("")) {
					errormsg.setErrorMsg(validatereg(reserve.getIdcreate(),reserve.getId_user()));
				}
			}
	}
	private String validatetypecap(int userid,int createid) {
		ArrayList<Events> eventInDBs = new ArrayList<Events>();
		ArrayList<reserve> reserveinDB=new ArrayList<reserve>();
		Events selectedevent = new Events();
		eventInDBs=eventsDAO.searchevent(createid);
		selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate(),eventInDBs.get(0).getEstCap());
		String result="";
		reserveinDB=reserveDAO.typeDateSearch(selectedevent.getType(),selectedevent.getDate(), userid);
		if(selectedevent.getType().equals("Athletic")) {
			if(reserveinDB.size()>=2) {
				result="you cant reserve more then 2 Athletic events per day";
			}
		}
		else {
			if(reserveinDB.size()>=1) {
				result="you cant reserve more then 1 Show event per day";
			}
		}
		return result;
	}
	private String validateCap(int createid) {
		String result="";
		ArrayList<Events> eventInDBs = new ArrayList<Events>();
		eventInDBs=eventsDAO.searchevent(createid);
		Events selectedevent = new Events();
		selectedevent.setEvent(eventInDBs.get(0).getEventname(), eventInDBs.get(0).getLocation(),eventInDBs.get(0).getCapacity(), eventInDBs.get(0).getDuration(),  eventInDBs.get(0).getType(),  eventInDBs.get(0).getDate(),  eventInDBs.get(0).getManagerid(),eventInDBs.get(0).getTime(), eventInDBs.get(0).getId_event(), eventInDBs.get(0).getIdcreate(),eventInDBs.get(0).getEstCap());
		ArrayList<reserve> reserveinDB=new ArrayList<reserve>();
		reserveinDB=reserveDAO.capSearch(createid);
		int cap=Integer.parseInt(selectedevent.getCapacity());
		if(reserveinDB.size()>=cap) {
			result="This event capasity is full";
		}		
		return result;
	}
	private String validatereg(int createid,int userid) {
		String result="";
		ArrayList<reserve> reserveinDB=new ArrayList<reserve>();
		reserveinDB=reserveDAO.regSearch(createid, userid);
		System.out.println(createid);
		System.out.println(userid);
		if(reserveinDB.size()>0) {
			result="you have already registered for this event";
		}
		return result;
	}
}
