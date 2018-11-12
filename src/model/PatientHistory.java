package model;

import java.util.Date;
import java.util.ArrayList;

public class PatientHistory {
	private Date appointmentDate;
	private Date appointmentTime;
	private Date dischargeDate;
	private Date dischargeTime;
	private String department;
	private String doctorId;
	private String disease;
	private ArrayList<String> medicines;
	private ArrayList<String> tests;
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public Date getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Date appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public Date getDischargeDate() {
		return dischargeDate;
	}
	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}
	public Date getDischargeTime() {
		return dischargeTime;
	}
	public void setDischargeTime(Date dischargeTime) {
		this.dischargeTime = dischargeTime;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	public ArrayList<String> getMedicines() {
		return medicines;
	}
	public void setMedicines(ArrayList<String> medicines) {
		this.medicines = medicines;
	}
	public ArrayList<String> getTests() {
		return tests;
	}
	public void setTests(ArrayList<String> tests) {
		this.tests = tests;
	}
}
