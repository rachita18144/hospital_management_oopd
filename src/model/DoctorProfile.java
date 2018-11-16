package model;

public class DoctorProfile 
{   
	String dID;
	String education;
	float experience;
	String joining_date;
	String s_time;
	String e_time;
	String designation;
	String Dept;
	String bio;
	Schedule schedule=new Schedule();
	

	public String getDept() {
		return Dept;
	}
	public void setDept(String dept) {
		Dept = dept;
	}
	public String getEducation() 
	{
		return education;
	}
	public void setEducation(String education) 
	{
		this.education = education;
	}
	public float getExperience() 
	{
		return experience;
	}
	public void setExperience(float experience) {
		this.experience = experience;
	}
	public String getJoining_date() {
		return joining_date;
	}
	public void setJoining_date(String joining_date) {
		this.joining_date = joining_date;
	}
	
	public String getdID() {
		return dID;
	}
	public void setdID(String dID) {
		this.dID = dID;
	}
	public String getS_time() {
		return s_time;
	}
	public void setS_time(String s_time) {
		this.s_time = s_time;
	}
	public String getE_time() {
		return e_time;
	}
	public void setE_time(String e_time) {
		this.e_time = e_time;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) 
	{
		this.designation = designation;
	}
	public String getBio() 
	{
		return bio;
	}
	public void setBio(String bio) 
	{
		this.bio = bio;
	}
	public Schedule getSchedule() 
	{
		return schedule;
	}
	public void setSchedule(Schedule schedule) 
	{
		this.schedule = schedule;
	}
	
}
