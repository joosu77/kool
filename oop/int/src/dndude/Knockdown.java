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
}
