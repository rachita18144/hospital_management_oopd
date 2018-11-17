package controller;

import java.io.IOException;
import model.DoctorModel;
import model.DoctorProfile;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorController
{

	@FXML
	private AnchorPane assignedpatients;
	
	@FXML
	private AnchorPane editprofile;
	
	@FXML
	private AnchorPane viewschedule;
	
	@FXML
	private Button logout;
	
	@FXML
	private AnchorPane viewprofile;
	
	Stage stage;
	Scene newscene;
	model.Doctor doc;
	FXMLLoader loader;
    Parent content;
	public void initialize()
	{

	
		
		editprofile.setOnMouseClicked(new EventHandler<MouseEvent>()
		{ 
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
		
		viewprofile.setOnMouseClicked(new EventHandler<MouseEvent>()
		{ 
			  public void handle(MouseEvent event)
			  {
				  System.out.println("view profile");
				myhandle(event); 
			  }
				});
		
		assignedpatients.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
		logout.setOnMouseClicked(new EventHandler<MouseEvent>()
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
		  stage = (Stage)viewprofile.getScene().getWindow();
		  try
		  { 
			  
			   if(s.equals("assignedpatients"))
			  {
				  System.out.println("here");
				  loader = new FXMLLoader(getClass().getResource("/view/assignedPatients.fxml"));
				  content=loader.load();
				  AssignedPatientsController controller = loader.getController();
				  controller.initData();
			  }
			  else if(s.equals("editprofile"))
			  {
				  DoctorModel db = new DoctorModel();
				  db.createConn();
				  int idd=db.getDID();
				  System.out.println("------------------"+idd);
				  doc= db.viewDRProfile();
				//  doc.show();
				  loader = new FXMLLoader(getClass().getResource("/view/editDrProfile.fxml"));
				  content=loader.load();
				  EditDoctorProfileController dpc = loader.getController();
				  dpc.passData(doc);
				 
			  }
			  else if(s.equals("viewprofile"))
			  {
				  DoctorModel db = new DoctorModel();
				  db.createConn();
				  doc= db.viewDRProfile();
				  loader = new FXMLLoader(getClass().getResource("/view/view_doctor_profile.fxml"));
				  content=loader.load();  
				  ViewDoctorProfileController dpc = loader.getController();
				  dpc.passData(doc);
			  }
			  else if(s.equals("home"))
			  {
				  content= FXMLLoader.load(getClass().getResource("/view/doctor_portal.fxml"));
			  }
			  else if(s.equals("logout"))
			  {
				  content= FXMLLoader.load(getClass().getResource("/view/login_screen.fxml"));
				  System.out.println("LOGOUT");
			  }
		  }catch (IOException e) {e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
	
}
