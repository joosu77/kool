public class Väljavõte {
    Klient klient;

    public Väljavõte(Klient klient) {
        this.klient = klient;
    }
    public void lisaVäljavõttesse (String saajaKonto, double summa){

    }
    public void arvuta(){
        for (String tehing: klient.getTehingud()){
            double sum = Integer.parseInt(tehing.split(", summa ")[-1]);
            String saaja = (tehing.split("ülekannekontole ",1)[1]).split(", summa ",-1)[0];
            lisaVäljavõttesse(saaja, sum);
        }
    }
}
