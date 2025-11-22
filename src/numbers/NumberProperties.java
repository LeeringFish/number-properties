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

    public static boolean isGapful(long num) {
        String numToString = Long.toString(num);
        int length = numToString.length();
        String firstAndLast = String.format("%c%c", numToString.charAt(0), numToString.charAt(length - 1));
        return length >= 3 && num % Integer.parseInt(firstAndLast) == 0;
    }

    public static boolean isSpy(long num) {
        String numToString = Long.toString(num);
        int sum = 0;
        int product = 1;
        int length = numToString.length();
        int current;

        for (int i = 0; i < length; i++) {
            current = Integer.parseInt(String.valueOf(numToString.charAt(i)));
            sum += current;
            product *= current;
        }

        return sum == product;
    }

    public static void printProperties(long num) {
        System.out.println("\nProperties of " + num);
        System.out.println("\t\tbuzz: " + isBuzz(num));
        System.out.println("\t\tduck: " + isDuck(num));
        System.out.println("\t\tpalindromic: " + isPalindrome(num));
        System.out.println("\t\tgapful: " + isGapful(num));
        System.out.println("\t\tspy: " + isSpy(num));
        System.out.println("\t\teven: " + isEven(num));
        System.out.println("\t\todd: " + isOdd(num));
    }

    public static void printPropertiesList(long num, int count) {
        for (int i = 0; i < count; i++) {
            StringBuilder builder = new StringBuilder();
            if (isBuzz(num)) {
                builder.append("buzz, ");
            }
            if (isDuck(num)) {
                builder.append("duck, ");
            }
            if (isPalindrome(num)) {
                builder.append("palindromic, ");
            }
            if (isGapful(num)) {
                builder.append("gapful, ");
            }

            if (isSpy(num)) {
                builder.append("spy, ");
            }

            if (isEven(num)) {
                builder.append("even");
            } else {
                builder.append("odd");
            }

            System.out.printf("\n\t\t\t%d is %s", num, builder);
            num++;
        }

    }

    public static void printPropertiesBySearch(long num, int count, String search) {
        final String PROPERTIES = "even odd buzz duck palindromic gapful spy";
        search = search.toLowerCase();

        if (PROPERTIES.contains(search)) {
            while (count > 0) {
                if (("buzz".equals(search) && isBuzz(num))
                        || ("duck".equals(search) && isDuck(num))
                        || ("palindromic".equals(search) && isPalindrome(num))
                        || ("gapful".equals(search) && isGapful(num))
                        || ("spy".equals(search) && isSpy(num))
                        || ("even".equals(search) && isEven(num))
                        || ("odd".equals(search) && isOdd(num))) {

                    printPropertiesList(num, 1);
                    count--;
                }
                num++;
            }
            System.out.println();

        } else {
            System.out.printf("The property [%s] is wrong.\n", search.toUpperCase());
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, EVEN, ODD]");
        }
    }

}
