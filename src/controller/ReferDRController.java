package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Doctor;
import model.DoctorModel;
import model.MyLogger;
import model.Patient;
import model.PatientModel;

public class ReferDRController 
{
	@FXML
	private ImageView home;
	
	@FXML
	private ListView<Doctor> same_dept,diff_dept;
	
	@FXML
	private Button refer;
	
	Stage stage;
	Scene newscene;
    Parent content;
    FXMLLoader loader;
    
    ObservableList<Doctor> samedept_list;
     ArrayList<Doctor> samedept_drList;
     
     ObservableList<Doctor> diffdept_list;
     ArrayList<Doctor> diffdept_drList;
	
     Patient p;
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
	
	public void initData(Patient p)
	{  this.p=p;
		DoctorModel dm= new DoctorModel();
		dm.createConn();
		samedept_drList=dm.getSameDeptDRProfile();
		diffdept_drList=dm.getDiffDeptDRProfile();
		samedept_list = FXCollections.observableArrayList(samedept_drList);
		diffdept_list = FXCollections.observableArrayList(diffdept_drList);
		same_dept.setItems(samedept_list);
		same_dept.setCellFactory(referListView -> new ReferDRItemCell());
		diff_dept.setItems(diffdept_list);
		diff_dept.setCellFactory(referListView -> new ReferDRItemCell());
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
					 PersonProfileController pvc = loader.getController();
					 pvc.passData(p);	  
			  }
			 
		  }catch (IOException e) {
			  MyLogger.logInfo(this.getClass().getName(), e);
			  e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
}
