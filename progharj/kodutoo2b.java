/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 2b
 * Teema: Tsüklid II
 *
 * Autor: Joosep Näks
 ******************************************************************************/

import java.lang.Math;
 
class kodutoo2b {
    public static void main (String[] args) {
        System.out.print("Kodutöö nr 2b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");
        
        n(0.1);
        n(0.01);
        n(0.001);
        n(0.0001);
        n(0.00001);
        n(0.000001);
        
        prindiString("=", 84);
        System.out.print("Joosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
    }
    
    /**
     * Arvutab Funktsiooni x^2+2x+2 väärtuse mingil x kohal.
     * 
     * @param x Sisendparameeter.
     * @return Funktsiooni väärtus valitud sisendparameetril.
     */
     static double f (double x) {
         return x*x+2*x+2;
     }
     
     /**
      *	Leiab funktsiooni f Riemanni summa nii, 
      * et funktsioon läbib ristkülikute
      * vaadeldavate servade keskkohti
      *
      * @param n Osalõikude kogus, mitmeks lõik [0,10] jagatakse.
      * @return Arvutatud riemanni summa. 
      */
     static double sumRiemann (int n) {
         // Muutuja kogu pindala leidmiseks
         double summa = 0;
         for (int i = 0; i < n; i++) {
             // Liidame järgmise ristküliku pindala
             summa += f((double)(i + 0.5) * 10 / n);
         }
         return 10 * summa / n;
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
    
    /**
     * Leiab funktsiooni f riemanni summa nii, et lõik [0,10]
     * oleks jagatud täpselt nii mitmeks osalõiguks, et
     * selle osalõikudega saadud summa ja ühe võrra vähemate
     * osalõikudega saadud summa vahe oleks ülimalt eps.
     *
     * @param eps Maksimaalne järjestikkude riemanni summade
     *  vahe mille puhul lõpetatakse arvutamine. 
     * @return Arvutatud riemanni summa väärtus.
     */
    static double n (double eps){
        int osaloike = 1;
        // Summa eelmise osaloikude koguse puhul:
        double eelmine = 0;
        // Leitud summa:
        double uus = sumRiemann(osaloike);
        // Lipp, mis on esimesel tsükliringil püsti et
        // näidata, et sel ringil pole vaja summasid võrrelda:
        boolean esimene = true;
        while (esimene || Math.abs(eelmine - uus) > eps){
            // Lisan ühe osalõigu:
            osaloike++;
            eelmine = uus;
            // Leian uue summa:
            uus = sumRiemann(osaloike);
            if (esimene)
                esimene = false;
        }
        System.out.printf("sumRiemann(%d) = %f\n", osaloike - 1, eelmine);
        System.out.printf("sumRiemann(%d) = %f\n", osaloike, uus);
         
        System.out.printf("Täpsus eps = %f saavutati [0; 10] jaotamisel %d osalõiguks\n\n", eps, osaloike);
        return uus;
    }
}
