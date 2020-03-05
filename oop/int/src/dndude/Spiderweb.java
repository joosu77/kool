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
}
