package busStationClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        ArrayList<Trip> filteredTrips = new ArrayList<>();
        loop: for (Map.Entry<String,Trip> trip: Trip.TRIP_MAP.entrySet()) {
            for (String x : filter.keySet()) {
                switch (x) {
                    case "source":
                        if (!trip.getValue().getSource().equals(filter.get(x))) {
                            continue loop;
                        }
                        break;
                    case "destination":
                        if (!trip.getValue().getDestination().equals(filter.get(x))) {
                            continue loop;
                        }
                        break;
                    case "Vehicle Type":
                        if (!Vehicle.VEHICLE_MAP.get(trip.getValue().getVehicleId()).getType().equals(filter.get(x))) {
                            continue loop;
                        }
                        break;
                    case "No. of Stops":
                        if (trip.getValue().getFlavor()!= (int)filter.get(x)) {
                            continue loop;
                        }
                        break;
                    case "Trip Type":
                        if (trip.getValue().isTripType()!=(boolean)filter.get(x)) {
                            continue loop;
                        }
                        break;
                    

                }
            }
        }

        return filteredTrips;
    }

    @Override
    public boolean checkAvailability(Trip selected,Trip selected2, int numOfSeats) {
            return selected.getAvailableSeats() >= numOfSeats && selected2.getAvailableSeats() >= numOfSeats;    
    }

    @Override
    public void reserve(Trip selected,Trip selected2, Customer c, int numOfSeats) {
        
        selected.setAvailableSeats(numOfSeats);
        selected2.setAvailableSeats(numOfSeats);
        Ticket t = new Ticket(selected,selected2,c.getiD());
        c.ticketsBooked.add(t.getTicketId());

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
