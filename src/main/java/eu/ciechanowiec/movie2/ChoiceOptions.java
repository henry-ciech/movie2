package eu.ciechanowiec.movie2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;
import eu.ciechanowiec.movie2.util.Printer;

import lombok.extern.slf4j.Slf4j;
@Slf4j
class ChoiceOptions {

    private final RandomGenerator random;
    private final ChooseMachineEngine chooseMachineEngine;
    private final Printer printer;
    private final UserOperationAsker userOperationAsker;

    ChoiceOptions() {
        chooseMachineEngine = new ChooseMachineEngine();
        userOperationAsker = new UserOperationAsker();
        printer = new Printer();
        random = new Random();
    }

    void getRandomMovieInGivenRange(int range, File movie) {
        int randomNumber = random.nextInt(range);
        String fileLine = chooseMachineEngine.getMovie(randomNumber, movie);
        printer.println(fileLine);
    }

    @SuppressWarnings("squid:S112")
    void printAllMovies(File moviesToPrint, int numberOfLines) {
        int numberOfDigitsInMaxLine = countDigits(numberOfLines);
        Scanner fileScanner;
        try {
            fileScanner = new Scanner(moviesToPrint);
        } catch (FileNotFoundException e) {
            log.error("No such file");
            throw new RuntimeException(e);
        }

        for (int i = 1; fileScanner.hasNext(); i++) {
            switch (numberOfDigitsInMaxLine) {
                case 3 ->  printer.println(String.format("%3d %s", i, fileScanner.nextLine()));
                case 2 ->  printer.println(String.format("%2d %s", i, fileScanner.nextLine()));
                case 1 ->  printer.println(String.format("%d %s", i, fileScanner.nextLine()));
                default -> printer.println("Invalid number of lines");
            }
        }
        fileScanner.close();
    }

    void delete(File movies) {
        int numberOfLines = chooseMachineEngine.countLines(movies);
        int movieToDelete = userOperationAsker.getMovieToDelete(numberOfLines);
        chooseMachineEngine.delete(movieToDelete, movies);
    }

    void change(File movies) {
        int numberOfLines = chooseMachineEngine.countLines(movies);
        int movieToChange = userOperationAsker.getMovieToChange(numberOfLines);
        String movieToSet = userOperationAsker.getMovieToSet();
        int score = userOperationAsker.getMovieScore();
        chooseMachineEngine.change(movieToChange, movieToSet, movies, score);
    }

    void addMovie(File movies) {
        String movieToAdd = userOperationAsker.getMovieToAdd();
        int score = userOperationAsker.getMovieScore();
        chooseMachineEngine.addMovie(movieToAdd, movies, score);
    }

    void markWatchedMovie(File movieSeen, File moviesToWatch) {
        int numberOfLines = chooseMachineEngine.countLines(moviesToWatch);
        int numberOfWatchedMovie = userOperationAsker.getWatchedMovieNumber(numberOfLines);
        String watchedMovie =  chooseMachineEngine.getMovie(numberOfWatchedMovie, moviesToWatch);
        int score = userOperationAsker.getMovieScore();
        chooseMachineEngine.addMovie(watchedMovie, movieSeen, score);
        chooseMachineEngine.delete(numberOfWatchedMovie, moviesToWatch);
    }

    void setMovieScore(File movieSeen) {
        int numberOfLines = chooseMachineEngine.countLines(movieSeen);
        int movieToSetScore = userOperationAsker.getMovieToSetScore(numberOfLines);
        int score = userOperationAsker.getMovieScore();
        chooseMachineEngine.setScore(movieToSetScore, score, movieSeen);
    }

    int getNumberOfLines(File movies) {
        return chooseMachineEngine.countLines(movies);
    }

    private int countDigits(int numberToCountDigits) {
        int count = 0;
        while(numberToCountDigits != 0) {
            numberToCountDigits /= 10;
            count++;
        }
        return count;
    }
}
