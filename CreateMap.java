import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
import java.io.RandomAccessFile;

public class CreateMap {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */

    final int PIECES_OF_STOP_INFORMATION_PER_LINE = 10;
    final int PIECES_OF_EDGE_INFORMATION_PER_LINE_TRANSFER = 4;
    final int PIECES_OF_EDGE_INFORMATION_PER_LINE_STOP_TIMES = 9;

    double edgeCost;

    int stop_id;
    int stop_code;
    String stop_name;
    String stop_desc;
    double stop_latitude;
    double stop_longitude;
    String zone_id;
    String stop_url;
    int location_type;
    int parent_station;

    int from_stop_id;
    int to_stop_id;
    int transfer_type;
    int min_transfer_time;

    int trip_id;
    String arrival_time;
    String departure_time;
    int stop_id_stop_times;
    int stop_sequence;
    int stop_headsign;
    int pickup_type;
    int drop_off_type;
    double shape_dist_traveled;


    Map theMap;
    Boolean emptyMap = false;

    CreateMap() throws FileNotFoundException {
        int numberOfStops = 0;
        String stopInfo = "";
        Stop newStop;
        String[] stopInfoSplit = new String[PIECES_OF_STOP_INFORMATION_PER_LINE];
        theMap = new Map(0);
        try {
            RandomAccessFile stopData = new RandomAccessFile("stops.txt", "r");
            numberOfStops = (int) stopData.length();
            theMap = new Map(numberOfStops);
            for (int i = 0; i < numberOfStops; i++)
            {
                stopInfo = stopData.readLine();
                stopInfoSplit = stopInfo.trim().split(",");
                this.stop_id = Integer.parseInt(stopInfoSplit[0]);
                this.stop_code = Integer.parseInt(stopInfoSplit[1]);
                this.stop_name = stopInfoSplit[2];
                this.stop_desc = stopInfoSplit[3];
                this.stop_latitude = Double.parseDouble(stopInfoSplit[4]);
                this.stop_longitude = Double.parseDouble(stopInfoSplit[5]);
                this.zone_id = stopInfoSplit[6];
                this.stop_url = stopInfoSplit[7];
                this.location_type = Integer.parseInt(stopInfoSplit[8]);
                this.parent_station = Integer.parseInt(stopInfoSplit[9]);
                newStop = new Stop (this.stop_id, this.stop_code, this.stop_name, this.stop_desc, this.stop_latitude,
                        this.stop_longitude, this.zone_id, this.stop_url, this.location_type, this.parent_station);
                this.theMap.stops[i] = newStop;
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
                this.from_stop_id = Integer.parseInt(splitEdgeInfo[0]);
                this.to_stop_id = Integer.parseInt(splitEdgeInfo[1]);
                this.transfer_type = Integer.parseInt(splitEdgeInfo[2]);
                this.min_transfer_time = Integer.parseInt(splitEdgeInfo[3]);

                if(this.transfer_type == 0) { this.edgeCost = 2; }
                else this.edgeCost = (min_transfer_time / 100);

                newConnection = new MapConnection(this.theMap.findStop(this.to_stop_id), null, this.edgeCost);
                this.theMap.findStop(this.from_stop_id).addDestination(newConnection);
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
            RandomAccessFile edgeDataStopTimes = new RandomAccessFile("stop_times.txt", "r");
            numberOfEdges = (int) edgeDataStopTimes.length();
            MapConnection newConnection;
            for (int i = 0; i < numberOfEdges; i++)
            {
                edgeInfo = edgeDataStopTimes.readLine();
                splitEdgeInfo = edgeInfo.trim().split(",");
                this.trip_id = Integer.parseInt(splitEdgeInfo[0]);

                if(previousTripID == this.trip_id) { makeConnection = true; }

                this.arrival_time = splitEdgeInfo[1];
                this.departure_time = splitEdgeInfo[2];
                this.stop_id_stop_times = Integer.parseInt(splitEdgeInfo[3]);
                this.stop_sequence = Integer.parseInt(splitEdgeInfo[4]);
                this.stop_headsign = Integer.parseInt(splitEdgeInfo[5]);
                this.pickup_type = Integer.parseInt(splitEdgeInfo[6]);
                this.drop_off_type = Integer.parseInt(splitEdgeInfo[7]);
                this.shape_dist_traveled = Double.parseDouble(splitEdgeInfo[8]);

                if(makeConnection)
                {
                    newConnection = new MapConnection(this.trip_id, this.arrival_time, this.departure_time,
                            this.theMap.findStop(stop_id_stop_times), this.stop_sequence, this.stop_headsign,
                            this.pickup_type, this.drop_off_type, this.edgeCost);
                    this.theMap.findStop(previousStopID).addDestination(newConnection);
                }
                previousStopID = this.stop_id_stop_times;
                previousTripID = this.trip_id;
                makeConnection = false;

            }
            edgeDataStopTimes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
