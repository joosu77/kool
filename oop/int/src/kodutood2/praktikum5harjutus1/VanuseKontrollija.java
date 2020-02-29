public class VanuseKontrollija implements Lõbustus {
    int nõutudVanus;
    Lõbustus delegaat;
    public VanuseKontrollija(int nõutudVanus, Lõbustus delegaat) {
        this.nõutudVanus = nõutudVanus;
        this.delegaat = delegaat;
    }
    public void lõbusta(Külastaja isik){
        if (isik.getVanus()<nõutudVanus){
            isik.lisaKirjeldus("külastaja ei läbinud vanusekontrolli");
        } else {
            delegaat.lõbusta(isik);
        }
    }
}
