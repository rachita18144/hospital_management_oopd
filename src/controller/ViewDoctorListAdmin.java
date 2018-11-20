package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Doctor;
import model.GetDoctorDetailsModel;

public class ViewDoctorListAdmin implements Initializable{
	
	@FXML
    private AnchorPane listview_pane;
    
	@FXML
	private ListView<Doctor> list_view;
	
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		
		ObservableList<Doctor> doctorObservableList = null;
		ArrayList<Doctor> doctorList = null;
		doctorList = GetDoctorDetailsModel.getAllDoctorDetails();

		//populate doctorObservable List
		doctorObservableList = FXCollections.observableArrayList(doctorList);
		//doctorObservableList.addAll(doctorList);
		list_view.setItems(doctorObservableList);
		list_view.setCellFactory(doctorListView -> new ListViewDoctorsAdminController());
	}
	
}
