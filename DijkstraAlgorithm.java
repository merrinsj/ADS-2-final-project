public class DijkstraAlgorithm
{
    MapNode startNode;
    Map theMap;
    MapNode[] settledNodes;
    MapNode[] unsettledNodes;
    int unsettledNodesIndex = 0;
    int settledNodesIndex = 0;
    int numberOfNodes = 0;
    MapNode nullNode = new MapNode();
    Boolean emptyMap = false;
    boolean allNodesReachable = true;

    DijkstraAlgorithm(int startingNode, Map theMap, int numberOfNodes)
    {
        if(theMap.nodes.length == 0)
        {
            emptyMap = true;
        }
        else
        {
            this.startNode = theMap.nodes[startingNode];
            this.startNode.distanceToNode = 0.0;
            this.theMap = theMap;
            this.numberOfNodes = numberOfNodes;
            this.settledNodes = new MapNode[this.numberOfNodes];
            this.unsettledNodes = new MapNode[this.numberOfNodes];
            this.unsettledNodes[unsettledNodesIndex] = this.startNode;
            this.startNode.isUnsettled = true;
            unsettledNodesIndex++;
        }
    }


    public void dijkstraMethod()
    {
        if(!emptyMap)
        {
            MapNode nodeToRelax = new MapNode();
            for(int i = 0; i < this.numberOfNodes; i++)
            {
                if(!isUnsettledNodesEmpty())
                {
                    double closestNodeDistance = Double.MAX_VALUE;
                    for(int j = 0; j < unsettledNodesIndex; j++)
                    {
                        if(unsettledNodes[j] != nullNode)
                        {
                            if(unsettledNodes[j].distanceToNode < closestNodeDistance)
                            {
                                nodeToRelax = unsettledNodes[j];
                                closestNodeDistance = unsettledNodes[j].distanceToNode;
                            }
                        }
                    }
                    relaxNode(nodeToRelax);
                }
                else
                {
                    allNodesReachable = false;
                    break;
                }
            }
        }
    }

    public void relaxNode(MapNode aNode)
    {
        for(int i = 0; i < aNode.connectionIndex; i++)
        {
            if(aNode.connectionList[i].connectsTo.isUnsettled == false)
            {
                unsettledNodes[unsettledNodesIndex] = aNode.connectionList[i].connectsTo;       //Put the reachable nodes into the unsettled array
                aNode.connectionList[i].connectsTo.isUnsettled = true;
                unsettledNodesIndex++;
            }
            if(aNode.connectionList[i].connectsTo.distanceToNode > aNode.distanceToNode + aNode.connectionList[i].linkLength)
            {
                aNode.connectionList[i].connectsTo.distanceToNode = aNode.distanceToNode + aNode.connectionList[i].linkLength; //Put the arrays in unsettled array
                aNode.connectionList[i].connectsTo.previousNode = aNode;                                                           //and set their length
            }
        }
        settledNodes[settledNodesIndex] = aNode;
        settledNodesIndex++;
        removeFromUnsettledNodes(aNode);
    }


    public double longestDistance()
    {
        MapNode farthestNode = new MapNode();
        double distanceToFarthestNode = 0.0; //shalom
        for(int i = 0; i < settledNodesIndex; i++)
        {
            if(settledNodes[i].distanceToNode > distanceToFarthestNode)
            {
                farthestNode = settledNodes[i];
                distanceToFarthestNode = settledNodes[i].distanceToNode;
            }
        }
        return distanceToFarthestNode;
    }

    public void removeFromUnsettledNodes(MapNode aNode)
    {
        int i = 0;
        while(unsettledNodes[i] != aNode) { i++; }
        unsettledNodes[i] = nullNode;
    }

    public boolean isUnsettledNodesEmpty()
    {
        boolean isEmpty = true;
        for(int i = 0; i < this.numberOfNodes; i++)
        {
            if(this.unsettledNodes[i] != nullNode && this.unsettledNodes[i] != null)
            {
                isEmpty = false;
            }
        }
        return isEmpty;
    }

}
