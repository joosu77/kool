import java.util.List;

public class Lõbustuspark {
    List<Lõbustus> lõbustused;

    public Lõbustuspark(List<Lõbustus> lõbustused) {
        this.lõbustused = lõbustused;
    }

    public void alustaSeiklust(Külastaja isik) {
        System.out.println("alustan seiklust");
        for (Lõbustus lõbustus : this.lõbustused) {
            lõbustus.lõbusta(isik);
        }
        System.out.println(isik.kõikKirjeldused());
        System.out.println("Pargi külastus läks maksma " + isik.kuludeSumma() + " rahaühikut");
    }
}
