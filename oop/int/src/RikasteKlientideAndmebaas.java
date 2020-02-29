import java.util.List;

public class RikasteKlientideAndmebaas extends KliendiAndmebaas {
    public RikasteKlientideAndmebaas(List<Klient> kliendid) {
        super(kliendid);
    }

    @Override
    public String väljastaAruanne(){
        String valjund = "";
        for (Klient k: kliendid){
            if (k.getSaldo()>10000) {
                valjund += k.toString();
            }
        }
        return valjund;
    }
}
