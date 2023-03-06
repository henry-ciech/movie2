package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.random.RandomGenerator;

class MovieRepository {

    private final List<Movie> listOfMovies;
    private final File moviesFile;
    private final Printer printer;

    MovieRepository() {
        printer = new Printer();
        moviesFile = new File("src/main/resources/movies.txt");
        listOfMovies = new ArrayList<>();
        fillList();
    }

    void create(Movie movieToAdd) {
        listOfMovies.add(movieToAdd);
        save();
    }

    void printAllMovies() {
        for (int i = 1; i <= listOfMovies.size(); i++) {
            Movie movieToPrint = listOfMovies.get(i - 1);
            printer.println(i + ": " + movieToPrint.getColoredLine());
        }
    }

    void update(int movieID, Movie movieToSet) {
        int movieNumber = getMovieNumberById(movieID);
        addElementToACertainPlace(movieNumber, movieToSet);
        save();
    }

    private int getMovieNumberById(int movieId) {
        for (int i = 0; i < listOfMovies.size(); i++) {
            Movie movie = listOfMovies.get(i);
            if (movieId == movie.getCopyOfId()) {
                return i;
            }
        }
        return -1;
    }

    private void addElementToACertainPlace(int placeToSetElement, Movie movieToSet) {
        Collection<Movie> tempListOfMovies = new ArrayList<>();
        for (int i = 0; i < listOfMovies.size(); i++) {
            if (i == placeToSetElement) {
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

    @SuppressWarnings("squid:S5413")
    void delete(int movieID) {
        for (int i = 0; i < listOfMovies.size(); i++) {
            Movie movie = listOfMovies.get(i);
            if (movie.getCopyOfId() == movieID) {
                listOfMovies.remove(i); //can't find problem code
            }
        }
        save();
    }

    @SuppressWarnings("squid:S2119")
    int getUnusedId() {
        RandomGenerator random = new Random();
        int unusedId = random.nextInt(1000);
        for (Movie movie : listOfMovies) {
            if (unusedId == movie.getCopyOfId()) {
                unusedId = random.nextInt(1000);
            }
        }
        return unusedId;
    }

    boolean isIdUsed(int idToCheck) {
        for (Movie movie : listOfMovies) {
            if (movie.getCopyOfId() == idToCheck) {
                return false;
            }
        }
        return true;
    }
}
