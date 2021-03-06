public class Map
{
    Stop[] stops;
    int numberOfStops;
    int nodeIndex = 0;

    Map(int numberOfStops)
    {
        this.numberOfStops = numberOfStops;
        this.stops = new Stop[numberOfStops];
        for(int i = 0; i < numberOfStops; i++)
        {
            this.stops[i] = null;
        }
    }

    public void resetMap()
    {
        for(int i = 0; i < numberOfStops; i++)
        {
            this.stops[i].resetStop();
        }
    }

    public Stop findStopName(String stopName)
    {
        for(int i = 0; i < numberOfStops; i++)
        {
            if(stops[i].stop_name.equalsIgnoreCase(stopName))
            {
                return stops[i];
            }
        }
        return null;
    }

    public Stop findStopID(int stopID)
    {
        for(int i = 0; i < numberOfStops; i++)
        {
            if(stops[i].stop_id == stopID)
            {
                return stops[i];
            }
        }
        return null;
    }

}

