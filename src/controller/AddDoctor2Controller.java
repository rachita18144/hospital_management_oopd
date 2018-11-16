package controller;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AddDoctor2Model;

public class AddDoctor2Controller implements Initializable{
	@FXML
    private AnchorPane add_doctor2_page;
    
    @FXML
    private Button add_button;
    
    @FXML
    private TextField education;
    
    @FXML
    private TextField experience;
    
    @FXML
    private TextField bio;
    
    @FXML
    private TextField role;
    
    @FXML
    private ComboBox level;
    
    @FXML
    private TextField password;
    
    @FXML
    private TextField hrs1;
    
    @FXML
    private TextField min1;
    
    @FXML
    private ComboBox am_pm1;

    @FXML
    private TextField hrs2;
    
    @FXML
    private TextField min2;
    
    @FXML
    private ComboBox am_pm2;
    
    @FXML
    private RadioButton monday;
    
    @FXML
    private RadioButton tuesday;
    
    @FXML
    private RadioButton wednesday;
    
    @FXML
    private RadioButton thursday;
    
    @FXML
    private RadioButton friday;
    
    @FXML
    private RadioButton saturday;
    
    @FXML
    private RadioButton sunday;
    
	Stage stage;
	Scene newscene;
	Parent content;
	String did;
	String fname;
	String lname;
	String eid;
	Date db;
	String ad;
	String cont;
	String dep;
	Boolean mn = false;
	Boolean ts = false;
	Boolean wd = false;
	Boolean thrs = false;
	Boolean fi = false;
	Boolean st = false;
	Boolean sn = false;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	//action listeners
    	
    	level.getItems().removeAll(level.getItems());
        level.getItems().addAll("Senior", "Junior");
        level.getSelectionModel().select("<-Select Level->");
        am_pm1.getItems().removeAll(am_pm1.getItems());
        am_pm1.getItems().addAll("AM", "PM");
        am_pm1.getSelectionModel().select("AM");
        am_pm2.getItems().removeAll(am_pm2.getItems());
        am_pm2.getItems().addAll("AM", "PM");
        am_pm2.getSelectionModel().select("AM");
        
        monday.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
    	        if (isNowSelected) { 
    	        	mn = true; 
    	        }
    	    }
    	});
        
        tuesday.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
    	        if (isNowSelected) { 
    	        	ts = true; 
    	        }
    	    }
    	});
        
        wednesday.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
    	        if (isNowSelected) { 
    	        	wd = true; 
    	        }
    	    }
    	});
        
        thursday.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
    	        if (isNowSelected) { 
    	        	thrs = true; 
    	        }
    	    }
    	});
        
        friday.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
    	        if (isNowSelected) { 
    	        	fi = true; 
    	        }
    	    }
    	});
        
        saturday.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
    	        if (isNowSelected) { 
    	        	st = true; 
    	        }
    	    }
    	});
        
        sunday.selectedProperty().addListener(new ChangeListener<Boolean>() {
    	    @Override
    	    public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
    	        if (isNowSelected) { 
    	        	sn = true; 
    	        }
    	    }
    	});
        
    	add_button.setOnMouseClicked(new EventHandler<MouseEvent>()
		{
    		public void handle(MouseEvent event)
			  {
				try {
					myhandle(event);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }
		});
    }  
    
	public void myhandle(MouseEvent event) throws ParseException
	{
		 Node node = (Node) event.getSource();
		 String s = node.getId();
		 System.out.println(s);
		 System.out.println("MOUSE CLICKED "+event.getSource());
		 stage = (Stage)add_doctor2_page.getScene().getWindow();
		 try
		 { 
			 if(s.equals("add_button"))
			 	{
				 //get all the details from the text boxes and call the function in model which will enter details in database
				 String docId = did;
				 String f_name = fname;
				 String l_name = lname;
				 String e_Id = eid;
				 Date dob = db;
				 String addr = ad;
				 String contact = cont;
				 String categ = dep;
				 String edu = education.getText();
				 String pass = password.getText();
				 String exp = experience.getText();
				 String dbio = bio.getText();
				 String rl = role.getText();
				 String lev = level.getSelectionModel().getSelectedItem().toString();
				 Boolean mon = mn;
				 Boolean tues = ts;
				 Boolean wed = wd;
				 Boolean thurs = thrs;
				 Boolean fri = fi;
				 Boolean sat = st;
				 Boolean sun = sn;
				 String h1 = hrs1.getText();
				 String m1 = min1.getText();
				 String ap1 = am_pm1.getSelectionModel().getSelectedItem().toString();
				 String dt1 = h1 + ":" + m1 + " " + ap1;
				 SimpleDateFormat dateparser1 = new SimpleDateFormat("h:mm a");
				 Date date1 = dateparser1.parse(dt1);
				 String h2 = hrs2.getText();
				 String m2 = min2.getText();
				 String ap2 = am_pm2.getSelectionModel().getSelectedItem().toString();
				 String dt2 = h2 + ":" + m2 + " " + ap2;
				 SimpleDateFormat dateparser2 = new SimpleDateFormat("h:mm a");
				 Date date2 = dateparser2.parse(dt2);
				 
				 boolean val = AddDoctor2Model.storeDoctor2data(docId,f_name,l_name,e_Id,dob,addr,contact,categ,dbio,edu, exp, rl,lev,date1,date2,mon,tues,wed,thurs,fri,sat,sun,pass);
				 if(val) {
					 content= FXMLLoader.load(getClass().getResource("../view/addDr2.fxml"));
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
			
			//newscene= new Scene(content);
			//stage.setScene(newscene);
	}

	public void getvalues(String doctorId, String firstName, String lastName, String emailId, Date dob, String addr,
			String contact, String categ) {
			did = doctorId;
			fname = firstName;
			lname = lastName;
			eid = emailId;
			db = dob;
			ad = addr;
			cont = contact;
			dep = categ;
	}
}
