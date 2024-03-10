public class Main {
    public static int gcd(int a, int b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
    }

    public static void main(String[] args) {
        if(args.length < 1)
            System.out.println("Please enter at least an argument");

        int rez = gcd(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        for(int i=2; i < args.length; i++)
            rez = gcd(rez, Integer.parseInt(args[i]));

        System.out.println(rez);
    }
}