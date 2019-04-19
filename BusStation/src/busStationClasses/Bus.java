
package busStationClasses;


public class Bus extends Vehicle{
    final int num_of_seats = 24;

    public Bus(String id) {
        setVehicleId(id);
    }

    @Override
    public String getType() {
        return "Bus";
    }
    
    
    
}
