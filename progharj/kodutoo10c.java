/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 10c
 * Teema: Rekursioon IV
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.lang.Math;

class kodutoo10c {
    static String abc = "abcdefghijklmnopqrstuvwxyz";
    static Map< String, Set<String> > lahiSonad;
    public static void main(String[] args) throws Exception{
        System.out.print("Kodutöö nr 10c.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");
        
        String[][] sisendid = {{"hiir", "kass", "pais", "paisk", "pass", "piisk", "viir", "viis", "viisk"},
                             {"hiir", "kass", "pais", "paisk", "pass", "piisk", "kiir", "viir", "viis", "viisk", "raisk", "kask"}};
        for (String[] sisend : sisendid){
            leiaLahisonad(sisend);
            String[] tulemus = jadaLeidjaAlgataja(sisend);
            System.out.printf("Antud sõnad: %s\nLeitud tulemus: ", Arrays.toString(sisend));
            for (int i = 0; i < tulemus.length; i++){
                System.out.print(tulemus[i]);
                if (i != tulemus.length-1){
                    System.out.print("->");
                } else {
                    System.out.println("\n");
                }
            }
        }
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    static String[] jadaLeidjaAlgataja(String[] ss){
        List<String> tulemus = null;
        for (int i = 0; i < ss.length; i++){
            List<String> saak = leiaJada(new ArrayList<String>(List.of(ss)), new ArrayList<String>(List.of(ss[i])), new HashSet<String>(Set.of(ss[i])));
            if (tulemus == null || tulemus.size() < saak.size()){
                tulemus = saak;
            }
            
        }
        
        return tulemus.toArray(new String[tulemus.size()]);
    }
    
    static List<String> leiaJada (List<String> ss, List<String> olemas, Set<String> kasutatud){
        String eelmine = olemas.get(olemas.size()-1);
        int eelPikkus = eelmine.length();
        String vaadeldav;
        List<String> tulemus = new ArrayList<String>(olemas);
        for (Iterator<String> iter = ss.iterator(); iter.hasNext();){
            vaadeldav = iter.next();
            if (!kasutatud.contains(vaadeldav)){
                if (eelPikkus == vaadeldav.length()){
                    if (lahiSonad.get(eelmine).contains(vaadeldav)){
                        olemas.add(vaadeldav);
                        kasutatud.add(vaadeldav);
                        List<String> saak = leiaJada(ss, olemas, kasutatud);
                        if (tulemus == null || tulemus.size() < saak.size()){
                            tulemus = saak;
                        }
                        kasutatud.remove(vaadeldav);
                        olemas.remove(olemas.size()-1);
                    }
                } else if (eelPikkus == vaadeldav.length()+1){
                    if (eelmine.substring(0,eelPikkus-1).equals(vaadeldav)){
                        olemas.add(vaadeldav);
                        kasutatud.add(vaadeldav);
                        List<String> saak = leiaJada(ss, olemas, kasutatud);
                        if (tulemus == null || tulemus.size() < saak.size()){
                            tulemus = saak;
                        }
                        kasutatud.remove(vaadeldav);
                        olemas.remove(olemas.size()-1);
                    }
                } else if (eelPikkus+1 == vaadeldav.length()){
                    if (vaadeldav.substring(0,vaadeldav.length()-1).equals(eelmine)){
                        olemas.add(vaadeldav);
                        kasutatud.add(vaadeldav);
                        List<String> saak = leiaJada(ss, olemas, kasutatud);
                        if (tulemus == null || tulemus.size() < saak.size()){
                            tulemus = saak;
                        }
                        kasutatud.remove(vaadeldav);
                        olemas.remove(olemas.size()-1);
                    }
                }
            }
        }
        
        return tulemus;
    }
    
    static void leiaLahisonad (String[] ss) {
        lahiSonad = new HashMap<String, Set<String> >();
        Set<String> sisendHulk = Set.of(ss);
        for (int i = 0; i < ss.length; i++){
            String vaadeldav = ss[i];
            lahiSonad.put(vaadeldav, new HashSet<String>());
            // Käin läbi vaadeldava taseme kõik sõnad
            for (int j = 0; j < vaadeldav.length(); j++){
                // Käin läbi vaadeldava sõna kõik tähed et neid välja vahetada
                for (int o = 0; o < abc.length(); o++){
                    String lahisona = vaadeldav.substring(0,j)+abc.charAt(o)+vaadeldav.substring(j+1);
                    // Kontrollin, kas saadud sõna on sõnastikus
                    if (sisendHulk.contains(lahisona) && !lahisona.equals(vaadeldav)){
                        lahiSonad.get(vaadeldav).add(lahisona);
                    }
                }
            }
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