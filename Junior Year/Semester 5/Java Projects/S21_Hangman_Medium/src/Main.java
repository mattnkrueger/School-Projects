// import package modules
import variables.Game;
import variables.WordBank;
import gui.HangmanFrame;
import gameLogic.Round;

/**
 * Entry point of hangman game
 */
public class Main {
    public static void main(String[] args) {
        // ensures that the static methods get initialized
        Game game = new Game();
        WordBank wordBank = new WordBank();
        Round round = new Round();

        // create application
        HangmanFrame frame = new HangmanFrame();
        frame.show();
    }
}
