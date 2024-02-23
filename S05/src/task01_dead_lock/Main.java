package task01_dead_lock;

// В рамках выполнения задачи необходимо:
// Создать два класс ObjectA, ObjectB
// Реализовать класс, в котором два потока при запуске провоцируют
// DeadLock для объектов ObjectA, ObjectB

public class Main {
    public static void main(String[] args) {
        ObjectA objectA = new ObjectA();
        ObjectB objectB = new ObjectB();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName() + ": ObjectA is locked!!!");
                    synchronized (objectB) {
                        System.out.println(Thread.currentThread().getName() + ": ObjectB is locked..");
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + ": ObjectB is locked!!!");
                    synchronized (objectA) {
                        System.out.println(Thread.currentThread().getName() + ": ObjectA is locked..");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

        System.out.println("Hello from thread: " + Thread.currentThread().getName());

    }
}
