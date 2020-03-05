package dndude;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wizard extends Dude {
    List<Effect> attackPool = new ArrayList<>(List.of(new Firebolt(2), new Spiderweb()));

    @Override
    Effect chooseEffect() {
        attackPool.removeIf(effect->effect.requiredActionPoints() > actionPoints);
        Random random = new Random();
        Effect effect = attackPool.get(random.nextInt(attackPool.size()));
        attackPool = new ArrayList<>(List.of(new Firebolt(2), new Spiderweb()));
        return effect;
    }

    public Wizard(double accuracy, double armour, double health, String name) {
        super(accuracy, armour, health, name);
    }
}
