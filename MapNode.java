public class MapNode
{
    int maxNumberOfConnections = 300;       //Estimated maximum number of connections per Node
    int connectionIndex = 0;
    MapConnection[] connectionList;
    double distanceToNode = Double.MAX_VALUE;
    MapNode previousNode;
    Boolean isUnsettled;


    MapNode()
    {
        this.connectionList = new MapConnection[maxNumberOfConnections];
        isUnsettled = false;
        for(int i = 0; i < 30; i++)
        {
            this.connectionList[i] = null;
        }
    }

    public void addConnection(MapConnection newConnection)
    {
        this.connectionList[this.connectionIndex] = newConnection;
        this.connectionIndex++;
    }

    public double findConnectionLength(MapNode destinationNode)
    {
        if(this == destinationNode){ return 0.0; }
        for(int i = 0; i < this.connectionIndex; i++)
        {
            if(this.connectionList[i].connectsTo == destinationNode)
            {
                return this.connectionList[i].linkLength;
            }
        }
        return Double.MAX_VALUE;
    }

    void resetNode()
    {
        this.previousNode = null;
        this.distanceToNode = Double.MAX_VALUE;
        this.isUnsettled = false;
    }

}
