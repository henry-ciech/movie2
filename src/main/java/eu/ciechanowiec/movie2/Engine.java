package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.InputChecker;
import eu.ciechanowiec.movie2.util.Printer;

class Engine {

    private final MovieRepository movieRepository;
    private final UserOperationAsker userOperationAsker;
    private final UserOptionAsker userOptionAsker;
    private final InputChecker inputChecker;
    private final Printer printer;

    Engine() {
        printer = new Printer();
        movieRepository = new MovieRepository();
        inputChecker = new InputChecker();
        userOptionAsker = new UserOptionAsker();
        userOperationAsker = new UserOperationAsker();
    }

    void select() {
        do {
            int choice = userOptionAsker.optionSelector();
            Choice choice1 = Choice.byNumber(choice);
            switch (choice1) {
                case CREATE -> movieRepository.printAllMovies();
                case PRINT -> printMovies();
                case UPDATE -> updateMovies();
                case DELETE -> deleteMovies();
                case UNDEFINED -> printer.println("something went wrong");
            }
        } while (inputChecker.doContinueOrNot());
    }

    void printMovies() {
        String movieName = userOperationAsker.getMovieToAdd();
        int movieScore = userOperationAsker.getMovieScore();
        boolean isMovieWatched = userOperationAsker.isMovieWatched();
        movieRepository.create(movieName, movieScore, isMovieWatched);
    }

    void updateMovies() {
        int numberOfMovies = movieRepository.getNumberOfMovies();
        int movieID = userOperationAsker.getMovieToChange(numberOfMovies);
        String movieToSet = userOperationAsker.getMovieToSet();
        int scoreToSet = userOperationAsker.getMovieScore();
        boolean isMovieWatched = userOperationAsker.isMovieWatched();
        movieRepository.update(movieID, movieToSet, scoreToSet, isMovieWatched);
    }

    void deleteMovies() {
        int numberOfMovies = movieRepository.getNumberOfMovies();
        int movieId = userOperationAsker.getMovieToDelete(numberOfMovies);
        movieRepository.delete(movieId);
    }
}
