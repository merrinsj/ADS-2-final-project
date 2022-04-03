public class Stop {
    public final int MAX_NUMBER_OF_CONNECTIONS = 3000;
    int stop_id;
    int stop_code;
    String stop_name;
    String stop_Desc;
    double stop_latitude;
    double stop_longitude;
    String zone_id;
    String stop_url;
    int location_type;
    //int parent_station;
    MapConnection[] destinationList;
    int destinationIndex = 0;

    double distanceToStop = Double.MAX_VALUE;
    Stop dijkstraPreviousStop;
    boolean isUnsettled;
    Stop[] stopsEnRoute;

    Stop(int stop_id, int stop_code, String stop_name, String stop_Desc, double stop_latitude, double stop_longitude,
         String zone_id, String stop_url, int location_type)//, int parent_station)
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
        //this.parent_station = parent_station;
        this.destinationList = new MapConnection[MAX_NUMBER_OF_CONNECTIONS];
        for(int i = 0; i < MAX_NUMBER_OF_CONNECTIONS; i++)
        {
            this.destinationList[i] = null;
        }
    }

    Stop()
    {
        this.stop_id = -1;
        this.stop_code = -1;
        this.stop_name = null;
        this.stop_Desc = null;
        this.stop_latitude = -1.0;
        this.stop_longitude = -1.0;
        this.zone_id = null;
        this.stop_url = null;
        this.location_type = -1;
        //this.parent_station = 0;
        this.destinationList = new MapConnection[MAX_NUMBER_OF_CONNECTIONS];
        for(int i = 0; i < MAX_NUMBER_OF_CONNECTIONS; i++)
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

    public String toString()
    {
        String stopInfo = this.stop_id + ", " +
                this.stop_code + ", " +
                this.stop_name + ", " +
                this.stop_Desc + ", " +
                this.stop_latitude + ", " +
                this.stop_longitude + ", " +
                this.zone_id + ", " +
                this.stop_url + ", " +
                this.location_type;
                //this.parent_station;
        return stopInfo;

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
