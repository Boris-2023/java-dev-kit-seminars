package task03;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Runner extends Thread {
    private final int MIN_GO_TO_START = 1000;
    private final int MAX_GO_TO_START = 2000;
    private final int MIN_GO_TO_FINISH = 5000;
    private final int MAX_GO_TO_FINISH = 7000;
    private String name;
    private Random rnd = new Random();
    CountDownLatch cdl;

    public Runner(String name, CountDownLatch cdl) {
        this.name = name;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            toStart();
            cdl.await();
            toFinish();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void toStart() throws InterruptedException {
        System.out.println(name + " goes to start..");
        Thread.sleep(rnd.nextInt(MIN_GO_TO_START, MAX_GO_TO_START));
        System.out.println(name + " is ready to start!");

        cdl.countDown();
    }

    public void toFinish() throws InterruptedException {
        System.out.println(name + " starts..");
        Thread.sleep(rnd.nextInt(MIN_GO_TO_FINISH, MAX_GO_TO_FINISH));
        System.out.println(name + " has finished!");
    }
}
