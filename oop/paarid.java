import java.util.Arrays;

class paarid {
    public static void main (String[] args) {
        // Loen poiste koguse ja loon poiste pikkuste massiivi:
        int poisteArv = Integer.parseInt (args[0]);
        int[] poisid = new int[poisteArv];
        
        // Loen sisse poiste pikkused:
        for (int i = 0; i < poisteArv; i++){
            poisid[i] = Integer.parseInt(args[i+1]);
        }
        
        // Loen tydrukute koguse ja loon tydrukute pikkuste massiivi:
        int tydrukuteArv = Integer.parseInt (args[poisteArv+1]);
        int[] tydrukud = new int[tydrukuteArv];
        
        // Loen sisse tydrukud pikkused:
        for (int i = 0; i < tydrukuteArv; i++){
            tydrukud[i] = Integer.parseInt(args[i+2+poisteArv]);
        }
        
        // Koostan paarid:
        int[][] paarid = combine (poisid, tydrukud);
        
        // Prindin paarid välja:
        for (int i = 0; i < paarid.length; i++) {
            System.out.println(paarid[i][0] + " ja " + paarid[i][1]);
        }
    }
    
    private static int[][] combine (int[] poisid, int[] tydrukud) {
        // Sorteerin massiivid:
        Arrays.sort(poisid);
        Arrays.sort(tydrukud);
        
        // Määran paaride koguse:
        int pikkus = Math.min(poisid.length,tydrukud.length);
        
        // Koostan paaride massiivi:
        int[][] paarid = new int[pikkus][2];
        
        for (int i = 0; i < pikkus; i++) {
            paarid[i][0] = poisid[i];
            paarid[i][1] = tydrukud[i];
        }
        
        return paarid;
    }
}