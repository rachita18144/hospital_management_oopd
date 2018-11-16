package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Doctor;

public class AppointmentDetailsController implements Initializable{
	@FXML
	private AnchorPane confirm_appt;

	@FXML
	private Button book_appt;

	@FXML
	private Text specialization;

	@FXML
	private Text doctor_name;

	@FXML
	private Label schedule_days;

	@FXML
	private Label experience;

	@FXML
	private Label education;

	@FXML
	private Label schedule_timings;

	@FXML
	private Label bio;

	@FXML
	private Label address;

	@FXML
	private Label contact_number;

	Stage stage;
	Scene newscene;
	Parent content;
	Doctor doctor;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		book_appt.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				myhandle(event); 
			}
		});
	}

	public void setProfileData(Doctor doctor) {
		String days = "";
		String startTime = "";
		String endTime = "";
		this.doctor = doctor;
		doctor_name.setText(doctor.getFirstName() + " " + doctor.getLastName());
		specialization.setText(doctor.getSpecialization());
		experience.setText(Float.toString(doctor.getExperience()) + " Years Experience");
		education.setText(doctor.getEducation());
		address.setText(doctor.getAddress());
		bio.setText(doctor.getBio());
		contact_number.setText(doctor.getContact() + " ");
		for(int i=0; i<doctor.getSchedule().getDays().size(); i++) {
			days = days + " " + doctor.getSchedule().getDays().get(i); 
		}
		schedule_days.setText(days);
		System.out.println(doctor.getSchedule().getStartTime().toString());
		startTime = getStringStartTime(doctor.getSchedule().getStartTime());
		endTime = getStringEndTime(doctor.getSchedule().getEndTime());
		schedule_timings.setText(startTime + " - " + endTime); 
	}

	private String getStringEndTime(Date endTime) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String formattedDate = dateFormat.format(endTime).toString();
		return formattedDate;
	}

	private String getStringStartTime(Date startTime) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String formattedDate = dateFormat.format(startTime).toString();
		return formattedDate;
	}

	public void myhandle(MouseEvent event)
	{
		Node node = (Node) event.getSource();
		String s = node.getId();
		System.out.println(s);
		System.out.println("MOUSE CLICKED "+event.getSource());
		stage = (Stage)confirm_appt.getScene().getWindow();
		try
		{ 
			if(s.equals("book_appt"))
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/appointment_details.fxml"));
				content = loader.load();
				ConfirmAppointmentController controller = loader.getController();
				controller.setDoctorData(doctor);
			}

		}catch (IOException e)	{
			e.printStackTrace();
		}
		newscene= new Scene(content);
		stage.setScene(newscene);
	}
	public void goToHome() {
		stage = (Stage)confirm_appt.getScene().getWindow();
		try {
			content= FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		newscene= new Scene(content);
		stage.setScene(newscene);
	}
}

