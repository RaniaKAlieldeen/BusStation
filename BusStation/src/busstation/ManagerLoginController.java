
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


public class ManagerLoginController implements Initializable {

    
    
    @FXML private TextField IdField;
    @FXML private PasswordField PasswordField; 
    @FXML private Button LoginB;
    @FXML private Button BackB;
    @FXML private Label ErrorL;
    
    
    public Manager check(String id, String password) throws IOException {
        Manager.readManagersFile();
        if (Manager.MANAGER_MAP.containsKey(id)) {
            Manager m = Manager.MANAGER_MAP.get(id);
            return m;
        }
        return null;
    }
    
    public void loginBClicked(ActionEvent event) throws IOException {
        //check values in idfield and passwordfield
        String pass = PasswordField.getText();
        String id = IdField.getText();
        Manager m = check(id,pass);
        if (m!=null) {
        //if correct go to next page(which i didnt create yet)
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManagerActions.fxml"));
        Parent AdminParent = loader.load();
        Scene AdminScene = new Scene(AdminParent);
        
        ManagerActionsController MAC = loader.getController();
        MAC.receiveManager(m);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AdminScene);
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
