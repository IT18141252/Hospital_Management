package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospitals {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return con;
	}

	public String insertHospital(String Id, String HName, String address, String email, String Tphone)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into payment1(`Id`,`HName`,`address`,`email`,`Tphone`)" + " values (?, ?, ?, ?, ?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, Id);    
			preparedStmt.setString(3, HName);    
			preparedStmt.setString(4, address);    
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, Tphone);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newHospital = readHospital(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
					newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	

	public String readHospital()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Hospital ID</th><th>Hospital Name</th><th>Address</th><th>Email</th><th>Telephone</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from hospital1";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (next())    
			{     
				String Id = Integer.toString(rs.getInt("Id"));     
				String HName = getString("HName");     
				String address = getString("address");     
				String email = getString("email");     
				String Tphone = getString("pDate"); 
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidPayIDUpdate\' name=\'hidPayIDUpdate\' type=\'hidden\' value=\'" + Id + "'>" 
							+ HName + "</td>";      
				output += "<td>" + address + "</td>";     
				output += "<td>" + email + "</td>";     
				output += "<td>" + Tphone + "</td>"; 
	 
				// buttons     
//				output += "<td><input name=\'btnUpdate\' type=\'button\' value=\'Update\' class=\' btnUpdate btn btn-secondary\'></td>"
//						//+ "<td><form method=\"post\" action=\"Hospital.jsp\">      "
//						+ "<input name=\'btnRemove\' type=\'submit\' value=\'Remove\' class=\'btn btn-danger\'> "
//						+ "<input name=\"hidPayIDDelete\" type=\"hidden\" value=\"" + Id + "\">" + "</form></td></tr>"; 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-HospitalId='" + Id + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the payments.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	private String getString(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private boolean next() {
		// TODO Auto-generated method stub
		return false;
	}

	public String updateHospital(String Id, String HName, String address, String email, String Tphone)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE hospital1 SET HName=?,address=?,email=?,Tphone=? WHERE Id=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, Id);    
			preparedStmt.setString(2, HName);    
			preparedStmt.setString(3, address);    
			preparedStmt.setString(4, email);    
			preparedStmt.setString(5, Tphone); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospital = readHospital();    
			output = "{\"status\":\"success\", \"data\": \"" +        
									newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deletePayment(String Id)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from hospital1 where Id=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(Id)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newHospital = readHospital();    
			output = "{\"status\":\"success\", \"data\": \"" +       
								newHospital + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the payment.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}