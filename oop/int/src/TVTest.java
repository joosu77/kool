import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TVTest {
    public static void main(String[] args) {
        TVStation PõhjaKorea_rahvuslikud_uudised = new TVStation(new ArrayList<>(Arrays.asList("USA majandus käib alla", "Kim jong un on leidnud ravi koroonaviirusele", "Põhja-Korea heaoluindeks läheneb suurel kiirusel 100%le")));
        TVStation FoxNews = new TVStation(new ArrayList<>(Arrays.asList("Tualettpaber on poodides otsas","Trump tegi koroonatesti, tulemus ilmub varsti")));
        PirateStation anonymous = new PirateStation();
        PõhjaKorea_rahvuslikud_uudised.subscribe(anonymous);
        FoxNews.subscribe(anonymous);
        TV KimJongUn = new TV("Kim Jong-un");
        PõhjaKorea_rahvuslikud_uudised.subscribe(KimJongUn);
        FoxNews.subscribe(KimJongUn);
        TV KimYongNam = new TV("Kim Yong-nam");
        PõhjaKorea_rahvuslikud_uudised.subscribe(KimYongNam);
        TV PakPongJu = new TV("Pak Pong-ju");
        anonymous.subscribe(PakPongJu);
        PõhjaKorea_rahvuslikud_uudised.sendNews();
        FoxNews.sendNews();
    }
}
