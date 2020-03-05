package dndude;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Dude {
    double accuracy;
    double armour;
    double health;
    int actionPoints = 2;
    String name;
    List<Effect> effects = new ArrayList<>();

    public Dude(double accuracy, double armour, double health, String name) {
        this.accuracy = accuracy;
        this.armour = armour;
        this.health = health;
        this.name = name;
    }

    public void addEffect(Effect effect) {
        effect.onHit(this);
        if (!effect.isExpired()) {
            effects.add(effect);
        }
    }

    void takeDamage(int damage) {
        health -= damage;
    }

    void takeTurn(Dude attackTarget) {
        for (Effect effect : effects) {
            effect.onTurnStart(this);
        }
        effects.removeIf(Effect::isExpired);

        Random rand = new Random();
        while (actionPoints>0) {
            Effect rynnak = chooseEffect();
            if (rand.nextInt(20) + accuracy + 1 >= attackTarget.armour) {
                attackTarget.addEffect(rynnak);
                System.out.printf(rynnak.getPihtaSonum(super.name, target.name));
            } else {
                System.out.printf(rynnak.getMoodaSonum(super.name, target.name));
            }
            actionPoints -= rynnak.requiredActionPoints();
        }

        actionPoints = 2;
        for (Effect effect : effects) {
            effect.onTurnEnd(this);
        }
        effects.removeIf(Effect::isExpired);
    }

    abstract Effect chooseEffect();

    boolean isAlive() {
        return health > 0;
    }
}
