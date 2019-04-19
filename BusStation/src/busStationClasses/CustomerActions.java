
package busStationClasses;

import java.util.ArrayList;
import java.util.Map;


public interface CustomerActions {
    
public ArrayList<Trip> listTrips(Map<String, Object> filter);

public boolean checkAvailability(Trip selected, Trip selected2,
int numOfSeats);

public void reserve(Trip selected, Trip selected2, Customer c,
int numOfSeats);

}


