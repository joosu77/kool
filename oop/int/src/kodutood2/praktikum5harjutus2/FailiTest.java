import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FailiTest {
    public static void main (String[] args) throws Exception {
        String kasutajanimi = args[0];
        Path root = Path.of(kasutajanimi);
        if (!Files.isDirectory(root)) {
            System.out.println("Ei leitud kataloogi.");
            return;
        }
        FailiVaatleja failiVaatleja = new FailiVaatleja(new ArrayList<String>());
        Files.walkFileTree(root, failiVaatleja);
        List<String> nimed = failiVaatleja.getFailiNimed();
        NimeVõrdleja nimeVõrdleja = new NimeVõrdleja();
        nimed.sort(nimeVõrdleja);
        System.out.println(nimed);
    }
}
