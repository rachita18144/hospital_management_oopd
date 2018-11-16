package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PatientModel 
{
 
	static Connection conn;
	 public static void createConn()
	    {
	    	try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartHealthSystem?allowPublicKeyRetrieval=true&useSSL=false", "shs", "qwerty");
	    	    } 
	    	catch (SQLException e) 
	    	{
	    		 System.out.println("SQLException: " + e.getMessage());
	    		    System.out.println("SQLState: " + e.getSQLState());
	    		    System.out.println("VendorError: " + e.getErrorCode());
				e.printStackTrace();
			}
	    	catch(ClassNotFoundException e)
	    	{
	    		System.out.println("no conn created");
	    	}
	    }
	public static ArrayList<Patient> getAllAssignedPatients()
	{
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		 createConn();
		 PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("SELECT * FROM APPOINTMENTS WHERE dID=?");
			stmt.setInt(1,DoctorModel.getDID());
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next())
			 {
				 int id=rs.getInt("pID");
				 Patient p=getPatient(id);
				 patientList.add(p);
			 }
		} catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		return patientList;
	}
	public static Patient getPatient(int id)
	{
		//get did from file
		//did = file
	  Patient patient= new Patient();
		try {
             createConn();
			 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PATIENT WHERE id=?");
			 stmt.setInt(1,id);
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) 
			 {
				patient.setpatientId(rs.getString("pID"));
				patient.setId(Integer.toString(rs.getInt("id")));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));
				patient.setType(rs.getString("type"));
				//patient.setDob(rs.getDate("dob"));
				patient.setAge(rs.getInt("age"));
				System.out.println("age set tto "+patient.getAge());
				patient.setWeight(rs.getFloat("weight"));
				patient.setHeight(rs.getFloat("height"));
				patient.setContact(rs.getLong("contact"));
				
			 }
			 System.out.println(patient.getFirstName());
		}catch(Exception e) {
			e.printStackTrace();
		}
	return patient ;
	} 
	
	public static Prescription saveSlipToDB(Prescription slip)
	{
		try {
			createConn();
			 PreparedStatement ps = conn.prepareStatement("INSERT INTO smartHealthSystem.PRESCRIPTION(dID,pID,diagnosis,labtest,medicines,date) VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			// ps.setInt(1,Integer.parseInt(slip.getsID()));
			 ps.setInt(1,Integer.parseInt(slip.getdID()) );
			 ps.setInt(2,Integer.parseInt(slip.getpID()) );
			 ps.setString(3,slip.getDiagnosis());
			 ps.setString(4,slip.getLabtests());
			 ps.setString(5,slip.getMedicines());
			 ps.setString(6,slip.getDate());
			 ps.executeUpdate();
			 ResultSet rs = ps.getGeneratedKeys();
             if(rs.next())
             {
                 int last_inserted_id = rs.getInt(1);
                 System.out.println(last_inserted_id);
                 slip.setsID(Integer.toString(last_inserted_id));
             }
             
		}catch(Exception e) {e.printStackTrace();}
		return slip;
	}
	
	
	public static Patient retrievePatient(String id)
	{
		createConn();
		Patient patient= new Patient();
		 try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM PATIENT WHERE id=?");
			ps.setInt(1,Integer.parseInt(id));
			ResultSet rs=ps.executeQuery();
			if(rs.next()) 
			 {System.out.println("found with"+id);
				patient.setpatientId(Integer.toString(rs.getInt("pID")));
				patient.setId(Integer.toString(rs.getInt("id")));
				patient.setFirstName(rs.getString("first_name"));
				patient.setLastName(rs.getString("last_name"));
				patient.setType(rs.getString("type"));
				//patient.setDob(rs.getDate("dob"));
				patient.setWeight(rs.getFloat("weight"));
				patient.setHeight(rs.getFloat("height"));
				patient.setContact(rs.getLong("contact"));
				patient.setAge(rs.getInt("age"));
			 }
		     } catch (SQLException e)  {e.printStackTrace(); }
		 return patient;
	}
	public static int calDOB(Date d)
	{
		LocalDate today = LocalDate.now();                         
		int curr_year=today.getYear();
		int b_year= Integer.parseInt(d.toString().substring(0,4));
		System.out.println("age is "+b_year);
		return curr_year-b_year;
	}
	public static ArrayList<Prescription> getPastAppointments(Patient p)
	{
		ArrayList<Prescription> presList = new ArrayList<>();
		try {
             createConn();
			 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PRESCRIPTION WHERE pID=?");
			 stmt.setInt(1,Integer.parseInt(p.getId()));
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) 
			 {
			    Prescription slip=new Prescription();
			    slip.setdID(Integer.toString(rs.getInt("dID")));
			    slip.setsID(Integer.toString(rs.getInt("sID")));
			    slip.setpID(Integer.toString(rs.getInt("pID")));
			    slip.setDate(rs.getString("date"));
			    slip.setDiagnosis(rs.getString("diagnosis"));
			    slip.setLabtests(rs.getString("labtest"));
			    slip.setMedicines(rs.getString("medicines"));
				presList.add(slip);
			 }
			 System.out.println(presList.size());
		}catch(Exception e) {
			e.printStackTrace();
		}
	return presList;
	} 
	public static String getDRName(int id)
	{
		String dname=null;
		try {
            createConn();
			 PreparedStatement stmt = conn.prepareStatement("SELECT first_name,last_name FROM DOCTOR WHERE dID=?");
			 stmt.setInt(1,id);
			 ResultSet rs = stmt.executeQuery();
			 if(rs.next()) 
			 {
			    String s= rs.getString("first_name");
			    String l=rs.getString("last_name");
			    dname=s+" "+l;
			 }
		}catch(Exception e) {e.printStackTrace();}
		return dname;
	}
	
	public static String getDRDept(int id)
	{
		String s=null;
		try {
            createConn();
			 PreparedStatement stmt = conn.prepareStatement("SELECT dept FROM DOCTOR WHERE dID=?");
			 stmt.setInt(1,id);
			 ResultSet rs = stmt.executeQuery();
			 if(rs.next()) 
			 {
			     s= rs.getString("dept");
			   
			 }
		}catch(Exception e) {e.printStackTrace();}
		return s;
	}
}
