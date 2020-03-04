import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Paralleelarvutused {
    public static void main(String[] args) {
        BlockingQueue<String> failid = new LinkedBlockingQueue<>(Arrays.asList(args));
        BlockingQueue<Tulem> tulemid = new LinkedBlockingQueue<>();
        Thread failiLugejaLõim1 = new Thread(new FailiTöötleja(failid, tulemid));
    }
}
