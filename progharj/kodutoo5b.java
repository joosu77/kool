/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 5b
 * Teema: Sõned I
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;

class kodutoo5b {
    public static void main(String[] args) throws Exception{
        System.out.print("Kodutöö nr 5b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");


        System.out.println("Sagedasemad 5-tähelised sõnad tekstifailis Kõrboja_sisu_puhastekst.txt:");
        List<paar> tulem = otsiSonu("Kõrboja_sisu_puhastekst.txt");
        
        for (paar p : tulem){
            System.out.printf("%d korda: %s\n",p.kogus,p.sona);
        }
        
        prindiString("-", 84);
        System.out.println();
        
        System.out.println("Sagedasemad 5-tähelised sõnad tekstifailis KuldninagaMees_est.txt:");
        List<paar> tulem2 = otsiSonu("KuldninagaMees_est.txt");
        
        for (paar p : tulem2){
            System.out.printf("%d korda: %s\n",p.kogus,p.sona);
        }


        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    /**
     * Leiab 10 kõige tihemini esinevad sõna antud tekstifailis, kui on sama
     * tähtedearvuga mitu sõna viigis, on sõnad leksikograafilises järjestuses.
     *
     * @param failitee Faili tee, kust sõnu otsitakse.
     * @return List kümnest kõige tihedamini esinevast sõnast paari kujul.
     */
    static List<paar> otsiSonu(String failitee) throws Exception {
        // Hoiab iga sõna kordustearvu failis
        HashMap<String,suurenevInt> sonadeKogused = new HashMap<String,suurenevInt>();
        // Hoiab neid sõnu, mis on vähemalt üks kord esinenud väikse algustähega
        Set<String> mitteNimed = new TreeSet<String>();
        
        // Loon scanneri, mis käib faili läbi
        File fail = new File(failitee);
        Scanner sc = new Scanner(Paths.get(failitee), "Cp1257");
        sc.useDelimiter("\\PL+");
        while (sc.hasNext()){
            String sona = sc.next();
            // Kontrollib et ei oleks tühi sõne
            if (!sona.equals("")){
                // Kontrollib et pikkus oleks vähemalt 5
                if (sona.length()>4){
                    if (sonadeKogused.containsKey(sona)){
                        sonadeKogused.get(sona).suurene();
                    } else {
                        sonadeKogused.put(sona,new suurenevInt(1));
                    }
                    if (Character.isLowerCase(sona.charAt(0))){
                        mitteNimed.add(sona);
                    }
                }
            }
        }
        
        List<paar> esiKymme = new ArrayList<paar>();
        // Käin läbi sõnad, mida esineb vähemalt kord väikse tähega
        for (String sona : mitteNimed){
            // Lisan esikümnesse uue sõna
            esiKymme.add(new paar(sona, sonadeKogused.get(sona).get()));
            // Sorteeris esikümne ära
            esiKymme.sort((paar p1, paar p2)->p2.kogus-p1.kogus);
            // Eemaldan viimase sõna kui sõnu on üle 10
            if (esiKymme.size()>10){
                esiKymme.remove(esiKymme.size()-1);
            }
        }
        return esiKymme;
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

/**
 * Andmestruktuur, mis sisaldab sõna ja sellele vastavat,
 * mis näitab, mitu korda sõna esineb failis
 */
class paar {
    String sona;
    int kogus;
    paar (String sona, int kogus){
        this.sona = sona;
        this.kogus = kogus;
    }
}

/**
 * Andmestruktuur, millega saab Integer tüüpi muutujat
 * mapi sees suurendada ühe võrra
 */
class suurenevInt {
    int val;
    suurenevInt(int val){
        this.val = val;
    }
    void suurene(){
        val++;
    }
    int get(){
        return val;
    }
}