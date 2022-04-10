import java.io.*;
import java.util.*;

public class busManagementSystem {
    public static void main(String[] args) throws FileNotFoundException {
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);

        while (!quit) {
            System.out.println();
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
                            System.out.println("option 1");
                            findPath path = new findPath();
                            path.setFile();
                            System.out.println("Unfinished.");
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
                            System.out.println("Enter the arrival time you want to search (HH:MM:SS - e.g.5:25:00)");
                            String time = scanner.next().trim();
                            arrivalTime.readIn(time);
                        } else {
                            System.out.println("Bye!");
                            quit = true;
                        }
                    } catch (Exception e) {
                        System.out.println("Error occur in system.");
                    }
                } else {
                    System.out.println("invalid number, please enter the correct number of your choice.");
                    quit = true;
                }
            } else {
                System.out.println("Error, please input a number.");
                quit = true;
            }
        }
        scanner.close();
    }

}