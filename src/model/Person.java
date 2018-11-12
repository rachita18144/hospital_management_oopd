package model;

class Person 
{
String firstName;
String LastName;
String address;
String contact;
String dob;
String gender;

	public Person () {}

	public Person(String firstName, String lastName, String address, String contact, String dob, String gender) {
	this.firstName = firstName;
	LastName = lastName;
	this.address = address;
	this.contact = contact;
	this.dob = dob;
	this.gender = gender;
	}

	public String getFirstName() 
		{return firstName;}
	
	public void setFirstName(String firstName) 
		{this.firstName = firstName;}
	
	public String getLastName() 
		{return LastName;}
	
	public void setLastName(String lastName) 
		{LastName = lastName;}
	
	public String getAddress() 
		{return address;}
	
	public void setAddress(String address) 
		{this.address = address;}
	
	public String getContact()
		{return contact;}
	
	public void setContact(String contact) 
		{this.contact = contact;}
	
	public String getDob() 
		{return dob;}
	
	public void setDob(String dob) 
		{this.dob = dob;}
	
	public String getGender() 
		{return gender;}
	
	public void setGender(String gender) 
		{this.gender = gender;}

	public void Login()
		{
	
		}
}
