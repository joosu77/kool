/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 7b-1
 * Teema: Rekursioon
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;

class kodutoo7b1 {
    public static void main(String[] args) throws Exception{
        System.out.print("Kodutöö nr 7b-1.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");

        System.out.println("n=8 puhul leian sellised summad:");
        System.out.printf("Neid oli %d\n\n", prindiSummad(new ArrayList<Integer>(), 0, 8));
        
        System.out.println("n=14 puhul leian sellised summad:");
        System.out.printf("Neid oli %d\n\n", prindiSummad(new ArrayList<Integer>(), 0, 14));
        
        System.out.println("n=20 puhul leian sellised summad:");
        System.out.printf("Neid oli %d\n\n", prindiSummad(new ArrayList<Integer>(), 0, 20));
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    /**
     * Leiab rekursiivselt kõik võimalused arvudest 2, 4 ja 6 kindla summa
     * saamiseks nii, et sama liidetavat et tuleks kaks korda järjest.
     *
     * @param olemas List liidetavatest, mis on juba ritta pandud.
     * @param olemasSumma Olemasolevate liidetavate summa.
     * @param summa Arv, mis tahetakse saavutada numbrite summeerimisel.
     * @return Antud liidetavate jada algusega erinevate sobivate liidetavate jadade kogus.
     */
    static int prindiSummad (List<Integer> olemas, int olemasSumma, int summa) {
        // Kui summad on võrdsed, olen leidnud ühe võimaliku summa
        if (olemasSumma == summa){
            // Prindin selle välja
            System.out.println(olemas.toString());
            return 1;
        } else {
            int kogus = 0;
            // Proovin liita 2, 4 ja 6 nii kaua, kuni liidetavate summa ei ületa antud summat
            for (int i = 2; i <= summa - olemasSumma && i < 7; i += 2){
                // Kontrollin et liidetav ei oleks eelmisega võrdne
                if (olemas.size() == 0 || i != olemas.get(olemas.size() - 1)){
                    olemas.add(i);
                    kogus += prindiSummad (olemas, olemasSumma + i, summa);
                    olemas.remove(olemas.size() - 1);
                }
            }
            return kogus;
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