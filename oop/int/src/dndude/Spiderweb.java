package dndude;

public class Spiderweb implements Effect {
    int turnsLeft = 2;

    @Override
    public void onHit(Dude effectTarget) {
        // ei tee midagi
    }

    @Override
    public void onTurnStart(Dude effectTarget) {
        effectTarget.actionPoints -= 1;
        turnsLeft -= 1;
    }

    @Override
    public void onTurnEnd(Dude effectTarget) {
        // ei tee midagi
    }

    @Override
    public int requiredActionPoints() {
        return 1;
    }

    @Override
    public boolean isExpired() {
        if (turnsLeft == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getPihtaSonum(String nimi1, String nimi2) {
        return String.format("%s lasi ämblikuvõrgu %s pihta ja pani %s lõksu\n", nimi1, nimi2, nimi2);
    }

    @Override
    public String getMoodaSonum(String nimi1, String nimi2) {
        return String.format("%s lasi ämblikuvõrgu %s pihta kuid %s lõikas võrgu poole lennu pealt mõõgaga pooleks\n", nimi1, nimi2, nimi2);
    }
}
