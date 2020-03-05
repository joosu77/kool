class Kodutoo3c {
    static int[] KadanePluss(int[] a){ // Kadane modifitseeritud meetod
        // Antud: järjend a
        // Tulemus: tagastatakse 3-elemendiline järjend, milles viimaseks
        //	on suurim järjestikuste elementide summa järjendis a
        //	ning kaheks esimeseks sellise summaga alamjärjendi
        //	algus- ja lõpuindeksid
        // Eeskuju: ......
        int jooksevSumma = 0;
        int jooksevAlgus = 0;
        int maksimaalneSumma = 0;
        int maksimaalseAlgus = -1;
        int maksimaalneLopp = -1;
            // ...... Kirjeldada int-tüüpi abimuutujad
            // Põhitsükkel:
        for(int i = 1; i < a.length; i++ ){
            jooksevSumma += a[i];
            if (maksimaalneSumma < jooksevSumma) {
                maksimaalneSumma = jooksevSumma;
                maksimaalseAlgus = jooksevAlgus;
                maksimaalneLopp = i;
            }
            if (jooksevSumma < 0) {
                jooksevSumma = 0;
                jooksevAlgus = i+1;
            }
        }
        // Mõnede abimuutujate väärtuste abil luua tulemus ja tagastada:
        return new int[]{maksimaalneSumma, maksimaalneAlgus, maksimaalneLopp};
    }//KadanePluss
    
    static int[] joumeetod(int[] a){
        int jooksevSumma = 0;
        int jooksevAlgus = 0;
        int maksimaalneSumma = 0;
        int maksimaalseAlgus = -1;
        int maksimaalneLopp = -1;
        for (int i=0;i<a.length;i++){
            int[] poolikudJadad = new int[a.length-i];
            for (int j=0;j<a.length;j++){
                   
            }
        }
    }
}