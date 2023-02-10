package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    void printMovie(int movieID) {
        Movie movieToPrint = listOfMovies.get(movieID - 1);
        printer.println(movieToPrint.getColoredLine());
    }

    void update(int movieID, String movieNameToSet, int movieScoreToSet, boolean isWatchedToSet) {
        Movie movieToUpdate = listOfMovies.get(movieID - 1);
        movieToUpdate.setName(movieNameToSet);
        movieToUpdate.setScore(movieScoreToSet);
        movieToUpdate.setWatched(isWatchedToSet);
        save();
    }

    void delete(int movieID) {
        listOfMovies.remove(movieID - 1);
        save();
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

    @SuppressWarnings("squid:S112")
    private void save() {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(moviesFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Movie listOfMovie : listOfMovies) {
            printWriter.println(listOfMovie.getFormattedLine());
        }
        printWriter.close();
    }

    int getNumberOfMovies() {
        return listOfMovies.size();
    }
}
