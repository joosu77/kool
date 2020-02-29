import java.util.Random;

class prax2 {
    public static void main (String[] args) {
        /*long s = 0;
        long i = 3810000;
        while (s>-1) {
            s = jada(i);
            i+=1;
            System.out.println(s);
        }
        System.out.println(i);
        */
        //System.out.println(jada2());
        //System.out.println(piOtsija(10000000));
        //System.out.println(syt2(Integer.parseInt(args[0]),Integer.parseInt(args[1])));
        System.out.println(ostjateTeenindaja(Integer.parseInt(args[0])));
    }
    static double harmoonilineJada (int num) {
        double sum = 0;
        for (long i=1;i<num+1;i++) {
            sum += (double)1/i;
        }
        return sum;
    }
    
    // 2342 on int tüüpi suurim
    // 3 810 778 on long tüüpi suurim
    static long jada (long num) {
        long sum = 0;
        for (long i=1; i<num+1; i+=2){
            sum += i*(i+1);
        }
        return sum;
    }
    
    // 23452 on tulemus
    static long jada2 () {
        long sum = 0;
        for (int i=2; i<51; i+=4) {
            sum += i*(104-i);
            System.out.println(sum);
        }
        return sum;
    }
    
    static int syt (int a, int b) {
        System.out.println(a + " " + b);
        if (b==0 || a==b) {
            return a;
        } else {
            return syt(b, a%b);
        }
    }
    
    static int syt2 (int a, int b){
        int tempa = a;
        int tempb = b;
        while (tempa>0 && tempb>0){
            int c = tempb;
            tempb = tempa%tempb;
            tempa = c;
            System.out.println(tempa + " " + tempb);
        }
        return tempa;
    }
    
    static double juur (double sisend) {
        int piir = 1000;
        double juur = (sisend+1)/2;
        for (int i=0; i<piir; i++){
            juur = (juur+sisend/juur)/2;
        }
        return juur;
    }
    
    // 10 000 000 mahub umbes aega ära
    static double piOtsija (int kogus) {
        double sees = 0;
        Random rand = new Random();
        for (int i=0; i<kogus; i++){
            double a = Math.random();
            double b = Math.random();
            if (a*a+b*b < 1)
                sees++;
        }
        return 4*sees/kogus;
    }
    
    static long ostjateTeenindaja (long aeg) {
        long ostjaid = 0;
        long teenindatud = 0;
        boolean pooleli = false;
        Random rand = new Random();
        for (int i=0; i<aeg; i++) {
            if (Math.random()>0.5)
                ostjaid++;
            if (pooleli) {
                pooleli = false;
                teenindatud++;
                ostjaid--;
            } else if (ostjaid>0) {
                pooleli = true;
            }
        }
        return teenindatud;
    }
}