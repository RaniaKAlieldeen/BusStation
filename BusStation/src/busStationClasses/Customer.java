package busStationClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Customer extends User implements CustomerActions {

    public static final HashMap<String,Customer> CUSTOMER_MAP = new HashMap<>();
    private ArrayList<String> ticketsBooked = new ArrayList<>();

    public Customer(String... args) {
        this.name = args[0];
        this.iD = args[1];
        this.password = args[2];
        for (int i = 3; i < args.length; i++) {
            ticketsBooked.add(args[i]);
        }
    }

    public ArrayList<String> getTicketsBooked() {
        return ticketsBooked;
    }
    
    public void setTicketsBooked(ArrayList<String> s) {
        this.ticketsBooked = s;
    }

    @Override
    public ArrayList<Trip> listTrips(Map<String, Object> filter) {
        
        ArrayList <Trip> Trips = new ArrayList<>();
        for(Map.Entry<String,Trip> entry : Trip.TRIP_MAP.entrySet())
            Trips.add(entry.getValue());
        
        if(filter.isEmpty())
            return Trips;
        
        System.out.println("so im here so filter is not empty");
        ArrayList<Trip> filteredTrips = new ArrayList<>();
        loop: for (Trip t : Trips) {
            for (String x : filter.keySet()) {
                
                switch (x) {
                    case "source":
                        if (!t.getSource().equals(filter.get(x))) {
                            continue loop;
                        }
                        //break;
                    case "destination":
                        if (!t.getDestination().equals(filter.get(x))) {
                            continue loop;
                        }
                        //break;
                    case "Vehicle Type":
                        if (!Vehicle.VEHICLE_MAP.get(t.getVehicleId()).getType().equals(filter.get(x))) {
                            continue loop;
                        }
                        //break;
                    case "No. of Stops":
                        if (t.getFlavor()!= (int)filter.get(x)) {
                            continue loop;
                        }
                        //break;
                    case "Trip Type":
                        if (t.isTripType()!=(boolean)filter.get(x)) {
                            continue loop;
                        }
                        //break;
                }
                filteredTrips.add(t);
            }
        }

        return filteredTrips;
    }

    @Override
    public boolean checkAvailability(Trip selected,Trip selected2, int numOfSeats) {
        if (selected2 != null) {
            return selected.getAvailableSeats() >= numOfSeats && selected2.getAvailableSeats() >= numOfSeats;
        } else {
            return selected.getAvailableSeats() >= numOfSeats;
        }
    }

    @Override
    public Ticket reserve(Trip selected,Trip selected2, Customer c, int numOfSeats) {
        
        selected.setAvailableSeats(numOfSeats);
        
        Trip.TRIP_MAP.put(selected.getTripId(), selected);
        if (selected2 != null) {
            selected2.setAvailableSeats(numOfSeats);
            Trip.TRIP_MAP.put(selected2.getTripId(), selected2);
        }
        Ticket tc = new Ticket(selected,selected2,c.getiD());
        
        c.ticketsBooked.add(tc.getTicketId());
        selected.setTicketsBooked(tc.getTicketId());
        if(selected2!=null)
            selected2.setTicketsBooked(tc.getTicketId());
        try {
            Manager.readManagersFile();
            Manager m = Manager.MANAGER_MAP.get("4288");
            m.saveTrips();
            saveCustomerFile();
        } catch (IOException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tc;

    }

    public static void readCustomerFile() throws FileNotFoundException, IOException {

        File file = new File("customers.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String str;
        while ((str = b.readLine()) != null) {
            String[] info = str.split(",");
            if (info.length >=3) {
            Customer c = new Customer(info);
            CUSTOMER_MAP.put(c.getiD(), c);
            }
        }
        b.close();
    }
    
    public static void saveCustomerFile() throws FileNotFoundException, IOException {

        File file = new File("customers.txt");
        BufferedWriter b = new BufferedWriter(new FileWriter(file,false));
        
        String str ;
        
        for(Map.Entry<String,Customer> c: CUSTOMER_MAP.entrySet()){
            str = c.getValue().getName()
                    +","+c.getValue().getiD()
                    +","+c.getValue().getPassword();
            for(int i =0;i<c.getValue().getTicketsBooked().size();i++)
                str = str + "," + c.getValue().getTicketsBooked().get(i);
            str = str + "\n";
            b.write(str);
        }
        b.close();
    }
    
    public boolean remove(Customer c,String ticketId){
        //i need to change available seats of that trip
        ArrayList<String> s = c.getTicketsBooked();
        boolean x = s.remove(ticketId);
        if(x){
            c.setTicketsBooked(s);
            return x;
        }
        return x;
    }
        
        
}
