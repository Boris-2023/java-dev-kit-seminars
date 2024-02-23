package task01b_dead_lock_friends;

public class Main {
    public static void main(String[] args) {

        Friend friend1 = new Friend("Vasya");
        Friend friend2 = new Friend("Petya");

        MyThread thread1 = new MyThread(friend1, friend2);
        MyThread thread2 = new MyThread(friend2, friend1);

        thread1.start();
        thread2.start();

        System.out.println("End of work!");

    }
}
