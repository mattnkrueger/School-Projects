// add to package
package variables;

// import java util dependencies
import java.util.Random;

/**
 * Hold correct word choices and provides method to get new correct word.
 */
public class WordBank {
    /**
     * word bank for four-letter words
     */
    private static final String[] fourLetterWords = {"able", "back", "ball", "bark", "bear", "blue", "boat", "bowl", "cage", "cold", "corn", "dark", "dawn", "dirt", "door", "dust", "easy", "fall", "fast", "gate", "gold", "hill", "hold", "iron", "join", "jump", "lamp", "leaf", "line", "moon"};

    /**
     * word bank for five-letter words
     */
    private static final String[] fiveLetterWords = {"apple", "bread", "crane", "daisy", "eagle", "flame", "grape", "honey", "ivory", "jolly", "kites", "lemon", "mango", "noble", "olive", "pearl", "quilt", "raise", "storm", "table", "upset", "vivid", "wheat", "xenon", "yacht", "zesty", "bliss", "charm", "dream", "earth", "frost"};

    /**
     * @return word to use as correct word
     */
    public static String getRandomWord() {
        int randomArrayChoice;
        int correctWordChoice;
        Random random = new Random();
        randomArrayChoice = random.nextInt(2) + 4;
        correctWordChoice = random.nextInt(fourLetterWords.length);

        String correctWord;
        if (randomArrayChoice == 4) {
            correctWord = getFourLetterWords()[correctWordChoice];
        } else {
            correctWord = getFiveLetterWords()[correctWordChoice];
        }
        return correctWord;
    }

    /**
     * @return array of four-letter words
     */
    public static String[] getFourLetterWords() {
        return fourLetterWords;
    }

    /**
     * @return array of five-letter words
     */
    public static String[] getFiveLetterWords() {
        return fiveLetterWords;
    }
}
