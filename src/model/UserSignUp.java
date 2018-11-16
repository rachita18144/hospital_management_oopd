package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class UserSignUp {

	public static boolean storeSignUpDataForUser(Patient patient) {

		if(patient.getFirstName().equals("") || patient.getLastName().equals("") || patient.getPassword().equals("") || patient.getEmail().equals("")) {
			System.out.println("error");
			return false;
		}

		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement smt = conn.prepareStatement("insert into patient(first_name,last_name,email_id,age,address,contact,password,weight,height,type,category,pID)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			smt.setString(1, patient.getFirstName());
			smt.setString(2, patient.getLastName());
			smt.setString(3, patient.getEmail());
			smt.setInt(4, patient.getAge());
			smt.setString(5, patient.getAddress());
			smt.setLong(6, patient.getContact());
			smt.setString(7, patient.getPassword());
			smt.setFloat(8, patient.getWeight());
			smt.setFloat(9, patient.getHeight());
			smt.setString(10, patient.getType());
			smt.setString(11, patient.getCategory());
			smt.setString(12, getPatientId(patient.getCategory(),patient.getType()));


			int i = smt.executeUpdate();
			ResultSet rs = smt.getGeneratedKeys();
			int id = 0;
			if (rs.next()){
				id = rs.getInt(1);
			}
			savePatientIdInFile(Integer.toString(id));
			conn.close();

		}catch(Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	private static String getPatientId(String category, String type) {
		String patientId = "";
		switch(category) {
		case "cardiology" :{
			patientId = "CAR-";
			break;
		}
		case "orthopedics" :{
			patientId = "ORTH-";
			break;
		}
		case "gynaecology" :{
			patientId = "GYNAE-";
			break;
		}
		case "medicine" :{
			patientId = "MED-";
			break;
		}
		case "pedriatrician" :{
			patientId = "PED-";
			break;
		}
		case "ent" :{
			patientId = "ENT-";
			break;
		}
		}

		if(type.equals("opd")) {
			patientId = patientId + "OPD";	
		}else {
			patientId = patientId + "LOC";	
		}
		return patientId;
	}

	public static String checkUserAuthenticationDetails(String username, String password) {

		if(username.equals("") || password.equals("")) {
			return "invalid";
		}

		if(username.equals("admin") && password.equals("admin")) {
			return "admin";
		}

		boolean isPatient = checkifUserPatient(username, password);
		if(isPatient) {
			return "patient";
		}

		boolean isDoctor = checkifUserDoctor(username, password);
		if(isDoctor) {
			return "doctor";
		}
		return "invalid";

	}

	private static boolean checkifUserDoctor(String username, String password) {
		boolean isAuthentic = false;

		if(username.equals("") || password.equals("")) {
			return false;
		}

		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement stmt = conn.prepareStatement("SELECT email_id, password, dID FROM doctor WHERE email_id =?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(!rs.first()) {
				return false;
			}

			String email = rs.getString("email_id");
			saveDoctorIdInFile(rs.getString("id"));
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

	private static void saveDoctorIdInFile(String dID) {
		File f = new File("d_id.txt");
		if(f.exists()){
			f.delete(); 
		}
		try {
			f.createNewFile();
			FileOutputStream fout = new FileOutputStream(f);
			fout.write(dID.getBytes(), 0, dID.length());
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean checkifUserPatient(String username, String password) {
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
