package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Scanner;

public class DoctorModel 
{
	private Connection conn=null;
	private Statement stmt=null;
	private PreparedStatement ps = null;
    private ResultSet rs = null;
    int id=0;

    public void createConn()
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

	
	public Doctor viewDRProfile()
	{
		Doctor doc=new Doctor();
		try {
			ps = conn.prepareStatement("select * from smartHealthSystem.DOCTOR WHERE dID=?");  
			ps.setInt(1,DoctorModel.getDID());
			rs=ps.executeQuery();
		   if(rs.first())
		   {
			   id=rs.getInt("dID");
			   doc.getDrProfile().setdID("CD"+rs.getInt("dID"));
			   System.out.println(rs.getInt("dID"));
			   doc.setFirstName(rs.getString("first_name"));
			   doc.setLastName(rs.getString("last_name"));
			   doc.getDrProfile().setDesignation(rs.getString("level")+" "+rs.getString("role"));  
			   doc.getDrProfile().setEducation(rs.getString("education"));
			   doc.getDrProfile().setExperience(rs.getFloat("experience"));
			   doc.setAddress(rs.getString("address"));
			   doc.getDrProfile().setDept(rs.getString("dept"));
			   doc.setContact(rs.getLong("contact"));
			   doc.getDrProfile().setBio(rs.getString("bio"));  
		   }
		   ps = conn.prepareStatement("select * from smartHealthSystem.SCHEDULE WHERE dID= ?");
		   ps.setInt(1,id);
		   rs=ps.executeQuery();
		   if(rs.first())
		   {
			   doc.getDrProfile().getSchedule().setStartTime(rs.getTime("s_Time"));
			   doc.getDrProfile().getSchedule().setEndTime(rs.getTime("e_Time"));
			   if(rs.getBoolean("is_monday")) {
					doc.getDrProfile().getSchedule().getDays().add("Mon");
				}
				if(rs.getBoolean("is_tuesday")) {
					doc.getDrProfile().getSchedule().getDays().add("Tue");
				}
				if(rs.getBoolean("is_wednesday")) {
					doc.getDrProfile().getSchedule().getDays().add("Wed");
				}
				if(rs.getBoolean("is_thursday")) {
					doc.getDrProfile().getSchedule().getDays().add("Thu");
				}
				if(rs.getBoolean("is_friday")) {
					doc.getDrProfile().getSchedule().getDays().add("Fri");
				}
				if(rs.getBoolean("is_saturday")) {
					doc.getDrProfile().getSchedule().getDays().add("Sat");
				}
				if(rs.getBoolean("is_sunday")) {
					doc.getDrProfile().getSchedule().getDays().add("Sun");
				}
			    
		   }
		   System.out.println(doc.getDrProfile().getSchedule().getDays());
			ps.close();
		    } catch (SQLException e) {e.printStackTrace();}

		return doc;
	}
	
	public ArrayList<Doctor> getSameDeptDRProfile()
	{
		ArrayList<Doctor>drlist=new ArrayList<>();
		id=DoctorModel.getDID();
		try {
			String role=getRole();
			String level=getLevel();
			String dept=getDept();
			if(level.equals("junior"))
			{
			ps = conn.prepareStatement("select * from smartHealthSystem.DOCTOR WHERE level=? AND dept=?");
			ps.setString(1,"senior");
			ps.setString(2,dept);
			rs= ps.executeQuery();
		   while(rs.next())
		   {
			   Doctor doc=new Doctor();
			   id=rs.getInt("dID");
			   doc.getDrProfile().setdID("CR"+rs.getInt("dID"));
			   System.out.println(rs.getInt("dID"));
			   doc.setFirstName(rs.getString("first_name"));
			   doc.setLastName(rs.getString("last_name"));
			   doc.getDrProfile().setDesignation(rs.getString("level")+" "+rs.getString("role"));  
			   drlist.add(doc);   
		   }
		   System.out.println("size of seniors: "+drlist.size());
			}
			ps.close();
		    } catch (SQLException e) {e.printStackTrace();}

		return drlist;
	}
	
	public ArrayList<Doctor> getDiffDeptDRProfile()
	{
		ArrayList<Doctor>drlist=new ArrayList<>();
		
		try {
			String role=getRole();
			String level=getLevel();
			String dept=getDept();
			System.out.println("checking for diff dept list "+role);
			if(role.equals("specialist"))
			{
				System.out.println("sksk");
			ps = conn.prepareStatement("select * from smartHealthSystem.DOCTOR WHERE dept<>?");
			ps.setString(1,dept);
			rs= ps.executeQuery();
		   while(rs.next())
		   {
			   Doctor doc=new Doctor();
			   id=rs.getInt("dID");
			   doc.getDrProfile().setdID("CR"+rs.getInt("dID"));
			   System.out.println(rs.getInt("dID"));
			   doc.setFirstName(rs.getString("first_name"));
			   doc.setLastName(rs.getString("last_name"));
			   doc.getDrProfile().setDesignation(rs.getString("level")+" "+rs.getString("role"));  
			  /* doc.getDrProfile().setEducation(rs.getString("education"));
			   doc.getDrProfile().setExperience(rs.getFloat("experience"));
			   doc.setAddress(rs.getString("address"));
			   doc.getDrProfile().setDept(rs.getString("dept"));
			   doc.setContact(rs.getLong("contact"));
			   doc.getDrProfile().setBio(rs.getString("dBio"));  */
			   drlist.add(doc);   
		   }
		   System.out.println("size of diff dept log: "+drlist.size());
			}
			ps.close();
		    } catch (SQLException e) {e.printStackTrace();}

		return drlist;
	}
	
