/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 4b
 * Teema: Järjendid
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.File; 
import java.util.Scanner;    
import java.util.Arrays;

class kodutoo4b {
    public static void main(String[] args) throws Exception {
        System.out.print("Kodutöö nr 3c.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");
        
        System.out.println("    AASTA 2018 TEMPERATUURIDE TABEL KUUDE LÕIKES");
        System.out.println(" E-ilmajaama (Tartus füüsikahoone katusel) andmetel\n");
        // Loon kuude massiivi
        String[] kuud = new String[] {"  jaanuar", " veebruar", "    märts", "   aprill", "      mai", "    juuni","    juuli", "   august", "september", " oktoober", " november", "detsember" };
        // Loen tabeli sisse
        float[][] tabel = hangiTabel("temperatuurid2018.txt");
        // Prindin algse tabeli
        prindiTabel(tabel, kuud);        
        
        prindiString("-", 84);
        
        System.out.println("\n\n TABEL SORTEERITUNA KESKMISTE TEMPERATUURIDE JÄRGI\n");
        // Sorteerin tabeli keskmiste temperatuuride järgi
        sorteeriTabel(tabel, kuud);
        // Prindin sorteeritud tabeli
        prindiTabel(tabel, kuud);
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
    }
    
    /**
     * Sorteerib tabeli veerud iga rea esimese elemendi
     * järgi bubble sort algoritmiga ning muudab selle 
     * põhjal ka kuude järjestust.
     *
     * @param tabel Tabel suuruses 4x12, mida sorteerida
     * @param kuud Kuude järjestus, mida tõstetakse ümber
     */
    static void sorteeriTabel(float[][] tabel, String[] kuud) {
        boolean muutus = true;
        // Läbin massiivi nii kaua, kuni järjestust enam ei
        // muudeta kuna see tähendab, et massiiv on sorteeritud
        while (muutus){
            muutus = false;
            for (int i = 1; i < 12; i++){
                // Vaatlen kahte järjestikust elementi,
                // kas nad on õiges järjekorras
                if (tabel[0][i - 1] > tabel[0][i]){
                    // Kui nad on vales järjekorras siis tõstan ümber
                    for (int j = 0; j < 4; j++){
                        float temp = tabel[j][i];
                        tabel[j][i] = tabel[j][i - 1];
                        tabel[j][i - 1] = temp;
                    }
                    String tempStr = kuud[i];
                    kuud[i] = kuud[i - 1];
                    kuud[i - 1] = tempStr;
                    muutus = true;
                }
            }
        }
    }
    
    /**
     * Prindib välja antud tabeli koos antud kuude järjestusega.
     *
     * @param tabel Tabel suuruses 4x12, mida printida
     * @param kuud Tabeli andmete kuude järjestus
     */
    static void prindiTabel (float[][] tabel, String[] kuud) {
        prindiString(" ", 13);
        System.out.println("keskmine    suurim     vähim      vahe");
        for (int i = 0; i < 12; i++){
            System.out.print(kuud[i] + "  ");
            for (int j = 0; j < 4; j++){
                System.out.printf("%10.3f", tabel[j][i]);
            }
            System.out.println();
        }
    }
    
    /**
     * Loeb tabeli sisse antud failinimega failist
     *
     * @param failiTee Tabeli asukoht failisüsteemis
     * @return Failist sisse loetud tabel, kus esimeses reas on
     *         vastavate kuud keskmised temperatuurid, teises
     *         maksimaalsed temperatuurid, kolmandas minimaalsed
     *         temperatuurid ning neljandas maksimaalse ja
     *         minimaalse temperatuuri vahe
     */
    static float[][] hangiTabel(String failiTee) throws Exception{ 
        // Hoiab iga kuu maksimaalset temperatuuri
        float[] maxid = new float[12];
        // Hoiab iga kuu minimaalset temperatuuri
        float[] minid = new float[12];
        // Hoiab iga kuu temperatuurida summat
        float[] summad = new float[12];
        // Hoiab iga kuu vaatlusandmete kogust
        float[] lugemiteArv = new float[12];
        // Algväärtustan massiivid
        for (int i=0;i<12;i++){
            maxid[i] = Integer.MIN_VALUE;
            minid[i] = Integer.MAX_VALUE;
            summad[i] = 0;
            lugemiteArv[i] = 0;
        }
        // Avan faili
        File file = new File(failiTee); 
        Scanner sc = new Scanner(file); 
      
        while (sc.hasNextLine()) {
            // Loen sisse järgmise andmerea
            String sisend = sc.nextLine();
            // Eraldan andmetest kuu
            int kuu = Integer.parseInt(sisend.substring(5,7));
            // Eraldan andmetest temperatuuri
            float temp = Float.valueOf(sisend.substring(20,sisend.length()));
            // Kontrollin, kas on kõige soojem kuu
            if (maxid[kuu-1] < temp)
                maxid[kuu-1] = temp;
            // Kontrollin, kas on kõige külmem kuu
            if (minid[kuu-1] > temp)
                minid[kuu-1] = temp;
            summad[kuu-1] += temp;
            lugemiteArv[kuu-1]++;
        }
        // Hoiab kuude minimaalsete ja maksimaalsete temperatuuride vahesid
        float[] vahed = new float[12];
        // Hoiab kuude keskmisi temperatuure
        float[] keskmised = new float[12];
        for (int i=0;i<12;i++){
            keskmised[i] = summad[i] / lugemiteArv[i];
            vahed[i] = maxid[i] - minid[i];
        }
        return new float[][]{keskmised, maxid, minid, vahed};
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