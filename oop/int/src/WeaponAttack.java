public class WeaponAttack implements Effect {
    int damage = 1;
    @Override
    public void onHit(Dude effectTarget) {
        effectTarget.takeDamage(damage);
    }
    @Override
    public int requiredActionPoints(){
        return 1;
    }

    @Override
    public boolean isExpired(){
        return true;
    }

    @Override
    public void onTurnStart(Dude effectTarget) {

    }

    @Override
    public void onTurnEnd(Dude effectTarget) {

    }
}
