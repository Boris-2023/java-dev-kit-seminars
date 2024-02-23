import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Table {

    private final int philosophersNumber, maxTimesToEat;
    ArrayList<Fork> forks = new ArrayList<>();
    ArrayList<Philosopher> philosophers = new ArrayList<>();
    CountDownLatch cdl;

    public Table(int philosophersNumber, int maxTimesToEat) {
        this.philosophersNumber = philosophersNumber;
        this.maxTimesToEat = maxTimesToEat;
        cdl = new CountDownLatch(philosophersNumber);

        setForks();
        setPhilosophers();
    }

    private void setForks() {
        for (int i = 0; i < philosophersNumber; i++) {
            forks.add(new Fork());
        }
    }

    private void setPhilosophers() {
        int leftForkIndex, rightForkIndex;
        for (int i = 0; i < philosophersNumber; i++) {
            leftForkIndex = i;
            rightForkIndex = i + 1;
            if (rightForkIndex == philosophersNumber) rightForkIndex = 0;

            philosophers.add(new Philosopher(forks.get(leftForkIndex), forks.get(rightForkIndex), maxTimesToEat, cdl));
        }
    }

    public static synchronized boolean areForksFree(Fork leftFork, Fork rightFork){
        if(leftFork.isFree() && rightFork.isFree()){
            leftFork.engage();
            rightFork.engage();
            return true;
        }
        return false;
    }

    public void run(){
        for (int i = 0; i < philosophersNumber; i++) {
            philosophers.get(i).start();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nВсе философы наелись!!!");
    }

}
