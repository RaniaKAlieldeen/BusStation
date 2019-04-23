/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busstation;

import busStationClasses.Customer;
import busStationClasses.Trip;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CustomerActionsController implements Initializable {

    
    @FXML private Button BackB; 
    @FXML private Button SearchB;
    @FXML private TextField FromTF;
    @FXML private TextField ToTF;
    @FXML private ChoiceBox VehicleCB;
    @FXML private ChoiceBox TicketTypeCB;
    @FXML private ChoiceBox TripTypeCB;
    @FXML private ChoiceBox TripFlavorCB;
    @FXML private ListView<String> TripsAvailableLV;
    
    Customer c;
    public void initData(Customer c) throws IOException{
        this.c = c ;
        Trip.readTripsFile();
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
    public void bookBClicked(ActionEvent event) throws IOException
    {
           FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("BookTicket.fxml"));
        Parent HomeParent = loader.load();
        Scene HomeScene = new Scene(HomeParent);
        ObservableList<String> chosenTrips =  TripsAvailableLV.getSelectionModel().getSelectedItems();
        System.out.println(chosenTrips.get(0));
        BookTicketController BTC = loader.getController();
        BTC.initData(c,chosenTrips);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(HomeScene);
        window.show();
    }
    
    public void searchBClicked(ActionEvent event) throws IOException
    {
        TripsAvailableLV.getItems().clear();
        TripsAvailableLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        HashMap<String, Object> filter = new HashMap<>();
        
        String from, to;
        
        from = FromTF.getText();
        if(from.equals(""))
            from = null;
        System.out.println("from: " + from);
        if (from != null) {
            filter.put("source", from);
        }
        
        to = ToTF.getText();
        if(to.equals(""))
            to = null;
        System.out.println("to: " + to);
        if (to != null) {
            filter.put("destination", to);
        }
        
        Object vehicle = VehicleCB.getValue();
        System.out.println("veh: "+vehicle);
        if (vehicle != null) {
            filter.put("Vehicle Type", vehicle.toString());
        }
        
        Object flavor = TripFlavorCB.getValue();
        
        if (flavor != null) {
            filter.put("No. of Stops", flavor.toString());
        }
        
        Object triptype = TripTypeCB.getValue();
        if (triptype != null) {
            if (triptype.toString().equals("Internal")) {
                filter.put("Trip Type", Trip.INTERNAL);
            } else {
                filter.put("Trip Type", Trip.EXTERNAL);

            }
        }
        
        ArrayList<Trip> listofTrips = c.listTrips(filter);
        for(int i = 0; i < listofTrips.size();i++)
        TripsAvailableLV.getItems().add(listofTrips.get(i).getTripId()+" "
                +listofTrips.get(i).getSource()+" "
                +listofTrips.get(i).getDestination()+" "
                +listofTrips.get(i).getFlavor()+ " "
                +listofTrips.get(i).isTripType()+" "
                +listofTrips.get(i).getAvailableSeats());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TicketTypeCB.getItems().addAll("Round trip","One way");
        TripTypeCB.getItems().addAll("Internal","External");
        VehicleCB.getItems().addAll("Car","Mini-Bus","Bus");
        TripFlavorCB.getItems().addAll("none","single","multiple");
        TripsAvailableLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //show trips read from file to listview
    }    
    
}
