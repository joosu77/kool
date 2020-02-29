public class LõbustavKloun implements Lõbustus {
    Kloun kloun;
    public LõbustavKloun(Kloun kloun) {
        this.kloun = kloun;
    }
    public void lõbusta (Külastaja isik){
        kloun.esine(isik);
    }
}
