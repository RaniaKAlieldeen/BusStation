
package busstation;

import busStationClasses.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ManagerActionsController implements Initializable {
    //main components
    @FXML private Button AddTripB ;
    @FXML private Button RemoveTripB;
    @FXML private Button AssignDriverB;
    @FXML private VBox AddTripV;
    @FXML private VBox RemoveTripV;
    @FXML private VBox AssignDriverV;
    @FXML private Button LogoutB;
    
    //assign driver components
    @FXML private ChoiceBox ChooseTripCB;
    @FXML private ChoiceBox ChooseDriverCB;
    @FXML private Button DoneAssignB;
    @FXML private Label driverAssignedL;
    
    //trip remove components
    @FXML private ListView<String> RemoveTripList;
    @FXML private Button DoneRemoveB;
    @FXML private Label tripRemovedL;
    
    //trip add components
    @FXML private ChoiceBox ChooseVehicleIDCB;
    @FXML private Button DoneAddB;
    @FXML private TextField FromTF;
    @FXML private TextField ToTF;
    @FXML private TextField DistanceTF;
    @FXML private ChoiceBox TripTypeCB;
    @FXML private ChoiceBox TripFlavorCB;
    
    Manager m;
    public void receiveManager(Manager m){
        this.m = m;
    }
    
    public void addTripBClicked(ActionEvent event)throws IOException
    {
        AddTripV.setDisable(false);
        RemoveTripV.setDisable(true);
        AssignDriverV.setDisable(true);
        ChooseVehicleIDCB.getItems().addAll(m.listVehicles());
        TripTypeCB.getItems().addAll("INTERNAl","EXTERNAL");
        TripFlavorCB.getItems().addAll("no stops","one stop","many stops");
    }
    
    public void doneAddBClicked(ActionEvent event)throws IOException{
        //%%
        String str = TripFlavorCB.getValue().toString();
        int x; String y,vehicle; Boolean z;
        if(str.equals("no stops"))
            x = Trip.NO_STOPS;
        else if(str.equals("one stop"))
            x= Trip.ONE_STOP;
        else
            x = Trip.MANY_STOPS;
        
        //%%
        vehicle = ChooseVehicleIDCB.getValue().toString();
        str = Vehicle.VEHICLE_MAP.get(vehicle).getType();
        if(str.equals("Car"))
            y = "4";
        else if(str.equals("Bus"))
            y = "24";
        else
            y = "12";
        
        //%%
        str = TripTypeCB.getValue().toString();
        if(str.equals("INTERNAL"))
            z = Trip.INTERNAL;
        else 
            z = Trip.EXTERNAL;
        //**
        Trip.readTripsFile();
        String tripId = String.valueOf(Integer.parseInt(Trip.TRIP_MAP.keySet().toArray()[0].toString())+1);
        Trip t = new Trip(tripId,FromTF.getText(),ToTF.getText(),DistanceTF.getText(),
                String.valueOf(x),y,"",vehicle,String.valueOf(z));
        Trip.TRIP_MAP.put(t.getTripId(), t);
        m.saveTrips();
    }
    
    public void removeTripBClicked(ActionEvent event)throws IOException
    {
        
        RemoveTripV.setDisable(false);
        AssignDriverV.setDisable(true);
        AddTripV.setDisable(true);
        tripRemovedL.setVisible(false);
        RemoveTripList.getItems().clear();
        RemoveTripList.getItems().addAll(m.listTrips());
        RemoveTripList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    
    public void doneRemoveBClicked(ActionEvent event) throws IOException{
        ObservableList<String> toberemoved = RemoveTripList.getSelectionModel().getSelectedItems();
        System.out.println(toberemoved);
        Trip.TRIP_MAP.remove(toberemoved);
        tripRemovedL.setText("Trip Removed.");
        tripRemovedL.setVisible(true);
        m.saveTrips();
    }
    
    public void assignDriverBBClicked(ActionEvent event)throws IOException
    {
        AssignDriverV.setDisable(false);
        AddTripV.setDisable(true);
        RemoveTripV.setDisable(true);
        tripRemovedL.setVisible(false);
        ChooseTripCB.getItems().addAll(m.listTrips());
        ChooseDriverCB.getItems().addAll(m.listDrivers());
    }
    
    public void doneAssignBClicked(ActionEvent event)throws IOException{
        String trip = ChooseTripCB.getValue().toString();
        String driver = ChooseDriverCB.getValue().toString();
        Trip.TRIP_MAP.get(trip).setDriverId(driver);
        Driver.DRIVER_MAP.get(driver).setTripsAssigned(trip);
        driverAssignedL.setText(driver + " assigned to " + trip);
        driverAssignedL.setVisible(true);
        m.saveDrivers();
        m.saveTrips();
    
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
        AddTripV.setDisable(true);
        RemoveTripV.setDisable(true);
        AssignDriverV.setDisable(true);
    }    
    
}
