import java.util.ArrayList;
import java.util.Scanner;

public class ShortestPathBetweenTwoStops {

    public static void ShortestPathBetweenTwoStops(Map theMap, TST theTST)
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
                        double theDistance = dijkstra.distanceFromStartNodeToSpecifiedNode(busStopTwo);
                        ArrayList<Stop> stopsEnRoute = dijkstra.findShortestPathStops(busStopTwo);
                        System.out.println("The journey of least cost from " + busStopOne + " to " + busStopTwo + " costs" +
                                theDistance);
                        System.out.println("The path of this journey goes through the following stops");
                        for(int i = stopsEnRoute.size() - 1; i >= 0; i--)
                        {
                            System.out.println(stopsEnRoute.get(i));
                        }
                    }
                    else BusManagementSystem.badInput();
                }
                else BusManagementSystem.badInput();
            }
            else BusManagementSystem.badInput();
        }
        else BusManagementSystem.badInput();
        System.out.println("Something went wrong shortest path");
    }

}
