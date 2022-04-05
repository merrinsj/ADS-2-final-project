import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ShortestPathBetweenTwoStops {

    public static void ShortestPathBetweenTwoStops(Map theMap, TST theTST)
    {
        boolean firstStopEntered = false;
        boolean secondStopEntered = false;
        int numbSelected = 0;
        String busStopOne = "";
        String busStopTwo = "";
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> listOfSuggestions;
        while(!firstStopEntered)
        {
            System.out.println("Please enter the name of the first stop");
            if (userInput.hasNext()) {
                busStopOne = userInput.next();
                busStopOne = busStopOne.toUpperCase();
                listOfSuggestions = theTST.autocompleteWord(busStopOne);
                if(listOfSuggestions.size() != 0)
                {
                    theTST.print(listOfSuggestions);
                    if (userInput.hasNextInt()) {
                        numbSelected = userInput.nextInt();
                        if (numbSelected < listOfSuggestions.size()) {
                            busStopOne = listOfSuggestions.get(numbSelected);
                            firstStopEntered = true;
                        }
                        else BusManagementSystem.badInput();
                    }
                    else BusManagementSystem.badInput();
                }
                else System.out.println("No stop names match the search criteria");
            }
            else BusManagementSystem.badInput();
        }

        while(!secondStopEntered) {
            System.out.println("Please enter the name of the second stop");
            if(userInput.hasNext()) {
                busStopTwo = userInput.next();
                busStopTwo = busStopTwo.toUpperCase();
                listOfSuggestions = theTST.autocompleteWord(busStopTwo);
                if(listOfSuggestions.size() != 0)
                {
                    theTST.print(listOfSuggestions);
                    if (userInput.hasNextInt()) {
                        numbSelected = userInput.nextInt();
                        if (numbSelected < listOfSuggestions.size()) {
                            busStopTwo = listOfSuggestions.get(numbSelected);
                            secondStopEntered = true;
                        }
                        else BusManagementSystem.badInput();
                    }
                    else BusManagementSystem.badInput();
                }
                else System.out.println("No stop names match the search criteria");
            }
            else BusManagementSystem.badInput();
        }

        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(busStopOne, theMap, theMap.numberOfStops);
        double theDistance = dijkstra.distanceFromStartNodeToSpecifiedNode(busStopTwo);
        if(theDistance != Double.MAX_VALUE)
        {
            ArrayList<Stop> stopsEnRoute = dijkstra.findShortestPathStops(busStopTwo);
            System.out.println("The journey of least cost from " + busStopOne + " to " + busStopTwo + " costs " +
                    theDistance);
            System.out.println("The path of this journey goes through the following stops");
            for(int i = stopsEnRoute.size() - 1; i >= 0; i--)
            {
                System.out.println(stopsEnRoute.get(i));
            }
            System.out.print("\n");
        }
        else System.out.println("There exists no path between these two stops\n");
    }
}

