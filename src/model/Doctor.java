package model;

import javafx.beans.property.SimpleStringProperty;

public class Doctor extends Person{
	
	private SimpleStringProperty drID;
	private SimpleStringProperty specialization;
	private String education;
	private String experience;
	private String timings;
	private String designation;
	private String bio;
	private Schedule schedule = new Schedule(); 

	public Doctor(String firstName, String lastName,String address,String contact,String dob,String gender) {
		super(firstName, lastName, address, contact, dob, gender);
	}

	public String getDrID() 
	{
		return drID.get();
	}
	public void setDrID(String drID) 
	{
		this.drID = new SimpleStringProperty(drID);
		this.drID.set(drID);
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
		return specialization.get();
	}
	public void setspecialization(String specialization) 
	{
		this.specialization = new SimpleStringProperty(specialization);
		this.specialization.set(specialization);
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

