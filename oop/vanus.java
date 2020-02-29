public class vanus {
    public static void main (String args[]){
        int kp1 = 0;
        int kp2 = 0;
        kp1 += i(args[0])+i(args[2])*365+i(args[2])/4-i(args[2])/100+i(args[2])/400+i(args[1])*30+i(args[1])/2;
        if (i(args[1])<6 && i(args[1])%2==1)kp1++;
        kp2 += i(args[3])+i(args[5])*365+i(args[5])/4-i(args[5])/100+i(args[5])/400+i(args[4])*30+i(args[4])/2;
        if (i(args[4])<6 && i(args[4])%2==1)kp2++;
        System.out.println(kp2-kp1);
    }
    public static int i(String inp){
        return Integer.parseInt(inp);
    }
}