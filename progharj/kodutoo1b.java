/*****************************************************************************
 * Programmeerimisharjutused. LTAT.03.007
 * 2019/2020 kevadsemester
 *
 * Kodutöö. Ülesanne nr 1b
 * Teema: Tsüklid I
 *
 * Autor: Joosep Näks
 ******************************************************************************/
 
class kodutoo1b {
    public static void main (String[] args) {
        System.out.print("Kodutöö nr 1b.");
        prindiString(" ", 53);
        System.out.println("Programmi väljund");
        prindiString("=", 84);
        System.out.println();
        
        System.out.println("\nRUUT, n = 7:");
        ruut(7);
        System.out.println("\nRUUT, n = 17:");
        ruut(17);
        System.out.println("\nROMB, n = 7:");
        romb(7);
        System.out.println("\nROMB, n = 17:");
        romb(17);
        System.out.println("\nTELK, n = 4:");
        telk(4);
        System.out.println("\nTELK, n = 11:");
        telk(11);
        System.out.println("\nSPIRAAL, n = 11:");
        spiraal(11);
        System.out.println("\nSPIRAAL, n = 21:");
        spiraal(21);
        
        prindiString("=", 84);
        System.out.print("Joosep Näks");
        prindiString(" ", 50);
        System.out.println(new java.sql.Timestamp(System.currentTimeMillis()));
    }
    
    /**
     * Prindib mingit sõne mitu korda välja.
     * 
     * @param prinditav Antud sõne.
     * @param kogus Kogus, mitu korda sõne prinditakse.
     */
    static void prindiString(String prinditav, int kogus) {
        for (int i = 0; i < kogus; i++) {
            System.out.print(prinditav);
        }
    }
    
    /**
     * Prindib standardväljundisse ruudu.
     *
     * @param suurus Ruudu küljepikkus.
     */
    static void ruut (int suurus) {
        // Prindin esimese rea:
        System.out.print("/");
        prindiString(" *",suurus-2);
        System.out.println(" \\");
        
        // Prindin keskmised read:
        for (int i = 0; i < suurus-2; i++) {
            System.out.print("*");
            prindiString("  ",suurus-2);
            System.out.println(" *");
        }
        
        // Prindin viimase rea:
        System.out.print("\\");
        prindiString(" *", suurus-2);
        System.out.println(" /");
    }
    
    /**
     * Prindib standardväljundisse rombi
     *
     * @param suurus Rombi diagonaali pikkus.
     */
    static void romb (int suurus) {
        // Prindin ülemise tipu:
        prindiString(" ", suurus / 2);
        System.out.println("^");
        
        // Prindin rombi ülemised servad:
        for (int i = 0; i < suurus / 2 - 1; i++) {
            prindiString(" ", suurus / 2 - i - 1);
            System.out.print("#");
            prindiString(" ", 2 * i + 1);
            System.out.println("#");
        }
        
        // Prindin keskmised nurgad:
        System.out.print("<");
        prindiString(" ", suurus - 2);
        System.out.println(">");
        
        // Prindin alumised servad:
        for (int i = suurus / 2 - 2; i >= 0; i--) {
            prindiString(" ", suurus / 2 - i - 1);
            System.out.print("#");
            prindiString(" ", 2 * i + 1);
            System.out.println("#");
        }
        
        // Prindin alumise nurga:
        prindiString(" ", suurus / 2);
        System.out.println("v");
    }
    
    /**
     * Prindib standardväljundisse telgi.
     * @param suurus Telgi kõrgus.
     */
    static void telk (int suurus) {
        // Prindin telgi tipu:
        prindiString(" ", suurus * 2 - 1);
        System.out.println("#");
        
        // Prindin ülejäänud telgi välja:
        for (int i = 1; i < suurus; i++) {
            prindiString(" ", suurus * 2 - i * 2);
            prindiString("#", i);
            prindiString(" ", 2 * (i - 1) + 1);
            prindiString("#", i);
            System.out.println();
        }
        // Prindin telgi aluse:
        prindiString("=", suurus*4);
        System.out.println();
    }
    
    /**
     * Prindib standardväljundisse spiraali.
     *
     * @param suurus Spiraali kõrgus.
     */
    static void spiraal (int suurus) {
        // valgeidParemal näitab igal real, mitu vertikaalse
        // joone juppi on horisontaalsest joonest paremal
        int valgeidParemal = 0;
        // valgeidVasakul näitab igal real, mitu vertikaalse
        // joone juppi on horisontaalsest joonest vasakul
        int valgeidVasakul = 0;
        // mustiParemal näitab igal real, mitu vertikaalse
        // joone juppi on tühja rea paremas ääres
        int mustiParemal = 1;
        // mustiVasakul näitab igal real, mitu vertikaalse
        // joone juppi on tühja rea vasakus ääres
        int mustiVasakul = 0;
        
        for (int i = 0; i < (suurus + 1) / 2; i++) {
            // Prindin välja horistontaalse joone ja
            // sellest otse paremal ja vasakul asuvad vertikaalsed jupid
            prindiString("#   ", valgeidVasakul);
            prindiString("# ",suurus - 1 - valgeidVasakul * 2 - valgeidParemal * 2);
            prindiString("  # ", valgeidParemal);
            System.out.println();
            // Prindin välja tühja rea ja selle kõrval olevad vertikaalsed jupid
            if (!(suurus % 2 == 0 && i == suurus / 4)){
                prindiString("#   ", mustiVasakul);
                prindiString("  ",suurus - 1 - mustiVasakul * 2 - mustiParemal * 2);
                prindiString("  # ", mustiParemal);
                System.out.println();
            }
            // Muudan muutujate valgeidParemal, valgeidVasakul, mustiParemal
            // ja mustiVasakul väärtuseid, kuna suuruse 4ga jagamise moodulist
            // sõltub, mis suunas spiraali keskmine ots suunatud on, on erinevatel
            // suuruse 4ga jagamise mooduli väärtustel muutujate väärtustamine erinev
            if (suurus % 4 == 1 || suurus % 4 == 0){
            
                if (i < suurus / 4) {
                    mustiVasakul++;
                } else {
                    mustiVasakul--;
                }
                
                if (i < suurus / 4) {
                    if (i != 0)
                        valgeidVasakul++;
                } else if (i > suurus / 4){
                    valgeidVasakul--;
                }
                
                if (i < suurus / 4 - 1) {
                    mustiParemal++;
                } else if (i > suurus / 4 - 1) {
                    mustiParemal--;
                }
                
                if (i < suurus / 4) {
                    valgeidParemal++;
                } else {
                    valgeidParemal--;
                }
            
            } else if (suurus % 4 == 3 || suurus % 4 == 2){
                
                if (i < suurus / 4) {
                    mustiVasakul++;
                } else if (i > suurus / 4) {
                    mustiVasakul--;
                }
                
                if (i < suurus / 4 + 1) {
                    if (i != 0)
                        valgeidVasakul++;
                } else {
                    valgeidVasakul--;
                }
                
                if (i < suurus / 4) {
                    mustiParemal++;
                } else {
                    mustiParemal--;
                }
                
                if (i < suurus / 4) {
                    valgeidParemal++;
                } else if (i > suurus / 4){
                    valgeidParemal--;
                }
            }
        }
        // Paarisarvuliste suurustega vahetub spiraali keskel tühjade
        // ja horisontaalsete ridade paarsus, seega tuleb lõppu
        // üks horisontaalne rida lisada
        if (suurus % 2 == 0) {
            prindiString("# ", suurus - 1);
            System.out.println();
        }
    }
}