package dndude;

public class Knockdown implements Effect {
    public void onHit(Dude effectTarget) {
        effectTarget.actionPoints = 0;
    }

    public void onTurnStart(Dude effectTarget) {
        // ei tehta midagi
    }

    public void onTurnEnd(Dude effectTarget) {
        // ei tehta midagi
    }

    public int requiredActionPoints() {
        return 1;
    }

    public boolean isExpired() {
        return true;
    }

    @Override
    public String getPihtaSonum(String nimi1, String nimi2) {
        return String.format("%s lükkas %s pikali\n", nimi1, nimi2);
    }

    @Override
    public String getMoodaSonum(String nimi1, String nimi2) {
        return String.format("%s üritas %s pikali lükata, kuid %s seisab kui kivimüür\n", nimi1, nimi2, nimi2);
    }
}
