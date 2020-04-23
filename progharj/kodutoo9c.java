/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 9c
 * Teema: Rekursioon III
 *
 * Autor: Joosep Näks
 ******************************************************************************/
import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.lang.Math;

class kodutoo9c {
    static String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    static List<Pair> kastiPaarid = new ArrayList<Pair>();
    public static void main(String[] args) throws Exception{
        System.out.print("Kodutöö nr 8b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println("\n");

        // Genereerin juhuslikust kastid:
        int n = 20;
        Random rand = new Random();
        List<kast> sisend = new ArrayList<kast>();
        for (int i = 0; i < n; i++){
            // Kui tahta n>62 proovida, tuleb abc.charAt(i) asemele panna (char)i kuna tähestik saab otsa,
            // programm töötab normaalajas kuni u n<500
            kast k = new kast(abc.charAt(i)/*(char)i*/, rand.nextInt(100), rand.nextInt(100), rand.nextInt(100), (float)1/rand.nextInt(10));
            sisend.add(k);
        }
        
        // Kui eelnev lõik välja kommenteerida ja järgnevad 2 rida sisse, siis saab näites antud kastid
        
        //List<kast> sisend = new ArrayList<kast>(List.of(new kast(abc.charAt(0),3,5,6,0.1),new kast(abc.charAt(1),6,6,5,0.1), new kast(abc.charAt(2),6,7,2,0.1), new kast(abc.charAt(3),1,1,5,0.1), new kast(abc.charAt(4),3,3,2,0.1), new kast(abc.charAt(5),4,5,2,0.1), new kast(abc.charAt(6),7,4,7,0.1), new kast(abc.charAt(7),5,5,1,0.1), new kast(abc.charAt(8),7,7,7,0.1)));
        //int n=8;
        System.out.println("Antud (juhu)kastid:");
        for (kast k : sisend){
            System.out.printf("%s[alus=%fx%f; h=%f; d=%f]\n", k.nimi, k.laius, k.pikkus, k.korgus, k.paksus);
        }

        // Sorteerin kastid:
        sisend.sort((kast k1, kast k2) -> (int)(k1.korgus - k2.korgus));
        System.out.println("\nSorteeritult kõrguste järgi:");
        int luger = 0;
        for (kast k : sisend){
            k.jrkNr = luger;
            System.out.printf("%d. %s[alus=%fx%f; h=%f; d=%f]\n", luger++, k.nimi, k.laius, k.pikkus, k.korgus, k.paksus);
        }
        
        // Leian relatsioonid:
        List<Pair> relatsioonid = leiaRelatsioonid(sisend);
        System.out.println("\nMahtuvusrelatsioonid:");
        for (Pair rel : relatsioonid){
            System.out.printf("Kast %d. mahub kasti %d.\n", rel.num1, rel.num2);
        }
        System.out.printf("Mahtuvusseoseid kokku: %d; relatsiooni alamhulki: %d\n\n", relatsioonid.size(), (int)Math.pow(2,relatsioonid.size()));
        
        // Leian vähima pakkimise
        System.out.println("Leitud pakkimine:");
        leiaRelatsiooniKett(relatsioonid, new HashSet<Integer>(), new ArrayList<Pair>());
        Map<Integer, Integer> relatsiooniKaart = new HashMap<Integer,Integer>();
        Set<Integer> siseKastideHulk = new HashSet<Integer>();
        for (Pair rel : kastiPaarid){
            System.out.printf("%d. --> %d.\n", rel.num1, rel.num2);
            relatsiooniKaart.put(rel.num2, rel.num1);
            siseKastideHulk.add(rel.num1);
        }
        
        System.out.print("Tulemus objektide järjendina:");
        for (int i = 0; i < n;i++){
            if (!siseKastideHulk.contains(i)){
                if (relatsiooniKaart.containsKey(i)){
                    System.out.printf(" %d(%d)",i,relatsiooniKaart.get(i));
                } else {
                    System.out.printf(" %d", i);
                }
            }
        }
        System.out.println("\n");
        
        prindiString("=", 84);
        System.out.print("\nJoosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));        
    }
    
    /**
     * Leiab sisestatud kastide listist kõik kastide paarid, milles üks teise sisse mahub.
     *
     * @param sisend List kastidest, millest paare otsida.
     * @return List kastide järjekorranumbrite paaridest, kus üks kast mahub teise sisse.
     */
    static List<Pair> leiaRelatsioonid(List<kast> sisend){
        List<Pair> relatsioonid = new ArrayList<Pair>();
        // Käiakse läbi potentsiaalsed sisemised kastid
        for (int i=0;i<sisend.size()-1; i++){
            // Käiakse läbi potentsiaalsed välimised kastid
            for (int j=i+1;j<sisend.size();j++){
                kast sisemine = sisend.get(i);
                kast valimine = sisend.get(j);
                if (sisemine.korgus + sisemine.paksus <= valimine.korgus
                  &&((sisemine.laius + sisemine.paksus <= valimine.laius && sisemine.pikkus + sisemine.paksus < valimine.pikkus)
                  ||(sisemine.pikkus + sisemine.paksus <= valimine.laius && sisemine.laius + sisemine.paksus < valimine.pikkus))){
                    relatsioonid.add(new Pair(sisemine.jrkNr, valimine.jrkNr));
                }
            }
        }
        return relatsioonid;
    }
    
    /**
     * Kui on antud paarid kastidest, mis üksteise sisse mahuvad, kastid, mis on juba kasutatud
     * uute paaride loomiseks ja uued paarid, mis on loodud, siis võtab uue sisemise kasti ning
     * loob kõik uued paarid, mida sellega luua saab ja kutsub igaühega ennast välja. Lõpuks salvestab
     * globaalsesse kastidePaarid listi kõige suurema kogumi uusi paari, mis õnnestus tekitada.
     *
     * @param relatsioonid Antud paarid, mille kohta on teada, et üks kast mahub teise sisse.
     * @param kasutatud Kastid, mis kas on juba mõne kasti sees, või milles on mõne kast sees.
     * @param paarid Uued paarid, mis on loodud.
     */
    static void leiaRelatsiooniKett(List<Pair> relatsioonid, Set<Integer> kasutatud, List<Pair> paarid){
        // Kui vanad paarid on otsas, pole vaja rohkem tegutseda
        if (relatsioonid.size()>0){
            int sisemine = relatsioonid.get(0).num1;
            List<Integer> valimised = new ArrayList<Integer>();
            Iterator<Pair> iter = relatsioonid.iterator();
            // Leiab kõik kastid, kuhu sisemine kast sisse mahub, lisab need välimiste kastide hulka
            // ning eemaldab need relatsioonid
            while (iter.hasNext()){
                Pair vaadeldav = iter.next();
                if (vaadeldav.num1 == sisemine){
                    valimised.add(vaadeldav.num2);
                    iter.remove();
                } else {
                    break;
                }
            }
            // Kutsub sama funktsiooni uuesti välja nii et on lisatud sisemine kast ja eraldi
            // iga liige välimistest kastidest paaridesse.
            boolean mindiEdasi = false;    
            if (!kasutatud.contains(sisemine)){
                for (Integer valimine : valimised){
                    if (!kasutatud.contains(valimine)){
                        mindiEdasi = true;
                        paarid.add(new Pair(sisemine, valimine));
                        kasutatud.add(valimine);
                        leiaRelatsiooniKett(relatsioonid, kasutatud, paarid);
                        paarid.remove(paarid.size() - 1);
                        kasutatud.remove(valimine);
                    }
                }
                
            }
            // Kui kõik kastid olid kasutusel ja funktsiooni ei kutsutud uuesti välja, kutsutakse
            // see siin uuesti välja, kuna teiste kastide juures võib veel olla kasutamata relatsioone
            if (!mindiEdasi){
                leiaRelatsiooniKett(relatsioonid, kasutatud, paarid);
            }
        }
        if (paarid.size() > kastiPaarid.size()){
            kastiPaarid = new ArrayList<Pair>(paarid);
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

// Klass, millega mugavalt kaste salvestada
class kast implements Comparable<kast>{
    public int jrkNr;
    public char nimi;
    public double laius;
    public double pikkus;
    public double korgus;
    public double paksus;
    public kast (char nimi, double laius, double pikkus, double korgus, double paksus){
        this.nimi = nimi;
        this.laius = laius;
        this.pikkus = pikkus;
        this.korgus = korgus;
        this.paksus = paksus;
    }
    @Override
    public int compareTo(kast k){
        if ((korgus-k.korgus) != 0){
            return (int)(korgus-k.korgus);
        } else {
            return (int)(nimi-k.nimi);
        }
    }
    
}

// Paari klass numbripaaride hoimiseks listides
class Pair {
    public int num1;
    public int num2;
    public Pair (int num1, int num2){
        this.num1 = num1;
        this.num2 = num2;
    }
}