/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busstation;

import busStationClasses.Customer;
import java.io.IOException;
//import station.*;
import java.net.URL;
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
import javafx.stage.Stage;


public class CustomerProfileController implements Initializable {

   // Passenger customer;
    
    @FXML private Label WelcomeL;
    @FXML private Label TripsL;
    @FXML private Button BookTripB;
    @FXML private Button LogoutB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    Customer c;
    public void initData(Customer c){
        WelcomeL.setText("Welcome, " + c.getName());
        this.c = c;
        TripsL.setText(c.getTicketsBooked().toString());
    }
    
    public void BookTripBClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerActions.fxml"));
        Parent BookParent = loader.load();
        Scene BookScene = new Scene(BookParent);
        
        CustomerActionsController CAC = loader.getController();
        CAC.initData(c);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(BookScene);
        window.show();   
        
    }
    
    public void LogoutBClicked(ActionEvent event) throws IOException
    {
        Parent HomeParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene HomeScene = new Scene(HomeParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();   
    }
    
}
