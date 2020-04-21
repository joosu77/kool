/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 8b
 * Teema: Rekursioon II
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;

class kodutoo8b {
    // Siia loen sõnastik.txt sisu sisse
    static Set<String> sonastik = new HashSet<String>();
    // See on kogu tähestik, millest sõnad koosnevad
    static String abc = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) throws Exception{
        System.out.print("Kodutöö nr 8b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");

        String ls = "salm";
        String ss = "riim";
        loeSonastik();
        
        System.out.printf("Lähtesõna (ls): %s\n\nSõnastik (H0):\n%s\n\nSihtsõna (ss, sõnastikust): %s\n\nÜldistatud tuletuskäik:\n", ls, sonastik.toString(), ss);
        
        List< Set<String> > yldLahend = leiaYldLahend(ls, ss);
        Iterator< Set<String> > iter = yldLahend.iterator();
        while (iter.hasNext()) {
            System.out.print(iter.next().toString());
            if (iter.hasNext()) System.out.print("->");
        }
        System.out.println("\n\nTuletuskäigud:");
        tuletusKaigud(new ArrayList<String>(List.of(ls)), yldLahend);
        System.out.println();
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    /**
     * Loeb sisse failist sõnastik.txt kõik sõnad, mida on lubatud kasutada ning salvestab need
     * globaalsesse hulka sonastik.
     */
    static void loeSonastik() throws Exception{
        File fail = new File("sõnastik.txt");
        Scanner sc = new Scanner(fail, "Cp1257");
        
        while (sc.hasNextLine()){
            String rida = sc.nextLine();
            if (!rida.isBlank()){
                sonastik.add(rida);
            }
        }
    }
    
    /**
     * Leiab kõik teed lähtesõnast sihtsõnani kasutades sonastikus antud sõnu ning liikudes
     * edasi vaid lähisõande kaudu.
     *
     * @param ls Lähtesõna, millest alustatakse otsingut.
     * @param ss Sihtsõna, milleni tahetakse jõuda.
     * @return List hulkadest, kus iga hulk vastab järgnevas sammus lähtesõnast sihtsõnani
     *         ning hulgad sisaldavad parajasti nii palju sõnu, et nendest saaks moodustada
     *         kõik lähisõnade ahelad lähtesõnast sihtsõnani.
     */
    static List< Set<String> > leiaYldLahend (String ls, String ss) {
        // Sisaldab sõnu, mis on juba ahelas olemas, neid pole uuesti vaja vaadata kuna
        // nendeni on juba lühem tee leitud.
        Set<String> vaadeldud = new HashSet<String>();
        List< Set<String> > yldLahend = new ArrayList< Set<String> >();
        yldLahend.add(new HashSet<String>(Set.of(ls)));
        
        int vaadeldav = 0;
        boolean lopp = false;
        // Siin tsüklis alustatakse lähtesõnast ja igal järgmisel tasemel on kõigi eelmise
        // taseme sõnade kõik lähtesõnad
        while (!lopp){
            yldLahend.add(new HashSet<String>());
            // Käin läbi vaadeldava taseme kõik sõnad
            for (String s : yldLahend.get(vaadeldav)){
                // Käin läbi vaadeldava sõna kõik tähed et neid välja vahetada
                for (int i = 0; i < s.length(); i++){
                    // Käin läbi kogu tähestiku et proovida igat tähte sõnasse asendada
                    for (int j = 0; j < abc.length(); j++){
                        String lahisona = s.substring(0,i)+abc.charAt(j)+s.substring(i+1);
                        // Kontrollin, kas saadud sõna on sõnastikus
                        if (sonastik.contains(lahisona) && !vaadeldud.contains(lahisona)){
                            vaadeldud.add(lahisona);
                            yldLahend.get(vaadeldav+1).add(lahisona);
                            if (lahisona.equals(ss)){
                                lopp = true;
                            }
                        }
                    }
                }
            }
            vaadeldav++;
        }
        Iterator<String> iteraator = yldLahend.get(vaadeldav).iterator();
        while (iteraator.hasNext()){
            if (!iteraator.next().equals(ss)){
                iteraator.remove();
            }
        }
        // Käin saadud tasemed tagurpidi läbi ja eemaldan kõik sõnad, mis ei vii sihtsõnani
        for (int v = vaadeldav - 1; v > 0; v--){
            // Käin läbi kõik selle taseme sõnad
            Iterator<String> iter = yldLahend.get(v).iterator();
            while (iter.hasNext()){
                String s = iter.next();
                boolean onLahisona = false;
                // Proovin, kas mõni selle sõna lähisõna leidub ülemises tasemes
                for (int i=0; i<s.length(); i++){
                    for (int j=0; j<abc.length(); j++){
                        String lahisona = s.substring(0,i)+abc.charAt(j)+s.substring(i+1);
                        if (yldLahend.get(v+1).contains(lahisona)){
                            onLahisona = true;
                        }
                    }
                }
                if (!onLahisona){
                    iter.remove();
                }
            }
        }
        return yldLahend;
    }
    
    /**
     * Leiab rekursiivselt kõik ahelad lähtesõnast sihtsõnani, kus iga kaks järjestikust
     * sõna on üksteise lähisõnad ning prindib need ahelad välja.
     *
     * @param olemas Eelmiste rekursiivsussammudega loodud jada
     * @param yldLahend Kõik võimlikud sõnad, mida peaks vaja minema et ahelaid moodustada.
     */
    
    static void tuletusKaigud(List<String> olemas, List< Set<String> > yldLahend){
        // Kui on maksimaalse pikkusega ahel loodud, prindin selle välja
        if (olemas.size() == yldLahend.size()){
            Iterator<String> iter = olemas.iterator();
            while (iter.hasNext()){
                System.out.print(iter.next());
                if (iter.hasNext()) System.out.print("->");
            }
            System.out.println();
        } else {
            String viimane = olemas.get(olemas.size() - 1);
            // Proovin läbi kõik ahela eelmise sõna lähisõnad
            for (int i = 0; i < viimane.length(); i++){
                for (int j = 0; j < abc.length(); j++){
                    String lahisona = viimane.substring(0, i) + abc.charAt(j) + viimane.substring(i + 1);
                    // Kui lähisõna leidub selle taseme yldLahendis siis kutsun sellega uuesti selle funktisooni välja
                    if (yldLahend.get(olemas.size()).contains(lahisona)){
                        olemas.add(lahisona);
                        tuletusKaigud(olemas, yldLahend);
                        olemas.remove(lahisona);
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