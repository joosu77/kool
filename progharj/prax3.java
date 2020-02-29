import java.util.Random;

class prax3 {
    public static void main (String[] args) {
        /*int[] arr = {1,2,3,4,5,6,7,8,9,10};
        paiskaSegi (arr);
        int[] arr2 = {1,2,3,4,5,6,7,8,9};
        paiskaSegi (arr2);*/
        int[] arr = {1,4,70,3,95,4,100,100};
        leia2max(arr);
    }
    static void paiskaSegi(int[] arr){
        for (int i=0;i<arr.length;i++){
            if (i%2==0){
                System.out.print(arr[i/2]);
            } else {
                System.out.print(arr[arr.length-(i+1)/2]);
            }
            System.out.print(" ");
        }
        System.out.println();
    }
    //yl3.3
    static void leia2max(int[] arr) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        for (int i=0;i<arr.length;i++){
            if (arr[i]>max2){
                if (arr[i]>max1){
                    max2 = max1;
                    max1 = arr[i];
                } else {
                    max2 = arr[i];
                }
            }
        }
        System.out.printf("Maxid on %d ja %d",max1,max2);
    }
    //yl3.15
    static void kolmeKordseteSum(int[] arr) {
        int sum = 0;
        for (int i=0; i < arr.length; i+=3){
            if (arr[i] % 3 == 0){
                sum += arr[i];
            }
        }
        System.out.printf("Summa on %d\n",sum);
    }
    //yl3.6
    static void pikimKasvavAlamMassiiv(int[] arr){
        int pikkus = 1;
        int pikim = 0;
        int pikimaAlgus = 0;
        for (int i=1;i<arr.length;i++){
            if (arr[i]>arr[i-1]){
                pikkus++;
            } else {
                if (pikim<pikkus){
                    pikim = pikkus;
                    pikimaAlgus = i-pikkus;
                }
                pikkus = 1;
            }
            System.out.printf("%d, %d, %d \n",pikim,pikkus,pikimaAlgus);
        }
        System.out.printf("Pikima kasvava osajada esimene element on indeksil %d ja viimane indeksil %d\n", pikimaAlgus,pikimaAlgus+pikim-1);
    }
    //yl3.4
    static void yhekordsedElemendid(int[] arr){
        String valjund = "";
        for (int i=0;i<arr.length;i++){
            boolean korduv = false;
            for (int j=0;j<arr.length;j++){
                if (arr[i] == arr[j] && i!=j) {
                    korduv = true;
                }
            }
            if (!korduv)
                valjund += " "+Integer.toString(arr[i]);
        }
        System.out.println(valjund);
    }
}