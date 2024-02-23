import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Philosopher extends Thread {

    public static final int MIN_EATING_TIME = 2000;
    public static final int MAX_EATING_TIME = 3000;
    public static final int MIN_THINKING_TIME = 1000;
    public static final int MAX_THINKING_TIME = 5000;
    private static int philosopherId = 0;
    private final Fork leftFork, rightFork;
    private final int maxEatingTimes;
    private final int id;
    private int countMeals;
    Random rnd = new Random();
    CountDownLatch cdl;

    public Philosopher(Fork leftFork, Fork rightFork, int maxEatingTimes, CountDownLatch cdl) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.maxEatingTimes = maxEatingTimes;
        this.cdl = cdl;

        countMeals = 0;
        id = ++philosopherId;
    }

    public void eat() throws InterruptedException {
        System.out.println("Философ № " + id + " начал есть в " + countMeals + "-й раз, используя вилки №№ " + leftFork.getId() + " и " + rightFork.getId());
        Thread.sleep(rnd.nextLong(MIN_EATING_TIME, MAX_EATING_TIME));
        leftFork.release();
        rightFork.release();
        System.out.println("Философ № " + id + " закончил прием пищи №" + countMeals + ", вилки №№ " + leftFork.getId() + " и " + rightFork.getId() + " освобождены!");
    }

    public void think() throws InterruptedException {
        if (countMeals < (maxEatingTimes - 1))
            Thread.sleep(rnd.nextLong(MIN_THINKING_TIME, MAX_THINKING_TIME));
    }

    @Override
    public void run() {
        while (countMeals < maxEatingTimes) {
            try {
                if (Table.areForksFree(leftFork, rightFork)) {
                    countMeals++;
                    eat();
                }
                think();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Философ № " + id + " наелся!!!");
        cdl.countDown();
    }

    @Override
    public long getId() {
        return id;
    }
}
