package controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Patient;

public class PatientItemCell extends ListCell<Patient>
{
	@FXML
	private Label pid;
	
	@FXML
	private Label patient_name;
	
	@FXML
	private Label type;
	
	@FXML
	private AnchorPane list_item_pane;
	
	@FXML
	private Button viewprofile;
	
	private FXMLLoader loader;
	Stage stage;
	Scene newscene;
    Parent content;
    Patient patient;
	
	@Override
	protected void updateItem(Patient patient, boolean empty) 
	{
        super.updateItem(patient, empty);
        this.patient=patient;
        if(empty || patient == null) {
            setText(null);
            setGraphic(null);
        } else 
        {

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("../view/item_patient.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
        patient_name.setText(patient.getFirstName() + " " + patient.getLastName());
        pid.setText(patient.getpatientId());
        type.setText(patient.getType());
        setText(null);
        setGraphic(list_item_pane);
         viewprofile.setOnMouseClicked(new EventHandler<MouseEvent>()
 		{
			  public void handle(MouseEvent event)
			  {
				  System.out.println("clicked onnnn " + viewprofile.getId());
				  myhandle(event); 
			  }
	      
	    }); 
        }
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
			  if(s.equals("viewprofile"))
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
