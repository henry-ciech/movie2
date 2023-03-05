package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.InputChecker;
import eu.ciechanowiec.movie2.util.Printer;

import java.util.Scanner;

class UserOperationAsker {

    private final Scanner scanner;
    private final Printer printer;
    private final InputChecker inputChecker;

    UserOperationAsker() {
        scanner = new Scanner(System.in);
        inputChecker = new InputChecker();
        printer = new Printer();
    }

    boolean isMovieWatched() {
        String isWatched;
        do {
            printer.println("Have you watched that movie?");
            isWatched = scanner.nextLine();
        } while (!inputChecker.isYesOrNo(isWatched));
        return isWatched.equalsIgnoreCase("yes");
    }

    String getMovieToSet() {
        printer.println("Enter movie title you want to set:");
        return scanner.nextLine();
    }

    String getMovieToAdd() {
        printer.println("Enter movie you would like to add");
        return scanner.nextLine();
    }

    int getMovieToChange(int numberOfLines) {
        String movieToChange;
        do {
            printer.println("Enter movie ID you want to change:");
            movieToChange = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(movieToChange, numberOfLines));
        return Integer.parseInt(movieToChange);
    }

    int getMovieScore() {
        String score;
        do {
            printer.println("Enter score:");
            score = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(score, 10));

        return Integer.parseInt(score);
    }

    int getMovieToDelete(int numberOfLines) {
        String movieToDelete;
        do {
            printer.println("Enter movie ID you want to delete:");
            movieToDelete = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(movieToDelete, numberOfLines));
        return Integer.parseInt(movieToDelete);
    }
}
