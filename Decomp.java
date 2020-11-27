
public class Decomp {

    public static String decomp(int n) {
        StringBuilder sb = new StringBuilder();
        String result = "";
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                int count = divisionCount(i, n);
                if (count == 1)
                    sb.append(i).append(" * ");
                else
                    sb.append(i).append("^").append(count).append(" * ");
            }
        }
        if (sb.toString().endsWith(" * "))
            result = sb.substring(0, sb.length()-3);
        return result;
    }

    public static int divisionCount(int i, int n) {
        int count = 0;
        int degree = 1;
        while (true) {
            int step = Math.floorDiv(n, (int)Math.pow(i, degree));
            if (step > 0) {
                count += step;
                degree++;
            }
            else
                return count;
        }
    }

    public static boolean isPrime(int p) { //check out does the number prime or not
        for (int i = 2; i < p; i++) if (p%i == 0) return false;
        return true;
    }

    public static void main(String[] args) {
        long a = System.currentTimeMillis();
        for (int i = 3060; i < 3500; i++)
            System.out.println(decomp(i));
        long b = System.currentTimeMillis();
        System.out.println("time = " + (b - a) + "ms");
    }
}
