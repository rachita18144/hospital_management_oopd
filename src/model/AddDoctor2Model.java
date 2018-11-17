package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

public class AddDoctor2Model {
	public static boolean storeDoctor2data(String docId, String f_name, String l_name, String e_Id, Date dob, String addr, String contact, String categ, String bio, String education, String experience, String role,
			String level, Date date1,Date date2, Boolean mon, Boolean tues, Boolean wed, Boolean thurs, Boolean fri, Boolean sat, Boolean sun, String pass) {
		
		if(education.equals(null) || experience.equals(null) || role.equals(null) || level.equals("<-Select Level->") || date1.equals(null) || date2.equals(null)) 
		{
			System.out.println("error");
			return false;
		}
		
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	 // Connection  conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/smartHealthSystem?","root","mypass");
	  PreparedStatement smt1 = conn.prepareStatement("insert into Doctor(dID,first_name,last_name,email_id,dob,address,contact,dept,education,experience,bio,role,level,password)"
		 		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 smt1.setString(1, docId);
		 smt1.setString(2, f_name);
		 smt1.setString(3, l_name);
		 smt1.setString(4, e_Id);
		 smt1.setDate(5, (new java.sql.Date (dob.getTime())));
		 smt1.setString(6, addr);
		 smt1.setLong(7, Long.parseLong(contact));
		 smt1.setString(8, categ);
		 smt1.setString(9, education);
		 smt1.setString(10, experience);
		 smt1.setString(11, bio);
		 smt1.setString(12, role);
		 smt1.setString(13, level);
		 smt1.setString(14, pass);
		 int i = smt1.executeUpdate();
		 System.out.println(i);
		 
		 PreparedStatement smt2 = conn.prepareStatement("insert into SCHEDULE(dID,s_Time,e_Time,is_monday,is_tuesday,is_wednesday,is_thursday,is_friday,is_saturday,is_sunday)"
		 		+ "values(?,?,?,?,?,?,?,?,?,?)");
		 
		 smt2.setString(1, docId);
		 smt2.setTimestamp(2, (new Timestamp(date1.getTime())));
		 smt2.setTimestamp(3, (new Timestamp(date2.getTime())));
		 smt2.setBoolean(4,mon);
		 smt2.setBoolean(5, tues);
		 smt2.setBoolean(6, wed);
		 smt2.setBoolean(7, thurs);
		 smt2.setBoolean(8, fri);
		 smt2.setBoolean(9, sat);
		 smt2.setBoolean(10, sun);
		 
		 int j = smt2.executeUpdate();
		 System.out.println(j);
		 conn.close();
		 
	}catch(Exception e) {
		e.printStackTrace();
	}
	return true;
	}
}
