package SomeStatic;

public class TestExceptions {
    public static void main(String[] args) {
        String test = "no";
        try {
            System.out.println("Begin try");
            TestExceptions.doRisky(test);
            System.out.println("End try");

        } catch (ScaryException se) {
            System.out.println("Creepy exception");
        } finally {
            System.out.println("finally");
        }
        System.out.println("End Main");
    }

    static void doRisky(String test) throws ScaryException {
        System.out.println("Begin risky method");
        if ("yes".equals(test)) {
            throw new ScaryException();
        }
        System.out.println("End risky method");
        return;
    }

}
