public class PirateStation extends Broadcaster implements BroadcastListener {
    @Override
    public void listen(String sonum) {
        for (BroadcastListener listener : super.listeners){
            listener.listen(sonum);
        }
    }
}
