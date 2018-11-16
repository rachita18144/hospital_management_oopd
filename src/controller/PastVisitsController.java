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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Patient;
import model.PatientModel;
import model.Prescription;

public class PastVisitsController
{
	@FXML
	private ImageView home;
	
	@FXML
	private ListView<Prescription> past_listview;
	
	ObservableList<Prescription> list;
    ArrayList<Prescription> pList;

	Stage stage;
	Scene newscene;
    Parent content;
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
    public void initData(Patient p)
	{
    	this.patient=p;
    	System.out.println("IN PAST VISIT CONTROLLER pid is "+p.getId());
		pList=PatientModel.getPastAppointments(p);
		System.out.println("initdata past visits controller from profile "+pList);
		list = FXCollections.observableArrayList(pList);
		past_listview.setItems(list);
		past_listview.setCellFactory(prescriptionListView -> new PrescriptionItemCell());
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
				  PersonProfileController ppc = loader.getController();
				  ppc.passData(patient);
			  }
			 
			 
		  }catch (IOException e) {e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
}
