package SomeStatic;

public class StaticTest extends StaticSuper {
    static int rand;

    static {
//        rand = (int) (Math.random() * 6);
        rand = 42;
        System.out.println("Статический блок " + rand);
    }

    StaticTest() {
        System.out.println("Конструктор");
    }

    public static void main(String[] args) {
        System.out.println("Внутри Main");
        StaticTest st = new StaticTest();
    }
}
