package gui;

// import package dependencies
import variables.Game;

// import java gui dependencies
import javax.swing.*;
import java.awt.*;

/**
 * Create letter buttons and listen for user action (guess)
 */
public class GuessButton {
    /**
     * respective letter (final)
     */
    private final String letter;

    /**
     * flag for button status. This is incomplete but would extend functionality
     */
    private Game.GuessFlag guessFlag;

    /**
     * Static array of all buttons created
     */
    private static JButton[] buttons = new JButton[26];

    /**
     * number of current button (used in iteration when adding to buttons)
     */
    private static int buttonNumber;

    /**
     * GuessButton (non-argument)
     * calls dyadic constructor with defaults: letter = '.', guessFlag = 'DEFAULT'
     */
    public GuessButton() {
        this(".", Game.GuessFlag.DEFAULT);
        buttonNumber++;
    }

    /**
     * GuessButton (dyadic)
     * initializes GuessButton attributes with user input
     *
     * @param letter
     * @param guessFlag
     */
    public GuessButton(String letter, Game.GuessFlag guessFlag) {
        this.letter = letter;
        this.guessFlag = guessFlag;
        buttonNumber++;
    }

    /**
     * @return a JButton with respective letter
     */
    public JButton createLetterButton(){
        JButton button = new JButton(letter);

        // Styling
        button.setFont(new Font("Impact", Font.BOLD, 12));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.DARK_GRAY);

        // Increase button size
        button.setPreferredSize(new Dimension(50, 50));
        button.setMinimumSize(new Dimension(50, 50));
        button.setMaximumSize(new Dimension(50, 50));

        // Set border and opaque property
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setOpaque(true);

        // add to buttons
        buttons[buttonNumber-1] = button;

        return button;
    }

    /**
     * @return respective letter
     */
    public String getLetter() {
        return letter;
    }

    /**
     * @return the guess flag
     */
    public Game.GuessFlag getGuessFlag() {
        return guessFlag;
    }

    /**
     * Set the guess flag
     *
     * @param guessFlag the new guess flag
     */
    public void setGuessFlag(Game.GuessFlag guessFlag) {
        this.guessFlag = guessFlag;
    }

    /**
     * @return the static array of buttons
     */
    public static JButton[] getButtons() {
        return buttons;
    }

    /**
     * @return the current button number
     */
    public static int getButtonNumber() {
        return buttonNumber;
    }
}