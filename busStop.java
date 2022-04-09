import java.io.*;
import java.util.*;

public class busStop {
    public Map<String, String> map;
    TST<String> tst;

    busStop() {
        tst = new TST<String>();
        try {
            File stops = new File("stops.txt");
            Scanner readIn = new Scanner(stops);
            readIn.nextLine();

            map = new HashMap<String, String>();

            while (readIn.hasNextLine()) {
                String stop = readIn.nextLine();
                String[] stopArray = stop.split(",");
                String stopID = stopArray[0];
                StringBuilder sb = new StringBuilder();
                sb.append(stopArray[2]);
                if (sb.substring(0, 8).equals("FLAGSTOP")) {
                    String dir = sb.substring(0, 11);
                    sb.delete(0, 12);
                    sb.append(" " + dir);
                } else if (sb.substring(0, 2).equals("NB") || sb.substring(0, 2).equals("SB")
                        || sb.substring(0, 2).equals("WB") || sb.substring(0, 2).equals("EB")) {
                    String dir = sb.substring(0, 2);
                    sb.delete(0, 3);
                    sb.append(" " + dir);
                }
                String stopLists = sb.toString();
                tst.put(stopLists, stopID);

                StringBuilder printableInfo = new StringBuilder();
                printableInfo.append("stop_id: " + stopID + "\n");
                printableInfo.append("stop_code: " + stopArray[1] + "\n");
                printableInfo.append("stop_name: " + stopLists + "\n");
                printableInfo.append("stop_desc: " + stopArray[3] + "\n");
                printableInfo.append("stop_lat: " + stopArray[4] + "\n");
                printableInfo.append("stop_lon: " + stopArray[5] + "\n");
                printableInfo.append("zone_id: " + stopArray[6] + "\n");
                printableInfo.append("stop_url: " + "\n");
                printableInfo.append("location_type: " + stopArray[8] + "\n");
                printableInfo.append("parent_station: " + "\n");
                String stopInfo = printableInfo.toString();
                map.put(stopID, stopInfo);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error occur when read in files.");
        }
    }

    public List<String> printStopDetails(String stopId) {
        List<String> stopDetails = new LinkedList<>();
        tst.keysWithPrefix(stopId).forEach((info) -> {
            stopDetails.add(map.get(tst.get(info)));
        });
        if (stopDetails.isEmpty()) {
            stopDetails.add("Undinfine bus stop, please make sure you enter in the correct bus stop number. \n");
        }
        return stopDetails;
    }
}