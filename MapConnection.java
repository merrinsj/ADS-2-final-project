public class MapConnection
{
    MapNode connectsTo;
    double linkLength;

    MapConnection(MapNode connectsTo, double linkLength)
    {
        this.connectsTo = connectsTo;
        this.linkLength = linkLength;
    }
}
