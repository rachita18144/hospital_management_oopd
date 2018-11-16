package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Doctor;
import model.DoctorModel;

public class BookingConfirmationController implements Initializable {
	
	
	@FXML
	private AnchorPane confirmation_pane;

	@FXML
	private Label doctor_name;

	@FXML
	private Label appt_date;

	@FXML
	private Label appt_time;
	Stage stage;
	Scene newscene;
	Parent content;

	public void setAppointmentConfirmationDetails(String doctorName, String time, String date) {
		doctor_name.setText(doctorName);
		appt_date.setText(date);
		appt_time.setText(time);
	};
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	public void goToHome() {
		stage = (Stage)confirmation_pane.getScene().getWindow();
		try {
			content= FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		newscene= new Scene(content);
		stage.setScene(newscene);
	}


}
