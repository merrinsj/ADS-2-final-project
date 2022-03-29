public class Map
{
    MapIntersection[] nodes;
    int numberOfNodes;
    int nodeIndex = 0;

    Map(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
        this.nodes = new MapIntersection[numberOfNodes];
        for(int i = 0; i < numberOfNodes; i++)
        {
            this.nodes[i] = new MapIntersection();
        }
    }

    public void resetMap()
    {
        for(int i = 0; i < numberOfNodes; i++)
        {
            this.nodes[i].resetNode();
        }
    }
}

