package numbers;

public class NumberProperties {

    public static boolean isOdd(int num) {
        return num % 2 != 0;
    }

    public static boolean isEven(int num) {
        return num % 2 == 0;
    }

    public static boolean isBuzz(int num) {
        return num % 10 == 7 || num % 7 == 0;
    }

    public static boolean isDuck(int num) {
        while (num >= 10) {
            if (num % 10 == 0) {
                return true;
            }
            num /= 10;
        }

        return false;
    }

}
