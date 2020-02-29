class paaris {
    public static void main(String[] args) {
        int sisend = Integer.parseInt(args[0]);
        joonistaRuut(sisend);
        
        int[] algarvud = new int [100];
        algarvud[3] = 2;
        soel (algarvud);
        System.out.println(algarvud[3]);
        
    }
    static void joonistaRuut(int suurus) {
        if (suurus>1) {
            for (int i=0;i<suurus;i++)
                System.out.print('#');
            System.out.print('\n');
        }
        for (int i=0;i<suurus-2;i++) {
            System.out.print('#');
            for (int j=0;j<suurus-2;j++) {
                System.out.print(' ');
            }
            System.out.println('#');
        }
        for (int i=0;i<suurus;i++)
            System.out.print('#');
        System.out.print('\n');
    }
    static void soel (int[] arr) {
        arr[3]=3;
    }
    static void paarsuseKontroll(int sisend) {
        if (sisend % 2 == 0) {
            System.out.println("Arv on paarisarv!");
        } else {
            System.out.println("Arv on paarituarv!");
        }
    }
    static void kuubiRistsummaKuubivõrdsuseKontroll(int sisend) {
        int kuup = sisend*sisend;
        int ristsumma = 0;
        int iteraator = 1;
        int jargNumber = sisend/iteraator-(sisend/(iteraator*10))*10;
        while (jargNumber>0) {
            iteraator *= 10;
            jargNumber = sisend/iteraator-(sisend/(iteraator*10))*10;
            ristsumma += jargNumber;
        }
        if (kuup==ristsumma*ristsumma)
            System.out.println("on sama");
        else
            System.out.println("ei ole sama");
    }
    static void pooraArvu (int sisend) {
        int viimaneNumber = sisend%10;
        int tempSisend = sisend;
        while (tempSisend!=0) {
            viimaneNumber *= 10;
            tempSisend /= 10;
        }
        viimaneNumber /= 10;
        int valjund = sisend/10+viimaneNumber;
        System.out.println("Pooratud arv on " + valjund);
    }
}