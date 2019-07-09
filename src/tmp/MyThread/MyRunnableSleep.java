package tmp.MyThread;

public class MyRunnableSleep implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        go();
    }

    public void go() {
        doSomething();
    }

    public void doSomething() {
        System.out.println("Do something");
    }
}
