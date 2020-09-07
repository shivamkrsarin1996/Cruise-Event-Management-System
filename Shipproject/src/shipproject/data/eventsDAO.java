package shipproject.data;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import shipproject.util.SQLConnection;
import shipproject.model.Events;

public class eventsDAO {

	static SQLConnection DBMgr = SQLConnection.getInstance();
	
	private static ArrayList<Events> ReturnMatchingCompaniesList (String queryString) {
		ArrayList<Events> companyListInDB = new ArrayList<Events>();
		
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			ResultSet companyList = stmt.executeQuery(queryString);
			while (companyList.next()) {
				Events company = new Events(); 
				company.setIdcompany(companyList.getString("idcompany"));
				company.setCompany_name(companyList.getString("company_name"));
				company.setPhone(companyList.getString("phone"));
				company.setEmail(companyList.getString("email"));  
				companyListInDB.add(company);	
			}
		} catch (SQLException e) {}
		return companyListInDB;
	}
	
	public static void insertCompany (Events company) {
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection();  
		try {
			stmt = conn.createStatement();
			String insertCompany = "INSERT INTO COMPANY (idcompany,company_name,phone,email,date_ins) " + " VALUES ('"  
					+ company.getIdcompany()  + "','"
					+ company.getCompany_name() + "','"		
					+ company.getPhone() + "','"
					+ company.getEmail() + "',"
					+ " SYSDATE())";
			stmt.executeUpdate(insertCompany);	
			conn.commit(); 
		} catch (SQLException e) {}
	}

	public static ArrayList<Events>  listCompanies() {  
			return ReturnMatchingCompaniesList(" SELECT * from COMPANY ORDER BY company_name");
	}
	
	//search companies
	public static ArrayList<Events>  searchCompanies(String companyname)  {  
			return ReturnMatchingCompaniesList(" SELECT * from COMPANY WHERE company_name LIKE '%"+companyname+"%' ORDER BY idcompany");
	}
	
	//determine if companyID is unique
	public static Boolean CompanyIDunique(String idComp)  {  
			return (ReturnMatchingCompaniesList(" SELECT * from COMPANY WHERE IDCOMPANY = '"+idComp+"' ORDER BY company_name").isEmpty());
	}
	//search company with company ID
	public static ArrayList<Events>   searchCompany (String idComp)  {  
			return ReturnMatchingCompaniesList(" SELECT * from COMPANY WHERE IDCOMPANY = '"+idComp+"' ORDER BY company_name");
	}
}