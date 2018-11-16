package controller;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddDoctor1Controller implements Initializable{
	    @FXML
	    private AnchorPane add_doctor1_page;
	    
	    @FXML
	    private Button continue_button;
	    
	    @FXML
	    private TextField doctor_id;
	    
	    @FXML
	    private TextField first_name;
	    
	    @FXML
	    private TextField last_name;
	    
	    @FXML
	    private TextField email_Id;
	    
	    @FXML
	    private DatePicker DOB;
	    
	    @FXML
	    private TextField address;
	    
	    @FXML
	    private TextField contact_no;
	    
	    @FXML
	    private ComboBox category;
	    
		Stage stage;
		Scene newscene;
		Parent content;
		
		public String get_dId()
		{
			return doctor_id.getText();
		}
		
		public String get_firstname()
		{
			return first_name.getText();
		}

		public String get_lastname()
		{
			return last_name.getText();
		}
		
		public String get_emailId()
		{
			return email_Id.getText();
		}
		
		public String get_contact()
		{
			return contact_no.getText();
		}

		public Date get_dob()
		{
			LocalDate localDate = DOB.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			return Date.from(instant);
		}
		
		public String get_addr()
		{
			return address.getText();
		}

		public String get_categ()
		{
			return category.getSelectionModel().getSelectedItem().toString();
		}

		
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	//action listeners
	    	
	    	category.getItems().removeAll(category.getItems());
	        category.getItems().addAll("cardiology", "orthopedics", "gynaecology", "medicine", "pedriatrician","ent");
	        category.getSelectionModel().select("<-Select Department->");
	    	continue_button.setOnMouseClicked(new EventHandler<MouseEvent>()
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
			 stage = (Stage)add_doctor1_page.getScene().getWindow();
			 try
			 { 
				 if(s.equals("continue_button"))
				 	{
					 //get all the details from the text boxes and call the function in model which will enter details in database
					 String doctorId = doctor_id.getText();
					 String firstName = first_name.getText();
					 String lastName = last_name.getText();
					 String emailId = email_Id.getText();
					 LocalDate localDate = DOB.getValue();
					 Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
					 Date dob = Date.from(instant);
					 String addr = address.getText();
					 String contact = contact_no.getText();
					 String categ = category.getSelectionModel().getSelectedItem().toString();
					 
					 Boolean val = true;
					 if(doctorId.equals(null)||firstName.equals(null) || lastName.equals(null) || emailId.equals(null) || dob.equals(null) || addr.equals(null) || contact.equals(null) || categ.equals("<-Select Department->")) 
					{
								System.out.println("error");
								val = false;
					} 
					 if(val) {
						 FXMLLoader loader = new FXMLLoader();
						 loader.setLocation(getClass().getResource("../view/addDr2.fxml"));
						 content= loader.load();
						 AddDoctor2Controller controller = loader.getController();
						 controller.getvalues(doctorId,firstName,lastName,emailId,dob,addr,contact,categ);
					 }else {
						 Alert errorAlert = new Alert(AlertType.ERROR);
						 errorAlert.setHeaderText("Error");
						 errorAlert.setContentText("Please fill all the required details!!");
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
