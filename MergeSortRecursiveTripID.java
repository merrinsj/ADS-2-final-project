import java.util.ArrayList;

//I used information from the following links to help implement merge sort recursive
//https://www.baeldung.com/java-merge-sort
public class MergeSortRecursiveTripID {

    public static MapConnection[] MergeSortRecursiveTripID(MapConnection[] a)
    {
        if(a == null)
        {
            return a;
        }

        if(a.length > 1)
        {
            int mid = a.length / 2;

            MapConnection[] left = new MapConnection[mid];        //Create a left subarray
            for(int i = 0; i < mid; i++)
            {
                left[i] = a[i];
            }

            MapConnection[] right = new MapConnection[a.length - mid];    //Create a right subarray
            for(int i = mid; i < a.length; i++)
            {
                right[i - mid] = a[i];
            }
            MergeSortRecursiveTripID(left);               //Call the method on the left subarray
            MergeSortRecursiveTripID(right);              //Call the method on the right subarray

            int i = 0;
            int j = 0;
            int k = 0;

            while(i < left.length && j < right.length) //Merge the arrays and sort them appropriately
            {
                if(left[i].trip_id < right[j].trip_id)
                {
                    a[k] = left[i];
                    i++;
                }
                else
                {
                    a[k] = right[j];
                    j++;
                }
                k++;
            }

            while(i < left.length)      //Add the remaining elements to the merged array
            {
                a[k] = left[i];
                i++;
                k++;
            }
            while(j < right.length)
            {
                a[k] = right[j];
                j++;
                k++;
            }
        }
        return a;
    }
}

