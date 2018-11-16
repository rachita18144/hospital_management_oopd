package model;

import javafx.beans.property.SimpleStringProperty;

class Person 
{
	private SimpleStringProperty firstName;
	private SimpleStringProperty LastName;
	private SimpleStringProperty address;
	String email;
	long contact;
	String dob;
	String gender;

	public Person () {}

	public Person(String firstName, String lastName, String address, long contact, String dob, String gender) {
		this.firstName = new SimpleStringProperty(firstName);
		this.LastName = new SimpleStringProperty(lastName);
		this.address = new SimpleStringProperty(address);
		this.contact = contact;
		this.dob = dob;
		this.gender = gender;
	}

	public String getFirstName() 
	{
		return firstName.get();
	}

	public void setFirstName(String fName) 
	{
		this.firstName = new SimpleStringProperty(fName);
		firstName.set(fName);
	}

	public String getLastName() 
	{
		return LastName.get();
	}

	public void setLastName(String lastName) 
	{
		this.LastName = new SimpleStringProperty(lastName);
		LastName.set(lastName);
	}

	public String getAddress() 
	{
		return address.get();
	}

	public void setAddress(String addressVal) 
	{
		this.address = new SimpleStringProperty(addressVal);
		this.address.set(addressVal);
	}

	public long getContact()
	{return contact;}

	public void setContact(long contact) 
	{this.contact = contact;}

	public String getDob() 
	{return dob;}

	public void setDob(String dob) 
	{this.dob = dob;}

	public String getGender() 
	{return gender;}

	public void setGender(String gender) 
	{this.gender = gender;}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void Login()
	{

	}
}
