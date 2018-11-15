package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.PatientModel;
import model.Patient;

public class EditPatientController 
{

	//TODO : Call a db function to get all the coresponsidng patient details and set them to the text view.
	//TODO: Once the user presses update button, get all the values from the text views and call the db update function. 

	@FXML
	private Button updateProfile;

	@FXML
	private TextField first_name;

	@FXML
	private TextField last_name;

	@FXML
	private TextField email;

	@FXML
	private TextField contact;

	@FXML
	private TextArea address;

	Stage stage;
	Scene newscene;
	Parent content;
	Patient patient;
	String patientId = ""; 

	public void initialize() {
		Scanner in;
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
		System.out.println(patientId);


		patient = PatientModel.getPatientDataFromID(patientId);
		setPatientData();

		updateProfile.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				updatePatientDetails();
			}
		});
	}
	private void setPatientData() {
		first_name.setText(patient.getFirstName());
		last_name.setText(patient.getLastName());
		email.setText(patient.getEmail());
		contact.setText(patient.getContact());
		address.setText(patient.getAddress());
	}

	public void updatePatientDetails() {
		patient.setFirstName(first_name.getText());
		patient.setLastName(last_name.getText());
		patient.setAddress(address.getText());
		patient.setContact(contact.getText());
		patient.setEmail(email.getText());
		patient.setpatientId(patientId);
		PatientModel.updatePatientData(patient);
	}
}