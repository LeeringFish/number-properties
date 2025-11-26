package numbers;

import java.lang.Math;

public class NumberProperties {
    static final String PROPERTIES = "buzz, duck, palindromic, gapful, spy, square, sunny, even, odd";

    public static boolean isOdd(Long num) {
        return num % 2 != 0;
    }

    public static boolean isEven(Long num) {
        return num % 2 == 0;
    }

    public static boolean isBuzz(Long num) {
        return num % 10 == 7 || num % 7 == 0;
    }

    public static boolean isDuck(Long num) {
        while (num >= 10) {
            if (num % 10 == 0) {
                return true;
            }
            num /= 10;
        }
        return false;
    }

    public static boolean isPalindrome(Long num) {
        String numToString = Long.toString(num);
        int length = numToString.length();
        for (int i = 0, j = length - 1; i < numToString.length() / 2; i++, j--) {
            if (numToString.charAt(i) != numToString.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGapful(Long num) {
        String numToString = Long.toString(num);
        int length = numToString.length();
        String firstAndLast = String.format("%c%c", numToString.charAt(0), numToString.charAt(length - 1));
        return length >= 3 && num % Integer.parseInt(firstAndLast) == 0;
    }

    public static boolean isSpy(Long num) {
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

    public static boolean isSquare(Long num) {
        long r = (long) Math.sqrt(num);
        return r * r == num;
    }

    public static boolean isSunny(Long num) {
        return isSquare(num + 1);
    }

    public static boolean hasProperty(Long num, String property) {
        return switch (property.toUpperCase()) {
            case "BUZZ" -> isBuzz(num);
            case "DUCK" -> isDuck(num);
            case "PALINDROMIC" -> isPalindrome(num);
            case "GAPFUL" -> isGapful(num);
            case "SPY" -> isSpy(num);
            case "SQUARE" -> isSquare(num);
            case "SUNNY" -> isSunny(num);
            case "EVEN" -> isEven(num);
            case "ODD" -> isOdd(num);
            default -> false;
        };
    }

    public static boolean propertiesAreMutuallyExclusive(String propOne, String propTwo) {
        return ("even odd".contains(propOne) && "even odd".contains(propTwo)) ||
                ("square sunny".contains(propOne) && "square sunny".contains(propTwo)) ||
                ("spy duck".contains(propOne) && "spy duck".contains(propTwo));
    }

    public static void printProperties(Long num) {
        System.out.println("\nProperties of " + num);
        System.out.println("\t\tbuzz: " + isBuzz(num));
        System.out.println("\t\tduck: " + isDuck(num));
        System.out.println("\t\tpalindromic: " + isPalindrome(num));
        System.out.println("\t\tgapful: " + isGapful(num));
        System.out.println("\t\tspy: " + isSpy(num));
        System.out.println("\t\tsquare: " + isSquare(num));
        System.out.println("\t\tsunny: " + isSunny(num));
        System.out.println("\t\teven: " + isEven(num));
        System.out.println("\t\todd: " + isOdd(num));
    }

    public static void printPropertiesList(Long num, int count) {
        for (int i = 0; i < count; i++) {
            StringBuilder builder = new StringBuilder();
            for (String property: PROPERTIES.split(", ")) {
                if (hasProperty(num, property)) {
                    builder.append(property);
                    if (!property.equals("even") && !property.equals("odd")) {
                        builder.append(", ");
                    }
                }
            }

            System.out.printf("\n\t\t\t%d is %s", num, builder);
            num++;
        }

    }

    public static void printPropertiesBySearch(Long num, int count, String search) {
        search = search.toLowerCase();

        if (PROPERTIES.contains(search)) {
            while (count > 0) {
                if (hasProperty(num, search)) {
                    printPropertiesList(num, 1);
                    count--;
                }
                num++;
            }
            System.out.println();

        } else {
            System.out.printf("The property [%s] is wrong.\n", search.toUpperCase());
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
        }
    }

    public static void printPropertiesByTwoSearches(Long num, int count, String searchOne, String searchTwo) {
        searchOne = searchOne.toLowerCase();
        searchTwo = searchTwo.toLowerCase();

        if (PROPERTIES.contains(searchOne) && PROPERTIES.contains(searchTwo)) {
            if (propertiesAreMutuallyExclusive(searchOne, searchTwo)) {
                System.out.printf("\nThe request contains mutually exclusive properties: [%s, %s]\n",
                        searchOne.toUpperCase(), searchTwo.toUpperCase());
                System.out.println("There are no numbers with these properties.");
            } else {
                while (count > 0) {
                    if (hasProperty(num, searchOne) && hasProperty(num, searchTwo)) {
                        printPropertiesList(num, 1);
                        count--;
                    }
                    num++;
                }
                System.out.println();
            }
        } else {
            System.out.println();
            if (!PROPERTIES.contains(searchOne)) {
                System.out.printf("The property [%s] is wrong.\n", searchOne.toUpperCase());
            }

            if (!PROPERTIES.contains(searchTwo)) {
                System.out.printf("The property [%s] is wrong.\n", searchTwo.toUpperCase());
            }
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]");
        }
    }

}
