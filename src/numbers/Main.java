package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        long userInput = -1;
        String inputString;
        String[] inputParts;
        int count;

        System.out.println("Welcome to Amazing Numbers!");
        printInstructions();

        while (userInput != 0) {
            System.out.print("\nEnter a request: > ");
            inputString = keyboard.nextLine();
            if ("".equals(inputString)) {
                printInstructions();
                continue;
            }

            inputParts = inputString.split(" ");
            if (firstParameterIsValid(inputParts[0])) {
                userInput = Long.parseLong(inputParts[0]);
                if (userInput == 0) {
                    break;
                }

                if (inputParts.length > 1) {
                    count = Integer.parseInt(inputParts[1]);
                    if (inputParts.length > 2) {
                        NumberProperties.printPropertiesBySearch(userInput, count, inputParts[2]);
                    } else {
                        printList(userInput, count);
                    }
                } else {
                    NumberProperties.printProperties(userInput);
                }
            } else {
                System.out.println("\nThe first parameter should be a natural number or zero.");
            }

        }

        System.out.println("Goodbye!");
    }

    public static void printInstructions() {
        System.out.println("\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }

    public static boolean firstParameterIsValid(String str) {
        for (char c: str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return Long.parseLong(str) >= 0;
    }

    public static void printList(Long num, int count) {
        if (count > 0) {
            NumberProperties.printPropertiesList(num, count);
            System.out.println();
        } else {
            System.out.println("\nThe second parameter should be a natural number.");
        }
    }





}
