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

public class ListViewDoctorsAdminController extends ListCell<Doctor>{
	
	@FXML
	private Text dr_id;
	
	@FXML
	private Label drtext;
	
	@FXML
	private AnchorPane list_item_doctor;
	
	@FXML
	private Button viewdoctorlist_button; 
	
	private FXMLLoader loader;
	Stage stage;
	
	@Override
    protected void updateItem(Doctor doctor, boolean empty) {
        super.updateItem(doctor, empty);
        
        if(empty || doctor == null) {
            setText(null);
            setGraphic(null);
        } else {

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("../view/doctor_listitem.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        dr_id.setText(doctor.getDrID());
        drtext.setText("Dr." + doctor.getFirstName() + " " + doctor.getLastName());
        
        //set on click listener to button to open doctor profile
        viewdoctorlist_button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println(getIndex());
                //TODO: Send data to doctor profile page 

                stage = (Stage)list_item_doctor.getScene().getWindow();
            	try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/doctor_profile.fxml"));
                Parent content = loader.load();
                Scene newscene= new Scene(content);
                AppointmentDetailsController controller = loader.getController();
                controller.setProfileData(doctor);
                stage.setScene(newscene);
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
            }                            
        });
        
        setText(null);
        setGraphic(list_item_doctor);
        
        }
	}
}
