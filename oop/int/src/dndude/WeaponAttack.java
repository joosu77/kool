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
}
