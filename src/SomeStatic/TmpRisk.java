package SomeStatic;

public class TmpRisk {
    boolean abandonAllHope = true;
    public void takeRisk() throws BadException {
        if (abandonAllHope) {
            System.out.println("Throw exception from TmpRisk");
            throw new BadException();
        }
    }
}
