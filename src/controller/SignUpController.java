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
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import model.UserSignUp;


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
	    private TextField contact_details;
	    
	    @FXML
	    private TextField email_id;
	     
		Stage stage;
		Scene newscene;
		Parent content;
		
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
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
			 Node node = (Node) event.getSource();
			 String s = node.getId();
			 System.out.println(s);
			 System.out.println("MOUSE CLICKED "+event.getSource());
			 stage = (Stage)sign_up_page.getScene().getWindow();
			 try
			 { 
				 if(s.equals("signup_button"))
				 	{
					 //get all the details from the text boxes and call the function in model which will enter details in database
					 String firstName = first_name.getText();
					 System.out.println(firstName);
					 String lastName = last_name.getText();
					 String passwordValue = password.getText();
					 String addressValue = address.getText();
					 String contactDetails = contact_details.getText();
					 String dateOfBirth = "random";
					 String emailId = email_id.getText();
					 boolean val = UserSignUp.storeSignUpDataForUser(firstName, lastName, passwordValue, addressValue, contactDetails, dateOfBirth, emailId);
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
				  e.printStackTrace();
			 }
				
				newscene= new Scene(content);
				stage.setScene(newscene);
		}
}
