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
                newConnection = new MapConnection(this.theMap.findStop(to_stop_id), transfer_type, min_transfer_time);
                this.theMap.findStop(from_stop_id).addDestination(newConnection);
            }
            edgeData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
