public class Main {
    public static boolean isPrime(int nr) {
        if (nr < 2)
            return false;
        if (nr % 2 == 0 && nr != 2)
            return false;

        for (int d = 3; d*d < nr; d = d+2) {
            if (nr % d == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        if(args.length < 1)
            System.out.println("Please enter at least an argument");

        for(int i = 0; i< args.length; i++){
            if(isPrime(Integer.parseInt(args[i])) == true){
                System.out.println(args[i]);
            }
        }
    }
}