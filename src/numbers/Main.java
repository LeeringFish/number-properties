package numbers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter a natural number:");
        System.out.print("> ");
        int num = keyboard.nextInt();

        if (num < 1) {
            System.out.println("The number is not natural!");
        } else {
            System.out.println("Properties of " + num);
            System.out.println("\t\teven: " + NumberProperties.isEven(num));
            System.out.println("\t\todd: " + NumberProperties.isOdd(num));
            System.out.println("\t\tbuzz: " + NumberProperties.isBuzz(num));
            System.out.println("\t\tduck: " + NumberProperties.isDuck(num));
        }
    }


}
