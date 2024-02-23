package task03;

// 3 бегуна должны прийти к старту гонки
// Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
// Программа должна отсчитать “На старт”, “Внимание”, “Марш”
// Программа должна завершиться когда все участники закончат гонку.
// Подумайте об использовании примитива синхронизации

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(4);

        Runner runner1 = new Runner("Vasya", cdl);
        Runner runner2 = new Runner("Petya", cdl);
        Runner runner3 = new Runner("Sasha", cdl);

        runner1.start();
        runner2.start();
        runner3.start();

        //cdl.await();
        while(cdl.getCount() != 1){
            Thread.sleep(100);
        }

        System.out.println("Ready!");
        Thread.sleep(100);
        System.out.println("Steady!");
        Thread.sleep(100);
        System.out.println("GO!");

        cdl.countDown();

    }
}
