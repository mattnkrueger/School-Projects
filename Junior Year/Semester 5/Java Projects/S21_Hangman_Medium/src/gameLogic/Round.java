package gameLogic;

// import package modules
import variables.WordBank;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handle hangman round logic
 */
public class Round {
    /**
     * Correct word for hangman round
     */
    private static String correctWord;

    /**
     * number of starting lives (6 - head, body, arms x2, legs x2)
     */
    private static final int startingLives = 6;

    /**
     * remaining lives
     */
    private static int livesLeft;

    /**
     * is the round finished?
     */
    private static boolean roundFinished;

    /**
     * holds correct guesses as an index
     */
    private static HashMap<Integer, String> correctGuessIndexes = new HashMap<>();

    /**
     * Round (non-argument)
     * set initial correct word
     */
    public Round() {
        setCorrectWord();
        livesLeft = startingLives;
    }

    /**
     * Set correct word
     */
    public static void setCorrectWord() {
        correctWord = WordBank.getRandomWord();
    }

    /**
     * @return correct word (hidden to user)
     */
    public static String getCorrectWord() {
        return correctWord;
    }

    /**
     * @return number of starting lives
     */
    public static int getStartingLives() {
        return startingLives;
    }

    /**
     * Reset lives remaining
     */
    public static void resetLives() {
        livesLeft = startingLives;
    }

    /**
     * @return number of lives remaining
     */
    public static int getLivesLeft() {
        return livesLeft;
    }

    /**
     * Set remaining lives
     * @param newLivesLeft the new number of lives left
     */
    public static void setLivesLeft(int newLivesLeft) {
        livesLeft = newLivesLeft;
    }

    /**
     * @return round status
     */
    public static boolean isRoundFinished() {
        return roundFinished;
    }

    /**
     * set round status
     */
    public static void setRoundFinished(boolean flag) {
        roundFinished = flag;
    }

    /**
     * @return correct guesses index
     */
    public static HashMap<Integer, String> getCorrectGuessIndexes() {
        return correctGuessIndexes;
    }
}