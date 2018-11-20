package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Doctor;
import model.Patient;

public class ListViewPatientAdmin extends ListCell<Patient>{
	
	@FXML
	private Text patient_id;
	
	@FXML
	private Label patient_label;
	
	@FXML
	private AnchorPane list_item_patient;
	
	@FXML
	private Button view_patientprofile_button; 
	
	private FXMLLoader loader;
	Stage stage;
	
	@Override
    protected void updateItem(Patient patient, boolean empty) {
        super.updateItem(patient, empty);
        
        if(empty || patient == null) {
            setText(null);
            setGraphic(null);
        } else {

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("../view/patients_listitem.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        patient_id.setText(patient.getpatientId());
        patient_label.setText(patient.getFirstName() + " " + patient.getLastName());
        
        //set on click listener to button to open doctor profile
        view_patientprofile_button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(getIndex());
                //TODO: Send data to doctor profile page 

                stage = (Stage)list_item_patient.getScene().getWindow();
            	try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/PProfile.fxml"));
                Parent content = loader.load();
                Scene newscene= new Scene(content);
                PersonProfileController controller = loader.getController();
                controller.passData(patient);
                stage.setScene(newscene);
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            }                            
        });
        
        setText(null);
        setGraphic(list_item_patient);
        
        }
	}

}
