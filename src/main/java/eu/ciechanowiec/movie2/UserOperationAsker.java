package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.InputChecker;
import eu.ciechanowiec.movie2.util.Printer;

import java.util.Scanner;

class UserOperationAsker {

    private static final int MAX_VALID_NUMBER = 999;
    private final Scanner scanner;
    private final Printer printer;
    private final InputChecker inputChecker;
    private final MovieRepository movieRepository;

    UserOperationAsker() {
        scanner = new Scanner(System.in);
        inputChecker = new InputChecker();
        printer = new Printer();
        movieRepository = new MovieRepository();
    }

    boolean isMovieWatched() {
        String isWatched;
        do {
            printer.println("Have you watched that movie?");
            isWatched = scanner.nextLine();
        } while (!inputChecker.isYesOrNo(isWatched));
        return isWatched.equalsIgnoreCase("yes");
    }

    String getMovieTitleToSet() {
        printer.println("Enter movie title you want to set:");
        return scanner.nextLine();
    }

    String getMovieToAdd() {
        printer.println("Enter movie you would like to add");
        return scanner.nextLine();
    }

    int getMovieToChange() {
        String movieToChange;
        do {
            printer.println("Enter movie ID you want to change:");
            movieToChange = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(movieToChange, MAX_VALID_NUMBER)
                || movieRepository.isIdUsed(Integer.parseInt(movieToChange)));
        return Integer.parseInt(movieToChange);
    }

    int getMovieToDelete() {
        String movieToDelete;
        do {
            printer.println("Enter movie ID you want to delete:");
            movieToDelete = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(movieToDelete, MAX_VALID_NUMBER)
                || movieRepository.isIdUsed(Integer.parseInt(movieToDelete)));
        return Integer.parseInt(movieToDelete);
    }

    int getMovieScore() {
        String score;
        do {
            printer.println("Enter score:");
            score = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(score, 10));

        return Integer.parseInt(score);
    }

    int askAboutOption() {
        String choice;
        do {
            printer.print("""
                Select Option:
                1. create
                2. print whole list
                3. update
                4. delete
                """);
            choice = scanner.nextLine();
        } while (inputChecker.checkIsInputNotValid(choice, 4));

        return Integer.parseInt(choice);
    }
}
