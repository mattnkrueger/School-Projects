package gui;

// import package modules
import gameLogic.Round;
import variables.Game;
import gameLogic.GameListener;

// import java gui dependencies
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.HashMap;

/**
 * Create components to be shown inside the Hangman Application
 */
public class HangmanFrame {
    /**
     * Parent frame
     */
    private JFrame frame;

    /**
     * Panel containing title
     */
    private JPanel northPanel;

    /**
     * panel containing buttons
     */
    private JPanel southPanel;

    /**
     * panel containing hangman hidden word and stick figure
     */
    private JPanel centerPanel;

    /**
     * child panel for hidden word
     */
    private JPanel hiddenWordPanel;

    /**
     * child label for hidden word
     */
    private JLabel hiddenWordLabel;

    /**
     * title of application
     */
    private JLabel npTitle;

    /**
     * label containing current lives
     */
    private JLabel livesLeftLabel;

    /**
     * label containing the current round status
     */
    private JLabel gameMessageLabel;

    /**
     * Panel holding stick figure
     */
    private StickFigure stickFigure;

    /**
     * maps letter to corresponding button
     */
    private HashMap<String, JButton> letterButtons = new HashMap<>();

    /**
     * HangmanFrame (non-argument)
     * initialize components
     */
    public HangmanFrame() {
        updateLetterButtons();
        initialize();
    }

    /**
     * Create all components to be shown on screen. these are updated by GameListener
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("S21_HangmanGUI_Medium");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);

        // title
        frame.add(createNorthPanel(), BorderLayout.NORTH);

        // hangman & hidden word
        frame.add(createCenterPanel(), BorderLayout.WEST);

        // buttons
        frame.add(createSouthPanel(), BorderLayout.EAST);

        show();
    }

    /**
     * @return panel with title of the application
     */
    public JPanel createNorthPanel() {
        northPanel = new JPanel();
        npTitle = new JLabel("Introduction to Software Design - mnkrueger");
        northPanel.add(npTitle);
        northPanel.setFont(new Font("Impact", Font.BOLD, 16));
        northPanel.setForeground(Color.WHITE);
        northPanel.setBackground(Color.DARK_GRAY);
        northPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, Color.BLACK));
        return northPanel;
    }

    /**
     * @return panel with letter buttons
     */
    public JPanel createSouthPanel() {
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(5, 6)); // need 26
        for (String letter : Game.getAlphabet()) {
            southPanel.add(letterButtons.get(letter));
        }
        southPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.BLACK));
        return southPanel;
    }

    /**
     * @return panel with hangman figure and hidden word
     */
    public JPanel createCenterPanel() {
        // hidden word
        StringBuilder originalHiddenWord = new StringBuilder();
        originalHiddenWord.append(" _ ".repeat(Round.getCorrectWord().length()));

        centerPanel = new JPanel(new BorderLayout());

        // Hidden word panel and label
        hiddenWordPanel = new JPanel();
        hiddenWordLabel = new JLabel(originalHiddenWord.toString());
        hiddenWordLabel.setFont(new Font("Impact", Font.ITALIC, 20));
        hiddenWordLabel.setForeground(Color.BLACK);
        hiddenWordPanel.add(hiddenWordLabel);
        centerPanel.add(hiddenWordPanel, BorderLayout.EAST);

        // Stick figure panel
        stickFigure = new StickFigure();
        stickFigure.setPreferredSize(new Dimension(300, 400)); // Set preferred size for the stick figure
        centerPanel.add(stickFigure, BorderLayout.WEST);

        String message = "Guess a Letter to Play!";
        gameMessageLabel = new JLabel(message);

        // initial lives statement
        String numLives = "Lives remaining: " + Round.getStartingLives();
        livesLeftLabel = new JLabel(numLives);

        JPanel centerSouthPanel = new JPanel();
        centerSouthPanel.add(gameMessageLabel, BorderLayout.NORTH);
        centerSouthPanel.add(livesLeftLabel, BorderLayout.CENTER);
        centerSouthPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        centerPanel.add(centerSouthPanel, BorderLayout.NORTH);
        return centerPanel;
    }

    /**
     * Update letter buttons to new state
     */
    public void updateLetterButtons() {
        Game.GuessFlag guessFlag;
        GameListener gameListener = new GameListener(this);
        for (String letter : Game.getAlphabet()) {
            guessFlag = Game.getGuesses().get(letter);
            JButton button = new GuessButton(letter, guessFlag).createLetterButton();
            button.addActionListener(gameListener);
            letterButtons.put(letter, button);
        }
    }

    /**
     * set visible
     */
    public void show() {
        this.frame.setVisible(true);
    }

    /**
     * @return the frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * @return the north panel
     */
    public JPanel getNorthPanel() {
        return northPanel;
    }

    /**
     * @return the south panel
     */
    public JPanel getSouthPanel() {
        return southPanel;
    }

    /**
     * @return the center panel
     */
    public JPanel getCenterPanel() {
        return centerPanel;
    }

    /**
     * @return the hidden word panel
     */
    public JPanel getHiddenWordPanel() {
        return hiddenWordPanel;
    }

    /**
     * @return the hidden word label
     */
    public JLabel getHiddenWordLabel() {
        return hiddenWordLabel;
    }

    /**
     * @return the lives left label
     */
    public JLabel getLivesLeftLabel() {
        return livesLeftLabel;
    }

    /**
     * @return the game message label
     */
    public JLabel getGameMessageLabel() {
        return gameMessageLabel;
    }

    /**
     * @return the stick figure panel
     */
    public StickFigure getStickFigure() {
        return stickFigure;
    }

    /**
     * @return the letter buttons map
     */
    public HashMap<String, JButton> getLetterButtons() {
        return letterButtons;
    }
}