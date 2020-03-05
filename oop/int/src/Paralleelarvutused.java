import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.lang.Runtime;

public class Paralleelarvutused {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> failid = new LinkedBlockingQueue<>(Arrays.asList(args));
        BlockingQueue<Tulem> tulemid = new LinkedBlockingQueue<>();
        List<Thread> pool = new ArrayList<>();

        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            pool.add(new Thread(new FailiTöötleja(failid, tulemid)));
            pool.get(pool.size() - 1).start();
        }
        for (Thread thread : pool) {
            thread.join();
        }

        BigInteger vahimSum = null;
        String vahimaFail = null;
        BigInteger koguSum = BigInteger.ZERO;
        BigInteger suurim = null;
        String suurimaFail = null;
        for (Tulem tulem : tulemid) {
            koguSum = koguSum.add(tulem.summa);
            if (vahimSum == null || vahimSum.compareTo(tulem.summa) > 0) {
                vahimSum = tulem.summa;
                vahimaFail = tulem.nimi;
            }
            if (suurim == null || suurim.compareTo(tulem.maxVal) < 0) {
                suurim = tulem.maxVal;
                suurimaFail = tulem.nimi;
            }
        }
        System.out.printf("Kõigi arvude summa on %d,\nsuurim leitud arv %d leiti failist %s,\nväikseima summaga (%d) fail on %s.", koguSum, suurim, suurimaFail, vahimSum, vahimaFail);
    }
}


/*
package ee.cs.ut.praks8;import java.util.ArrayList;
        import java.util.List;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;
        import java.util.concurrent.TimeUnit;
        import java.util.concurrent.atomic.AtomicInteger;
        import java.util.stream.Collectors;
        import java.util.stream.IntStream;class Task implements Runnable {
    private static final AtomicInteger counter = new AtomicInteger(0);	@Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Task " + counter.getAndIncrement() + " completed");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}// TODO: ideas to take away
    //      1. Space limitations
//      2. Cost of setting up new thread
//      3. Thread coordinationclass ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        final List<Task> taskList = IntStream.range(0, 100000)
                .mapToObj(i -> new Task())
                .collect(Collectors.toList());
        System.out.println("Tasks to execute: " + taskList.size());
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (Task task : taskList) {
            final Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Execution finished in "+ (end - start) + " ms");
    }
}
class ExecutorExample {
    public static void main(String[] args) throws InterruptedException {
        final List<Task> taskList = IntStream.range(0, 100000)
                .mapToObj(i -> new Task())
                .collect(Collectors.toList());
        System.out.println("Tasks to execute: " + taskList.size());
        long start = System.currentTimeMillis();
        final ExecutorService executorService = Executors.newFixedThreadPool(10000);
        for (Task task : taskList) {
            executorService.submit(task);
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Execution finished in "+ (end - start) + " ms");
    }
}*/