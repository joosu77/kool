package dndude;
public class Firebolt implements Effect {
    int damage;
    boolean expired = false;

    public Firebolt(int damage) {
        this.damage = damage;
    }

    @Override
    public void onHit(Dude effectTarget) {
        // ei tee midagi
    }

    @Override
    public void onTurnStart(Dude effectTarget) {
        // ei tee midagi
    }

    @Override
    public void onTurnEnd(Dude effectTarget) {
        effectTarget.takeDamage(damage);
        expired = true;
    }

    @Override
    public int requiredActionPoints() {
        return 1;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public String getPihtaSonum(String nimi1, String nimi2){
        return String.format("%s lasi tulekuuliga %s pihta ja tegi %d kahju\n", nimi1, nimi2, 2);
    }
    @Override
    public String getMoodaSonum(String nimi1, String nimi2){
        return String.format("%s lasi tulekuuliga %s pihta kuid tulekuul susises ohutult %s rüüsse\n", nimi1, nimi2, nimi2);
    }

}
