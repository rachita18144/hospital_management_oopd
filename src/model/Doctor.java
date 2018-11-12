package model;

public class Doctor extends Person{
	
	String drID;
	String education;
	String experience;
	//String joining_date;
	String timings;
	String designation;
	String bio;
	String specialization;
	Schedule schedule;
	
	public Doctor(String firstName, String lastName,String address,String contact,String dob,String gender) {
		super(firstName, lastName, address, contact, dob, gender);
	}
	
	public String getDrID() 
	{
		return drID;
	}
	public void setDrID(String drID) 
	{
		this.drID = drID;
	}
	public String getEducation() 
	{
		return education;
	}
	public void setEducation(String education) 
	{
		this.education = education;
	}
	public String getExperience() 
	{
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
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
	public String getSpecialization() 
	{
		return specialization;
	}
	public void setspecialization(String specialization) 
	{
		this.specialization = specialization;
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

