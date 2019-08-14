import java.util.Calendar;
import java.util.Locale;

public class HelloWorld {
    private int a = 10;

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        System.out.println(calendar.getTime());
        calendar.set(2014, 7, 31, 15, 40, 00);
        System.out.println(calendar.getTime());
        long day = calendar.getTimeInMillis();
        System.out.println(day);
        day += 1000 * 60 * 60;
        calendar.setTimeInMillis(day);
        System.out.println(calendar.getTime());

        calendar.add(calendar.DATE, 36);
        System.out.println(calendar.getTime());

        calendar.roll(calendar.DATE, 35);
        System.out.println(calendar.getTime());

        calendar.set(calendar.DATE, 1);
        calendar.set(calendar.YEAR, 1999);
        System.out.println(calendar.getTime());
        Locale locale = Locale.ENGLISH;
        System.out.println(calendar.getDisplayName(1, 2, locale ));





    }
}
