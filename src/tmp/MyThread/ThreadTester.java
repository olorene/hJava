package tmp.MyThread;

public class ThreadTester {
    public static void main(String[] args) {
        MyRunnable threadJob = new MyRunnable();
        Thread myThread = new Thread(threadJob);
        myThread.start();
        System.out.println("Возвращаемся в метод main");
    }
}
