package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.InputChecker;
import eu.ciechanowiec.movie2.util.Printer;

import java.util.Scanner;

class UserOptionAsker {

    private final Printer printer;
    private final InputChecker inputChecker;
    private final Scanner scanner;

    UserOptionAsker() {
        printer = new Printer();
        inputChecker = new InputChecker();
        scanner = new Scanner(System.in);
    }

    int optionSelector() {
        String choice;
        do {
            printer.print("""
                Select Option:
                1. print whole list
                2. create
                3. update
                4. delete
                """);
            choice = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(choice, 4));

        return Integer.parseInt(choice);
    }
}
