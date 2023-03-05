package eu.ciechanowiec.movie2;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

class Movie {

    private final String name;
    private final int score;
    private final boolean isWatched;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";

    Movie() {
        name = "";
        score = -1;
        isWatched = false;
    }

    Movie(String movieLine) {
        String[] splitLine = movieLine.split("\"");
        name = splitLine[1];
        splitLine[1] = StringUtils.EMPTY;
        String lineWithoutMovie = Arrays.toString(splitLine);
        String[] splitLineWithoutMovie = lineWithoutMovie.split(", ");
        int maxValue = splitLineWithoutMovie.length;
        score = Integer.parseInt(splitLineWithoutMovie[maxValue - 2]);
        isWatched = splitLineWithoutMovie[maxValue - 1].equals("true]");
    }

    String getFormattedLine(String name, int score, boolean isWatched) {
        return "\"" + name + "\", " + score + ", " + isWatched;
    }

    String getFormattedLine() {
        return "\"" + name + "\", " + score + ", " + isWatched;
    }

    String getColoredLine() {
        return ANSI_GREEN + "score: "
                + ANSI_RESET + score + "/10 " + ANSI_GREEN + "movie: " + ANSI_RESET +
                name + ANSI_GREEN + " is watched: " + ANSI_RESET + isWatched;
    }
}
