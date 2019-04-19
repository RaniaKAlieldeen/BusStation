package busStationClasses;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class Java101 {

    public static void main(String[] args) throws IOException {

        Trip.readTripsFile();
        System.out.println("1: "+Trip.TRIP_MAP.keySet());
        Trip.TRIP_MAP.remove("1002");
        System.out.println("2: "+Trip.TRIP_MAP.keySet());
        Manager.readManagersFile();
        Manager.MANAGER_MAP.get("4288").saveTrips();
        System.out.println("3: "+Trip.TRIP_MAP.keySet());
        Trip.readTripsFile();
        System.out.println("4: "+Trip.TRIP_MAP.keySet());

    }

}
