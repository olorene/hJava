import java.util.ArrayList;

public class tmp {
    public static void main(String[] args) {
        int number = 20;
        switch (number) {
            case 10:
                System.out.println(10);
                break;
            case 20:
                System.out.println(20);
                break;
            case 30:
                System.out.println(30);
                break;
            default:
                System.out.println("Not in 10 to 30");
        }

        char ch = 'o';
        switch (ch) {
            case 'a':
                System.out.println("Vowel");
                break;
            case 'b':
                System.out.println("Vowel");
                break;
            case 'o':
                System.out.println("Vowel");
                break;
            default:
                System.out.println("Constant");
        }

        String level = "Expert";
        switch (level) {
            case "Juniour":
                System.out.println("Juniour");
                break;
            case "Middle":
                System.out.println("Middle");
                break;
            case "Expert":
                System.out.println("Expert");
                break;
        }
    }

}
