public class TasulineLõbustus implements Lõbustus {
    private double hind;
    private Lõbustus delegaat;

    public TasulineLõbustus(double hind, Lõbustus delegaat) {
        this.hind = hind;
        this.delegaat = delegaat;
    }

    public void lõbusta(Külastaja isik) {
        delegaat.lõbusta(isik);
        isik.lisaKulu(hind);
        isik.lisaKirjeldus("tasusin külastuse eest " + hind);
    }
}
