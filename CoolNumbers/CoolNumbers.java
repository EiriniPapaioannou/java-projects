import java.util.Scanner;

/*
 * A class for finding "cool numbers" between two bounds.
 * A number is cool if all its non-zero digits divide evenly into a given number n.
 */
public class CoolNumbers {

    // Returns true if every non-zero digit in x divides evenly into n
    public static boolean isCool(int x, int n) {
        int digitCount = String.valueOf(x).length();  // total digits in x
        int count = 0;
        int temp = x;

        while (temp > 0) {
            int digit = temp % 10;

            if (digit == 0 || n % digit != 0) {
                return false;
            }

            count++;
            temp /= 10;
        }

        return count == digitCount;
    }

    // Counts how many "cool" numbers exist from a to b (inclusive) relative to n
    public static int countCools(int n, int a, int b) {
        int count = 0;
        for (int i = a; i <= b; i++) {
            if (isCool(i, n)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // -- TESTING/USER INTERACTION --
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number (n):");
        int n = scanner.nextInt();

        System.out.println("Enter lower bound (a):");
        int a = scanner.nextInt();

        System.out.println("Enter upper bound (b):");
        int b = scanner.nextInt();

        // -- TESTING PURPOSES: Show all cool numbers in the range --
        System.out.println("Cool numbers in range:");
        for (int i = a; i <= b; i++) {
            if (isCool(i, n)) {
                System.out.println(i);
            }
        }

        // Count and print how many cool numbers exist
        int total = countCools(n, a, b);
        System.out.println("Total cool numbers: " + total);
    }
}
