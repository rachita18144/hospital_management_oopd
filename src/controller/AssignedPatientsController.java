package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Patient;
import model.PatientModel;
import javafx.collections.FXCollections;
public class AssignedPatientsController 
{
	
	@FXML
	private ImageView home;
	
	@FXML
	private ListView<Patient> assignedlist;
	
	@FXML
	private ScrollPane assignedpatients_list;
	
	 @FXML
	 private ComboBox<String>sortby;
	
	Stage stage;
	Scene newscene;
    Parent content;
    
    ObservableList<Patient> list;
     ArrayList<Patient> patientList;
	
	public void initialize()
	{
		sortby.getItems().addAll("PID", "NAME","TYPE");
        sortby.getSelectionModel().select("<-Select Mode->");
        sortby.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            System.out.println(newValue);
            if(newValue.equals("PID"))
            {
            	ArrayList<Patient> newList=sortList(newValue);
            	list = FXCollections.observableArrayList(newList);
            	assignedlist.setItems(list);
        		assignedlist.setCellFactory(assignedPatientsListView -> new PatientItemCell());
        		System.out.println("done using pid");
            }
            else if(newValue.equals("NAME"))
            {
            	ArrayList<Patient> newList=sortList(newValue);
            	list = FXCollections.observableArrayList(newList);
            	assignedlist.setItems(list);
        		assignedlist.setCellFactory(assignedPatientsListView -> new PatientItemCell());
        		System.out.println("done using name");
            }
            else
            {
            	ArrayList<Patient> newList=sortList(newValue);
            	list = FXCollections.observableArrayList(newList);
            	assignedlist.setItems(list);
        		assignedlist.setCellFactory(assignedPatientsListView -> new PatientItemCell());
        		System.out.println("done using type");
            }
        }
        ); 
		home.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
	     }
	
	public void initData()
	{
		patientList=PatientModel.getAllAssignedPatients();
		System.out.println("patientList "+patientList.size());
		list = FXCollections.observableArrayList(patientList);
		assignedlist.setItems(list);
		assignedlist.setCellFactory(assignedPatientsListView -> new PatientItemCell());
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
	public ArrayList<Patient> sortList(String newValue)
	{
		 if(newValue.equals("PID"))
         {
			 Collections.sort(patientList, Patient.IDComparator);
         }
         else if(newValue.equals("NAME"))
         {
        	 Collections.sort(patientList, Patient.NameComparator);
         }
         else if(newValue.equals("TYPE"))
         {
        	 Collections.sort(patientList, Patient.TypeComparator);
         }
		 return patientList;
	}
}
