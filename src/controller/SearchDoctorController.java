package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import model.DoctorModel;
import model.Doctor;


public class SearchDoctorController implements Initializable {
	@FXML
	private AnchorPane search_doctor_pane;

	@FXML
	private TableView<Doctor> doctor_table;

	@FXML
	private TableColumn<Doctor, String> first_name;

	@FXML
	private TableColumn<Doctor, String> last_name;

	@FXML
	private TableColumn<Doctor, String> address;

	@FXML
	private TableColumn<Doctor, String> specialization;

	@FXML
	private TableColumn<Doctor, String> dr_id;

	@FXML
	private TextField search_box;

	private ObservableList<Doctor> doctorObservableList = FXCollections.observableArrayList();

	Stage stage;
	Scene newscene;
	Parent content;

	static ArrayList<Doctor> doctorList;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		doctorList = DoctorModel.getAllDoctorDetails();
		System.out.println(doctorList.get(0).getFirstName());

		//TODO:Set doctor details in table

		doctorObservableList = FXCollections.observableArrayList(doctorList);

		first_name.setCellValueFactory(
				new PropertyValueFactory<Doctor,String>("firstName")
				);
		last_name.setCellValueFactory(
				new PropertyValueFactory<Doctor,String>("lastName")
				);

		address.setCellValueFactory(
				new PropertyValueFactory<Doctor,String>("address")
				);

		specialization.setCellValueFactory(
				new PropertyValueFactory<Doctor,String>("specialization")
				);

		dr_id.setCellValueFactory(
				new PropertyValueFactory<Doctor,String>("drID")
				);

		FilteredList<Doctor> filteredData = new FilteredList<>(doctorObservableList , p -> true);
		search_box.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(doctor -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (doctor.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (doctor.getLastName().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				} else if (doctor.getAddress().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (doctor.getSpecialization().toLowerCase().contains(lowerCaseFilter)) {
					return true; 
				} else if (doctor.getDrID().contains(lowerCaseFilter)) {
					return true; 
				}

				return false;
			});
		});
		
        SortedList<Doctor> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(doctor_table.comparatorProperty());
        doctor_table.setItems(sortedData);

		//action listeners
		doctor_table.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			public void handle(MouseEvent event)
			{
				myhandle(event); 
			}
		});
	}  

	public void myhandle(MouseEvent event)
	{
		/*Node node = (Node) event.getSource();
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

			 }catch (IOException e)	{
				  e.printStackTrace();
			 }

				newscene= new Scene(content);
				stage.setScene(newscene);
		 */
	}
}

