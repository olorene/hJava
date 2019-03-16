import com.sun.xml.internal.ws.api.ha.StickyFeature;

public class tmp {
    public void tmpMethod() {
        String[] strings = new String[]{"Заклинания", "Невидемость"};
        String[] result = new String[strings.length + 1];

        print_String_Array(strings);

        for (int i = 0; i < strings.length; i++) {
            result[i] = strings[i];
        }
        result[strings.length] = "Щит";
        System.out.println(strings.length);
        System.out.println(result.length);

        print_String_Array(result);

    }

    private void print_String_Array(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
        System.out.println("----------");
    }
}
