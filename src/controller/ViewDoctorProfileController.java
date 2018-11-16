package controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ViewDoctorProfileController
{
	@FXML
	private ImageView home;
	@FXML
	private Label name;
	
	@FXML
	private Label department;
	
	@FXML
	private Label education;
	
	@FXML
	private Label experience;
	
	@FXML
	private Label bio;
	
	@FXML
	private Label address;
	
	@FXML
	private Label contact;
	@FXML
	private Label time;
	@FXML
	private Label days;
	
	Stage stage;
	Scene newscene;
    Parent content;
	model.Doctor doc;

	public void initialize()
	{
		System.out.println("view profile controller");
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
		  stage = (Stage)home.getScene().getWindow();
		  try
		  {
			  
			 if(s.equals("home"))
			  {
				  content= FXMLLoader.load(getClass().getResource("/view/doctor_portal.fxml"));
			  }
			 
		  }catch (IOException e) {e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
	
	public void passData(model.Doctor doc)
	{
		this.doc=doc;
		String timee=doc.getDrProfile().getSchedule().getStartTime()+" AM to "+ doc.getDrProfile().getSchedule().getEndTime()+" PM";
		name.setText(doc.getFirstName()+" "+doc.getLastName());
		education.setText(doc.getDrProfile().getEducation());
		experience.setText(doc.getDrProfile().getExperience()+" YEARS EXPERIENCE");
		bio.setText(doc.getDrProfile().getBio());
		address.setText(doc.getAddress());
		contact.setText("CONTACT : "+doc.getContact());
		String listDays = "";
		for (String s : doc.getDrProfile().getSchedule().getDays())
		{
		    listDays += s + " ";
		}
		days.setText(listDays);
		time.setText(timee);
		department.setText(doc.getDrProfile().getDept());
	}
}
