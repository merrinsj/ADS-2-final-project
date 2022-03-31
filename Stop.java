public class Stop {
    int stop_id;
    int stop_code;
    String stop_name;
    String stop_Desc;
    double stop_latitude;
    double stop_longitude;
    String zone_id;
    String stop_url;
    int location_type;
    int parent_station;
    MapConnection[] destinationList;
    int maxNumberOfConnections = 300;
    int destinationIndex = 0;

    double distanceToStop = Double.MAX_VALUE;
    Stop dijkstraPreviousStop;
    boolean isUnsettled;
    Stop[] stopsEnRoute;

    Stop(int stop_id, int stop_code, String stop_name, String stop_Desc, double stop_latitude, double stop_longitude,
         String zone_id, String stop_url, int location_type, int parent_station)
    {
        this.stop_id = stop_id;
        this.stop_code = stop_code;
        this.stop_name = stop_name;
        this.stop_Desc = stop_Desc;
        this.stop_latitude = stop_latitude;
        this.stop_longitude = stop_longitude;
        this.zone_id = zone_id;
        this.stop_url = stop_url;
        this.location_type = location_type;
        this.parent_station = parent_station;
        this.destinationList = new MapConnection[maxNumberOfConnections];
        for(int i = 0; i < maxNumberOfConnections; i++)
        {
            this.destinationList[i] = null;
        }
    }

    Stop()
    {
        this.stop_id = 0;
        this.stop_code = 0;
        this.stop_name = "0";
        this.stop_Desc = "0";
        this.stop_latitude = 0.0;
        this.stop_longitude = 0.0;
        this.zone_id = "0";
        this.stop_url = "0";
        this.location_type = 0;
        this.parent_station = 0;
        this.destinationList = new MapConnection[maxNumberOfConnections];
        for(int i = 0; i < maxNumberOfConnections; i++)
        {
            this.destinationList[i] = null;
        }
    }


    public void addDestination(MapConnection newConnection)
    {
        this.destinationList[this.destinationIndex] = newConnection;
        this.destinationIndex++;
    }

    public void resetStop()
    {
        this.dijkstraPreviousStop = null;
        this.distanceToStop = Double.MAX_VALUE;
        this.isUnsettled = false;
    }

//    public double findConnectionLength(Stop destinationNode)
//    {
//        if(this == destinationNode){ return 0.0; }
//        for(int i = 0; i < this.destinationIndex; i++)
//        {
//            if(this.destinationList[i].connectsTo == destinationNode)
//            {
//                return this.destinationList[i].min_transfer_time;
//            }
//        }
//        return Double.MAX_VALUE;
//    }
}
