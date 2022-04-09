
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class arrivalTime {
    public static void readIn(String time) {
        try {
            ArrayList<String> stopTimes = (ArrayList<String>) Files
                    .readAllLines(Paths.get("stop_times.txt"));
            ArrayList<String> arrivalTime = new ArrayList<String>();
            boolean validTime = false;
            try {
                if (time.trim().matches("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]")) {
                    for (int i = 1; i < stopTimes.size(); i++) {
                        String compare = stopTimes.get(i).trim();
                        String[] compareArray = compare.split(", ");
                        String[] compareArray2 = compare.split(",");
                        if (compareArray[1].equals(time)) {
                            arrivalTime.add(stopTimes.get(i).trim());
                        } else if (compareArray2[1].equals(time)) {
                            arrivalTime.add(stopTimes.get(i).trim());
                        }
                        validTime = true;
                    }
                } else {
                    System.out.println("Please check your input format HH:MM:SS - e.g.5:25:00");
                }
            } catch (Exception e) {
                System.out.println(
                        "Error occur when take in input. Please enter the exact time format.");
            }
            if (validTime) {
                printArrivalTimes(arrivalTime);
            } else if (!validTime) {
                System.out.println("The arrival time could not be found.");
            }
        } catch (Exception e) {
            System.out.println("Error occur when running option 3.");
        }
    }

    public static void printArrivalTimes(ArrayList<String> arrivalTime) {
        System.out.println(
                "Trip ID, Arrival Time, Departure Time, Stop ID, Stop sequence, Stop Headsign, Pickup Type, Drop Off Type, Shape, Distance Traveled  ");
        for (int i = 0; i < arrivalTime.size(); i++) {
            System.out.println(arrivalTime.get(i));
        }
    }
}
