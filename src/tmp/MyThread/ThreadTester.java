package tmp.MyThread;

public class ThreadTester {
    public static void main(String[] args) {
        MyRunnableSleep theJobSleep = new MyRunnableSleep();
        Thread newThread = new Thread(theJobSleep);
        newThread.start();
        System.out.println("Возвращаемся в метод main-2");

        MyRunnable threadJob = new MyRunnable();
        Thread myThread = new Thread(threadJob);
        myThread.start();
        System.out.println("Возвращаемся в метод main-1");


    }
}
