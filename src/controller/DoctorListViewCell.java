package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import model.Doctor;

public class DoctorListViewCell extends ListCell<Doctor> {

	@FXML
	private Label experience;
	
	@FXML
	private Label education;
	
	@FXML
	private Label doctor_name;
	
	@FXML
	private AnchorPane list_item_pane;
	
	private FXMLLoader loader;
	
	@Override
    protected void updateItem(Doctor doctor, boolean empty) {
        super.updateItem(doctor, empty);
        
        if(empty || doctor == null) {
            setText(null);
            setGraphic(null);
        } else {

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("../view/list_item.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(doctor.getExperience());
        experience.setText(doctor.getExperience());
        education.setText(doctor.getEducation());
        doctor_name.setText(doctor.getFirstName() + " " + doctor.getLastName());
        
        setText(null);
        setGraphic(list_item_pane);
        
        }
	}
}
