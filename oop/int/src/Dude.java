public abstract class Dude {
    double accuracy;
    double armour;
    double health;
    int actionPoints;

    public Dude(double accuracy, double armour, double health, int actionPoints) {
        this.accuracy = accuracy;
        this.armour = armour;
        this.health = health;
        this.actionPoints = actionPoints;
    }

    void takeDamage(int damage){
        health -= damage;
    }

    abstract void takeTurn(Dude attackTarget);

    boolean isAlive() {
        return health > 0;
    }
}
