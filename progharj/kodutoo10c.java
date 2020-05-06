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
    
    // Antud: ss -- erinevate väiketäht-sõnade järjend
    // Tulemus: pikim järjendi ss mingi osajärjendi permuatatsioon tt,
    // 		mille korral
    // 	   	tt[i] = <tt[i-1] ilma viimase täheta>
    // 	   või
    // 		tt[i] = <tt[i-1] + üks täht (a|b|c|...|z)>
    //     või
    // 		tt[i] = <tt[i-1] lähisõna> [lähisõna - vt Koduülesanne nr 8b]
    //     (i = 1, 2, ..., n-1)
    static String[] jadaLeidjaAlgataja(String[] ss){
        List<String> tulemus = null;
        // Alustan rekursiivset ahela otsimist igast sõnast:
        for (int i = 0; i < ss.length; i++){
            List<String> saak = leiaJada(new ArrayList<String>(List.of(ss)), new ArrayList<String>(List.of(ss[i])), new HashSet<String>(Set.of(ss[i])));
            if (tulemus == null || tulemus.size() < saak.size()){
                tulemus = saak;
            }
            
        }
        
        return tulemus.toArray(new String[tulemus.size()]);
    }
    
    /**
     * Leiab varem seletatud reeglite põhjal sõnade ahela, kus ahela algus on
     * antud argumendis olemas ning edasise ahela ehitamiseks kasutatakse listi
     * ss liikmeid, mis ei esine hulgas kasutatud.
     *
     * @param ss Olemasolevate sõnade kogum.
     * @param olemas Etteantud ahela algus.
     * @param kasutatud Olemasolevate sõnade hulgast need sõnad, mis on juba
     *        etteantud ahela alguses olemas.
     */
    static List<String> leiaJada (List<String> ss, List<String> olemas, Set<String> kasutatud){
        // Eelmine sõna olemasolevas ahelas
        String eelmine = olemas.get(olemas.size()-1);
        int eelPikkus = eelmine.length();
        // Sõna, mida proovitakse panna järgmiseks ahelas, sellele
        // omistatakse iteraatori väärtuseid tsüklis
        String vaadeldav;
        // See asendatakse rekursiivselt kutsutud funktsioonist saadud ahelaga, kui see on pikem
        List<String> tulemus = new ArrayList<String>(olemas);
        // Käin kõik võimalikud sõnad läbi et kontrollida, kas need sobivad ahelas järgmiseks
        for (Iterator<String> iter = ss.iterator(); iter.hasNext();){
            vaadeldav = iter.next();
            if (!kasutatud.contains(vaadeldav)){
                if (eelPikkus == vaadeldav.length()){
                    // Sama pikkade sõnade puhul kontrollitakse, kas on tegemist lähisõnadega
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
                    // Kui järgmine sõna on 1 võrra lühem, kontrollitakse, kas see sõna tekib,
                    // kui eelmisest viimane täht kustutada
                    if (eelmine.substring(0, eelPikkus-1).equals(vaadeldav)){
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
                    // Kui järgmine sõna on 1 võrra pikem kontrollitakse, kas see sõna tekib
                    // eelmisele ühe tähe lisamisel
                    if (vaadeldav.substring(0, vaadeldav.length()-1).equals(eelmine)){
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
    
    /**
     * Koostab iga sõna jaoks kõik selle sõna lähisõnad võimalike sõnade hulgast
     * ning salvestab need globaalsesse kaarti lahiSonad.
     * 
     * @param ss Kogum sõnu, mille seast lähisõnu otsida.
     */
    static void leiaLahisonad (String[] ss) {
        // Tühjendan lähisonade kogumi
        lahiSonad = new HashMap<String, Set<String> >();
        // Teen sisendsõnadest hulka, et kiiremini sõnu leida
        Set<String> sisendHulk = Set.of(ss);
        // Käin läbi sõnu, et leida neile lähissõnu
        for (int i = 0; i < ss.length; i++){
            String vaadeldav = ss[i];
            lahiSonad.put(vaadeldav, new HashSet<String>());
            // Käin läbi sõna tähed, et neid asendada
            for (int j = 0; j < vaadeldav.length(); j++){
                // Proovin kõiki tähti asemele asendada
                for (int o = 0; o < abc.length(); o++){
                    String lahisona = vaadeldav.substring(0,j)+abc.charAt(o)+vaadeldav.substring(j+1);
                    // Kui sõna leidub, lisan kogumikku
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