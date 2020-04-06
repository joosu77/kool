/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 7b-2
 * Teema: Rekursioon
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;

class kodutoo7b2 {
    public static void main(String[] args) throws Exception{
        System.out.print("Kodutöö nr 7b-2.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");

        System.out.println("n=4 puhul leian sellised rongid:");
        System.out.printf("Neid oli %d.\n\n", prindiRongid(new StringBuilder(4), 4));
        
        System.out.println("n=10 puhul leian sellised rongid:");
        System.out.printf("Neid oli %d\n\n", prindiRongid(new StringBuilder(10), 10));
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    /**
     * Prindib rekursiivselt välja kõik võimalikun n pikkused rongid,
     * kus vagunid on paigutatud vastavalt reeglitele, et C- ja B-vagun
     * ei tohi üksteise järel olla, D-vagun ei tohi A-vaguni järel olla
     * ning sama tüüpi vaguneid ei tohi mitu tükki järjest olla.
     *
     * @param olemas List vagunitest, mis on juba ritta pandud.
     * @param pikkus Arv, tahetava rongi pikkus.
     * @return Antud vagunite jada algusega erinevate sobivate rongide kogus.
     */
    static int prindiRongid (StringBuilder olemas, int pikkus) {
        // Kui pikkused on võrdsed, olen leidnud ühe võimaliku rongi
        if (olemas.length() == pikkus){
            // Prindin selle välja
            System.out.println(olemas);
            return 1;
        } else {
            int kogus = 0;
            if (olemas.length() == 0){
                // Juhul kui eelnevat vagunit pole, sobib ükskõik milline vagun
                kogus += lisaCharid(new char[]{'A','B','C','D'}, olemas, pikkus);
            } else {
                // Vastavalt eelnevale vagunile valitakse sobilike vagunite komplekt
                char eelmine = olemas.charAt(olemas.length() - 1);
                if (eelmine == 'C' || eelmine == 'B'){
                    kogus += lisaCharid (new char[]{'A','D'}, olemas, pikkus);
                } else if (eelmine == 'A'){
                    kogus += lisaCharid (new char[]{'B','C'}, olemas, pikkus);
                } else if (eelmine == 'D'){
                    kogus += lisaCharid (new char[]{'A', 'B','C'}, olemas, pikkus);
                }
            }
            return kogus;
        }
    }
    
    /**
     * Võtab kogumist järjest iga vaguni, lisab selle rongile, kutsub prindiRongid
     * välja, ning seejärel eemaldav selle vaguni.
     *
     * @param kogum Massiiv sobivatest vagunitest, mida saab järgmiseks valida.
     * @param olemas List vagunitest, mis on juba ritta pandud.
     * @param pikkus Nõutav rongi kogupikkus.
     */
    static int lisaCharid (char[] kogum, StringBuilder olemas, int pikkus) {
        int kogus = 0;
        for (char c : kogum){
            olemas.append(c);
            kogus += prindiRongid (olemas, pikkus);
            olemas.deleteCharAt(olemas.length() - 1);
        }
        return kogus;
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