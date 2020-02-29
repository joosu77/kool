import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

class kodutoo {
    public static void main (String[] args) {
        // Leian kõik algarvud kuni 1000000ni:
        HashSet<Integer> algarvud = algarvuS6el();
        
        TreeSet<Integer> tset = new TreeSet<Integer>(algarvud);
        
        Iterator<Integer> iteraator = tset.iterator();
        while (iteraator.hasNext()) {
            System.out.println(iteraator.next());
        }
    }
    
    private static HashSet<Integer> algarvuS6el () {
        // Algväärtustan massiivi ühtedega:
        boolean[] arvud = new boolean [1000000];
        for (int i = 0; i < 1000000; i++) {
            arvud[i] = true;
        }
        
        // Tuvastan Eratosthenese sõela meetodiga algarvud
        // * ning sisestan need räsi hulka.
        // Kuna arvud on algarvud kui nende kordsete hulgas on mõni ühest suurem
        // * ja arvust endast väiksem arv, pole mõtet ühe ja nulli kordsetega
        // * arvestada, seega vastab massiivi esimene element numbrile 2.
        HashSet<Integer> algarvud = new HashSet<Integer>();
        
        for (int i = 0; i < 1000000; i++) {
            if (arvud[i]) {
                for (int j = i * 2 + 2; j < 1000000; j += i + 2) {
                    arvud[j] = false;
                }
                algarvud.add(i+2);
            }
        }
        return algarvud;
    }
    
    private static int[] algarvuRingideLeidja (HashSet<Integer> algarvud) {
        Iterator<Integer> iteraator = algarvud.iterator();
        while (iteraator.hasNext()) {
            arv = iteraator.next();
            
        }
    }
}