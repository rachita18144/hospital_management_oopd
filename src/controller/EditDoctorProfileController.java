package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.DoctorModel;
import model.MyLogger;

public class EditDoctorProfileController 
{
	@FXML
	private Button updateProfile;
	
	@FXML
	private ImageView home;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField education;
	
	@FXML
	private TextField department;
	
	@FXML
	private TextField experience;
	
	@FXML
	private TextArea bio;
	
	@FXML
	private TextArea address;
	
	@FXML
	private TextField contact;
	@FXML
	private TextField stime;
	
	@FXML
	private TextField etime;
	
	@FXML
	private CheckBox mon,tue,wed,thu,fri,sat,sun;
	
	
	Stage stage;
	Scene newscene;
    Parent content;
	model.Doctor doc;

	public void initialize()
	{
		updateProfile.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				//myhandle(event); 
				  updateData();
				  passData(doc);
			  }
				});
		
		home.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
	}
	
	public void myhandle(MouseEvent event)
	{
		 Node node = (Node) event.getSource();
		 String s=node.getId();
		 System.out.println(s);
		 System.out.println("MOUSE CLICKED "+event.getSource());
		  stage = (Stage)updateProfile.getScene().getWindow();
		  try
		  { 
			  if(s.equals("updateProfile"))
			  	{ /*content= FXMLLoader.load(getClass().getResource("/view/editDrProfile.fxml"));*/
				  updateData();
				 /* DoctorModel db = new DoctorModel();
				  db.createConn();
				  doc= db.viewDRProfile();*/
				  passData(doc);
				  content= FXMLLoader.load(getClass().getResource("/view/editDrProfile.fxml"));
			  	}
			  
			  else if(s.equals("home"))
			  {
				  content= FXMLLoader.load(getClass().getResource("/view/doctor_portal.fxml"));
			  }
			 
		  }catch (IOException e) {
			  MyLogger.logInfo(this.getClass().getName(), e);
			  e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
	public void passData(model.Doctor doc)
	{
		this.doc=doc;
		//String timee=doc.getDrProfile().getSchedule().getStartTime()+" to "+ doc.getDrProfile().getSchedule().getEndTime();
		name.setText(doc.getFirstName()+" "+doc.getLastName());
		education.setText(doc.getDrProfile().getEducation());
		experience.setText(Float.toString(doc.getDrProfile().getExperience()));
		bio.setText(doc.getDrProfile().getBio());
		address.setText(doc.getAddress());
		department.setText(doc.getDrProfile().getDept());
		contact.setText(Long.toString(doc.getContact()));
		stime.setText(doc.getDrProfile().getSchedule().getStartTime()+" AM");
		etime.setText(doc.getDrProfile().getSchedule().getEndTime()+" PM");
		ArrayList<String> dd=doc.getDrProfile().getSchedule().getDays();
		for(String s:dd)
		{
			if(s.equals("Mon"))
			{mon.setSelected(true);	}
			if(s.equals("Tue"))
			{tue.setSelected(true);	}
			if(s.equals("Wed"))
			{wed.setSelected(true);	}
			if(s.equals("Thu"))
			{thu.setSelected(true);	}
			if(s.equals("Fri"))
			{fri.setSelected(true);	}
			if(s.equals("Sat"))
			{sat.setSelected(true);	}
			if(s.equals("Sun"))
			{sun.setSelected(true);	}	
		}
	}
	
	public void updateData()
	{
		String []fullname= name.getText().split(" ");
		doc.setFirstName(fullname[0]);
		doc.setLastName(fullname[1]);
		doc.getDrProfile().setEducation(education.getText());
		doc.getDrProfile().setExperience(Float.parseFloat(experience.getText()));
		doc.getDrProfile().setBio(bio.getText());
		doc.setAddress(address.getText());
		doc.setContact(Long.parseLong(contact.getText()));
		doc.getDrProfile().setS_time(stime.getText());
		doc.getDrProfile().setE_time(etime.getText());
		ArrayList<String> editedDays= new ArrayList<>();
		if(mon.isSelected())
		{ editedDays.add("Mon");}
		if(tue.isSelected())
		{ editedDays.add("Tue");}
		if(wed.isSelected())
		{ editedDays.add("Wed");}
		if(thu.isSelected())
		{ editedDays.add("Thu");}
		if(fri.isSelected())
		{ editedDays.add("Fri");}
		if(sat.isSelected())
		{ editedDays.add("Sat");}
		if(sun.isSelected())
		{ editedDays.add("Sun");}
		System.out.println("hurray"+editedDays);
		doc.getDrProfile().getSchedule().setDays(editedDays);
		DoctorModel dm = new DoctorModel();
		dm.createConn();
		dm.editDRProfile(doc);
		
	}
}
