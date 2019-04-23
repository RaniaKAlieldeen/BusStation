
package busstation;

import busStationClasses.Customer;
import busStationClasses.Ticket;
import busStationClasses.Trip;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class BookTicketController implements Initializable {

    
    @FXML Button BackB ;
    @FXML Label PriceL;
    @FXML Label TripL;
    @FXML Label ErrorL;
    @FXML Label BookedL;
    @FXML Button BookTicketB;
    @FXML TextField NoOfSeatsTF;
    @FXML Button CheckB;
    Customer c;
    Trip t1;
    Trip t2;

    public void initData(Customer c, ObservableList<String> chosenTrips) throws IOException{
        this.c = c ; 
        if(chosenTrips.size()==1){
            t1 = Trip.TRIP_MAP.get(chosenTrips.get(0).substring(0,4));
            t2 = null;
            TripL.setText("One Way Ticket:\n"+chosenTrips.get(0));}
        else{
            t1 = Trip.TRIP_MAP.get(chosenTrips.get(0).substring(0,4));
            t2 = Trip.TRIP_MAP.get(chosenTrips.get(1).substring(0,4));
            TripL.setText("Round Trip Ticket:\n"+chosenTrips.get(0)+"\n"+chosenTrips.get(1));
        }
        //PriceL.setText("");
        BookTicketB.setDisable(true);
        
    }
    
    public void checkBClicked(ActionEvent event) throws IOException
    {
        
        try{
         int x = Integer.parseInt(NoOfSeatsTF.getText());
         if(c.checkAvailability(t1, t2, x)){
            PriceL.setText("available");
            BookTicketB.setDisable(false);}
         else{
             PriceL.setText("not available");
             
         }
        }catch(NumberFormatException e){
            ErrorL.setVisible(true);
        }
         
    }
    
    public void bookTicketBClicked(ActionEvent event) throws IOException
    {
        Ticket tc = c.reserve(t1, t2, c, Integer.parseInt(NoOfSeatsTF.getText()));
        PriceL.setText(String.valueOf(tc.getPrice()));
        BookedL.setVisible(true);
        BookTicketB.setDisable(true);
        
    }
    
    public void backBClicked(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomerProfile.fxml"));
        Parent HomeParent = loader.load();
        Scene HomeScene = new Scene(HomeParent);
        
        CustomerProfileController CPC = loader.getController();
        CPC.initData(c);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
