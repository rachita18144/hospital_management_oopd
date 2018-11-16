package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Doctor;
import model.DoctorModel;
import model.PatientModel;

public class SelectDoctorController implements Initializable{
	@FXML
	private RadioButton select_doctor;

	@FXML
	private RadioButton specialization;

	@FXML
	private Button show_doctors_button;

	@FXML
	private AnchorPane select_patient_category;

	Stage stage;
	Parent content;
	Scene newScene;
	String radioButtonSelected;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		select_doctor.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					radioButtonSelected = "select_doctor"; 
				}
			}
		});
		specialization.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
				if (isNowSelected) { 
					radioButtonSelected = "specialization";
				}
			}
		});

		show_doctors_button.setOnMouseClicked(new EventHandler<MouseEvent>()
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
		String s = node.getId();
		System.out.println(s);
		System.out.println("MOUSE CLICKED "+event.getSource());
		stage = (Stage)select_patient_category.getScene().getWindow();
		try
		{ 
			if(s.equals("show_doctors_button")) {
				if(radioButtonSelected.equals("specialization")) {
					content= FXMLLoader.load(getClass().getResource("../view/patient_categories.fxml"));
				}
				if(radioButtonSelected.equals("select_doctor")) {
					Doctor doctor = getDoctorFromSmartAlgorithm();
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("../view/doctor_profile.fxml"));
					content = loader.load();
					AppointmentDetailsController controller = loader.getController();
					controller.setProfileData(doctor);
				}
			}

		}catch (IOException e)	{
			e.printStackTrace();
		}
		newScene= new Scene(content);
		stage.setScene(newScene);
	}

	public void goToHome() {
		stage = (Stage)select_patient_category.getScene().getWindow();
		try {
			content= FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		newScene= new Scene(content);
		stage.setScene(newScene);
	}
	public Doctor getDoctorFromSmartAlgorithm(){
		String id = getIdFromFile();
		String pid = PatientModel.getPatientIdFromId(id);
		String[] parts = pid.split("-");
		String category = getCategoryFromPatientId(parts[0]);
		ArrayList<Doctor> doctors = DoctorModel.getAllDoctorDetailsForCategory(category);
		int random = (int)(Math.random() * doctors.size()-1 + 0);
		return doctors.get(random);
	}
	private String getCategoryFromPatientId(String pCat) {
		String category = "";

		switch(pCat) {
		case "CAR" :{
			category = "cardiology";
			break;
		}
		case "ORTH" :{
			category = "orthopedics";
			break;
		}
		case "GYNAE" :{
			category = "gynaecology";
			break;
		}
		case "MED" :{
			category = "medicine";
			break;
		}
		case "PED" :{
			category = "pedriatrician";
			break;
		}
		case "ENT" :{
			category = "ent";
			break;
		}
		}

		return category;
	}

	private String getIdFromFile() {
		Scanner in;
		String patientId = ""; 
		try {
			in = new Scanner(new FileReader("p_id.txt"));
			StringBuilder sb = new StringBuilder();
			while(in.hasNext()) {
				sb.append(in.next());
			}
			in.close();
			patientId = sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return patientId;
	}
}
