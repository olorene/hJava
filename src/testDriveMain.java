public class testDriveMain {
    private static int a = 1;
    private static int b;

    public static void main(String[] args) {
        testDriveMain testDriveMain = new testDriveMain();
        int sumResult = testDriveMain.sum(a, b);
        System.out.println(sumResult);
    }

    public Integer sum(Integer firstNum, Integer secondNum) {
        return firstNum + secondNum;
    }
}
