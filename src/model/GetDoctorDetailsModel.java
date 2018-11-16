package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class GetDoctorDetailsModel {
	public static ArrayList<Doctor> getAllDoctorDetailsForCategory(String category) {
	ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement stmt = conn.prepareStatement("SELECT doctor.*, schedule.* FROM doctor, schedule WHERE schedule.dID = doctor.dID AND dept =?");
			stmt.setString(1, category);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Doctor doctor = new Doctor(rs.getString("first_name"),rs.getString("last_name"),rs.getString("address"),
						Long.parseLong(rs.getString("contact")),rs.getString("dob"),rs.getString("gender")); 
				doctor.setDrID(rs.getString("dID"));
				doctor.setEducation(rs.getString("education"));
				doctor.setExperience(rs.getFloat("experience"));
				doctor.setBio(rs.getString("bio"));
				doctor.setspecialization(rs.getString("dept"));
				doctor.setContact(Long.parseLong(rs.getString("contact")));
				doctor.setBio(rs.getString("bio"));
				doctor.setAddress(rs.getString("address"));

				//getting doctor schedule from db
				doctor.getSchedule().setStartTime(rs.getTime("s_time"));
				doctor.getSchedule().setEndTime(rs.getTime("e_time"));
				if(rs.getBoolean("is_monday")) {
					doctor.getSchedule().getDays().add("Mon");
				}
				if(rs.getBoolean("is_tuesday")) {
					doctor.getSchedule().getDays().add("Tue");
				}
				if(rs.getBoolean("is_wednesday")) {
					doctor.getSchedule().getDays().add("Wed");
				}
				if(rs.getBoolean("is_thursday")) {
					doctor.getSchedule().getDays().add("Thu");
				}
				if(rs.getBoolean("is_friday")) {
					doctor.getSchedule().getDays().add("Fri");
				}
				if(rs.getBoolean("is_saturday")) {
					doctor.getSchedule().getDays().add("Sat");
				}
				if(rs.getBoolean("is_sunday")) {
					doctor.getSchedule().getDays().add("Sun");
				}

				doctorList.add(doctor);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return doctorList;
	}
	public static void enterAppointmentDetailsinDB(String drID, Date appointmentDate, Date appointmentTime) {
		Scanner in;
		String patientId = ""; 
		try {
			in = new Scanner(new FileReader("p_id.txt"));
			StringBuilder sb = new StringBuilder();
			while(in.hasNext()) {
				sb.append(in.next());
			}
			in.close();
			patientId = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement smt = conn.prepareStatement("insert into appointments(dID,pID,appointment_date,appointment_time) "
					+ "values(?,?,?,?)");
			smt.setString(1, drID);
			smt.setInt(2, Integer.parseInt(patientId));
			
			//Date and time formatting
			smt.setDate(3, new java.sql.Date(appointmentDate.getTime()));
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			String apptTimeString = dateFormat.format(appointmentTime);
			smt.setString(4, apptTimeString);

			int i = smt.executeUpdate();
			System.out.println(i);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	} 
	
	//getting all doctors from db
	public static ArrayList<Doctor> getAllDoctorDetails() {
	ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
			PreparedStatement stmt = conn.prepareStatement("SELECT doctor.*, schedule.* FROM doctor, schedule WHERE schedule.dID = doctor.dID");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Doctor doctor = new Doctor(rs.getString("first_name"),rs.getString("last_name"),rs.getString("address"),
						Long.parseLong(rs.getString("contact")),rs.getString("dob"),rs.getString("gender")); 
				doctor.setDrID(rs.getString("dID"));
				doctor.setEducation(rs.getString("education"));
				doctor.setExperience(rs.getFloat("experience"));
				doctor.setBio(rs.getString("bio"));
				doctor.setspecialization(rs.getString("dept"));
				doctor.setContact(Long.parseLong(rs.getString("contact")));
				doctor.setBio(rs.getString("bio"));
				doctor.setAddress(rs.getString("address"));

				//getting doctor schedule from db
				doctor.getSchedule().setStartTime(rs.getTime("s_time"));
				doctor.getSchedule().setEndTime(rs.getTime("e_time"));
				if(rs.getBoolean("is_monday")) {
					doctor.getSchedule().getDays().add("Mon");
				}
				if(rs.getBoolean("is_tuesday")) {
					doctor.getSchedule().getDays().add("Tue");
				}
				if(rs.getBoolean("is_wednesday")) {
					doctor.getSchedule().getDays().add("Wed");
				}
				if(rs.getBoolean("is_thursday")) {
					doctor.getSchedule().getDays().add("Thu");
				}
				if(rs.getBoolean("is_friday")) {
					doctor.getSchedule().getDays().add("Fri");
				}
				if(rs.getBoolean("is_saturday")) {
					doctor.getSchedule().getDays().add("Sat");
				}
				if(rs.getBoolean("is_sunday")) {
					doctor.getSchedule().getDays().add("Sun");
				}

				doctorList.add(doctor);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return doctorList;
	}
	
}

