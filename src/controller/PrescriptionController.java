package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.sql.Date;

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
import model.Patient;
import model.PatientModel;
import model.Prescription;

public class PrescriptionController {
	@FXML
	private ImageView home;
	
	@FXML
	private TextField name,age,pid,weight,contact,date,did,sid;
	
	@FXML
	private Label dr_name,dr_dept;
	
	@FXML
	private TextArea diagnosis,medicines,labtest;
	
	@FXML
	private Button save;

	Stage stage;
	Scene newscene;
    Parent content;
    Prescription slip=new Prescription();
    FXMLLoader loader;
    
    public void initialize()
	{
		home.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
		save.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				 
				 slip.setDiagnosis(diagnosis.getText());
				 slip.setLabtests(labtest.getText());
				 slip.setMedicines(medicines.getText());
				 slip.setdID(Integer.toString(DoctorModel.getDID()));
				 slip.setDate(date.getText());
				 slip= PatientModel.saveSlipToDB(slip);
				 setTextAreas();
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
				  loader = new FXMLLoader(getClass().getResource("/view/PProfile.fxml"));
				  content=loader.load();  
				  PersonProfileController pc = loader.getController();
				 pc.generateprescription.setDisable(true);
				  pc.getPID(slip.getpID());
			  }
			 
		  }catch (IOException e) {e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
    
    public void passData(Patient patient)
    {
    	System.out.println(patient.getContact());
    	LocalDate today = LocalDate.now();         
    	Date curr_date = java.sql.Date.valueOf(today);
    	name.setText(patient.getFirstName()+" "+patient.getLastName());
		pid.setText(patient.getpatientId());
		weight.setText(Float.toString(patient.getWeight()));
		age.setText(Integer.toString(patient.getAge()));
		contact.setText(Long.toString(patient.getContact()));
		date.setText(today.toString());
		slip.setDate(curr_date.toString());
		slip.setpID(patient.getId());	
		dr_name.setText(PatientModel.getDRName(DoctorModel.getDID()));
		dr_dept.setText(PatientModel.getDRDept(DoctorModel.getDID()));
    }
    
    public void setTextAreas()
    {
    	diagnosis.setText(slip.getDiagnosis());
    	labtest.setText(slip.getLabtests());
    	medicines.setText(slip.getMedicines());
    	sid.setText(slip.getsID());
    }
    

}
