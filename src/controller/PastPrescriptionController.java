package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DoctorModel;
import model.MyLogger;
import model.Patient;
import model.PatientModel;
import model.Prescription;

public class PastPrescriptionController 
{
	@FXML
	private ImageView home;
	
	@FXML
	private TextField name,age,pid,weight,contact,date,did,sid;
	
	@FXML
	private Label dr_name,dr_dept;
	
	@FXML
	private TextArea diagnosis,medicines,labtest;
	

	Stage stage;
	Scene newscene;
    Parent content;
    Prescription slip=new Prescription();
    FXMLLoader loader;
    Patient patient;
    
    public void initialize()
	{
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
				  loader = new FXMLLoader(getClass().getResource("/view/pastVisits.fxml"));
				  content=loader.load();  
				  PastVisitsController pvc = loader.getController();
					 pvc.initData(patient);
			  }
			 
		  }catch (IOException e) {
			  MyLogger.logInfo(this.getClass().getName(), e);
			  e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
    
    public void passSlipData(Prescription slip)
    {
    	this.slip=slip;
    	System.out.println("slip id is "+slip.getsID()+" pId on slip is "+slip.getpID());
    	patient=PatientModel.retrievePatient(slip.getpID());
    	System.out.println(patient.getContact());
    	LocalDate today = LocalDate.now();         
    	name.setText(patient.getFirstName()+" "+patient.getLastName());
		pid.setText(patient.getId());
		did.setText(Integer.toString(DoctorModel.getDID()));
		weight.setText(Float.toString(patient.getWeight()));
		age.setText(Integer.toString(patient.getAge()));
		contact.setText(Long.toString(patient.getContact()));
		date.setText(today.toString());
		dr_name.setText(PatientModel.getDRName(Integer.parseInt(slip.getdID())));
		dr_dept.setText(PatientModel.getDRDept(Integer.parseInt(slip.getdID())));
		setTextAreas();
    }
    
    public void setTextAreas()
    {
    	diagnosis.setText(slip.getDiagnosis());
    	labtest.setText(slip.getLabtests());
    	medicines.setText(slip.getMedicines());
    	sid.setText(slip.getsID());
    }
   


}
