package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MyLogger;
import model.UserSignUp;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;


public class LauncherController implements Initializable {
	    @FXML
	    private AnchorPane launch_page;
	    
	    @FXML
	    private Button sign_up_button;
	    
	    @FXML
	    private Button login_button;
	    
	    @FXML
	    private TextField username;
	    
	    @FXML
	    private TextField password;
	    
		Stage stage;
		Scene newscene;
		Parent content;
		
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	//action listeners
	    	sign_up_button.setOnMouseClicked(new EventHandler<MouseEvent>()
			{
	    		public void handle(MouseEvent event)
				  {
					myhandle(event);
				  }
			});
	    	
	    	login_button.setOnMouseClicked(new EventHandler<MouseEvent>()
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
			 stage = (Stage)launch_page.getScene().getWindow();
			 try
			 { 
				 if(s.equals("sign_up_button"))
				 	{
					 content= FXMLLoader.load(getClass().getResource("../view/signup.fxml"));
					}
				 
				 if(s.equals("login_button"))
				 	{
					 String userNameValue = username.getText();
					 String passwordValue = password.getText();
					 String user = UserSignUp.checkUserAuthenticationDetails(userNameValue, passwordValue);
					 System.out.println(user);
					 if(user.equals("patient")) {
						 System.out.println("IM patient");
						 content = FXMLLoader.load(getClass().getResource("../view/patient_portal.fxml"));
					 } else if(user.equals("doctor")) {
						 content = FXMLLoader.load(getClass().getResource("../view/doctor_portal.fxml"));
					 }else if(user.equals("admin")) {
						 //content = FXMLLoader.load(getClass().getResource("../view/doctor_portal.fxml"));
						 content = FXMLLoader.load(getClass().getResource("../view/admin.fxml"));
					 }else {
						 Alert errorAlert = new Alert(AlertType.ERROR);
						 errorAlert.setHeaderText("Error");
						 errorAlert.setContentText("Username/password incorrect");
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
