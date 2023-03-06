package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.InputChecker;
import eu.ciechanowiec.movie2.util.Printer;

class Engine {

    private final MovieRepository movieRepository;
    private final UserOperationAsker userOperationAsker;
    private final InputChecker inputChecker;
    private final Printer printer;
    private final Movie movie;

    Engine() {
        printer = new Printer();
        movieRepository = new MovieRepository();
        inputChecker = new InputChecker();
        userOperationAsker = new UserOperationAsker();
        movie = new Movie();
    }

    void select() {
        do {
            int choiceAsInt = userOperationAsker.askAboutOption();
            Choice choice = Choice.byNumber(choiceAsInt);
            switch (choice) {
                case CREATE -> addMovie();
                case PRINT -> movieRepository.printAllMovies();
                case UPDATE -> updateMovies();
                case DELETE -> deleteMovies();
                case UNDEFINED -> printer.println("Something went wrong");
            }
        } while (inputChecker.isContinueOrNot());
    }



    private void addMovie() {
        String movieName = userOperationAsker.getMovieToAdd();
        int movieScore = userOperationAsker.getMovieScore();
        boolean isMovieWatched = userOperationAsker.isMovieWatched();
        int id = movieRepository.getUnusedId();
        String movieToAdd = movie.getFormattedLine(movieName, movieScore, isMovieWatched, id);
        Movie movieToCreate = new Movie(movieToAdd);
        movieRepository.create(movieToCreate);
    }

    private void updateMovies() {
        int movieID = userOperationAsker.getMovieToChange();
        String movieTitleToSet = userOperationAsker.getMovieTitleToSet();
        int scoreToSet = userOperationAsker.getMovieScore();
        boolean isMovieWatched = userOperationAsker.isMovieWatched();
        int id = movieRepository.getUnusedId();
        String movieToSetString = movie.getFormattedLine(movieTitleToSet, scoreToSet, isMovieWatched, id);
        Movie movieToSet = new Movie(movieToSetString);
        movieRepository.update(movieID, movieToSet);
    }

    private void deleteMovies() {
        int movieId = userOperationAsker.getMovieToDelete();
        movieRepository.delete(movieId);
    }
}
