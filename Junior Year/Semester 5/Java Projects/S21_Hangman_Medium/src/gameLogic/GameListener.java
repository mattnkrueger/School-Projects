// add to package
package gameLogic;

// import package modules
import gui.GuessButton;
import variables.Game;
import gui.HangmanFrame;

// imoprt java gui dependencies
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

/**
 * Action listener for GuessButton class
 */
public class GameListener implements ActionListener {
    /**
     * letter guessed by user
     */
    private String guessedLetter;

    /**
     * button of the letter guessed by user
     */
    private JButton buttonClicked;

    /**
     * referenced frame to update the components present in frame
     */
    private HangmanFrame hangmanFrame;

    public GameListener(HangmanFrame hangmanFrame) {
        this.hangmanFrame = hangmanFrame;
    }

    /**
     * Obtain the button click from the user
     *
     * OVERRIDE: ActionListener
     * @param event the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        buttonClicked = (JButton) event.getSource();
        setGuessedLetter(buttonClicked.getText());
        updateHangman();
        updateButtonClicked();
        updateHiddenWord();
        updateLivesAndGameMessages();
        repaintHangmanVisual();
    }

    /**
     * @param letter
     */
    private void setGuessedLetter(String letter) {
        this.guessedLetter = letter;
    }

    /**
     * Run hangman core logic (game rules)
     */
    private void updateHangman() {
        if (isGuessInWord()) {
            Game.getGuesses().put(guessedLetter, Game.GuessFlag.CORRECT);
            updateCorrectGuessIndexes();
        } else {
            Game.getGuesses().put(guessedLetter, Game.GuessFlag.INCORRECT);
            Round.setLivesLeft(Round.getLivesLeft() - 1);
        }
        if (Round.getLivesLeft() <= 0) {
            Round.setRoundFinished(true);
        }
        if (Round.isRoundFinished()) {
            deactivateButtons();
        }
    }

    /**
     * Deactivate all buttons... ensure that game is unplayable when over
     */
    private void deactivateButtons() {
        for (JButton button : GuessButton.getButtons()) {
            button.setEnabled(false);
        }
    }

    /**
     * @return true if the letter guessed is in the hidden word
     */
    private boolean isGuessInWord() {
        return (Round.getCorrectWord().contains(guessedLetter));
    }

    /**
     * Updates Round's hashmap holding the users correct guess and index of guess
     */
    private void updateCorrectGuessIndexes() {
        for (int i = 0; i < Round.getCorrectWord().length(); i++) {
            if (String.valueOf(Round.getCorrectWord().charAt(i)).equals(guessedLetter)) {
                Round.getCorrectGuessIndexes().put(i, guessedLetter);
            }
        }
    }

    /**
     * Update the hangman visual
     */
    private void repaintHangmanVisual() {
        hangmanFrame.getStickFigure().repaint();
        hangmanFrame.getStickFigure().revalidate();
    }

    /**
     * Deactivate and recolor button after it has been clicked by user
     */
    private void updateButtonClicked() {
        // Conditional flag styling
        if (Game.getGuesses().get(guessedLetter) == Game.GuessFlag.DEFAULT) {
            buttonClicked.setForeground(Color.WHITE);
            buttonClicked.setBackground(Color.DARK_GRAY);
            buttonClicked.setToolTipText("Guess the letter: '" + guessedLetter + "'");
            buttonClicked.setEnabled(true);
        } else if (Game.getGuesses().get(guessedLetter) == Game.GuessFlag.INCORRECT) {
            buttonClicked.setForeground(Color.WHITE);
            buttonClicked.setBackground(Color.RED);
            buttonClicked.setToolTipText("The letter: '" + guessedLetter + "' was guessed incorrectly!");
            buttonClicked.setEnabled(false);
        } else if (Game.getGuesses().get(guessedLetter) == Game.GuessFlag.CORRECT) {
            buttonClicked.setForeground(Color.WHITE);
            buttonClicked.setBackground(Color.GREEN);
            buttonClicked.setToolTipText("The letter: '" + guessedLetter + "' was guessed correctly!");
            buttonClicked.setEnabled(false);
        }
        buttonClicked.repaint();
    }

    /**
     * Update the underscore (hidden) text in the JLabel after each guess
     */
    private void updateHiddenWord() {
        String completion = buildWordCompletion();
        hangmanFrame.getHiddenWordLabel().setText(completion);
        hangmanFrame.getHiddenWordLabel().repaint();
        hangmanFrame.getHiddenWordLabel().revalidate();
    }

    /**
     * Build the current word guessed by the user & end the game if it is equal to the correct word. Yes, LOLP broken here.
     * @return string of current word
     */
    private String buildWordCompletion() {
        StringBuilder wordCompletion = new StringBuilder();
        String letter;
        for (int i = 0; i < Round.getCorrectWord().length(); i++) {
            letter = Round.getCorrectGuessIndexes().getOrDefault(i, " _ ");
            wordCompletion.append(letter);
        }

        if (wordCompletion.toString().equals(Round.getCorrectWord())) {
            deactivateButtons();
        }
        return wordCompletion.toString();
    }

    /**
     * Updates the components used to output messages in JLabels after each guess
     */
    private void updateLivesAndGameMessages() {
        String gameMessage;
        String numLivesLeft = "Lives remaining: " + String.valueOf(Round.getLivesLeft());
        if (buildWordCompletion().equals(Round.getCorrectWord())) {
            gameMessage = " YOU WON! with " + Round.getLivesLeft() + " lives remaining";
            numLivesLeft = "";
        }
        else if (Round.isRoundFinished()) {
            gameMessage = " YOU LOST! Correct word: " + Round.getCorrectWord();
            numLivesLeft = "";
        } else if (isGuessInWord()){
            gameMessage = "Correct Guess: " + guessedLetter + " is in word!";
        } else {
            gameMessage = "Incorrect Guess!";
        }

        hangmanFrame.getLivesLeftLabel().setText(numLivesLeft);
        hangmanFrame.getGameMessageLabel().setText(gameMessage);
    }

}
