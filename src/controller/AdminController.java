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
import model.MyLogger;

public class AdminController implements Initializable{
	@FXML
    private AnchorPane admin_page;
    
    @FXML
    private Button view_doctors;
    
    @FXML
    private Button view_patients;
    
    @FXML
    private Button add_doctors;
    
	Stage stage;
	Scene newscene;
	Parent content;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//action listeners
    	
    	add_doctors.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event);
			  }
		});
    	view_doctors.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event);
			  }
		});
    	
    	view_patients.setOnMouseClicked(new EventHandler<MouseEvent>()
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
		 stage = (Stage)admin_page.getScene().getWindow();
		 try
		 { 
			 if(s.equals("add_doctors"))
			 {
				content= FXMLLoader.load(getClass().getResource("../view/addDr1.fxml"));
			 }
			 if(s.equals("view_doctors"))
			 {
				content= FXMLLoader.load(getClass().getResource("../view/doctor_listview.fxml"));
			 }
			 
			 if(s.equals("view_patients"))
			 {
				content= FXMLLoader.load(getClass().getResource("../view/patients_listview.fxml"));
			 }
			 
		 }catch (IOException e)	{
			 MyLogger.logInfo(this.getClass().getName(), e);
			  e.printStackTrace();
		 }
			
			newscene= new Scene(content);
			stage.setScene(newscene);
	}
}
