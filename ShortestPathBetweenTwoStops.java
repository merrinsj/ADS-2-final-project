import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPathBetweenTwoStops {

    public static double ShortestPathBetweenTwoStops(Map theMap, TST theTST)
    {
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> listOfSuggestions;
        System.out.println("Please enter the name of the first stop");
        if(userInput.hasNext())
        {
            String busStopOne = userInput.next();
            listOfSuggestions = theTST.autocompleteWord(busStopOne);
            theTST.print(listOfSuggestions);
            if(userInput.hasNextInt() && userInput.nextInt() < listOfSuggestions.size())
            {
                busStopOne = listOfSuggestions.get(userInput.nextInt());
                System.out.println("Please enter the name of the second stop");
                if(userInput.hasNext())
                {
                    String busStopTwo = userInput.next();
                    listOfSuggestions = theTST.autocompleteWord(busStopTwo);
                    theTST.print(listOfSuggestions);
                    if(userInput.hasNextInt() && userInput.nextInt() < listOfSuggestions.size())
                    {
                        busStopTwo = listOfSuggestions.get(userInput.nextInt());
                        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(busStopOne, theMap, theMap.numberOfStops);
                        return dijkstra.distanceFromStartNodeToSpecifiedNode(busStopTwo);
                    }
                    else BusManagementSystem.badInput();
                }
                else BusManagementSystem.badInput();
            }
            else BusManagementSystem.badInput();
        }
        else BusManagementSystem.badInput();
        System.out.println("Something went wrong shortest path");
        return 0.0;
    }

}
