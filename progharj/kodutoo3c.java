/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 3c
 * Teema: Järjendid
 *
 * Autor: Joosep Näks
 ******************************************************************************/

import java.util.Arrays;

class kodutoo3c {
    public static void main(String[] args){
        System.out.print("Kodutöö nr 3c.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");

        kontrolli(new int[]{1});
        kontrolli(new int[]{1, 2});
        kontrolli(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        kontrolli(new int[]{-2, -1, -3, -4, -1, -2, -1, -5, -4});
        kontrolli(new int[]{2, 1, -3, 4});
        kontrolli(new int[]{42, -62, -73, 93, -68, 53, -67, 70, 78, 68, 62});
        kontrolli(new int[]{-27, 20, 66, -74, -27, -37, -48, 0, -6, 43, 39, 12, -16, -34, 77, 0});
        kontrolli(new int[]{7, -56, 65, 92, -3, -62, -61, -11, 0, 9, -80, 14, 95, -5, 12, -40, 17, 64});
        kontrolli(new int[]{1, 2, 3, -5, 6});
        kontrolli(new int[]{1, 2, 3, -15, 6});
        
        prooviJuhu(2500, 999);
        
        prindiString("=", 84);
        System.out.print("Joosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));

    }

    /**
     * Loob etteantud mõõtmetega juhusliku sisuga massiivi ning
     * leiab selles kadane- ja jõumeetodiga suurima alamjada
     * summa ning võrdleb meetodite ajakulusid.
     *
     * @param pikkus Loodava massiivi pikkus.
     * @param max Loodava massiivi elemendi absoluutväärtuse maksimaalne väärtus 
     */
    static void prooviJuhu(int pikkus, int max){
        // Loon juhusliku massiivi:
        int[] a = looMassiiv(pikkus, -1*max, max);
        System.out.printf("Antud: juhujärjend pikkusega %d (elemendid lõigust [-%d; %d])\n", pikkus, max,max);
        System.out.println("Suurima summaga alamjärjendi summa ja pikkus:");
        // Arvutan alamjada summa kadanemeetodiga:
        long algusAeg = System.nanoTime();
        int[] kadaneTulemus = KadanePluss(a);
        double kadanaAeg = ((double)System.nanoTime()-algusAeg)/1000000;
        System.out.printf("Kadane:        summa = %d, pikkus = %d; tööaeg ms = %f\n", kadaneTulemus[2], kadaneTulemus[1] - kadaneTulemus[0] + 1, kadanaAeg);
        // Arvutan alamjada summa jõumeetodiga
        algusAeg = System.nanoTime();
        int[] jouTulemus = jouMeetod(a);
        double jouAeg = ((double)System.nanoTime() - algusAeg) / 1000000;
        System.out.printf("Jõumtd:        summa = %d, pikkus = %d; tööaeg ms = %f\n",jouTulemus[2], jouTulemus[1] - jouTulemus[0] + 1, jouAeg);
        System.out.println();
    }

    /**
     * Arvutab etteantud massiivis suurima alamjada summa kadane- ja
     * jõumeetodiga ning võrdleb meetodide tulemusi omavahel.
     *
     * @param a Mõõdetav massiiv.
     */
    static void kontrolli(int[] a){
        // Arvutan tulemuse kadanemeetodiga:
        int[] kadaneTulemus = KadanePluss(a);
        // Arvutan tulemuse jõumeetodiga:
        int[] jouTulemus = jouMeetod(a);
        System.out.printf("Antud: " + Arrays.toString(a) + "; indeksid 0 ... %d\n", a.length - 1);
        System.out.printf("Kadane         tulemus: " + Arrays.toString(kadaneTulemus) + "\n"); 
        System.out.printf("Jõumeetod      tulemus: " + Arrays.toString(jouTulemus) + "\n"); 
        // Kontrollin, kas meetodid said samad tulemused:
        if (kadaneTulemus[0] == jouTulemus[0] && kadaneTulemus[1] == jouTulemus[1] && kadaneTulemus[2] == jouTulemus[2]){
            int[] alamMassiiv = new int[jouTulemus[1] - jouTulemus[0] + 1];
            System.arraycopy(a, jouTulemus[0], alamMassiiv, 0, alamMassiiv.length);
            System.out.printf("Tulemused samad. Suurim summa (%d) on alamjärjendi " + Arrays.toString(alamMassiiv) + "; indeksid %d ... %d\n", jouTulemus[2], jouTulemus[0], jouTulemus[1]);
        } else {
            System.out.println("Tulemused ei kattu, midagi on valesti.");
        }
        System.out.println();
    }
    
    /**
     * Modifitseeritud kadane meetod, mis leiab etteantud jadas suurima
     * alamjada summa ning selle algus- ja lõppemisindeksid.
     *
     * @param a Etteantud massiiv
     * @return 3 elemendiline massiiv, milles on massiivi suurima
     *         alamjada algusindeks, lõpuindeks ning alamjadaliikmete summa
     */
    static int[] KadanePluss(int[] a){
        // Parasjagu vaadeldava alamjada summa ja algindeks
        int jooksevSumma = 0;
        int jooksevAlgus = 0;
        // Suurima leitud alamjada summa, algindeks ja lõppindeks
        int maksimaalneSumma = 0;
        int maksimaalseAlgus = -1;
        int maksimaalseLopp = -1;
        // Jada suurim element ja selle asukoht juhuks,
        // kui jadas puuduvad positiivsed liikmed
        int suurim = Integer.MIN_VALUE;
        int suurimaAsukoht = -1;
        for(int i = 0; i < a.length; i++){
            // Lisan uue elemendi vaadeldavasse alamjadasse
            jooksevSumma += a[i];
            // Kontrollin kas vaadeldav element on seni nähtutest suurim
            if (a[i] > suurim){
                suurim = a[i];
                suurimaAsukoht = i;
            }
            // Kontrollin, kas vaadeldav alamjada on seni nähtutest suurim
            if (maksimaalneSumma < jooksevSumma) {
                maksimaalneSumma = jooksevSumma;
                maksimaalseAlgus = jooksevAlgus;
                maksimaalseLopp = i;
            }
            // Kontrollin, ega vaadeldav alamjada negatiivseks läinud pole
            if (jooksevSumma < 0) {
                jooksevSumma = 0;
                jooksevAlgus = i + 1;
            }
        }
        // Kontrollin, kas viimane vaadeldud alamjada oli suurim
        if (maksimaalneSumma < jooksevSumma) {
            maksimaalneSumma = jooksevSumma;
            maksimaalseAlgus = jooksevAlgus;
            maksimaalseLopp = a.length-1;
        }
        // Kontrollin, kas leidus jadas mõni positiivne element
        if (maksimaalneSumma == 0){
            maksimaalneSumma = suurim;
            maksimaalseAlgus = suurimaAsukoht;
            maksimaalseLopp = suurimaAsukoht;
        }
        return new int[]{maksimaalseAlgus, maksimaalseLopp, maksimaalneSumma};
    }
    
    /**
     * Jõumeetod, mis käib läbi kõik antud jada alamjadad ning
     * leiab neist suurima.
     *
     * @param a Etteantud massiiv
     * @return 3 elemendiline massiiv, milles on massiivi suurima
     *         alamjada algusindeks, lõpuindeks ning alamjadaliikmete summa
     */
    
    static int[] jouMeetod(int[] a){
        // Parasjagu vaadeldava alamjada summa, algindeks ja lõppindeks
        int maksimaalneSumma = 0;
        int maksimaalseAlgus = 0;
        int maksimaalseLopp = 0;
        // Jada suurim element ja selle asukoht juhuks,
        // kui jadas puuduvad positiivsed liikmed
        int suurim = Integer.MIN_VALUE;
        int suurimaAsukoht = -1;
        for (int i = 0; i < a.length; i++){
            // Kontrollin, kas vaadeldav element on suurim seni nähtu
            if (a[i] > suurim){
                suurim = a[i];
                suurimaAsukoht = i;
            }
            // Käin läbi kõik käesolevast elemendist algavad alamjadad
            int jooksevSumma = 0;
            for (int j = i; j < a.length; j++){
                jooksevSumma += a[j];
                // Kontrollin, kas käesolev alamjada on suurim seni nähtu
                if (jooksevSumma > maksimaalneSumma){
                    maksimaalneSumma = jooksevSumma;
                    maksimaalseAlgus = i;
                    maksimaalseLopp = j;
                }
            }
        }
        // Kontrollin, ega jadas kõik elemendid negatiivsed polnud
        if (maksimaalneSumma == 0){
            maksimaalneSumma = suurim;
            maksimaalseAlgus = suurimaAsukoht;
            maksimaalseLopp = suurimaAsukoht;
        }
        return new int[]{maksimaalseAlgus, maksimaalseLopp, maksimaalneSumma};
    }
    
    /**
     * Loob ette antud pikkuse ning elemendi suurustega juhusliku massiivi.
     *
     * @param pikkus Loodava massiivi pikkus
     * @param max Loodava massiivi elemendi suurim võimalik väärtus
     * @param min Loodava massiivi elemendi vähim võimalik väärtus
     * @return Loodud massiiv
     */
    static int[] looMassiiv(int pikkus, int max, int min){
        int[] massiiv = new int[pikkus];
        for(int i = 0; i < pikkus; i++)
            massiiv[i] = (int)(Math.random() * (max - min + 1) + min);
        return massiiv;
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