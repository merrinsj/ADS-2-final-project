import org.junit.Test;

import java.io.FileNotFoundException;

public class BusManagementTests {
    @Test
    public void dijkstraTest() throws FileNotFoundException {
        CreateMapAndTST.CreateMapAndTST();
        Map theMap = CreateMapAndTST.getTheMap();
        TST theTST = CreateMapAndTST.getTheTST();
        String startNode = "HASTINGS ST FS HOLDOM AVE- WB";
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(startNode, theMap, theMap.numberOfStops);
        for(int i = 0; i < theMap.findStopName(startNode).destinationIndex; i++)
        {
            System.out.println(theMap.findStopName(startNode).destinationList[i].toString());
        }
    }
}
