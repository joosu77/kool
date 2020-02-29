import java.util.ArrayList;
import java.util.List;

public class Külastaja {
    private List<String> külastuseKirjeldused;
    private int vanus;
    private double kulud;

    public void lisaKulu(double summa) {
        kulud += summa;
    }

    public double kuludeSumma() {
        return kulud;
    }

    public int getVanus() {
        return vanus;
    }

    public Külastaja(int vanus) {
        this.külastuseKirjeldused = new ArrayList<String>();
        this.vanus = vanus;
    }

    public void lisaKirjeldus(String kirje) {
        külastuseKirjeldused.add(kirje);
    }

    public List<String> kõikKirjeldused() {
        return külastuseKirjeldused;
    }
}
