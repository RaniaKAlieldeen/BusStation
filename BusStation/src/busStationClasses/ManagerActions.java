
package busStationClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public interface ManagerActions {
 

public ArrayList<String> listVehicles()throws IOException;

public ArrayList<String> listTrips()throws IOException;

public ArrayList<String> listDrivers()throws IOException;

public void saveVehicles()throws IOException;

public void saveTrips()throws IOException;

public void saveDrivers()throws IOException;

}
    
    

