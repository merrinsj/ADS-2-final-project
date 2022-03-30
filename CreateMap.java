import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;

public class CreateMap {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */

    final int PIECES_OF_INFORMATION_PER_LINE = 10;
    int stop_id;
    int stop_code;
    String stop_name;
    String stop_Desc;
    double stop_latitude;
    double stop_longitude;
    String zone_id;
    String stop_url;
    int location_type;
    int parent_station;

    Map theMap;
    String filename;
    Boolean emptyMap = false;

    CreateMap() {
        this.filename = "stops.txt";

        int numberOfStops = -1;
        int lineCounter = 0;
        String stopInfo = "";
        String[] stopInfoSplit = new String[PIECES_OF_INFORMATION_PER_LINE];
        theMap = new Map(0);
        MapConnection newConnection;
        try {
            File mapData = new File(filename);
            Scanner myReader = new Scanner(mapData);

            while (myReader.hasNextLine()) {
                numberOfStops++;
            }
            theMap = new Map(numberOfStops);
                if (lineCounter == 0) {
                    this.numberOfNodes = Integer.parseInt(myReader.nextLine());
                    if (this.numberOfNodes == 0) {
                        emptyMap = true;
                    }
                }

                    theMap = new Map(this.numberOfNodes);
                    lineCounter++;
                } else if (lineCounter == 1) {
                    myReader.nextLine();
                    lineCounter++;
                } else {
                    nums = myReader.nextLine();
                    numsSplit = nums.trim().split("\\s+");
                    homeIntersection = Integer.parseInt(numsSplit[0]);
                    awayIntersection = Integer.parseInt(numsSplit[1]);
                    connectionLength = Double.parseDouble(numsSplit[2]);

                    newConnection = new MapConnection(theMap.nodes[awayIntersection], connectionLength);
                    theMap.nodes[homeIntersection].addConnection(newConnection);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
