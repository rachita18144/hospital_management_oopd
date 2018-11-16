package model;

import java.util.Comparator;

public class Patient extends Person{
	private String patientId;
	private String type;
	private int bill;
	private float weight;
	private float height;
	private int age;
	private boolean isslip=false;
	
	//new fields
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private String password;
	private String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIsslip() {
		return isslip;
	}
	public void setIsslip(boolean isslip) {
		this.isslip = isslip;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public float getWeight() 
	{
		return weight;
	}
	public void setWeight(float weight) 
	{
		this.weight = weight;
	}
	public float getHeight()
	{
		return height;
	}
	public void setHeight(float height) 
	{
		this.height = height;
	}
	public String getpatientId()
	{
		return patientId;
	}
	public void setpatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getBill() {
		return bill;
	}
	public void setBill(int bill) {
		this.bill = bill;
	}
	
	//comparator classes
	public static Comparator<Patient> NameComparator = new Comparator<Patient>() {

		public int compare(Patient p1, Patient p2) {
		   String patientName1 = p1.getFirstName()+p1.getLastName().toLowerCase();
		   String patientName2 = p2.getFirstName()+p2.getLastName().toLowerCase();
		   return patientName1.compareTo(patientName2);
	    }};
	    
	    public static Comparator<Patient> IDComparator = new Comparator<Patient>() {

	    	public int compare(Patient p1, Patient p2) {

	    	   int pid1 = Integer.parseInt(p1.getpatientId());
	    	   int pid2 = Integer.parseInt(p2.getpatientId());
	    	   return pid1-pid2;

	       }};
	       
	       public static Comparator<Patient> TypeComparator = new Comparator<Patient>() {

		    	public int compare(Patient p1, Patient p2) {

		    		String patientType1 = p1.getType();
		    		String patientType2 = p2.getType();
		    		 return patientType1.compareTo(patientType2);

		       }};

}
