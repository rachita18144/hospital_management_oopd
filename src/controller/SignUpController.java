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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import model.UserSignUp;
import model.MyLogger;
import model.Patient;


public class SignUpController implements Initializable {
	    @FXML
	    private AnchorPane sign_up_page;
	    
	    @FXML
	    private Button signup_button;
	    
	    @FXML
	    private TextField first_name;
	    
	    @FXML
	    private TextField last_name;
	    
	    @FXML
	    private TextField password;
	    
	    @FXML
	    private TextField address;

	    @FXML
	    private TextField email_id;
	    
	    @FXML
	    private TextField contact_details;
	    
	    @FXML
	    private TextField age;

	    @FXML
	    private TextField weight;

	    @FXML
	    private TextField height;

	    @FXML
	    private ComboBox<String> type;

	    @FXML
	    private ComboBox<String> category;

		Stage stage;
		Scene newscene;
		Parent content;
		
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	
	    	type.getItems().addAll(
	    		    "opd",
	    		    "local"
	    		);

	    	category.getItems().addAll(
	    		    "cardiology",
	    		    "orthopedics",
	    		    "ent",
	    		    "gynaecology",
	    		    "medicine",
	    		    "pedriatrician"
	    		);

	    	//action listeners
	    	signup_button.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
	    		public void handle(MouseEvent event)
				  {
					myhandle(event);
				  }
			});
	    }  
	    
		public void myhandle(MouseEvent event)
		{
			 Patient patient = new Patient();
			 Node node = (Node) event.getSource();
			 String s = node.getId();
			 System.out.println(s);
			 System.out.println("MOUSE CLICKED "+event.getSource());
			 stage = (Stage)sign_up_page.getScene().getWindow();
			 try
			 { 
				 if(s.equals("signup_button"))
				 	{
					 patient.setType(type.getValue());
					 patient.setCategory(category.getValue());
					 //get all the details from the text boxes and call the function in model which will enter details in database
					 patient.setFirstName(first_name.getText());
					 patient.setLastName(last_name.getText());
					 patient.setPassword(password.getText());
					 patient.setAddress(address.getText());
					 patient.setContact(Long.parseLong(contact_details.getText()));
					 patient.setAge(Integer.parseInt(age.getText()));
					 patient.setEmail(email_id.getText());
					 patient.setWeight(Integer.parseInt(weight.getText()));
					 patient.setHeight(Integer.parseInt(weight.getText()));

					 boolean val = UserSignUp.storeSignUpDataForUser(patient);
					 if(val) {
						 content= FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
					 }else {
						 Alert errorAlert = new Alert(AlertType.ERROR);
						 errorAlert.setHeaderText("Error");
						 errorAlert.setContentText("Please fill all details to complete SignUp");
						 errorAlert.showAndWait();
						 return;
					 }
					}
				 
			 }catch (IOException e)	{
				 MyLogger.logInfo(this.getClass().getName(), e);
				  e.printStackTrace();
			 }
				newscene= new Scene(content);
				stage.setScene(newscene);
		}
}
