import java.util.ArrayList;
import java.util.List;

public class Broadcaster {
    List<BroadcastListener> listeners = new ArrayList<BroadcastListener>();
    void subscribe(BroadcastListener broadcastListener){
        listeners.add(broadcastListener);
    }
    void broadcast(String sonum){
        for (BroadcastListener listener : listeners){
            listener.listen(sonum);
        }
    }
}
