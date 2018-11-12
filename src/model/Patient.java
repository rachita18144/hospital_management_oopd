package model;

public class Patient extends Person{
	private String patientId;
	private String location; //either opd or in-patient
	private int bill;
	private PatientHistory history;
	public String getpatientId() {
		return patientId;
	}
	public void setpatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	public PatientHistory getProfile() {
		return history;
	}
	public void setProfile(PatientHistory profile) {
		this.history = profile;
	}
}
