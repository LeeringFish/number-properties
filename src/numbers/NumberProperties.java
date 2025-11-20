package numbers;

public class NumberProperties {

    public static boolean isOdd(long num) {
        return num % 2 != 0;
    }

    public static boolean isEven(long num) {
        return num % 2 == 0;
    }

    public static boolean isBuzz(long num) {
        return num % 10 == 7 || num % 7 == 0;
    }

    public static boolean isDuck(long num) {
        while (num >= 10) {
            if (num % 10 == 0) {
                return true;
            }
            num /= 10;
        }

        return false;
    }

    public static boolean isPalindrome(long num) {
        String numToString = Long.toString(num);
        int length = numToString.length();
        for (int i = 0, j = length - 1; i < numToString.length() / 2; i++, j--) {
            if (numToString.charAt(i) != numToString.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public static void printProperties(long num) {
        System.out.println("Properties of " + num);
        System.out.println("\t\teven: " + isEven(num));
        System.out.println("\t\todd: " + isOdd(num));
        System.out.println("\t\tbuzz: " + isBuzz(num));
        System.out.println("\t\tduck: " + isDuck(num));
        System.out.println("\t\tpalindromic: " + isPalindrome(num));
    }

}
