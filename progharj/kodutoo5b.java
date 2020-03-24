import java.io.*;
import java.util.*;

class kodutoo5b {
    public static void main(String[] args) throws Exception{
        //List<paar> tulem = otsiSonu("KuldninagaMees_est.txt");
        List<paar> tulem = otsiSonu("Kõrboja_sisu_puhastekst.txt");
        
        for (paar p : tulem){
            System.out.printf("%d korda: %s\n",p.kogus,p.sona);
        }
        
    }
    static List<paar> otsiSonu(String failitee) throws Exception {
        HashMap<String,suurenevInt> sonadeKogused = new HashMap<String,suurenevInt>();
        Set<String> mitteNimed = new HashSet<String>();
        File fail = new File(failitee);
        Scanner sc = new Scanner(new FileReader(fail));
        sc.useDelimiter(" ");
        while (sc.hasNext()){
            String sona = sc.next();
            System.out.println(sona);
            sona = sona.replace(".","");
            sona = sona.replace("!","");
            sona = sona.replace("?","");
            sona = sona.replace(":","");
            sona = sona.replace("\"","");
            sona = sona.replace(",","");
            sona = sona.toLowerCase();
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
        List<paar> esiKymme = new ArrayList<paar>();
        for (String sona : mitteNimed){
            esiKymme.add(new paar(sona, sonadeKogused.get(sona).get()));
            esiKymme.sort((paar p1, paar p2)->p2.kogus-p1.kogus);
            if (esiKymme.size()>10){
                esiKymme.remove(esiKymme.size()-1);
            }
        }
        return esiKymme;
    }
}

class paar {
    String sona;
    int kogus;
    paar (String sona, int kogus){
        this.sona = sona;
        this.kogus = kogus;
    }
}

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