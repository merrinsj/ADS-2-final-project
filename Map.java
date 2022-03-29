public class Map
{
    MapNode[] nodes;
    int numberOfNodes;
    int nodeIndex = 0;

    Map(int numberOfNodes)
    {
        this.numberOfNodes = numberOfNodes;
        this.nodes = new MapNode[numberOfNodes];
        for(int i = 0; i < numberOfNodes; i++)
        {
            this.nodes[i] = new MapNode();
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
