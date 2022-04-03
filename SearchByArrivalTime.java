import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SearchByArrivalTime {
    public static final int NUMBER_OF_NUMBERS_IN_TIME_INPUT = 3;

    public static void SearchByArrivalTime(Map theMap, TST theTST)
    {
        boolean appropriateTimeEntered = false;
        String timeInput = "";
        Scanner userInput = new Scanner(System.in);
        ArrayList<MapConnection> listOfAppropriateStops = new ArrayList<>();
        while(!appropriateTimeEntered)
        {
            System.out.println("Please enter an arrival time in the format 'hh:mm:ss'");
            if(userInput.hasNext())
            {
                timeInput = userInput.next();
                String[] timeInputSplit = new String[NUMBER_OF_NUMBERS_IN_TIME_INPUT];
                timeInputSplit = timeInput.trim().split(":");
                int[] timeNumbers = new int[NUMBER_OF_NUMBERS_IN_TIME_INPUT];
                for(int i = 0; i < timeInputSplit.length; i++)
                {
                    timeNumbers[i] = Integer.parseInt(timeInputSplit[i]);
                }
                if((timeNumbers[0] >= 0 && timeNumbers[0] <= 23) && (timeNumbers[1] >= 0 && timeNumbers[1] <= 59) &&
                        (timeNumbers[2] >= 0 && timeNumbers[2] <= 59))
                {
                    appropriateTimeEntered = true;
                }
            }
            else
            {
                BusManagementSystem.badInput();
                System.out.println("Please enter an arrival time in the specified format");
            }
        }
        Stop currStop = null;
        for(int i = 0; i < theMap.numberOfStops; i++)
        {
            currStop = theMap.stops[i];
            for(int j = 0; j < currStop.destinationList.length; j++)
            {
                if(currStop.destinationList[j].arrivalTime != null)
                {
                    if(currStop.destinationList[j].arrivalTime == timeInput)
                    {
                        listOfAppropriateStops.add(currStop.destinationList[j]);
                    }
                }
            }
        }
        MapConnection[] sortedAppropriateTimeList = new MapConnection[listOfAppropriateStops.size()];
        for(int i = 0; i < sortedAppropriateTimeList.length; i++)
        {
            sortedAppropriateTimeList[i] = listOfAppropriateStops.get(i);                                     //Convert to array for merge sort
        }
        sortedAppropriateTimeList = MergeSortRecursiveTripID.MergeSortRecursiveTripID(sortedAppropriateTimeList);
        System.out.println("Results sorted by trip ID:");
        for(int i = 0; i < sortedAppropriateTimeList.length; i++)
        {
            System.out.println(sortedAppropriateTimeList[i].toString());
        }
    }
}
