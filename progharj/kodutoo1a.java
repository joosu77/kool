/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1b
 * Teema: Tsüklid I
 *
 * Autor: Joosep Näks
 ******************************************************************************/

import java.util.HashSet;

class kodutoo1a {
    public static void main (String[] args) {
        // Leian kõik algarvud kuni 1000000ni:
        HashSet<Integer> algarvud = algarvuS6el ();
        
    }
    
    static int[] leiaRingid (int piirarv, HashSet<Integer> algarvud) {
        int[] algarvuringide
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
    
    static void pooraArvu (int sisend) {
        int viimaneNumber = sisend%10;
        int tempSisend = sisend;
        while (tempSisend!=0) {
            viimaneNumber *= 10;
            tempSisend /= 10;
        }
        viimaneNumber /= 10;
        int valjund = sisend/10+viimaneNumber;
        System.out.println("Pooratud arv on " + valjund);
    }
}