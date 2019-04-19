
package busStationClasses;


public class Car extends Vehicle{
    final int num_of_seats = 4;
    
    public Car(String Id) {
        setVehicleId(Id);

    }
    @Override
    public String getType() {
        return "Car";
    }
    
}
