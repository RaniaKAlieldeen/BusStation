
package busStationClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Driver extends User {
    
    public static final HashMap<String, Driver> DRIVER_MAP = new HashMap<>();
    //public static final ArrayList<Driver> DRIVERS = new ArrayList<>();
    
    private final ArrayList<String> TRIPS_ASSIGNED = new ArrayList<>();
    
    
    public Driver(String... args){
        setName(args[0]);
        setiD(args[1]);
        setPassword(args[2]);
        for (int i = 3; i < args.length; i++) {
            TRIPS_ASSIGNED.add(args[i]);
        }
    }

    public void setTripsAssigned(String tripId) {
        this.TRIPS_ASSIGNED.add(tripId);
    }
    
    public ArrayList<String> getTripsAssigned(){
        return this.TRIPS_ASSIGNED;
    }
    
    public static void readDriversFile() throws IOException{
    File file = new File("drivers.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String str;
        while((str = b.readLine())!=null){
            String[] info = str.split(",");
            Driver d = new Driver(info);
            DRIVER_MAP.put(d.getiD(),d);
        }
        b.close();   
        
    }
    
    
}
