import java.io.*;
import java.util.*;

public class findPath {

    public Map<Integer, String> stopID;
    public Map<Integer, String> tripID;
    public Map<Integer, String> edges;
    public Map<Integer, String> startStop;
    public Map<Integer, String> endStop;
    public Map<Integer, String> cost;
    public Map<Integer, String> weight;
    public double distance[][];
    public int edge[][];

    public void setFile() {
        stopID = new HashMap<>();
        try {
            File stopFile = new File("stops.txt");
            Scanner stop = new Scanner(stopFile);
            stop.nextLine();
            int i = 0;
            while (stop.hasNextLine()) {
                String stopLists = stop.nextLine();
                String[] stopArray = stopLists.split(",");
                stopID.put(i, stopArray[0]);
                i++;
            }
            stop.close();
        } catch (Exception e) {
            System.out.println("Error occur when reading in stops.txt");
        }

        edges = new HashMap<>();
        tripID = new HashMap<>();
        try {
            File timeFile = new File("stop_times.txt");
            Scanner time = new Scanner(timeFile);
            time.nextLine();
            int i = 0;
            while (time.hasNextLine()) {
                String timeLists = time.nextLine();
                String[] timeArray = timeLists.split(",");
                tripID.put(i, timeArray[0]);
                edges.put(i, timeArray[3]);
                i++;
            }
            time.close();
        } catch (Exception e) {
            System.out.println("Error occur when reading in stop_times.txt");
        }

        startStop = new HashMap<>();
        endStop = new HashMap<>();
        cost = new HashMap<>();
        weight = new HashMap<>();
        try {
            File transfersFile = new File("transfers.txt");
            Scanner transfer = new Scanner(transfersFile);
            transfer.nextLine();
            int i = 0;
            while (transfer.hasNextLine()) {
                String transferLists = transfer.nextLine();
                String[] transferArray = transferLists.split(",");
                startStop.put(i, transferArray[0]);
                endStop.put(i, transferArray[1]);
                cost.put(i, transferArray[2]);
                if (transferArray.length != 3) {
                    weight.put(i, transferArray[3]);
                }
                i++;
            }
            transfer.close();
        } catch (Exception e) {
            System.out.println("Error occur when reading in transfers.txt");
        }

        int index = 2;
        int a = 1;
        int b = 2;
        int i = 0;
        int j = 0;
        while (tripID.get(index) != null) {
            while (tripID.get(index) == tripID.get(index - 1)) {
                i = Integer.parseInt(edges.get(a));
                j = Integer.parseInt(edges.get(b));
                edge[i][j] = Integer.parseInt(edges.get(a));
                a++;
                b++;
                index++;
            }
            index++;
        }

        index = 1;
        while (index < 5084) {
            i = Integer.parseInt(startStop.get(index));
            j = Integer.parseInt(endStop.get(index));
            if (Integer.parseInt(cost.get(index)) == 0) {
                distance[i][j] = 2;
            } else {
                distance[i][j] = (Integer.parseInt(weight.get(index))) / 100;
            }
            index++;
        }

        for (int k = 0; k < distance.length; k++) {
            shortestPath(k);
        }

    }

    public void shortestPath(int input) {
        boolean[] shortestPath = new boolean[distance.length];
        shortestPath[input] = true;
        while (true) {
            int output = -1;
            for (int i = 0; i < distance.length; i++) {
                if ((shortestPath[i] == false) && (distance[input][i] != Integer.MAX_VALUE)) {
                    output = i;
                    break;
                }
            }
            if (output == -1)
                return;
            shortestPath[output] = true;

            for (int i = 0; i < distance.length; i++) {
                if (distance[input][output] + distance[output][i] < distance[input][i]) {
                    distance[input][i] = distance[input][output] + distance[output][i];
                    shortestPath[i] = false;
                    edge[input][i] = output;
                }
            }
        }
    }
}