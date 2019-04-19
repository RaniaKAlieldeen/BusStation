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

public class CustomerLoginController implements Initializable {

    @FXML
    private TextField IdField;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button LoginB;
    @FXML
    private Button BackB;
    @FXML
    private Label ErrorL;

    public Customer check(String id, String password)throws IOException{
        Customer.readCustomerFile();
        if(Customer.CUSTOMER_MAP.containsKey(id)){
            //System.out.println("ye its correct right here");
            Customer c = Customer.CUSTOMER_MAP.get(id);
            //System.out.println(c.getiD());
            return c;
        }else 
            return null;
    }
    
    public void loginBClicked(ActionEvent event) throws IOException {
        //check values in idfield and passwordfield
        String pass = PasswordField.getText();
        String id = IdField.getText();
        Customer c = check(id,pass);
        if (c!=null) {
            //if correct go to next page(which i didnt create yet)
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().
                    getResource("busstation/CustomerProfile.fxml"));
            Parent CustomerParent = loader.load();
            Scene CustomerScene = new Scene(CustomerParent);
            CustomerProfileController CPC = loader.getController();
            CPC.initData(c);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(CustomerScene);
            window.show();

        } else {
            //if wrong display error message
            ErrorL.setVisible(true);

        }
    }

    public void backBClicked(ActionEvent event) throws IOException {
        Parent HomeParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene HomeScene = new Scene(HomeParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
