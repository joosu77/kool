import java.util.ArrayList;
import java.util.List;

// Vastus küsimusele, mis juhtuks, kui TasulineLõbustus delegeeriks vanusekontrolliga lasketiiru, mitte nii nagu praegu:
// Külastaja peaks hinda maksma enne vanusekontrolli ehk ta peaks maksma külastuse eest ka juhul, kui ta ei saa sisse.
public class Test {
    public static void main(String[] args) {
        // Loon vaateratta, mis maksab 2.25 ühikut
        Vaateratas ratas = new Vaateratas();
        TasulineLõbustus maksuRatas = new TasulineLõbustus(2.25, ratas);
        // Loon lasketiiru, mis maksab 1.5 ühikut ja mille vanusepiirang on 10
        Lasketiir tiir = new Lasketiir();
        TasulineLõbustus maksuTiir = new TasulineLõbustus(1.5, tiir);
        VanuseKontrollija kontrollija = new VanuseKontrollija(10, maksuTiir);
        // Loon klouni
        Kloun kloun = new Kloun("PluriLuri");
        LõbustavKloun lkloun = new LõbustavKloun(kloun);
        // Loon lõbustuste nimekirja
        List<Lõbustus> lõbustused = new ArrayList<>();
        lõbustused.add(maksuRatas);
        lõbustused.add(kontrollija);
        lõbustused.add(lkloun);
        // Loon lõbustuspargi
        Lõbustuspark park = new Lõbustuspark(lõbustused);
        // Loon külastajad
        Külastaja isik1 = new Külastaja(9);
        Külastaja isik2 = new Külastaja(11);
        park.alustaSeiklust(isik1);
        park.alustaSeiklust(isik2);
    }
}
