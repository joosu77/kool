public class TestPank {
    public static void main(String[] args) {
        Klient klient = new Klient(2,3,  2);
        Kuldklient kuldklient = new Kuldklient(4,5, "sisalik", 4);
        klient.teostaÜlekanne("sisalik",1);
        kuldklient.teostaÜlekanne("poder",2);
        klient.arvutaTehinguTasud();
        kuldklient.arvutaTehinguTasud();

    }
}
