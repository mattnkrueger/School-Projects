// add to package
package variables;

// import java util dependencies
import java.util.HashMap;

/**
 * Hold static attributes for current game. Provides methods to obtain attributes
 */
public class Game {

    /**
     * Hold constant flags: guessed (incorrect), correct (guessed & correct), default (yet to guess)
     */
    public enum GuessFlag {
        INCORRECT,
        CORRECT,
        DEFAULT,
    }

    /**
     * Hold all guessable letters
     */
    private static final String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    /**
     * Map letter to guess flag
     */
    private static HashMap<String, GuessFlag> guesses = new HashMap<>();

    /**
     * GameVariables (non-argument)
     * initialize all guesses in hashmap to DEFAULT
     */
    public Game() {
        clearGuesses();
    }

    /**
     * @return alphabet
     */
    public static String[] getAlphabet() {
        return alphabet;
    }

    /**
     * @return guesses
     */
    public static HashMap<String, GuessFlag> getGuesses() {
        return guesses;
    }

    /**
     * removes all correct and incorrect guesses
     */
    public static void clearGuesses() {
        for (String letter : alphabet) {
            guesses.put(letter, GuessFlag.DEFAULT);
        }
    }

}