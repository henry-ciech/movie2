package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.InputChecker;
import eu.ciechanowiec.movie2.util.Printer;

class OptionHandler {

    private final MovieRepository movieRepository;
    private final UserOperationAsker userOperationAsker;
    private final UserOptionAsker userOptionAsker;
    private final InputChecker inputChecker;

    OptionHandler() {
        movieRepository = new MovieRepository();
        inputChecker = new InputChecker();
        userOptionAsker = new UserOptionAsker();
        userOperationAsker = new UserOperationAsker();
    }

    void conductSelect() {
        do {
            int choice = userOptionAsker.optionSelector();
            switch (choice) {
                case 1 -> movieRepository.printAllMovies();
                case 2 -> {
                    String movieName = userOperationAsker.getMovieToAdd();
                    int movieScore = userOperationAsker.getMovieScore();
                    movieRepository.create(movieName, movieScore, true);
                }
                case 3 -> {
                    int movieID = userOperationAsker.getMovieToChange(movieRepository.getNumberOfMovies());
                    String movieToSet = userOperationAsker.getMovieToSet();
                    int scoreToSet = userOperationAsker.getMovieScore();
                    movieRepository.update(movieID, movieToSet, scoreToSet, true);
                }
                case 4 -> {
                    int movieId = userOperationAsker.getMovieToDelete(movieRepository.getNumberOfMovies());
                    movieRepository.delete(movieId);
                }
            }
        } while (inputChecker.isContinueOrNot());
    }
}
