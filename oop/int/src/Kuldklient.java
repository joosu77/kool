public class Kuldklient extends Klient {

    private String kliendiHaldur;

    public Kuldklient(int isikukood, double saldo, String kliendiHaldur, double aktsiaPortfelliVäärtus) {
        super(isikukood, saldo, aktsiaPortfelliVäärtus); // Klient konstruktori käivitamine
        this.kliendiHaldur = kliendiHaldur;
    }

    @Override
    public double arvutaPortfelliHaldustasu(){
        if (super.aktsiaPortfelliVäärtus<5000){
            return 0;
        } else {
            return (super.aktsiaPortfelliVäärtus-5000)*0.005;
        }
    }

    @Override
    public String toString(){
        return super.toString() + ", kliendihaldur " + kliendiHaldur;
    }

    @Override
    public double arvutaTehinguTasud() {
        return 10.0; // alati sama teenustasu sõltumata tehingute arvust
    }

    public String getKliendiHaldur() {
        return kliendiHaldur;
    }

    public void setKliendiHaldur(String kliendiHaldur) {
        this.kliendiHaldur = kliendiHaldur;
    }
}