	public void editDRProfile(Doctor doc)
	{
		String i=doc.getDrProfile().getdID();
		id= Integer.parseInt(i.substring(2));
		//System.out.println("id is "+DoctorModel.getDID());
		
		try {
			ps = conn.prepareStatement("UPDATE smartHealthSystem.DOCTOR SET first_name=? , last_name=? ,education=?,experience=?,address=?,dept=?,contact=?,bio=?"
					+ "WHERE dID=?");  
			ps.setString(1,doc.getFirstName());
			ps.setString(2,doc.getLastName());
			ps.setString(3,doc.getDrProfile().getEducation() );
			ps.setFloat(4,doc.getDrProfile().getExperience());
		    ps.setString(5,doc.getAddress());
		    ps.setString(6, doc.getDrProfile().getDept());
		    ps.setLong(7,doc.getContact());
		    ps.setString(8, doc.getDrProfile().getBio());
		    ps.setInt(9,id);
		    ps.executeUpdate();
		    ps.close();
		    ps = conn.prepareStatement("UPDATE smartHealthSystem.SCHEDULE SET is_monday=? , is_tuesday=? , is_wednesday=? , is_thursday=?,"
		    		+ "is_friday=?,is_saturday=?,is_sunday=? WHERE dID = ?"); 
		    ps.setBoolean(1,false); ps.setBoolean(2,false); ps.setBoolean(3,false); ps.setBoolean(4,false); ps.setBoolean(5,false); ps.setBoolean(6,false); ps.setBoolean(7,false);
		    ps.setInt(8,id);
		    ps.executeUpdate();
		    ArrayList<String>temp=doc.getDrProfile().getSchedule().getDays();
		    for(String s:temp)
		    {
		    	if(s.equals("Mon"))
				{ps.setBoolean(1,true); System.out.println("mon set true");}
				if(s.equals("Tue"))
				{ps.setBoolean(2,true);	System.out.println("tue set true");}
				if(s.equals("Wed"))
				{ps.setBoolean(3,true);	System.out.println("wed set true");}
				if(s.equals("Thu"))
				{ps.setBoolean(4,true); System.out.println("thu set true");	}
				if(s.equals("Fri"))
				{ps.setBoolean(5,true);	System.out.println("fri set true");}
				if(s.equals("Sat"))
				{ps.setBoolean(6,true);	System.out.println("sat set true");}
				if(s.equals("Sun"))
				{ps.setBoolean(7,true);	System.out.println("sun set true");}
		    }
		    System.out.println("id is "+id);
		    ps.setInt(8,id);
	
		    ps.executeUpdate();
		    } catch (SQLException e) {e.printStackTrace();}
	}
	public static int getDID()
	{
		Scanner cin;
		String doctorId = ""; 
		try {
			cin = new Scanner(new FileReader("d_id.txt"));
			StringBuilder sb = new StringBuilder();
			while(cin.hasNext()) {
				sb.append(cin.next());
			}
			cin.close();
			doctorId = sb.toString();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		}
		return  Integer.parseInt(doctorId);
	}
	
	public String getRole()
	{
		String role=null;
		try {
			ps=conn.prepareStatement("SELECT role FROM smartHealthSystem.DOCTOR WHERE dID=? ");
			ps.setInt(1,DoctorModel.getDID());
			System.out.println("gettin role for "+DoctorModel.getDID());
			rs=ps.executeQuery();
			if(rs.first())
			{
				role=rs.getString("role");
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return role;
	}
	public String getLevel()
	{
		String level=null;
		try {
			ps=conn.prepareStatement("SELECT level FROM smartHealthSystem.DOCTOR WHERE dID=? ");
			ps.setInt(1,DoctorModel.getDID());
			rs=ps.executeQuery();
			if(rs.first())
			{
				level=rs.getString("level");
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return level;
	}
	public String getDept()
	{
		String dept=null;
		try {
			ps=conn.prepareStatement("SELECT dept FROM smartHealthSystem.DOCTOR WHERE dID=? ");
			ps.setInt(1,DoctorModel.getDID());
			rs=ps.executeQuery();
			if(rs.first())
			{
				dept=rs.getString("dept");
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return dept;
	}
}
