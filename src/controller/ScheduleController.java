package controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.MyLogger;

public class ScheduleController 
{
	@FXML
	private Button viewprofile;
	
	@FXML
	private ImageView home;
	
	Stage stage;
	Scene newscene;
    Parent content;
	
	public void initialize()
	{
		viewprofile.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
			  public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
				});
		
		home.setOnMouseClicked(new EventHandler<MouseEvent>()
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
		 String s=node.getId();
		 System.out.println(s);
		 System.out.println("MOUSE CLICKED "+event.getSource());
		  stage = (Stage)viewprofile.getScene().getWindow();
		  try
		  { 
			  if(s.equals("viewprofile"))
			  	{ content= FXMLLoader.load(getClass().getResource("/view/PProfile.fxml"));}
			  
			  else if(s.equals("home"))
			  {
				  content= FXMLLoader.load(getClass().getResource("/view/doctor_portal.fxml"));
			  }
			 
		  }catch (IOException e) {
			  MyLogger.logInfo(this.getClass().getName(), e);
			  e.printStackTrace();}
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
}
