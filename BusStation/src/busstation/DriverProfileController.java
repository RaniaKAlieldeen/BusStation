/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busstation;
import busStationClasses.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class DriverProfileController implements Initializable {

    @FXML private Label DriverNameL;
    @FXML private Label ScheduleL;
    @FXML private Button LogoutB;
    
    public void setScheduleL(Driver d) throws IOException
    {
        //get drivers assigned trips and display in label
        DriverNameL.setText(d.getName()+"'s Schedule");
        ScheduleL.setText(d.getTripsAssigned().toString());
    }
    
    public void LogoutBClicked(ActionEvent event) throws IOException
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
