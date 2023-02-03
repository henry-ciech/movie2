package eu.ciechanowiec.movie2;
import java.io.File;

class Main {

    public static void main(String[] args) {
        File moviesToWatchFile = new File("src/main/resources/moviesToWatch.txt");
        File moviesSeenFile = new File("src/main/resources/moviesSeen.txt");
        UserOperationEngine userOperationEngine = new UserOperationEngine(moviesToWatchFile, moviesSeenFile);
        userOperationEngine.conductSelect();
    }
}
