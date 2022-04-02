public class MapConnection
{
    Stop connectsTo;
    double cost;
    String arrivalTime;

    int trip_id;
    String departure_time;
    int stop_sequence;
    int stop_headsign;
    int pickup_type;
    int drop_off_type;
    double shape_dist_traveled;

    MapConnection(Stop connectsTo, String arrivalTime, double cost)
    {
        this.connectsTo = connectsTo;
        this.arrivalTime = arrivalTime;
        this.cost = cost;

        this.trip_id = -1;
        this.departure_time = "";
        this.stop_sequence = -1;
        this.stop_headsign = -1;
        this.pickup_type = -1;
        this.drop_off_type = -1;
        this.shape_dist_traveled = -1.0;
    }

    MapConnection(int trip_id, String arrival_time, String departure_time, Stop connectsTo, int stop_sequence,
                  int stop_headsign, int pickup_type, int drop_off_type, double shape_dist_traveled, double cost)
    {
        this.trip_id = trip_id;
        this.arrivalTime = arrival_time;
        this.departure_time = departure_time;
        this.connectsTo = connectsTo;
        this.stop_sequence = stop_sequence;
        this.stop_headsign = stop_headsign;
        this.pickup_type = pickup_type;
        this.drop_off_type = drop_off_type;
        this.shape_dist_traveled = shape_dist_traveled;
        this.cost = cost;
    }

    public String toString()
    {
        String connectionInfo = this.trip_id + ", " +
                this.arrivalTime + ", " +
                this.departure_time + ", " +
                this.connectsTo + ", " +
                this.stop_sequence + ", " +
                this.stop_headsign + ", " +
                this.pickup_type + ", " +
                this.drop_off_type + ", " +
                this.shape_dist_traveled;
        return connectionInfo;
    }
}
