package controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.GetPatientDetailsModel;
import model.Patient;

public class EditPatientController implements Initializable
{
	@FXML
	private Button updateProfile;

	@FXML
	private AnchorPane edit_profile_pane;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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


		patient = GetPatientDetailsModel.getPatientDataFromID(patientId);
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
		contact.setText(Long.toString(patient.getContact()));
		address.setText(patient.getAddress());
	}

	public void updatePatientDetails() {
		patient.setFirstName(first_name.getText());
		patient.setLastName(last_name.getText());
		patient.setAddress(address.getText());
		patient.setContact(Long.parseLong(contact.getText()));
		patient.setEmail(email.getText());
		patient.setId(patientId);
		GetPatientDetailsModel.updatePatientData(patient);
	}
	public void goToHome() {
		stage = (Stage)edit_profile_pane.getScene().getWindow();
		try {
			content= FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		newscene= new Scene(content);
		stage.setScene(newscene);
	}

}