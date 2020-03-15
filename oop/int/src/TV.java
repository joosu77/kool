public class TV implements BroadcastListener {
    String omanik;

    public TV(String omanik) {
        this.omanik = omanik;
    }

    @Override
    public void listen(String sonum) {
        System.out.printf("%s: %s\n",omanik,sonum);
    }
}
