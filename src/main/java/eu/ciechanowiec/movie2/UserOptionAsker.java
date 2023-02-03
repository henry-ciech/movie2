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
                SelectOption:
                1. get random movie to watch
                2. see whole movies to watch list
                3. see whole seen movies list
                """);
            choice = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(choice, 3));

        return Integer.parseInt(choice);
    }

    int optionSelectorAfterMovieToWatchList() {
        String choice;
        do {
            printer.print("""
                SelectOption:
                1. delete
                2. change
                3. add
                4. mark watched movie
                5. set movie score
                """);
            choice = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(choice, 5));

        return Integer.parseInt(choice);
    }

    int optionSelectorAfterMovieSeenList() {
        String choice;
        do {
            printer.print("""
                SelectOption:
                1. delete
                2. change
                3. add
                4. set movie score
                """);
            choice = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(choice, 4));

        return Integer.parseInt(choice);
    }
}
