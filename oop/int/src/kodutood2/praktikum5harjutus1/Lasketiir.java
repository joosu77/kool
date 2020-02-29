public class Lasketiir implements Lõbustus {
    public void lõbusta(Külastaja isik) {
        int N = (int) (Math.random() * 20);
        isik.lisaKirjeldus(String.format("tabasin lasketiirus %d sihtmärki", N));
    }
}
