package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import model.Doctor;
import model.GetDoctorDetailsModel;
import model.GetPatientDetailsModel;
import model.Patient;

public class ViewPatientListAdmin implements Initializable{
	
	@FXML
    private AnchorPane patient_listview;
    
	@FXML
	private ListView<Patient> list_viewpatient;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		ObservableList<Patient> patientObservableList = null;
		ArrayList<Patient> patientList = null;
		patientList = GetPatientDetailsModel.getPatientDetails();

		//populate doctorObservable List
		patientObservableList = FXCollections.observableArrayList(patientList);
		//doctorObservableList.addAll(doctorList);
		list_viewpatient.setItems(patientObservableList);
		list_viewpatient.setCellFactory(doctorListView -> new ListViewPatientAdmin());
	}


}
