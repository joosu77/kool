import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class FailiTöötleja implements Runnable {
    private BlockingQueue<String> failid;
    private BlockingQueue<Tulem> tulemid;

    public FailiTöötleja(BlockingQueue<String> failid, BlockingQueue<Tulem> tulemid) {
        this.failid = failid;
        this.tulemid = tulemid;
    }

    @Override
    public void run() {
        while (!failid.isEmpty()) {
            String file = failid.poll();
            try (Scanner scanner = new Scanner(new File(file))) {
                BigInteger suurim = null;
                BigInteger summa = BigInteger.ZERO;
                while (scanner.hasNextBigInteger()) {
                    BigInteger val = scanner.nextBigInteger();
                    summa = summa.add(val);
                    if (suurim == null || suurim.compareTo(val) < 0) {
                        suurim = val;
                    }
                }
                Tulem tulem = new Tulem(file, summa, suurim);
                tulemid.offer(tulem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
