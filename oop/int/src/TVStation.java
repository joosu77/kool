import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TVStation extends Broadcaster {
    List<String> uudised;

    public TVStation(List<String> uudised) {
        this.uudised = uudised;
    }
    void sendNews(){
        Random random = new Random();
        super.broadcast(uudised.get(random.nextInt(uudised.size())));
    }
}
