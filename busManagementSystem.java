import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class busManagementSystem {
    public static void main(String[] args) throws FileNotFoundException {
        boolean quit = false;

        while (!quit) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to this Bus Management System.");
            System.out.println("Enter 1 if you want to find the shortest path of two stops.");
            System.out.println("Enter 2 if you want to search information about a bus stop.");
            System.out.println("Enter 3 if you want to search bus by arrival time.");
            System.out.println("Enter 0 if you want to exit the program.");
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option >= 0 && option <= 3) {
                    try {
                        if (option == 1) {
                            System.out.println("choice 1");
                        } else if (option == 2) {
                            busStop search = new busStop();
                            System.out.println(
                                    "Enter the name or first few letters of the bus stop you want to search. ");
                            String input = scanner.nextLine();
                            input = input.toUpperCase();
                            search.printStopDetails(input).forEach((info) -> {
                                System.out.println(info);
                            });
                        } else if (option == 3) {
                            try {
                                ArrayList<String> stopTimes = (ArrayList<String>) Files
                                        .readAllLines(Paths.get("stop_times.txt"));
                                ArrayList<String> arrivalTime = new ArrayList<String>();
                                System.out
                                        .println("Enter the arrival time you want to search (HH:MM:SS - e.g.5:25:00)");
                                boolean searchTime = false;
                                try {
                                    String time = scanner.next().trim();
                                    if (time.trim().matches("(([0-1]?[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]")) {
                                        for (int i = 1; i < stopTimes.size(); i++) {
                                            String compare = stopTimes.get(i).trim();
                                            String[] compareArray = compare.split(", ");
                                            String[] compareArray2 = compare.split(",");
                                            if (compareArray[1].equals(time)) {
                                                arrivalTime.add(stopTimes.get(i).trim());
                                            }
                                            if (compareArray2[1].equals(time)) {
                                                arrivalTime.add(stopTimes.get(i).trim());
                                            }
                                            searchTime = true;
                                        }
                                    } else {
                                        System.out.println("Please check your input format HH:MM:SS - e.g.5:25:00");
                                    }
                                } catch (Exception e) {
                                    System.out.println(
                                            "Error occur when take in input. Please enter the exact time format.");
                                }
                                if (searchTime) {
                                    System.out.println(
                                            "Trip ID, Arrival Time, Departure Time, Stop ID, Stop sequence, Stop Headsign, Pickup Type, Drop Off Type, Shape, Distance Traveled  ");
                                    for (int i = 0; i < arrivalTime.size(); i++) {
                                        System.out.println(arrivalTime.get(i));
                                    }
                                } else if (!searchTime) {
                                    System.out.println("The arrival time could not be found.");
                                }
                            } catch (Exception e) {
                                System.out.println("Error occur when running option 3.");
                            }
                        } else {
                            System.out.println("Bye!");
                            quit = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Error occur in system.");
                    }
                } else {
                    System.out.println("invalid number, please enter the correct number of your choice.");
                }
            } else {
                System.out.println("Error, please input a number.");
            }
        }

    }

}