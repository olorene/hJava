public class Duck {
    private int size;

    public static void main(String[] args) {
        Duck aDuck = new Duck();
        System.out.println("Размер равен " + aDuck.getSize());
    }

    public void setSize(int s) {
        size = s;
    }

    public int getSize() {
        return size;
    }
}
