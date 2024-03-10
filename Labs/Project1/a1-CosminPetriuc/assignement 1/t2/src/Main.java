public class Main {
    public static void main(String[] args) {
        if(args.length < 1)
            System.out.println("Please enter at least an argument");

        double rez = Double.parseDouble(args[0]);
        for(int i = 1; i< args.length; i++){
            if(Double.parseDouble(args[i]) > rez){
                rez = Double.parseDouble(args[i]);
            }
        }
        System.out.println(rez);
    }
}