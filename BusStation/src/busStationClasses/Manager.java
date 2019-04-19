
package busStationClasses;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Manager extends User implements ManagerActions{
    
    public static final HashMap<String, Manager> MANAGER_MAP = new HashMap<>();

    public Manager(String name, String iD, String password) {
        setName(name);
        setiD(iD);
        setPassword(password);
    }
    
    @Override
    public ArrayList<String> listVehicles() throws IOException{
        Vehicle.readVehiclesFile();
        ArrayList<String> vehicleslist = new ArrayList<>();
        vehicleslist.addAll(Vehicle.VEHICLE_MAP.keySet());
        return vehicleslist;
    }

    @Override
    public ArrayList<String> listTrips() throws IOException{
        Trip.readTripsFile();
        ArrayList<String> tripslist = new ArrayList<>();
        tripslist.addAll(Trip.TRIP_MAP.keySet());
        return tripslist;
    }

    @Override
    public ArrayList<String> listDrivers() throws IOException {
        Driver.readDriversFile();
        ArrayList<String> driverslist = new ArrayList<>();
        driverslist.addAll(Driver.DRIVER_MAP.keySet());
        return driverslist;
    }

    @Override
    public void saveVehicles() throws IOException{
        File file = new File("vehicles.txt");
        BufferedWriter b = new BufferedWriter(new FileWriter(file));
        String str ;
        for(Map.Entry<String,Vehicle> v : Vehicle.VEHICLE_MAP.entrySet()){
            str = v.getValue().getType() + "," + v.getValue().getVehicleId() + "\n";
            System.out.println("str: " + str);
            b.write(str);
        }
        b.close();
    }

    @Override
    public void saveTrips() throws IOException{
        File file = new File("trips.txt");
        BufferedWriter b = new BufferedWriter(new FileWriter(file));
        String str ;
        for(Map.Entry<String,Trip> t: Trip.TRIP_MAP.entrySet()){
            str = t.getValue().getTripId() + "," +
                  t.getValue().getSource() + "," +
                  t.getValue().getDestination() + "," + 
                  t.getValue().getDistance() + "," +
                  t.getValue().getFlavor() + "," + 
                  t.getValue().getAvailableSeats() + "," +
                  t.getValue().getDriverId() + "," +
                  t.getValue().getVehicleId() + ","+
                  t.getValue().isTripType()+"\n";
            System.out.println("str: " + str);
            b.write(str);
            b.flush();
        }
        b.close();
    }

    @Override
    public void saveDrivers() throws IOException{
        File file = new File("drivers.txt");
        BufferedWriter b = new BufferedWriter(new FileWriter(file));
        String str ;
        for(Map.Entry<String,Driver> d: Driver.DRIVER_MAP.entrySet()){
            str = d.getValue().getName()+ ","+ d.getValue().getiD() +"," 
                    + d.getValue().getPassword() + "," +
                    d.getValue().getTripsAssigned().toString()+"\n";
            System.out.println("str: " + str);
            b.write(str);
        }
        b.close();
        
    }
    
    public static void readManagersFile() throws IOException{
        File file = new File("managers.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String str;
        while((str = b.readLine())!=null){
            String[] info = str.split(",");
            Manager m = new Manager(info[0],info[1],info[2]);
            MANAGER_MAP.put(m.getiD(),m);  
        }
        b.close();
    }

}
