public class Fighter extends Dude {
    @Override
    void takeTurn(Dude attackTarget) {
        Effect rynne = WeaponAttack();
        attackTarget.addEffect(rynne);
    }

    public Fighter(double accuracy, double armour, double health, int actionPoints) {
        super(accuracy, armour, health, actionPoints);
    }
}
