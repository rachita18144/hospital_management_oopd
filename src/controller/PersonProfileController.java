package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Patient;
import model.PatientModel;

public class PersonProfileController
{
	
	@FXML
	private ImageView home;
	
	@FXML
	private Button pasthistory;
	
	@FXML
	private Button refer_dr;
	
	@FXML
    Button generateprescription;
	
	@FXML
	private Text weight,height,age;
	
	@FXML
	private TextField name,pid,type;
	
	Stage stage;
	Scene newscene;
	FXMLLoader loader;
    Parent content;
    int p_age;
   static Patient patient;
	
	public void initialize()
	{
		home.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
		pasthistory.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
		generateprescription.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
		refer_dr.setOnMouseClicked(new EventHandler<MouseEvent>()
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
			  else if(s.equals("pasthistory"))
			  {
				  loader = new FXMLLoader(getClass().getResource("/view/pastVisits.fxml"));
				  content=loader.load();  
				 PastVisitsController pvc = loader.getController();
				 pvc.initData(patient);
			  }
			  else if(s.equals("generateprescription"))
			  {
				  loader = new FXMLLoader(getClass().getResource("/view/Prescription.fxml"));
				  content=loader.load();  
				  PrescriptionController pc = loader.getController();
				  pc.passData(patient);
				  generateprescription.setDisable(true);
			  }
			  else if(s.equals("refer_dr"))
			  {
				  loader = new FXMLLoader(getClass().getResource("/view/referDR.fxml"));
				  content=loader.load();  
				  ReferDRController pvc = loader.getController();
					 pvc.initData(patient);
			  }
			 
		  }catch (IOException e) {e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);	 
	       }
	
	public void passData(Patient patient)
	{
		this.patient=patient;
		name.setText(patient.getFirstName()+" "+patient.getLastName());
		pid.setText(patient.getpatientId());
		type.setText(patient.getType());
		weight.setText(Float.toString(patient.getWeight()));
		height.setText(Float.toString(patient.getHeight()));
		//calDOB(patient.getDob());
		age.setText(Integer.toString(patient.getAge()));
		//patient.setAge(p_age);
	}
	public void calDOB(Date d)
	{
		LocalDate today = LocalDate.now();                         
		int curr_year=today.getYear();
		System.out.println(curr_year);
		int b_year= Integer.parseInt(d.toString().substring(0,4));
		System.out.println("age is "+b_year);
		p_age=curr_year-b_year;
	}
	public void getPID(String id)
	{
		System.out.println("getPID called");
		this.patient=PatientModel.retrievePatient(id);
		passData(patient);
	}
}
