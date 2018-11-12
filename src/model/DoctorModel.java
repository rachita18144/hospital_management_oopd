package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DoctorModel {
	static ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
	public static ArrayList<Doctor> getAllDoctorDetailsForCategory(String category) {
		try {
			 Connection conn = DriverManager.getConnection(
		               "jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM doctor WHERE specialization =?");
			 stmt.setString(1, category);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) {
				Doctor doctor = new Doctor(rs.getString("first_name"),rs.getString("last_name"),rs.getString("address"),
						rs.getString("contact"),rs.getString("dob"),rs.getString("gender")); 
				doctor.setDrID(rs.getString("dID"));
				doctor.setEducation(rs.getString("education"));
				doctor.setExperience(rs.getString("experience"));
				doctor.setBio(rs.getString("bio"));
				doctorList.add(doctor);
			 }
		}catch(Exception e) {
			e.printStackTrace();
		}
	return doctorList;
	} 
}
