public class Map
{
    Stop[] stops;
    int numberOfStops;
    int nodeIndex = 0;

    Map(int numberOfStops)
    {
        this.numberOfStops = numberOfStops;
        this.stops = new [numberOfStops];
        for(int i = 0; i < numberOfStops; i++)
        {
            this.stops[i] = new Stop();
        }
    }

    public void resetMap()
    {
        for(int i = 0; i < numberOfNodes; i++)
        {
            this.stops[i].resetNode();
        }
    }
}

