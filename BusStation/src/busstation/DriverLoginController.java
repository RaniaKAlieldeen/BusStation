/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busstation;
import busStationClasses.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rania
 */
public class DriverLoginController implements Initializable {

    @FXML private TextField IdField;
    @FXML private PasswordField PasswordField; 
    @FXML private Button LoginB;
    @FXML private Button BackB;
    @FXML private Label ErrorL;
    
    
    
    public Driver check(String id, String password)throws IOException{
        Driver.readDriversFile();
        if(Driver.DRIVER_MAP.containsKey(id)){
            Driver d = Driver.DRIVER_MAP.get(id);
            return d;
        }else 
            return null;
    }

    public void loginBClicked(ActionEvent event) throws IOException
    {
        //check values in idfield and passwordfield
        String id = IdField.getText();
        String pass = PasswordField.getText();
        Driver d = check(id,pass);
        if(d!=null){
        //if correct go to next page
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DriverProfile.fxml"));
        Parent DriverParent = loader.load();
        Scene DriverScene = new Scene(DriverParent);
        
        DriverProfileController DPC = loader.getController();
            DPC.setScheduleL(d);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(DriverScene);
        window.show();
        
        }else{
        //if wrong display error message
        ErrorL.setVisible(true);
        
        }
    }
    
    public void backBClicked(ActionEvent event) throws IOException
    {
        Parent HomeParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene HomeScene = new Scene(HomeParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}