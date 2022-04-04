import javax.print.DocFlavor;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BusManagementSystem {

    public static void main(String[] args) throws FileNotFoundException {
        CreateMapAndTST.CreateMapAndTST();
        Map theMap = CreateMapAndTST.getTheMap();
        TST theTST = CreateMapAndTST.getTheTST();
        boolean inSystem = true;
        System.out.println("Welcome to our bus management system");
        while(inSystem)
        {
            System.out.println("What would you like to know? Please enter a number for whichever option you'd like\n");
            System.out.println("1. Find the distance between two stops\n" +
                    "2. Search for a bus stop\n" +
                    "3. Search for all trips by arrival time\n" +
                    "4. Exit the program");
            Scanner userInput = new Scanner(System.in);
            if(userInput.hasNextInt())
            {
                int input = userInput.nextInt();
                if(input >= 1 && input <= 4)
                {
                    switch(input)
                    {
                        case 1:
                            ShortestPathBetweenTwoStops.ShortestPathBetweenTwoStops(theMap, theTST);
                            break;
                        case 2:
                            SearchForABusStop.SearchForABusStop(theMap, theTST);
                            break;
                        case 3:
                            SearchByArrivalTime.SearchByArrivalTime(theMap, theTST);
                            break;
                        case 4:
                            inSystem = false;
                    }
                }
                else badInput();
            }
            else badInput();
        }
    }
    public static void badInput()
    {
        System.out.print("Bad input. Please enter a number between 1 and 4");
    }
}
