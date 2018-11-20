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
import model.GetDoctorDetailsModel;
import model.MyLogger;

public class ConfirmAppointmentController implements Initializable{

	@FXML
	private AnchorPane appt_pane;

	@FXML
	private Button confirm_booking_appt;

	@FXML
	private Label appt_date;

	@FXML
	private Label doctor_name;

	@FXML
	private Label appt_day;

	@FXML
	private Label doctor_timings;

	@FXML
	private DatePicker appt_date_picker;

	Stage stage;
	Scene newscene;
	Parent content;
	private Doctor doctor;
	boolean available;
	Date appointmentDate;
	Date appointmentTime;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		confirm_booking_appt.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				myhandle(event); 
			}
		});
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{ 
				appt_date.setText(appt_date_picker.getValue().toString());
				String day = getDayFromDate(appt_date_picker.getValue());
				appt_day.setText(day);
				available = checkIfDoctorAvailable(day,doctor);
				if(available) {
					appointmentDate = Date.from(appt_date_picker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					appointmentTime = doctor.getSchedule().getStartTime();
					String startTime = getStringTime(doctor.getSchedule().getStartTime());
					String endTime = getStringTime(doctor.getSchedule().getEndTime());
					doctor_timings.setText(startTime + " - " + endTime);
				}else {
					doctor_timings.setText("Doctor not available on selected date");
				}
			}
		}; 
		appt_date_picker.setOnAction(event);
	}

	private String getStringTime(Date startTime) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String formattedDate = dateFormat.format(startTime).toString();
		return formattedDate;
	}

	private boolean checkIfDoctorAvailable(String day, Doctor doctor) {
		return doctor.getSchedule().getDays().contains(day);
	}


	private String getDayFromDate(LocalDate value) {
		return value.getDayOfWeek()                       
				.getDisplayName(                      
						TextStyle.SHORT_STANDALONE ,     
						Locale.US                        
						); 
	}

	public void setDoctorData(Doctor doctor) {
		this.doctor = doctor;
		doctor_name.setText(doctor.getFirstName() + " " + doctor.getLastName());
	}

	public void myhandle(MouseEvent event)
	{
		Node node = (Node) event.getSource();
		String s = node.getId();
		System.out.println(s);
		System.out.println("MOUSE CLICKED "+event.getSource());
		stage = (Stage)appt_pane.getScene().getWindow();
		try
		{ 
			//TODO: Pass doctor information to confrim appointment page and create controller for same.
			if(s.equals("confirm_booking_appt"))
			{
				if(available) {
					System.out.println("im here babe");
					GetDoctorDetailsModel.enterAppointmentDetailsinDB(doctor.getDrID(), appointmentDate, appointmentTime);
				}else {
					//TODO: Display error box
					return;
				}
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("../view/appointment_confirmation.fxml"));
				content = loader.load();
				BookingConfirmationController controller = loader.getController();
				controller.setAppointmentConfirmationDetails(doctor.getFirstName()+ " " + doctor.getLastName(), 
						getStringTime(doctor.getSchedule().getStartTime()),appt_date.getText());
			}

		}catch (IOException e)	{
			MyLogger.logInfo(this.getClass().getName(), e);
			e.printStackTrace();
		}
		newscene= new Scene(content);
		stage.setScene(newscene);
	}

	public void goToHome() {
		stage = (Stage)appt_pane.getScene().getWindow();
		try {
			content= FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
		} catch (IOException e) {
			MyLogger.logInfo(this.getClass().getName(), e);
			e.printStackTrace();
		}
		newscene= new Scene(content);
		stage.setScene(newscene);
	}
}
