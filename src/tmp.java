import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class tmp {
    public static void main(String[] args) {
//        Tmp2 tmp2 = new Tmp2();
//        tmp2.data();
//        tmp2.addStringToStringArray();

        HashMap<String, String> hashMap = new HashMap<>();

        hashMap.put( "1", "Chassis 1");
        hashMap.put( "2", "Chassis 1");
        hashMap.put( "3", "Chassis 1");
        hashMap.put( "1", "Chassis 2");
        hashMap.put( "2", "Chassis 2");

        Set set = hashMap.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.println("key is: " + mentry.getKey() + " & Value is: " + mentry.getValue());
        }




    }


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
