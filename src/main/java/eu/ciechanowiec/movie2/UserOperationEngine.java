package eu.ciechanowiec.movie2;

import eu.ciechanowiec.movie2.util.InputChecker;

import java.io.File;

class UserOperationEngine {

    private final InputChecker inputChecker;
    private final ChoiceOptions choiceOptions;
    private final File moviesToWatchFile;
    private final File moviesSeenFile;
    private final UserOptionAsker userOptionAsker;

    UserOperationEngine(File moviesToWatchFile, File moviesSeenFile) {
        choiceOptions = new ChoiceOptions();
        userOptionAsker = new UserOptionAsker();
        inputChecker = new InputChecker();
        this.moviesToWatchFile = moviesToWatchFile;
        this.moviesSeenFile = moviesSeenFile;
    }

    @SuppressWarnings({"squid:S131", "squid:S1301"})
    void conductSelect() {
        do {
            int choice = userOptionAsker.optionSelector();
            int range = choiceOptions.getNumberOfLines(moviesToWatchFile);
            switch (choice) {
                case 1 -> choiceOptions.getRandomMovieInGivenRange(range, moviesToWatchFile);
                case 2 -> {
                    choiceOptions.printAllMovies(moviesToWatchFile, choiceOptions.getNumberOfLines(moviesToWatchFile));
                    conductSelectAfterMovieToWatchList(moviesToWatchFile, moviesSeenFile);
                }
                case 3 -> {
                    choiceOptions.printAllMovies(moviesSeenFile, choiceOptions.getNumberOfLines(moviesSeenFile));
                    conductSelectAfterMovieSeenList(moviesSeenFile);
                }
            }
        } while (inputChecker.isContinueOrNot());
    }
    @SuppressWarnings({"squid:S131", "squid:S1301"})
    private void conductSelectAfterMovieToWatchList(File moviesToWatch, File moviesSeen) {
        int choice = userOptionAsker.optionSelectorAfterMovieToWatchList();
        switch (choice) {
            case 1 -> choiceOptions.delete(moviesToWatch);
            case 2 -> choiceOptions.change(moviesToWatch);
            case 3 -> choiceOptions.addMovie(moviesToWatch);
            case 4 -> choiceOptions.markWatchedMovie(moviesSeen, moviesToWatch);
            case 5 -> choiceOptions.setMovieScore(moviesToWatch);
        }
    }

    @SuppressWarnings({"squid:S131", "squid:S1301"})
    private void conductSelectAfterMovieSeenList(File movies) {
        int choice = userOptionAsker.optionSelectorAfterMovieSeenList();
        switch (choice) {
            case 1 -> choiceOptions.delete(movies);
            case 2 -> choiceOptions.change(movies);
            case 3 -> choiceOptions.addMovie(movies);
            case 4 -> choiceOptions.setMovieScore(movies);
        }
    }
}
