import java.util.ArrayList;
import java.util.Scanner;

public class SearchForABusStop {

    public static void SearchForABusStop(Map theMap, TST theTST)
    {
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> listOfSuggestions;
        System.out.println("Please enter the name of the stop");
        if(userInput.hasNext())
        {
            String theBusStop = userInput.next();
            listOfSuggestions = theTST.autocompleteWord(theBusStop);
            Stop busStop = null;
            for(int i = 0; i < listOfSuggestions.size(); i++)
            {
                System.out.println(theMap.findStopName(listOfSuggestions.get(i)).toString());           //Print all bus stops matching search criteria
            }
        }
        else BusManagementSystem.badInput();
    }
}
