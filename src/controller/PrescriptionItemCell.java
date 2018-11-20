package controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.MyLogger;
import model.Patient;
import model.PatientModel;
import model.Prescription;

public class PrescriptionItemCell extends ListCell<Prescription>
{
	
		@FXML
		private TextField assigned_doctor,last_visit;
		
		@FXML
		private AnchorPane list_item_pane;
		
		@FXML
		private Button viewprescription;
		
		private FXMLLoader loader;
		Stage stage;
		Scene newscene;
	    Parent content;
		Prescription slip;
		@Override
		protected void updateItem(Prescription slip, boolean empty) 
		{
	        super.updateItem(slip, empty);
	       this.slip=slip;
	        if(empty || slip == null) {
	            setText(null);
	            setGraphic(null);
	        } else 
	        {

	        if (loader == null) 
	        {
	            loader = new FXMLLoader(getClass().getResource("../view/item_prescription.fxml"));
	            loader.setController(this);
	            try {
	                loader.load();
	            } catch (IOException e) 
	            {
	                e.printStackTrace();
	            }
	        }
	       System.out.println("date "+slip.getDate());
	        last_visit.setText(slip.getDate().toString());
	        String temp=PatientModel.getDRName(Integer.parseInt(slip.getdID()));
	        assigned_doctor.setText(temp);
	        setText(null);
	        setGraphic(list_item_pane);
	         viewprescription.setOnMouseClicked(new EventHandler<MouseEvent>()
	 		{
				  public void handle(MouseEvent event)
				  {
					  System.out.println("clicked onnnn " + viewprescription.getId());
					  myhandle(event); 
				  }
		      
		    }); 
	        }
		}
		
		public void myhandle(MouseEvent event)
		{
			 Node node = (Node) event.getSource();
			 String s=node.getId();
			 System.out.println("MOUSE CLICKED "+event.getSource());
			  stage = (Stage)viewprescription.getScene().getWindow();
			  try
			  { 
				  if(s.equals("viewprescription"))
				  	{ 
					  loader = new FXMLLoader(getClass().getResource("/view/Past_Prescription.fxml"));
					  content=loader.load();  
					  PastPrescriptionController ppc = loader.getController();
					  System.out.println("passing slip");
					  ppc.passSlipData(slip);
					}
				  
				 
			  }catch (IOException e) {
				  MyLogger.logInfo(this.getClass().getName(), e);
				  e.printStackTrace();}
			
				newscene= new Scene(content);
				 stage.setScene(newscene);
			 
		}
	}

