package controller;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Doctor;
import model.Patient;

public class ReferDRItemCell extends ListCell<Doctor>
{
	@FXML
	private TextField did,name,role;
	
	@FXML
	private AnchorPane list_item_pane;
	
	@FXML
	private Button refer;
	
	private FXMLLoader loader;
	Stage stage;
	Scene newscene;
    Parent content;
    Doctor doc;
	
	@Override
	protected void updateItem(Doctor doc, boolean empty) 
	{
        super.updateItem(doc, empty);
        this.doc=doc;
        if(empty || doc == null) {
            setText(null);
            setGraphic(null);
        } else 
        {

        if (loader == null) {
            loader = new FXMLLoader(getClass().getResource("../view/item_refer.fxml"));
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
        did.setText(doc.getDrProfile().getdID());
        name.setText(doc.getFirstName()+" "+doc.getLastName());
        role.setText(doc.getDrProfile().getDesignation());
        setText(null);
        setGraphic(list_item_pane);
         refer.setOnMouseClicked(new EventHandler<MouseEvent>()
 		{
			  public void handle(MouseEvent event)
			  {
				  myhandle(event); 
			  }
	      
	    }); 
        }
	}
	
	public void myhandle(MouseEvent event)
	{
		 Node node = (Node) event.getSource();
		 String s=node.getId();
		 System.out.println(s);
		 System.out.println("MOUSE CLICKED "+event.getSource());
		  stage = (Stage)refer.getScene().getWindow();
		  
			  if(s.equals("refer"))
			  	{ 
				  Alert errorAlert = new Alert(AlertType.CONFIRMATION);
	 				errorAlert.setHeaderText("Refer");
	 				errorAlert.setContentText("Refered To Another Doctor");
	 				errorAlert.showAndWait();
				 
				}
			  
		
			newscene= new Scene(content);
			 stage.setScene(newscene);
		 
	}
}
