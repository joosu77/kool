/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 12b
 * Teema: Kahendpuud II
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.lang.Math;

class kodutoo12b {
    public static void main(String[] args){
        System.out.print("Kodutöö nr 11b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");
        
        // Näites toodud esimene puu:
        Tipp juur = new Tipp("A");
        juur.v = new Tipp("B");
        juur.v.v = new Tipp("C");
        juur.v.v.v = new Tipp("D");
        juur.v.v.p = new Tipp("E");
        juur.v.p = new Tipp("F");
        juur.p = new Tipp("G");
        juur.p.v = new Tipp("H");
        juur.p.p = new Tipp("I");
        
        // Esimese puu generaator ning generaatori tulemused välja prinditud:
        Gen_Tasemed gen = new Gen_Tasemed(juur);
        List<Tipp> list;
        while ((list = gen.next()) != null){
            for (Tipp t : list){
                System.out.print(t.info+" ");
            }
            System.out.println();
        }
        // Muudan puu kompaktseks ning prindin uuesti generaatori tulemuse välja:
        juur = looKompaktnePuu(juur);
        gen = new Gen_Tasemed(juur);
        while ((list = gen.next()) != null){
            for (Tipp t : list){
                System.out.print(t.info+" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        
        // Näites toodud teine puu::
        Tipp juur2 = new Tipp("A");
        juur2.v = new Tipp("B");
        juur2.v.v = new Tipp("C");
        juur2.v.v.v = new Tipp("D");
        juur2.v.v.p = new Tipp("E");
        juur2.v.p = new Tipp("F");
        juur2.p = new Tipp("G");
        juur2.p.v = new Tipp("H");
        juur2.p.p = new Tipp("I");
        juur2.p.p.v = new Tipp("J");
        juur2.p.p.v.v = new Tipp("K");
        juur2.p.p.v.p = new Tipp("L");
        juur2.p.p.v.p.v = new Tipp("M");
        juur2.p.p.v.p.p = new Tipp ("N");
        juur2.p.p.p = new Tipp ("O");
        juur2.p.p.p.v = new Tipp("P");
        juur2.p.p.p.p = new Tipp("Q");

        // Teise puu generaator ning generaatori tulemuse printimine:
        Gen_Tasemed gen2 = new Gen_Tasemed(juur2);
        while ((list = gen2.next()) != null){
            for (Tipp t : list){
                System.out.print(t.info+" ");
            }
            System.out.println();
        }
        // Muudan teise puu kompaktseks ning prindin genraatori tulemuse välja
        juur2 = looKompaktnePuu(juur2);
        gen2 = new Gen_Tasemed(juur2);
        while ((list = gen2.next()) != null){
            for (Tipp t : list){
                System.out.print(t.info+" ");
            }
            System.out.println();
        }
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    /**
     * Loob sellise sisseantud puu alampuu, mis on kompaktne ning mille
     * tippude arv on maksimaalne.
     * @param juur Sisseantava puu juur.
     * @return Loodud kompaktne alampuu.
     */
    static Tipp looKompaktnePuu(Tipp juur){
        // Loodava puu juur
        Tipp uusJuur = new Tipp(juur);
        Gen_Tasemed gen = new Gen_Tasemed(juur);
        Gen_Tasemed uusGen = new Gen_Tasemed(uusJuur);
        List<Tipp> tase;
        List<Tipp> eelmineTase = null;
        List<Tipp> uusEelmineTase = null;
        boolean esimene = true;
        int luger = 0;
        while ((tase = gen.next()) != null){
            // Kontrollib, kas käesoleval tasemel on maksimaalne kogus tippe,
            // mis seal olla saab, olemas
            if (tase.size() != (int)Math.pow(2, luger)){
                // Kui ei ole, on see poolik või tühi tase, ehk läbi käimine
                // tuleb lõpetada ning antud kihis tuleb alles jätta tipud, mis on vasakul ääres
                for (int i = eelmineTase.size()-1; i >= 0; i--){
                    // Tippe lisatakse uude puuse vasakult alustades kuni jõutakse esimese lasteta tipuni
                    if (eelmineTase.get(i).v==null){
                        break;
                    } else {
                        uusEelmineTase.get(i).v = new Tipp(eelmineTase.get(i).v);
                        uusEelmineTase.get(i).p = new Tipp(eelmineTase.get(i).p);
                    }
                }
                break;
            }
            // Siia jõutakse vaid juhul kui on teada, et tegu on täiskihiga
            if (!esimene){
                // Lisan vanast puust tipud uude puusse
                for (int i=0;i<uusEelmineTase.size();i++){
                    uusEelmineTase.get(i).v = new Tipp(eelmineTase.get(i).v);
                    uusEelmineTase.get(i).p = new Tipp(eelmineTase.get(i).p);
                }
            } else {
                esimene = false;
            }
            eelmineTase = tase;
            uusEelmineTase = uusGen.next();
            luger++;
        }
        return uusJuur;
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
/*************************************************************
 * Generaator-klass kahendpuu tippude kõigi tasemete
 * genereerimiseks
 *
 * Konstruktorile antakse ette vaadeldav kahendpuu (juur).
 * Meetodi next() tagastustüübiks on ArrayList<Tipp>,
 *         järjekordsel pöördumisel
 *         antakse välja järjekordne tase tippude listina,
 *                   või null, kui tasemeid rohkem ei ole.
 */
class Gen_Tasemed {
    List<Tipp> eelmineTase;
    boolean esimene;
    public Gen_Tasemed (Tipp juur){
        eelmineTase = new ArrayList<Tipp>(List.of(juur));
        esimene = true;
    }
    // Asendatakse list eelmise listi lastega ning väljastatakse see,
    // list sisaldab antud puu käesoleva taseme tippe järjekorras
    // paremalt vasakule.
    public List<Tipp> next(){
        if (!esimene){
            List<Tipp> uusTase = new ArrayList<Tipp>();
            for (Tipp t: eelmineTase) {
                if (t.p != null){
                    uusTase.add(t.p);
                }
                if (t.v != null){
                    uusTase.add(t.v);
                }
            }
            eelmineTase = uusTase;
        } else {
            // Esimesel korral tuleb tagastada juur,
            // mis on juba konstruktoris omistatud listi,
            // seega list pole vaja uuendada.
            esimene = false;
        }
        return ((eelmineTase.size()==0)?(null):(eelmineTase));
    }
}