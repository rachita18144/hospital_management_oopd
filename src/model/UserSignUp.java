package model;

import java.sql.*;

public class UserSignUp {

	public static boolean storeSignUpDataForUser(String firstName, String lastName, String passwordValue,
			String addressValue, String contactDetails, String dateOfBirth, String emailId) {
		
		if(firstName.equals("") || lastName.equals("") || passwordValue.equals("") || addressValue.equals("") || contactDetails.equals("") || dateOfBirth.equals("") || emailId.equals("")) {
			System.out.println("error");
			return false;
		}
		
	try {
		 Connection conn = DriverManager.getConnection(
	               "jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
		 PreparedStatement smt = conn.prepareStatement("insert into patient(first_name,last_name,email_id,dob,address,contact_details,password) "
		 		+ "values(?,?,?,?,?,?,?)");
		 smt.setString(1, firstName);
		 smt.setString(2, lastName);
		 smt.setString(3, emailId);
		 smt.setString(4, dateOfBirth);
		 smt.setString(5, addressValue);
		 smt.setInt(6, Integer.parseInt(contactDetails));
		 smt.setString(7, passwordValue);
		 
		 int i = smt.executeUpdate();
		 System.out.println(i);
		 conn.close();
		 
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return true;
	}
	
	public static boolean checkUserAuthenticationDetails(String username, String password) {
		if(username.equals("") || password.equals("")) {
			return false;
		}
		
		try {
			 Connection conn = DriverManager.getConnection(
		               "jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			 PreparedStatement stmt = conn.prepareStatement("SELECT email_id, password FROM patient WHERE email_id =?");
			 stmt.setString(1, username);
			 ResultSet rs = stmt.executeQuery();
			 if(!rs.first()) {
				 return false;
			 }
			 
			 String email = rs.getString("email_id");
			 String verifyPass = rs.getString("password");
			 if(email.equals(username) && verifyPass.equals(password)) {
				System.out.println("correct");
			 }else {
				System.out.println("wrong values");
				return false;
			}
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
