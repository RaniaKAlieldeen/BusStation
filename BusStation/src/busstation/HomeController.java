
package busstation;

import java.io.IOException;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class HomeController implements Initializable {
    
    @FXML private Label Welcomelabel;
    @FXML private Button PassengerB;
    @FXML private Button EmployeeB;
    @FXML private ChoiceBox EmployeeCB;
    @FXML private Button GoB;
    
    
    
    
    
    public void passengerButton(ActionEvent event) throws IOException
    {
        //go to passenger login scene
        Parent PassengerLoginParent = FXMLLoader.load(getClass().getResource("CustomerLogin.fxml"));
        Scene PassengerLoginScene = new Scene(PassengerLoginParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(PassengerLoginScene);
        window.show();
    }
    
    public void employeeButton(ActionEvent event) throws IOException
    {
        EmployeeCB.setDisable(false);
        EmployeeCB.setVisible(true);
        EmployeeB.setDisable(true);
        GoB.setDisable(false);
        GoB.setVisible(true);
        
        
    }
    
    public void goB(ActionEvent event) throws IOException
    {
        //EmployeeB.setText(EmployeeCB.getValue().toString());
        
        if(EmployeeCB.getValue().toString().equals("Driver"))
        {
            Parent DriverLoginParent = FXMLLoader.load(getClass().getResource("DriverLogin.fxml"));
        Scene DriverLoginScene = new Scene(DriverLoginParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(DriverLoginScene);
        window.show();
        }else{
        Parent ManagerLoginParent = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
        Scene ManagerLoginScene = new Scene(ManagerLoginParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(ManagerLoginScene);
        window.show();
        
        }
            
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        EmployeeCB.getItems().addAll("Driver","Manager");
    }    
    
}

