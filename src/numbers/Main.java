package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        long userInput = -1;

        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter 0 to exit.");

        while (userInput != 0) {
            System.out.print("\nEnter a request: > ");
            userInput = keyboard.nextLong();
            System.out.println();

            if (userInput < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
            } else if (userInput > 0){
               NumberProperties.printProperties(userInput);
            }
        }

        System.out.println("Goodbye!");
    }


}
