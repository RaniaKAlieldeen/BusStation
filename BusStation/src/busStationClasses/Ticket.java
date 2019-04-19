
package busStationClasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import static busStationClasses.Trip.MANY_STOPS;
import static busStationClasses.Trip.NO_STOPS;
import static busStationClasses.Trip.ONE_STOP;


public class Ticket {
    
    public static final boolean ONE_WAY = true;
    public static final boolean ROUND_TRIP = false;
    
    private String ticketId;
    private boolean ticketType;
    private String customerId;
    private double ticketPrice;

    public static final HashMap<String,Ticket> TICKET_MAP = new HashMap<>();
    
    public Ticket(String[] args) {
        ticketId = args[0];
        ticketType = Boolean.parseBoolean(args[1]);
        customerId = args[2];
        ticketPrice = Double.parseDouble(args[3]);
        
    }
    
    public Ticket(Trip t1,Trip t2,String cId) {
        if(t2 ==null){
            ticketId = t1.getTripId()+t1.getAvailableSeats();
            ticketType = ONE_WAY;
        }else{
            ticketId = t1.getTripId()+t1.getAvailableSeats()+
                       t2.getTripId()+t2.getAvailableSeats();
            ticketType = ROUND_TRIP;
        }
        customerId = cId;
        setPrice(t1,t2);
        
    }
    
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String... args) {
        this.ticketId = args[0]+args[1];
    }

    public boolean isTicketType() {
        return ticketType;
    }

    public void setTicketType(boolean ticketType) {
        this.ticketType = ticketType;
    }
    
    public double getPrice() {
        return ticketPrice;
    }

    public void setPrice(Trip t1,Trip t2) {
        double distance;
        int flavor;
        if(t2==null){
            distance = t1.getDistance();
            flavor = t1.getFlavor();
        }else{
            distance = t1.getDistance()+t2.getDistance();
            if(t1.getFlavor()>t2.getFlavor())
                flavor = t2.getFlavor();
            else
                flavor = t1.getFlavor();
        }
        switch (flavor) {
            case NO_STOPS:
                this.ticketPrice = distance ; //1km = LE 1
                break;
            case ONE_STOP:
                this.ticketPrice = distance * 0.8;
                break;
            case MANY_STOPS:
                this.ticketPrice = distance * 0.6;
                break;
            default:
                this.ticketPrice = distance ;
                break;
        }
        if(ticketType == ROUND_TRIP)
            this.ticketPrice = this.ticketPrice * 0.8;
        
        
    }
    
    public static void readTicketsFile() throws IOException {
        File file = new File("tickets.txt");
        BufferedReader b = new BufferedReader(new FileReader(file));
        String str;
        String[] info;
        while ((str = b.readLine()) != null) {
            info = str.split(",");
            if (info.length == 4) {
                Ticket t = new Ticket(info);
                TICKET_MAP.put(t.getTicketId(),t);
            }
        }
        b.close();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    
    
    
}
