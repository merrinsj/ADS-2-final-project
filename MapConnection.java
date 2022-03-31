public class MapConnection
{
    Stop connectsTo;
    int transferType;
    int min_transfer_time;

    MapConnection(Stop connectsTo, int transferType, int min_transfer_time)
    {
        this.connectsTo = connectsTo;
        this.transferType = transferType;
        this.min_transfer_time = min_transfer_time;
    }
}
