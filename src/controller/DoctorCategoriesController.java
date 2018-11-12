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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DoctorCategoriesController implements Initializable{
	@FXML
    private AnchorPane doctor_specializations;
    
    @FXML
    private AnchorPane cardiology;

    @FXML
    private AnchorPane orthopedics;

    @FXML
    private AnchorPane ent;

    @FXML
    private AnchorPane gynaecology;

    @FXML
    private AnchorPane medicine ;

    @FXML
    private AnchorPane pedriatician;
    
	Stage stage;
	Scene newscene;
	Parent content;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		cardiology.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
		});
		orthopedics.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
		});
		ent.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
		});
		gynaecology.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
		});

		medicine.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				myhandle(event); 
			  }
		});

		pedriatician.setOnMouseClicked(new EventHandler<MouseEvent>()
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
		 stage = (Stage)doctor_specializations.getScene().getWindow();
		 try
		 { 
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(getClass().getResource("../view/doctor_list.fxml"));
			 content = loader.load();
			 newscene= new Scene(content);
			 BookAppointmentController controller = loader.getController();
			 controller.initData(s);
			 stage.setScene(newscene);
			 
		 }catch (IOException e)	{
			  e.printStackTrace();
		 }
			
		}
	}

