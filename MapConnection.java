public class MapConnection
{
    MapIntersection connectsTo;
    double linkLength;

    MapConnection(MapIntersection connectsTo, double linkLength)
    {
        this.connectsTo = connectsTo;
        this.linkLength = linkLength;
    }
}
