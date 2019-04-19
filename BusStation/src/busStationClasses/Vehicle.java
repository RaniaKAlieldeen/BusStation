
package busStationClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class Vehicle {
    
    public static final HashMap<String, Vehicle> VEHICLE_MAP = new HashMap<>();   
    String Id;
    
    public String getVehicleId() {
        return Id;
    }
    
    
    public abstract String getType();
    
    public void setVehicleId(String Id) {
        this.Id = Id;
    }
    
   public static void readVehiclesFile() throws IOException{
    File file = new File("vehicles.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String str;
        while((str = b.readLine())!=null){
            String[] info = str.split(",");
            Vehicle v ;
            if(info[0].equals("Car"))
                v = new Car(info[1]);
            else if(info[0].equals("Bus"))
                v = new Bus(info[1]);
            else if(info[0].equals("MiniBus"))
                v = new MiniBus(info[1]);
            else
                continue;
            VEHICLE_MAP.put(v.getVehicleId(),v);
        }
        b.close();
   }
   
   
}