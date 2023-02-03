package eu.ciechanowiec.movie2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ChooseMachineEngine {

    private static final String NO_SUCH_FILE = "No such file";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";

    @SuppressWarnings("squid:S112")
    int countLines(File movies) {
        Scanner scanner;
        try {
            scanner = new Scanner(movies);
        } catch (FileNotFoundException e) {
            log.error(NO_SUCH_FILE);
            throw new RuntimeException(e);
        }
        int numberOfLines = 0;
        while (scanner.hasNext()) {
            numberOfLines++;
            scanner.nextLine();
        }
        scanner.close();
        return numberOfLines;
    }

    @SuppressWarnings("squid:S112")
    void delete(int lineToDelete, File movies) {
        lineToDelete--;
        String[] fileString = getFileInString(movies);
        fileString[lineToDelete] = "%^&Line)(*To@$#Delete#@$";
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(movies);
        } catch (FileNotFoundException e) {
            log.error(NO_SUCH_FILE);
            throw new RuntimeException(e);
        }
        for (String s : fileString) {
            if (!s.equals("%^&Line)(*To@$#Delete#@$")) {
                printWriter.println(s);
            }
        }
        printWriter.close();
    }

    @SuppressWarnings("squid:S112")
    String getMovie(int fileLine, File movies) {
        Scanner scanner;
        try {
            scanner = new Scanner(movies);
        } catch (FileNotFoundException e) {
            log.error(NO_SUCH_FILE);
            throw new RuntimeException(e);
        }
        String foundLine;
        for (int i = 0; i < fileLine - 1; i++) {
            scanner.nextLine();
        }
        foundLine = scanner.nextLine();
        scanner.close();
        String[] split = foundLine.split("movie: ");

        return split[1];
    }

    @SuppressWarnings("squid:S112")
    void setScore(int movieToSetScore, int score, File movies) {
        movieToSetScore--;
        String[] fileString = getFileInString(movies);
        fileString[movieToSetScore] = getColoredLine(score, getMovie(movieToSetScore + 1, movies));
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(movies);
        } catch (FileNotFoundException e) {
            log.error(NO_SUCH_FILE);
            throw new RuntimeException(e);
        }
        for (String s : fileString) {
            printWriter.println(s);
        }
        printWriter.close();
    }

    @SuppressWarnings("squid:S112")
    void addMovie(String movieToAdd, File movies, int score) {
        String[] fileString = getFileInString(movies);
        fileString = append(fileString, getColoredLine(score, movieToAdd));
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(movies);
        } catch (FileNotFoundException e) {
            log.error(NO_SUCH_FILE);
            throw new RuntimeException(e);
        }
        for (String movie : fileString) {
            printWriter.println(movie);
        }
        printWriter.close();
    }

    private String getColoredLine(int score, String movieName) {
        return  ANSI_GREEN + "score: " +
                ANSI_RESET +  score + "/10" + ANSI_GREEN + " movie: " + ANSI_RESET + movieName;
    }

    private <T> T[] append(T[] arr, T element) {
        int length = arr.length;
        arr = Arrays.copyOf(arr, length + 1);
        arr[length] = element;
        return arr;
    }

    @SuppressWarnings("squid:S112")
    void change(int lineToChange, String movieToSet, File movies, int score) {
        lineToChange--;
        String[] fileString = getFileInString(movies);
        fileString[lineToChange] = getColoredLine(score, movieToSet);
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(movies);
        } catch (FileNotFoundException e) {
            log.error(NO_SUCH_FILE);
            throw new RuntimeException(e);
        }
        for (String s : fileString) {
            printWriter.println(s);
        }
        printWriter.close();
    }

    @SuppressWarnings("squid:S112")
    private String[] getFileInString(File movies) {
        Scanner scanner;
        StringBuilder fileString = new StringBuilder();
        try {
            scanner = new Scanner(movies);
        } catch (FileNotFoundException e) {
            log.error(NO_SUCH_FILE);
            throw new RuntimeException(e);
        }

        while (scanner.hasNext()) {
            fileString.append(scanner.nextLine());
            fileString.append("\n");
        }
        scanner.close();
        return String.valueOf(fileString).split("\n");
    }
}
