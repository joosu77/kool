package dndude;
public class Lahing {
    public static void main(String[] args) {

        Dude kuriVoitleja = new Wizard(6, 14, 14, "Kati");
        Dude heaVoitleja = new Fighter(4, 16, 16, "Mati");

        while (kuriVoitleja.isAlive() && heaVoitleja.isAlive()) {
            kuriVoitleja.takeTurn(heaVoitleja);
            heaVoitleja.takeTurn(kuriVoitleja);
            System.out.printf("Kurjal %sl on %.0f elu, heal %sl on %.0f elu\n", kuriVoitleja.name, kuriVoitleja.health, heaVoitleja.name, heaVoitleja.health);
        }
        if (kuriVoitleja.isAlive()) {
            System.out.printf("Kuri võlur %s võitis\n", kuriVoitleja.name);
        } else {
            System.out.printf("Hea rüütel %s võitis\n", heaVoitleja.name);
        }
    }
}
