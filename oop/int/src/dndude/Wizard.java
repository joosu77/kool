package dndude;
import java.util.Random;

public class Wizard extends Dude {
    void attack(Dude target) {
        Random rand = new Random();
        while (super.actionPoints > 0) {
            int val = rand.nextInt(2);
            switch (val) {
                case 0:
                    Effect firebolt = new Firebolt(2);
                    if (rand.nextInt(20) + accuracy + 1 >= target.armour) {
                        target.addEffect(firebolt);
                        System.out.printf("%s lasi tulekuuliga %s pihta ja tegi %d kahju\n", super.name, target.name, 2);
                    } else {
                        System.out.printf("%s lasi tulekuuliga %s pihta kuid tulekuul susises ohutult %s rüüsse\n", super.name, target.name, target.name);
                    }
                    actionPoints -= firebolt.requiredActionPoints();
                    break;
                case 1:
                    Effect spiderweb = new Spiderweb();
                    if (rand.nextInt(20) + accuracy + 1 >= target.armour) {
                        target.addEffect(spiderweb);
                        System.out.printf("%s lasi ämblikuvõrgu %s pihta ja tegi pani %s lõksu\n", super.name, target.name, target.name);
                    } else {
                        System.out.printf("%s lasi ämblikuvõrgu %s pihta kuid %s lõikas võrgu poole lennu pealt mõõgaga pooleks\n", super.name, target.name, target.name);
                    }
                    actionPoints -= spiderweb.requiredActionPoints();
                    break;
            }
        }
    }

    public Wizard(double accuracy, double armour, double health, String name) {
        super(accuracy, armour, health, name);
    }
}
