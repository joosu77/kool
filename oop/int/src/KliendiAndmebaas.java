import java.util.List;

public class KliendiAndmebaas {
    List<Klient> kliendid;

    public KliendiAndmebaas(List<Klient> kliendid) {
        this.kliendid = kliendid;
    }

    public List<Klient> getKliendid() {
        return kliendid;
    }
    public String väljastaAruanne(){
        String valjund = "";
        for (Klient k: kliendid){
            valjund += k.toString();
        }
        return valjund;
    }
}
