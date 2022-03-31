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

    public Stop findStop(int stopID)
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

