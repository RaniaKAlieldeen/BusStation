
package busStationClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Trip {
    
    
    
    public static final int NO_STOPS = 1;
    public static final int ONE_STOP = 2;
    public static final int MANY_STOPS = 3;
    
    public static final boolean INTERNAL = true;
    public static final boolean EXTERNAL = false;
    
    private String tripId;
    private final boolean tripType;
    private String source;
    private String destination;
    private String driverId;
    private String vehicleId;
    private int flavor; 
    private int availableSeats;
    private double distance;
    private String[] ticketsBooked;
    
    public static final HashMap<String, Trip> TRIP_MAP = new HashMap<>();
    
    public Trip(String... args) {
        
        tripId = args[0];
        source = args[1];
        destination = args[2];
        distance = Double.parseDouble(args[3]);
        flavor = Integer.parseInt(args[4]);
        availableSeats = Integer.parseInt(args[5]);
        driverId = args[6];
        vehicleId = args[7];
        tripType = Boolean.parseBoolean(args[8]); 
        ticketsBooked = new String[args.length-9];
        for (int i = 9; i < args.length; i++) {
            ticketsBooked[i-9] = args[i];
        }
    }

    public boolean isTripType() {
        return tripType;
    }
    
    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getFlavor() {
        return flavor;
    }

    public void setFlavor(int flavor) {
        this.flavor = flavor;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    public boolean setAvailableSeats(int bookseat) {
        if(availableSeats!=0){
            this.availableSeats -= bookseat;
            return true;
        }
        else
            return false;
            
    }

    public String[] getTicketsBooked() {
        return ticketsBooked;
    }

    public void setTicketsBooked(String[] ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }
    
    
    
    public static void readTripsFile() throws IOException {
        File file = new File("trips.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String str;
        String[] info;
        while ((str = b.readLine()) != null) {
            info = str.split(",");
            if (info.length >= 9) {
                Trip t = new Trip(info);
                TRIP_MAP.put(t.getTripId(), t);
            }
        }b.close();
    }
    
}
