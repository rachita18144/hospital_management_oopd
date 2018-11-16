package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetPatientDetailsModel {

	public static Patient getPatientDataFromID(String id) {
		Patient patient = new Patient();
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM patient WHERE id =?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("first_name"));
				patient.setFirstName(rs.getString("first_name"));	
				patient.setLastName(rs.getString("last_name"));	
				patient.setEmail(rs.getString("email_id"));	
				patient.setAddress(rs.getString("address"));	
				patient.setContact(rs.getLong("contact"));	
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return patient;
	}

	public static void updatePatientData(Patient data) {
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");

			PreparedStatement stmt = conn.prepareStatement("UPDATE patient SET first_name=?, last_name=?, email_id=?, address=?, contact=? WHERE id=?");
			stmt.setString(1, data.getFirstName());
			stmt.setString(2, data.getLastName());
			stmt.setString(3, data.getEmail());
			stmt.setString(4, data.getAddress());
			stmt.setLong(5, data.getContact());
			stmt.setInt(6, Integer.parseInt(data.getId()));
			int i = stmt.executeUpdate();
			System.out.println(i);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPatientIdFromId(String id) {
		String pID = "";
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement stmt = conn.prepareStatement("SELECT pID FROM patient WHERE id =?");
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				pID = rs.getString("pID");
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return pID;
	}
}
