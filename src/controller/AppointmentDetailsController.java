package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppointmentDetailsController implements Initializable{
	@FXML
    private AnchorPane confirm_appt;
    
    @FXML
    private Button book_appt;
    
	Stage stage;
	Scene newscene;
	Parent content;
	
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
				 content= FXMLLoader.load(getClass().getResource("../view/appointment_details.fxml"));
				}
			 
		 }catch (IOException e)	{
			  e.printStackTrace();
		 }
			
			newscene= new Scene(content);
			stage.setScene(newscene);
		}
	}

