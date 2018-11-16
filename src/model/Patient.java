package model;

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
}
