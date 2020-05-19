/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 11b
 * Teema: Kahendpuud I
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.lang.Math;

class kodutoo11b {
    public static void main(String[] args){
        System.out.print("Kodutöö nr 11b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");
        
        prindiJuhupuuAndmed(11);
        prindiJuhupuuAndmed(21);
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    static void prindiJuhupuuAndmed(int n){
        System.out.printf("n=%d\n", n);
        Tipp t = juhuslikPuu(n,0,100);
        System.out.printf("Avaldis: %s\n", infiksKuju(t));
        System.out.printf("Väärtus: %d\n", vaartus(t));
        System.out.println("Puu:");
        valjastaPuu(t);
        System.out.println();
        
    }
    
    /**
     * Konstrueerib kahendpuu, kus on n tippu, igal tipul on kas 0 või 2 last,
     * igal lehttipul on väärtuseks täisarv vahemikus a..b ning igal vahetipul
     * tehtemärk valikust +, - või *.
     * @param n Tippude arv.
     * @param a Lehttippude minimaalne väärtus
     * @param b Lehttippude maksimaalne väärtus
     * @return Loodud kahendpuu juur
     */
    public static Tipp juhuslikPuu(int n, int a, int b){
        return juhuslikPuu(n, a, b, 0);
    }
    
    /**
     * Konstrueerib kahendpuu, kus on n tippu, igal tipul on kas 0 või 2 last,
     * igal lehttipul on väärtuseks täisarv vahemikus a..b ning igal vahetipul
     * tehtemärk valikust +, - või *.
     * @param n Tippude arv.
     * @param a Lehttippude minimaalne väärtus
     * @param b Lehttippude maksimaalne väärtus
     * @param tase Näitab, mitmendale tasemele loodav tipp lõplikus puus läheb
     * @return Loodud kahend puu juur
     */
    public static Tipp juhuslikPuu(int n, int a, int b, int tase){
        Random random = new Random();
        Tipp juur;
        if (n == 1){
            // Kui olen jõudnud lõppu, loon ja tagastan lehe 
            juur = new Tipp (random.nextInt(b-a+1) + a, tase+1);
        } else {
            // Jagan vajaliku tippude arvu n-1 kahe lapse vahel suvaliselt ära
            // nii, et kummalegi jääks paaritu arv tippe
            int pikkus = random.nextInt(n-1);
            pikkus = pikkus + (pikkus+1)%2;
            juur = new Tipp (juhuslikPuu(pikkus, a, b), juhuslikPuu(n-pikkus-1, a, b), new char[]{'+', '-', '*'}[random.nextInt(3)], tase+1);
        }
        return juur;
    }
    
    /**
     * Leiab kahendpuu tehete väärtuse
     * @param t Vaadeldava kahendpuu juur
     * @return Antud kahendpuu tehete väärtus
     */
    public static int vaartus(Tipp t){
        if (t.leht){
            // Kui olen jõudnud leheni, tagastan selle väärtuse
            return t.val;
        } else {
            // Teostan tipus määratud operatsiooni selle laste väärtustele
            switch (t.tehe){
                case '+':
                    return vaartus(t.laps1) + vaartus(t.laps2);
                case '-':
                    return vaartus(t.laps1) - vaartus(t.laps2);
                case '*':
                    return vaartus(t.laps1) * vaartus(t.laps2);
            }
        }
        // Siia ei jõuta kunagi korrektse sisendpuu puhul kuid kompilaator pole nõus ilma selle returnita kopileerima
        return -1;
    }
    
    /**
     * Prindib välja kahendpuu graafilise kujutise
     * @param t Vaadeldava kahendpuu juur
     */
    public static void valjastaPuu(Tipp t){
       looPilt(t).print();
    }
    
    /**
     * Loob kahendpuust ilusa graafilise pildi
     * @param t Vaadeldava kahendpuu juur
     * @return Kahendpuust loodud pilt
     */
    public static ModulaarneSone looPilt(Tipp t){
        if (t.leht){
            // Kui olen jõudnud leheni, alustan uut modulaarset sõne
            return new ModulaarneSone(t.val);
        } else {
            // Paneb laste graafilised kujutised kõrvuti ning väljastab ühendi
            ModulaarneSone res = looPilt(t.laps1);
            res.lisaParemale(looPilt(t.laps2), t.tehe);
            return res;
        }
    }
    
    /**
     * Tagastab kahendpuu infiks kuju
     * @param t Vaadeldava kahendpuu juur
     * @return Kahendpuu infiks kuju
     */
    public static String infiksKuju(Tipp t){
        if (t.leht){
            // Kui olen jõudnud leheni, tagastan stringi kujul lehe väärtuse
            return Integer.toString(t.val);
        } else {
            // Panen kokku laste infikskujud, nende vahele tehtemärgi, sulud ümber, ning tagastan selle
            return '(' + infiksKuju(t.laps1) + t.tehe + infiksKuju(t.laps2) + ')';
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


/**
 * Andmestruktuur, millena puude tippe hoitakse
 */
class Tipp {
    public Tipp laps1;
    public Tipp laps2;
    public char tehe;
    public int val;
    public boolean leht;
    public int tase;
    
    // Tipu konstruktor lehe jaoks    
    public Tipp (int val, int tase){
        this.val = val;
        leht = true;
        this.tase = tase;
    }
    
    // Tipu konstruktor vahetipu jaoks
    public Tipp (Tipp laps1, Tipp laps2, char tehe, int tase){
        this.laps1 = laps1;
        this.laps2 = laps2;
        this.tehe = tehe;
        leht = false;
        this.tase = tase;
    }
}

/**
 * Sõnede list, mis on ristküliku kujuline plokk, kui listi liikmed üksteise alla ritta panna.
 * Võimaldab Selliseid plokke horisontaalselt kokku ühendada ning nende keskele
 * tehtesümboli peale panna.
 */
class ModulaarneSone {
    public List<String> val;
    public ModulaarneSone (int algVaartus){
        val = new ArrayList<String>(List.of(Integer.toString(algVaartus)));
    }
    
    /**
     * Liidab olemasolevale sõneplokile paremale teise ploki juurde, paneb
     * nende kohale keskele tehtemärgi ning joonistab ilusa joone, mis ühendab
     * tehtemärki ning plokkide ülemisi liikmeid
     */
    public void lisaParemale(ModulaarneSone sone2, char symbol){
        int esimesePikkus = this.val.get(0).length();
        // Liidan kokku sonede read, mis on molemal olemas
        for (int i = 0; i < Integer.min(sone2.val.size(), this.val.size()); i++){
            this.val.set(i, this.val.get(i) + " " + sone2.val.get(i));
        }
        
        // Kui juurde liidetav sone on kõrgem, liidan praegusele ridu piisavalt juurde
        for (int i = this.val.size(); i < sone2.val.size(); i++){
            this.val.add(" ".repeat(esimesePikkus) + " " + sone2.val.get(i));
        }
        
        // Kui praegune sõne on kõrgem, liidan tühje lõppe juurde sinna, kuhu liidetav sõne ei ulatu
        for (int i = sone2.val.size(); i <this.val.size(); i++){
            this.val.set(i, this.val.get(i) + " " + " ".repeat(sone2.val.get(0).length()));
        }
        // Loob ilusad jooned vanemast lasteni
        StringBuilder ilusJoon = new StringBuilder();
        for (int i=0; this.val.get(0).charAt(i) == ' '; i++){
            ilusJoon.append(" ");
        }
        for (int i=ilusJoon.length(); i < esimesePikkus-1; i++){
            ilusJoon.append("_");
        }
        ilusJoon.append("/ \\");
        if (this.val.get(0).charAt(ilusJoon.length()-1) == ' '){
            for (int i=ilusJoon.length(); this.val.get(0).charAt(i) == ' '; i++){
                ilusJoon.append("_");
            }
            ilusJoon.append("_");
        }
        ilusJoon.append(" ".repeat(this.val.get(0).length()-ilusJoon.length()));
        this.val.add(0,ilusJoon.toString());
        // Lisab tehtemärgi tippu
        this.val.add(0," ".repeat(esimesePikkus) + symbol + " ".repeat(sone2.val.get(0).length()));
        
    }
    
    // Prindib loodud sõne välja
    public void print(){
        for (String sone : val){
            System.out.println(sone);
        }
    }
}