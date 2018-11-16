package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.GetDoctorDetailsModel;
import model.Doctor;

public class BookAppointmentController implements Initializable{
	@FXML
    private ScrollPane doctor_list;
    
	@FXML
	private ListView<Doctor> list_view;
   
    @FXML
    private Label doctor_label;
    
    String categoryText;
    
	Stage stage;
	Scene newscene;
	Parent content;

	@Override
    public void initialize(URL url, ResourceBundle rb) {
		System.out.println(categoryText);
		/*book_appt_button.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
		});*/
	}

	public void initData(String category) {
		ObservableList<Doctor> doctorObservableList = null;
		ArrayList<Doctor> doctorList = null;
		System.out.println(category);
		doctorList = GetDoctorDetailsModel.getAllDoctorDetailsForCategory(category);
		categoryText = category;
		doctor_label.setText(category);
		System.out.println("size");
		System.out.println(doctorList.size());

		//populate doctorObservable List
		doctorObservableList = FXCollections.observableArrayList(doctorList);
		//doctorObservableList.addAll(doctorList);
		list_view.setItems(doctorObservableList);
		list_view.setCellFactory(doctorListView -> new DoctorListViewCell());
	}
	
	public void setStage(int index) {
		System.out.println(index);
	}
	
	public void myhandle(MouseEvent event)
	{
		 Node node = (Node) event.getSource();
		 String s = node.getId();
		 System.out.println(s);
		 System.out.println("MOUSE CLICKED "+event.getSource());
		 stage = (Stage)doctor_list.getScene().getWindow();
		 try
		 { 
			 if(s.equals("book_appt_button"))
			 	{
				 content= FXMLLoader.load(getClass().getResource("../view/doctor_profile.fxml"));
				}
		 }catch (IOException e)	{
			  e.printStackTrace();
		 }
			
			newscene= new Scene(content);
			stage.setScene(newscene);
	}

	public void goToHome() {
		stage = (Stage)doctor_list.getScene().getWindow();
		try {
			content= FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		newscene= new Scene(content);
		stage.setScene(newscene);
	}

	}

