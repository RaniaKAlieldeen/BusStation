
package busStationClasses;


public class MiniBus extends Vehicle{
    final int num_of_seats = 12;

    public MiniBus(String id) {
        setVehicleId(id);
    }
    @Override
    public String getType() {
        return "MiniBus";
    }
    
}
