package model;

import java.sql.Date;

public class Prescription 
{
private String sID;
private String pID;
private String dID;
private String diagnosis,labtests,medicines;
private String date;
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getsID() {
	return sID;
}
public void setsID(String sID) {
	this.sID = sID;
}
public String getpID() {
	return pID;
}
public void setpID(String pID) {
	this.pID = pID;
}
public String getdID() {
	return dID;
}
public void setdID(String dID) {
	this.dID = dID;
}
public String getDiagnosis() {
	return diagnosis;
}
public void setDiagnosis(String diagnosis) {
	this.diagnosis = diagnosis;
}
public String getLabtests() {
	return labtests;
}
public void setLabtests(String labtests) {
	this.labtests = labtests;
}
public String getMedicines() {
	return medicines;
}
public void setMedicines(String medicines) {
	this.medicines = medicines;
}

}
