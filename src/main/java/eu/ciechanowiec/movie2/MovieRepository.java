package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

class MovieRepository {

    private final List<Movie> listOfMovies;
    private final File moviesFile;
    private final Movie movie;
    private final Printer printer;

    MovieRepository() {
        printer = new Printer();
        moviesFile = new File("src/main/resources/movies.txt");
        listOfMovies = new ArrayList<>();
        movie = new Movie();
        fillList();
    }

    void create(String movieToCreate, int score, boolean isWatched) {
        String lineToAdd = movie.getFormattedLine(movieToCreate, score, isWatched);
        listOfMovies.add(new Movie(lineToAdd));
        save();
    }

    void printAllMovies() {
        for (int i = 1; i <= listOfMovies.size(); i++ ) {
            Movie movieToPrint = listOfMovies.get(i - 1);
            printer.println(i + ": " + movieToPrint.getColoredLine());
        }
    }

    void update(int movieID, String movieNameToSet, int movieScoreToSet, boolean isWatchedToSet) {
        String movieLineToSet = movie.getFormattedLine(movieNameToSet, movieScoreToSet, isWatchedToSet);
        addElementToACertainPlace(movieID, movieLineToSet);
        save();
    }

    private void addElementToACertainPlace(int placeToSetElement, String lineToSet) {
        Collection<Movie> tempListOfMovies = new ArrayList<>();
        delete(placeToSetElement);
        for (int i = 0; i <= listOfMovies.size(); i++) {
            if (i == placeToSetElement - 1) {
                Movie movieToSet = new Movie(lineToSet);
                tempListOfMovies.add(movieToSet);
            } else {
                tempListOfMovies.add(listOfMovies.get(i));
            }
        }
        listOfMovies.clear();
        listOfMovies.addAll(tempListOfMovies);
    }

    @SuppressWarnings("squid:S112")
    private void save() {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(moviesFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Movie listOfMovie : listOfMovies) {
            String formattedLine = listOfMovie.getFormattedLine();
            printWriter.println(formattedLine);
        }
        printWriter.close();
    }

    @SuppressWarnings("squid:S112")
    private void fillList() {
        Scanner scanner;
        try {
            scanner = new Scanner(moviesFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (scanner.hasNext()) {
            String movieLine = scanner.nextLine();
            listOfMovies.add(new Movie(movieLine));
        }
        scanner.close();
    }

    void delete(int movieID) {
        listOfMovies.remove(movieID - 1);
        save();
    }

    int getNumberOfMovies() {
        return listOfMovies.size();
    }
}
