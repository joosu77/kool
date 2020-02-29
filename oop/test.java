public class test {
    static double aritkeskmine(double arv1, double arv2) {
        double summa = arv1 + arv2;
        return summa / 2;
    }
 
    public static void main(String[] args) {
        System.out.println("100ni: " + fracSum(100));
    }
    public static int count(int num){
        int sum = 0;
        for (int i=0;i<=num;i++){
            sum+=i;
        }  
        return sum;
    }
    public static double fracSum(double inp){
        double res = 0;
        for (int i=0;i<inp;i++){
            res+=(double)i/(i+2);
            System.out.println(res);
        }
        return res;
    }
}