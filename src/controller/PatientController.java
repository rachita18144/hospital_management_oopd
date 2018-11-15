package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;


public class PatientController implements Initializable {
	@FXML
	private AnchorPane book_appointment;

	@FXML
	private AnchorPane view_doctor_profile;

	@FXML
	private AnchorPane patient_portal;

	@FXML
	private AnchorPane search_doctor;

	@FXML
	private AnchorPane edit_profile;

	Stage stage;
	Scene newscene;
	Parent content;
	String radioButtonSelected;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//action listeners
		book_appointment.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				myhandle(event); 
			}
		});

		view_doctor_profile.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				myhandle(event); 
			}
		});

		search_doctor.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				myhandle(event); 
			}
		});
		edit_profile.setOnMouseClicked(new EventHandler<MouseEvent>()
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
		stage = (Stage)patient_portal.getScene().getWindow();
		try
		{ 
			if(s.equals("book_appointment"))
			{
				content= FXMLLoader.load(getClass().getResource("../view/patient_category.fxml"));
			}
			if(s.equals("view_doctor_profile"))
			{
				content= FXMLLoader.load(getClass().getResource("../view/doctor_profile.fxml"));
			}
			if(s.equals("search_doctor"))
			{
				content= FXMLLoader.load(getClass().getResource("../view/search_doctor.fxml"));
			}
			if(s.equals("edit_profile"))
			{
				content= FXMLLoader.load(getClass().getResource("../view/editPatientProfile.fxml"));
			}

		}catch (IOException e)	{
			e.printStackTrace();
		}

		newscene= new Scene(content);
		stage.setScene(newscene);
	}
}
