/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 10b
 * Teema: Rekursioon IV
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.lang.Math;

class kodutoo10b {
    static Set<String> osaSoned = new HashSet<String>();
    public static void main(String[] args) throws Exception{
        System.out.print("Kodutöö nr 8b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");
        
        String[][] sisendid = {{"kaer", "aekarekae"},{"oekorekror", "koerte"}};
        for (String[] paar : sisendid){
            osaSoned = new HashSet<String>();
            leiaOsaSoned(paar[0], paar[1], 0, 0, new StringBuilder());
            System.out.printf("Antud sõned: a = %s, b = %s\nNende ühised osasõned c:\n%s\n\n", paar[0], paar[1], osaSoned.toString());
        }
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    /**
     * Leiab kõik sellised sõned, mille algus on sisendist olemas võetud, ning edasine on saadud
     * sisendsõnede s1 ja s2 vastavatest alamsõnedest cur1 kuni lõpp ja cur2 kuni lõpp mingite
     * tähtede kustutamisel ning sisestab pikimad nendest globaalsesse hullk osaSoned.
     * @param s1 Esimene sisendsõne.
     * @param s2 Teine sisendsõne.
     * @param cur1 Hetkeasukoht esimeses sisendsõnes.
     * @param cur2 Hetkeasukoht teises sisendsõnes.
     * @param olemas Leitava osa sõne algus.
     */
    static void leiaOsaSoned(String s1, String s2, int cur1, int cur2, StringBuilder olemas){
        boolean liiguti = false;
        if (s1.charAt(cur1) == s2.charAt(cur2)){
            // Kui vaadeldavad tähed on samad, lisatakse see täht vastusesse ning liigutakse
            // mõlemas sisendsõnes edasi, kuna neid tähti ei saa enam kasutada.
            olemas.append(s1.charAt(cur1));
            cur1++;
            cur2++;
            liiguti = true;
        }
        // Kui mõlemas sõnes ollaks viimase tähe juures või ühes sõnes ollakse sõnest välja jõudnud,
        // lõpetatakse ahel.
        if (cur1 == s1.length()-1 && cur2 == s2.length()-1 || cur1 == s1.length() || cur2 == s2.length()){
            if (osaSoned.size() == 0){
                osaSoned.add(olemas.toString());
            } else if (osaSoned.iterator().next().length() < olemas.length()){
                // OsaSonedes olevad osasõned on lühemad kui saadud osasõne seega kustutatakse
                // osaSoned tühjaks ja pannakse uus vastus asemele.
                osaSoned = new HashSet<String>(Set.of(olemas.toString()));
            } else if (osaSoned.iterator().next().length() == olemas.length()){
                osaSoned.add(olemas.toString());
            }
        } else if (liiguti){
            // Kui juba liiguti edasi, pole vaja uuesti liikuda enne järgmist rekursiivsammu
            leiaOsaSoned(s1, s2, cur1, cur2, olemas);
        } else {
            if (cur1 < s1.length()-1){
                leiaOsaSoned(s1, s2, cur1+1, cur2, olemas);
            }
            if (cur2 < s2.length()-1){
                leiaOsaSoned(s1, s2, cur1, cur2+1, olemas);
            }
        }
        if (liiguti){
            // Kui lisati tulemusse uus täht eemaldatakse see enne eelmisesse
            // rekursiivsustasemesse naasmist.
            olemas.setLength(olemas.length()-1);
        }
    }
    
    
    /**
     * Prindib mingit sõne mitu korda välja.
     * 
     * @param prinditav Antud sõne.
     * @param kogus Kogus, mitu korda sõne prinditakse.
     */
    static void prindiString (String prinditav, int kogus) {
        for (int i = 0; i < kogus; i++) {
            System.out.print(prinditav);
        }
    }
}