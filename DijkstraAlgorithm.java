import java.util.ArrayList;

public class DijkstraAlgorithm
{
    Stop startNode;
    Map theMap;
    Stop[] settledNodes;
    Stop[] unsettledNodes;
    int unsettledNodesIndex = 0;
    int settledNodesIndex = 0;
    int numberOfNodes = 0;
    Stop nullNode = new Stop();
    boolean emptyMap = false;
    boolean allNodesReachable = true;

    public DijkstraAlgorithm(String startingNode, Map theMap, int numberOfNodes)
    {
        if(theMap.stops.length == 0)
        {
            emptyMap = true;
        }
        else
        {
            this.startNode = theMap.findStopName(startingNode);
            this.startNode.distanceToStop = 0.0;
            this.theMap = theMap;
            this.numberOfNodes = numberOfNodes;
            this.settledNodes = new Stop[this.numberOfNodes];
            this.unsettledNodes = new Stop[this.numberOfNodes];
            this.unsettledNodes[unsettledNodesIndex] = this.startNode;
            this.startNode.isUnsettled = true;
            unsettledNodesIndex++;
        }
    }


    public void dijkstraMethod()
    {
        if(!emptyMap)
        {
            Stop nodeToRelax = new Stop();
            for(int i = 0; i < this.numberOfNodes; i++)
            {
                if(!isUnsettledNodesEmpty())
                {
                    double closestNodeDistance = Double.MAX_VALUE;
                    for(int j = 0; j < unsettledNodesIndex; j++)
                    {
                        if(unsettledNodes[j] != nullNode)
                        {
                            if(unsettledNodes[j].distanceToStop < closestNodeDistance)
                            {
                                nodeToRelax = unsettledNodes[j];
                                closestNodeDistance = unsettledNodes[j].distanceToStop;
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

    public void relaxNode(Stop aNode)
    {
        for(int i = 0; i < aNode.destinationIndex; i++)
        {
            if(aNode.destinationList[i].connectsTo.isUnsettled == false)
            {
                unsettledNodes[unsettledNodesIndex] = aNode.destinationList[i].connectsTo;       //Put the reachable nodes into the unsettled array
                aNode.destinationList[i].connectsTo.isUnsettled = true;
                unsettledNodesIndex++;
            }
            if(aNode.destinationList[i].connectsTo.distanceToStop > aNode.distanceToStop + aNode.destinationList[i].cost)
            {
                aNode.destinationList[i].connectsTo.distanceToStop = aNode.distanceToStop + aNode.destinationList[i].cost; //Put the arrays in unsettled array
                aNode.destinationList[i].connectsTo.dijkstraPreviousStop = aNode;                                                           //and set their length
            }
        }
        settledNodes[settledNodesIndex] = aNode;
        settledNodesIndex++;
        removeFromUnsettledNodes(aNode);
    }


    public double longestDistance()
    {
        Stop farthestNode = new Stop();
        double distanceToFarthestNode = 0.0; //shalom
        for(int i = 0; i < settledNodesIndex; i++)
        {
            if(settledNodes[i].distanceToStop > distanceToFarthestNode)
            {
                farthestNode = settledNodes[i];
                distanceToFarthestNode = settledNodes[i].distanceToStop;
            }
        }
        return distanceToFarthestNode;
    }

    public void removeFromUnsettledNodes(Stop aNode)
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

    public double distanceFromStartNodeToSpecifiedNode(String nodeName)
    {
        Stop destinationStop = theMap.findStopName(nodeName);
        dijkstraMethod();
        return destinationStop.distanceToStop;
    }

    public ArrayList<Stop> findShortestPathStops(String destinationStop)      //Returns the order of stops on the journey in
    {                                                                           //reverse order
        ArrayList<Stop> stopsEnRoute = new ArrayList<>();
        Stop currStop = theMap.findStopName(destinationStop);
        if(currStop != null)
        {
            while(currStop != startNode)
            {
                stopsEnRoute.add(currStop);
                currStop = currStop.dijkstraPreviousStop;
            }
            stopsEnRoute.add(currStop);
        }
        return stopsEnRoute;
    }

}
