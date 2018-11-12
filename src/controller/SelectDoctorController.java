package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SelectDoctorController implements Initializable{
		@FXML
	    private RadioButton select_doctor;
	    
	    @FXML
	    private RadioButton specialization;
	    
	    @FXML
	    private Button show_doctors_button;
	    
	    @FXML
	    private AnchorPane select_patient_category;
	    
	    Stage stage;
	    Parent content;
	    Scene newScene;
	    String radioButtonSelected;
	   
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
		 
		 select_doctor.selectedProperty().addListener(new ChangeListener<Boolean>() {
	    	    @Override
	    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
	    	        if (isNowSelected) { 
	    	        	radioButtonSelected = "select_doctor"; 
	    	        }
	    	    }
	    	});
	    	specialization.selectedProperty().addListener(new ChangeListener<Boolean>() {
	    	    @Override
	    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
	    	        if (isNowSelected) { 
	    	        	radioButtonSelected = "specialization";
	    	        }
	    	    }
	    	});
	    	
	    	show_doctors_button.setOnMouseClicked(new EventHandler<MouseEvent>()
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
			 stage = (Stage)select_patient_category.getScene().getWindow();
			 try
			 { 
				 if(s.equals("show_doctors_button")) {
					 if(radioButtonSelected.equals("specialization")) {
					 content= FXMLLoader.load(getClass().getResource("../view/patient_categories.fxml"));
				 	}
				 if(radioButtonSelected.equals("select_doctor")) {
					 content= FXMLLoader.load(getClass().getResource("../view/doctor_profile.fxml"));
				 	}
				 }
				 
			 }catch (IOException e)	{
				  e.printStackTrace();
			 }
			 	newScene= new Scene(content);
			 	stage.setScene(newScene);
			}
	 	}
