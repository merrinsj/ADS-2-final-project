import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.io.RandomAccessFile;

public class CreateMapAndTST {

    final static int PIECES_OF_STOP_INFORMATION_PER_LINE = 10;
    final static int PIECES_OF_EDGE_INFORMATION_PER_LINE_TRANSFER = 4;
    final static int PIECES_OF_EDGE_INFORMATION_PER_LINE_STOP_TIMES = 9;

    static double edgeCost;
     
    static int stop_id;
    static int stop_code;
    static String stop_name;
    static String stop_desc;
    static double stop_latitude;
    static double stop_longitude;
    static String zone_id;
    static String stop_url;
    static int location_type;
    static int parent_station;
    
    static int from_stop_id;
    static int to_stop_id;
    static int transfer_type;
    static int min_transfer_time;
     
    static int trip_id;
    static String arrival_time;
    static String departure_time;
    static int stop_id_stop_times;
    static int stop_sequence;
    static int stop_headsign;
    static int pickup_type;
    static int drop_off_type;
    static double shape_dist_traveled;


    static Map theMap;
    static TST theTST;
    static Boolean emptyMap = false;

    public static void CreateMapAndTST() throws FileNotFoundException {
        int numberOfStops = 0;
        String stopInfo = "";
        Stop newStop;
        String[] stopInfoSplit = new String[PIECES_OF_STOP_INFORMATION_PER_LINE];
        String[] stopNameSplit;                                             //Possible error, not right length
        theMap = new Map(0);
        theTST = new TST();
        try {
            RandomAccessFile stopData = new RandomAccessFile("stops.txt", "r");
            numberOfStops = (int) stopData.length();
            theMap = new Map(numberOfStops);
            for (int i = 0; i < numberOfStops; i++)
            {
                stopInfo = stopData.readLine();
                stopInfoSplit = stopInfo.trim().split(",");
                stop_id = Integer.parseInt(stopInfoSplit[0]);
                stop_code = Integer.parseInt(stopInfoSplit[1]);
                stop_name = stopInfoSplit[2];

                stopNameSplit = stop_name.split("\\s+");
                if(stopNameSplit[0] == "WB" || stopNameSplit[0] == "NB" || stopNameSplit[0] == "SB" ||
                        stopNameSplit[0] == "EB" || stopNameSplit[0] == "FLAGSTOP")
                {
                    String tmp = "";
                    String alteredStringForTST = "";
                    tmp = stopNameSplit[0];
                    for(int j = 0; j < stopNameSplit.length; j++)
                    {
                        if(i + 1 < stopNameSplit.length)
                        {
                            stopNameSplit[i] = stopNameSplit[i + 1];
                            alteredStringForTST = alteredStringForTST + stopNameSplit[i] + " ";
                        }
                    }
                    alteredStringForTST = alteredStringForTST + stopNameSplit[0];
                    theTST.insert(alteredStringForTST);
                    stop_name = alteredStringForTST;
                }

                stop_desc = stopInfoSplit[3];
                stop_latitude = Double.parseDouble(stopInfoSplit[4]);
                stop_longitude = Double.parseDouble(stopInfoSplit[5]);
                zone_id = stopInfoSplit[6];
                stop_url = stopInfoSplit[7];
                location_type = Integer.parseInt(stopInfoSplit[8]);
                parent_station = Integer.parseInt(stopInfoSplit[9]);
                newStop = new Stop (stop_id, stop_code, stop_name, stop_desc, stop_latitude,
                        stop_longitude, zone_id, stop_url, location_type, parent_station);
                theMap.stops[i] = newStop;
            }
            stopData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            int numberOfEdges = 0;
            String edgeInfo = "";
            String[] splitEdgeInfo = new String[PIECES_OF_EDGE_INFORMATION_PER_LINE_TRANSFER];
            RandomAccessFile edgeData = new RandomAccessFile("transfers.txt", "r");
            numberOfEdges = (int) edgeData.length();
            MapConnection newConnection;
            for (int i = 0; i < numberOfEdges; i++)
            {
                edgeInfo = edgeData.readLine();
                splitEdgeInfo = edgeInfo.trim().split(",");
                from_stop_id = Integer.parseInt(splitEdgeInfo[0]);
                to_stop_id = Integer.parseInt(splitEdgeInfo[1]);
                transfer_type = Integer.parseInt(splitEdgeInfo[2]);
                min_transfer_time = Integer.parseInt(splitEdgeInfo[3]);

                if(transfer_type == 0) { edgeCost = 2; }
                else edgeCost = (min_transfer_time / 100);

                newConnection = new MapConnection(theMap.findStopID(to_stop_id), null, edgeCost);
                theMap.findStopID(from_stop_id).addDestination(newConnection);
            }
            edgeData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            edgeCost = 1.0;
            boolean makeConnection = false;
            int previousTripID = 0;
            int previousStopID = 0;
            int numberOfEdges = 0;
            String edgeInfo = "";
            String[] splitEdgeInfo = new String[PIECES_OF_EDGE_INFORMATION_PER_LINE_STOP_TIMES];
            String[] arrivalTimeIntegers = new String[3];   //Number of integers in this time representation
            RandomAccessFile edgeDataStopTimes = new RandomAccessFile("stop_times.txt", "r");
            numberOfEdges = (int) edgeDataStopTimes.length();
            MapConnection newConnection;
            for (int i = 0; i < numberOfEdges; i++)
            {
                edgeInfo = edgeDataStopTimes.readLine();
                splitEdgeInfo = edgeInfo.trim().split(",");
                trip_id = Integer.parseInt(splitEdgeInfo[0]);

                if(previousTripID == trip_id) { makeConnection = true; }       //Check if these stops are on the same trip

                arrival_time = splitEdgeInfo[1];
                arrivalTimeIntegers = arrival_time.trim().split(":");
                if(Integer.parseInt(arrivalTimeIntegers[0]) > 23){ makeConnection = false; }    //Check for invalid arrival times

                departure_time = splitEdgeInfo[2];
                stop_id_stop_times = Integer.parseInt(splitEdgeInfo[3]);
                stop_sequence = Integer.parseInt(splitEdgeInfo[4]);
                stop_headsign = Integer.parseInt(splitEdgeInfo[5]);
                pickup_type = Integer.parseInt(splitEdgeInfo[6]);
                drop_off_type = Integer.parseInt(splitEdgeInfo[7]);
                shape_dist_traveled = Double.parseDouble(splitEdgeInfo[8]);

                if(makeConnection)
                {
                    newConnection = new MapConnection(trip_id, arrival_time, departure_time,
                            theMap.findStopID(stop_id_stop_times), stop_sequence, stop_headsign,
                            pickup_type, drop_off_type, shape_dist_traveled, edgeCost);
                    theMap.findStopID(previousStopID).addDestination(newConnection);
                }
                previousStopID = stop_id_stop_times;
                previousTripID = trip_id;
                makeConnection = false;

            }
            edgeDataStopTimes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map getTheMap()
    {
        return theMap;
    }

    public static TST getTheTST()
    {
        return theTST;
    }
}
