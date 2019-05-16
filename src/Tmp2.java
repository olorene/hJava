import java.util.ArrayList;
import java.util.Date;

public class Tmp2 {
    public void first(String str) {
        System.out.println(str);
    }
    public void data(){
        Date date = new Date();
        System.out.println(date);
    }

    public void addStringToStringArray() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("One");
        arrayList.add("Two");

        String[] stringsArray = new String[arrayList.size()];
        stringsArray = arrayList.toArray(stringsArray);

        for (int i = 0; i < stringsArray.length; i++) {
            System.out.println(stringsArray[i]);
        }
    }
}
