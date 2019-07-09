package RayanAndMonica;

public class RayanAndMonicaJob implements Runnable {
    private BankAccount account = new BankAccount();

    public static void main(String[] args) {
        RayanAndMonicaJob theJob = new RayanAndMonicaJob();
        Thread one = new Thread(theJob);
        Thread two = new Thread(theJob);
        one.setName("Rayan");
        two.setName("Monica");
        one.start();
        two.start();

    }

    @Override
    public void run() {
        for (int x = 0; x < 10; x++) {
            makeWithdraw(10);
            if (account.getBalance() < 0) {
                System.out.println("Превышение лимита");
            }
        }
    }

    public void makeWithdraw(int amount) {
        if (account.getBalance() >= 0) {
            System.out.println(Thread.currentThread().getName() + " собирается снять деньги");
            try {
                System.out.println(Thread.currentThread().getName() + " идет дремать");
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " просыпается");
            account.withdraw(amount);
            System.out.println(Thread.currentThread().getName() + " заканчивается тразакция");
        } else {
            System.out.println("Денег для клиента " + Thread.currentThread().getName() + " недостаточно денег");

        }
    }


}
