package dndude;
import java.util.Random;

public class Fighter extends Dude {

    void attack(Dude target) {
        Random rand = new Random();
        while (super.actionPoints > 0) {
            int val = rand.nextInt(2);
            switch (val) {
                case 0:
                    Effect swordslash = new WeaponAttack(2);
                    if (rand.nextInt(20) + accuracy + 1 >= target.armour) {
                        target.addEffect(swordslash);
                        System.out.printf("%s lõi mõõgaga %s ja tegi %d kahju\n", super.name, target.name, 2);
                    } else {
                        System.out.printf("%s üritas mõõgaga %s lüüa aga ei saanud pihta\n", super.name, target.name);
                    }
                    actionPoints -= swordslash.requiredActionPoints();
                    break;
                case 1:
                    Effect knockdown = new Knockdown();
                    if (rand.nextInt(20) + accuracy + 1 >= target.armour) {
                        target.addEffect(knockdown);
                        System.out.printf("%s lükkas %s pikali\n", super.name, target.name);
                    } else {
                        System.out.printf("%s üritas %s pikali lükata, kuid %s seisab kui kivimüür\n", super.name, target.name, target.name);
                    }
                    actionPoints -= knockdown.requiredActionPoints();
                    break;
            }
        }
    }

    public Fighter(double accuracy, double armour, double health, String name) {
        super(accuracy, armour, health, name);
    }
}
