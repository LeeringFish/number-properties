package numbers;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class NumberProperties {
    static final String PROPERTIES = "buzz, duck, palindromic, gapful, spy, square, sunny, jumping, even, odd";

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

    public static boolean isSquare(long num) {
        long r = (long) Math.sqrt(num);
        return r * r == num;
    }

    public static boolean isSunny(long num) {
        return isSquare(num + 1);
    }

    public static boolean isJumping(long num) {

        while (num >= 10) {
            long last = num % 10;
            long prev = (num / 10) % 10;
            if (Math.abs(last - prev) != 1) {
                return false;
            }
            num /= 10;
        }

        return true;
    }

    public static long sumDigitSquares(long num) {
        long sum = 0;
        long digit;

        while (num >= 10) {
            digit = num % 10;
            sum += digit * digit;
            num /= 10;
        }
        sum += num * num;
        return sum;
    }

    public static boolean hasProperty(long num, String property) {
        return switch (property.toUpperCase()) {
            case "BUZZ" -> isBuzz(num);
            case "DUCK" -> isDuck(num);
            case "PALINDROMIC" -> isPalindrome(num);
            case "GAPFUL" -> isGapful(num);
            case "SPY" -> isSpy(num);
            case "SQUARE" -> isSquare(num);
            case "SUNNY" -> isSunny(num);
            case "JUMPING" -> isJumping(num);
            case "EVEN" -> isEven(num);
            case "ODD" -> isOdd(num);
            default -> false;
        };
    }

    public static boolean hasAllProperties(long num, String[] properties) {
        for (String property: properties) {
            if (!hasProperty(num, property)) {
                return false;
            }
        }
        return true;
    }

    public static void printProperties(long num) {
        System.out.println("\nProperties of " + num);
        System.out.println("\t\tbuzz: " + isBuzz(num));
        System.out.println("\t\tduck: " + isDuck(num));
        System.out.println("\t\tpalindromic: " + isPalindrome(num));
        System.out.println("\t\tgapful: " + isGapful(num));
        System.out.println("\t\tspy: " + isSpy(num));
        System.out.println("\t\tsquare: " + isSquare(num));
        System.out.println("\t\tsunny: " + isSunny(num));
        System.out.println("\t\tjumping: " + isJumping(num));
        System.out.println("\t\teven: " + isEven(num));
        System.out.println("\t\todd: " + isOdd(num));
    }

    public static void printPropertiesList(long num, int count) {
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

    public static void printPropertiesBySearch(long num, int count, String[] properties) {
        ArrayList<String> invalidProperties = getInvalidProperties(properties);
        if (invalidProperties.isEmpty()) {
            ArrayList<String> exclProps = mutuallyExclusiveProperties(properties);
            if (exclProps.isEmpty()) {
                while (count > 0) {
                    if (hasAllProperties(num, properties)) {
                        printPropertiesList(num, 1);
                        count--;
                    }
                    num++;
                }
                System.out.println();
            } else {
                System.out.printf("\nThe request contains mutually exclusive properties: %s\n", exclProps);
                System.out.println("There are no numbers with these properties.");
            }
        } else {
            System.out.println();
            if (invalidProperties.size() == 1) {
                System.out.printf("The property [%s] is wrong.\n", invalidProperties.getFirst());
            } else {
                System.out.printf("The property %s is wrong.\n", invalidProperties);
            }
            System.out.println("Available properties: [BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, EVEN, ODD]");
        }
    }

    public static ArrayList<String> getInvalidProperties(String[] properties) {
        ArrayList<String> invalidProperties = new ArrayList<>();
        for (String property: properties) {
            if (!PROPERTIES.contains(property.toLowerCase())) {
                invalidProperties.add(property.toUpperCase());
            }
        }
        return invalidProperties;
    }

    public static String getMutuallyExclusiveProperty(String property) {
        return switch (property.toUpperCase()) {
            case "EVEN" -> "ODD";
            case "ODD" -> "EVEN";
            case "SQUARE" -> "SUNNY";
            case "SUNNY" -> "SQUARE";
            case "SPY" -> "DUCK";
            case "DUCK" -> "SPY";
            default -> "";
        };
    }

    public static ArrayList<String> mutuallyExclusiveProperties(String[] props) {
        ArrayList<String> properties = new ArrayList<>(Arrays.asList(props));
        ArrayList<String> propsToReturn = new ArrayList<>();

        for (String property: properties) {
            String otherProperty = getMutuallyExclusiveProperty(property);
            if (properties.contains(otherProperty) || properties.contains(otherProperty.toLowerCase())) {
                propsToReturn.add(property.toUpperCase());
            }
        }

        return propsToReturn;
    }

}
