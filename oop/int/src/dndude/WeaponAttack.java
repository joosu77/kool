package dndude;
public class WeaponAttack implements Effect {
    int damage;
    double accuracy;

    public WeaponAttack(int damage) {
        this.damage = damage;
    }

    public void onHit(Dude effectTarget) {
        effectTarget.takeDamage(damage);
    }

    public int requiredActionPoints() {
        return 1;
    }

    public boolean isExpired() {
        return true;
    }

    public void onTurnStart(Dude effectTarget) {
        // ei tehta midagi
    }

    public void onTurnEnd(Dude effectTarget) {
        // ei tehta midagi
    }

    @Override
    public String getPihtaSonum(String nimi1, String nimi2) {
        return String.format("%s lõi mõõgaga %s pihta ja tegi %d kahju\n", nimi1, nimi2, damage);
    }

    @Override
    public String getMoodaSonum(String nimi1, String nimi2) {
        return String.format("%s üritas mõõgaga %s pihta lüüa aga ei saanud pihta\n", nimi1, nimi2);
    }
}
