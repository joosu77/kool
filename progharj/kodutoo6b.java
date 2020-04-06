/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 6b
 * Teema: Sõned II
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.File; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List; 
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;

class kodutoo6b {
    // List inimeste ja nende aegade hoidmiseks
    static List<Isik> isikud = new ArrayList<Isik>();
    // List vahefiniši kaupa aegade hoidmiseks
    static List< List<Integer> > ajad = new ArrayList< List<Integer> >();
    // Map, kus panna vastama vahefiniši ajad edetabeli kohtadega
    static List< Map<Integer,Integer> > kohad = new ArrayList< Map<Integer,Integer> >();
        
    public static void main(String[] args) throws Exception{
        // Täidan aegade listi listidega
        for (int i = 0; i < 7; i++){
            List<Integer> aegadetulp = new ArrayList<Integer>();
            ajad.add(aegadetulp);
        }
        // Täidan kohtade listi mapidega
        for (int i = 0; i < 7; i++){
            Map<Integer, Integer> kohtadetulp = new HashMap<Integer, Integer>();
            kohad.add(kohtadetulp);
        }
        // Loen andmete failist andmed sisse ning kirjutan isikute ja aegade listidesse
        loeSekundid();
        // Sorteerin aegade listid
        for (int i=0;i<6;i++){
            ajad.get(i).sort((Integer i1, Integer i2) -> i1-i2);
        }
        // Täidan kohtade mapi
        taidaMap();
        // Prindin tulemused faili
        prindiIsikud();
    }
    
    /**
     * Loeb andmed failist "andmed.txt" sisse ning täidab
     * nendega isikute ja aegade listid
     */
    static void loeSekundid() throws Exception{
        File file = new File("andmed.txt"); 
        Scanner sc = new Scanner(file);
  
        while (sc.hasNextLine()){
            String rida = sc.nextLine();
            Isik isik = new Isik(rida);
            isikud.add(isik);
            
            for (int i = 0; i < 7; i++){
                ajad.get(i).add(isik.ajad[i]);
            }
        }
    }
    
    /**
     * Käin läbi aegade listid ning täidan
     * nende põhjal edetabeli kohtade mapid
     */
    static void taidaMap() {
        for (int i = 0; i < 7; i++){
            int luger = 1;
            int eelmineNum = -1;
            for (Integer num : ajad.get(i)){
                // Vaatan et ei oleks sama aeg nagu eelmine
                if (eelmineNum != num){
                    kohad.get(i).put(num, luger);
                }
                eelmineNum = num;
                luger++;
            }
        }
    }
    
    /**
     * Käib läbi isikute listi, vaatab kohtade mapidest isiku
     * aegadele vastavad edetabeli kohad ning prindib need faili "tulemused.txt"
     */
    static void prindiIsikud() throws Exception{
        FileWriter kirjutaja = new FileWriter("tulemus.txt");

        for (Isik isik : isikud){
            kirjutaja.write(isik.nimi);
            for (int i=0;i<7;i++){
                kirjutaja.write(" ");
                kirjutaja.write(kohad.get(i).get(isik.ajad[i]).toString());
            }
            kirjutaja.write("\n");
        }
        kirjutaja.close();
    }
}

/**
 * Klass, millele saab anda andmete faili rea ning sealt loetakse sisse real
 * olev isiku nimi ja vahefinišite ajad ning teisendatakse need sekunditesse
 */
class Isik {
    public String nimi;
    public int[] ajad = new int[7];
    
    public Isik (String sisend){
        String[] sisendid = sisend.split(",");
        nimi = sisendid[0];
        for (int i = 1; i < 8; i++){
            // Panen tunnid, minutid ja sekundid eraldi stringidesse
            String[] tms = sisendid[i].split(":");
            // Eemaldan lõplikust ajast sekundite tagant kümnendikud
            if (tms[2].indexOf(".") != -1){
                tms[2] = (tms[2].split("\\."))[0];
            }
            ajad[i-1] = Integer.parseInt(tms[0]) * 3600 + Integer.parseInt(tms[1]) * 60 + Integer.parseInt(tms[2]);
        }
    }
}