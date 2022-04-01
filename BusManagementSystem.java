import javax.print.DocFlavor;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BusManagementSystem {

    public static void main() throws FileNotFoundException {
        CreateMapAndTST.CreateMapAndTST();
        Map theMap = CreateMapAndTST.getTheMap();
        TST theTST = CreateMapAndTST.getTheTST();
        boolean inSystem = true;
        System.out.print("Welcome to our bus management system\n");
        while(inSystem)
        {
            System.out.print("What would you like to know? Please enter a number for whichever option you'd like\n");
            System.out.print("1. Find the distance between two stops\n" +
                    "2. Search for a bus stop\n" +
                    "3. Search for all trips by arrival time" +
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
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:

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