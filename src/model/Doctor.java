package model;

import javafx.beans.property.SimpleStringProperty;

public class Doctor extends Person{

	private SimpleStringProperty drID;
	private SimpleStringProperty specialization;
	private String education;
	private float experience;
	private String timings;
	private String bio;
	private String role;
	private String level;
	private Schedule schedule = new Schedule(); 

	public Doctor(String firstName, String lastName,String address,long contact,String dob,String gender) {
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
	public float getExperience() 
	{
		return experience;
	}
	public void setExperience(float experience) {
		this.experience = experience;
	}

	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
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

