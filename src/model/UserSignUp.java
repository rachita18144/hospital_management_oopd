package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
		boolean isAuthentic = false;
		if(username.equals("") || password.equals("")) {
			return false;
		}

		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement stmt = conn.prepareStatement("SELECT email_id, password, id FROM patient WHERE email_id =?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(!rs.first()) {
				return false;
			}

			String email = rs.getString("email_id");
			savePatientIdInFile(rs.getString("id"));
			String verifyPass = rs.getString("password");
			if(email.equals(username) && verifyPass.equals(password)) {
				System.out.println("correct");
				isAuthentic = true;
			}else {
				System.out.println("wrong values");
				return false;
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return isAuthentic;
	}
	public static void savePatientIdInFile(String id) {
		File f = new File("p_id.txt");
		if(f.exists()){
			f.delete(); 
		}
		try {
			f.createNewFile();
			FileOutputStream fout = new FileOutputStream(f);
			fout.write(id.getBytes(), 0, id.length());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}





