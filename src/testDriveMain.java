public class testDriveMain {
    private static int a = 1;
    private static int b;
    private int e = 6;

     testDriveMain(){

    }

    public static void main(String[] args) {
        int c = 2;
        int d = 4;

        testDriveMain testDriveMain = new testDriveMain();
        int sumResult = testDriveMain.sum(a, b);
        int sumResult2 = testDriveMain.sum(c, d);
        System.out.println(sumResult);
        System.out.println(sumResult2);

        tmp aSum = new tmp();
        aSum.tmp();
    }

    public /*static*/ Integer sum(Integer firstNum, Integer secondNum) {

        return firstNum + secondNum + e;
    }
}
