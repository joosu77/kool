import java.util.Comparator;

public class NimeVõrdleja implements Comparator<String> {
    public int compare(String s1, String s2) {
        return s2.compareTo(s1);
    /*
        if (s1.length()<s2.length()){
            return -1;
        } else if (s1.length()>s2.length()){
            return 1;
        } else {
            s1.compareTo()
        }
        */
    }
}
