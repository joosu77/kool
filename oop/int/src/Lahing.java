public class Lahing {
    public static void main(String[] args) {

        Dude kuriVoitleja = new Wizard(0.1,5,10,3);
        Dude heaVoitleja = new Fighter(0.8,12,20,2);

        while (kuriVoitleja.isAlive() && heaVoitleja.isAlive()) {
            kuriVoitleja.takeTurn(heaVoitleja);
            heaVoitleja.takeTurn(kuriVoitleja);
        }
    }
}
