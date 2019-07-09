package tmp.MyThread;

public class MyThreadName implements Runnable {
    public static void main(String[] args) {
        Runnable myThreadJob = new MyThreadName();
        Thread alpha = new Thread(myThreadJob);
        Thread betta = new Thread(myThreadJob);
        alpha.setName("Thread alpha");
        betta.setName("Thread betta");
        alpha.start();
        betta.start();
    }
    @Override
    public void run() {
        for (int i = 0; i < 25; i++) {
            String nameThread = Thread.currentThread().getName();
            System.out.println("Зараз працює: " + nameThread);
        }

    }
}
