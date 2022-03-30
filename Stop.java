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
