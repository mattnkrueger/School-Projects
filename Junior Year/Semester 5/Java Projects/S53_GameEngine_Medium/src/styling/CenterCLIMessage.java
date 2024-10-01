// add to package
package styling;

/**
 * Center CLI output message (used for starting and ending game strings)
 */
public class CenterCLIMessage {

    /**
     * string text to be centered
     */
    private String textToCenter;

    /**
     * integer value for terminal width
     */
    private int terminalWidth;

    /**
     * CenterCLIMessage (non-argument)
     * call monadic constructor with defaults: textToCenter = 'Text to Center', terminalWidth = '80'
     */
    public CenterCLIMessage() {
        this("Text to Center", 80);
    }

    /**
     * CenterCLIMessage (monadic constructor)
     * initialize attributes with user input
     * @param textToCenter
     * @param terminalWidth
     */
    public CenterCLIMessage(String textToCenter, int terminalWidth) {
        this.textToCenter = textToCenter;
        this.terminalWidth = terminalWidth;
    }

    /**
     * set new textToCenter
     * @param textToCenter
     */
    public void setTextToCenter(String textToCenter) {
        this.textToCenter = textToCenter;
    }

    /**
     * set new terminal width
     * @param terminalWidth
     */
    public void setTerminalWidth(int terminalWidth) {
        this.terminalWidth = terminalWidth;
    }

    /**
     * @return text to be centered
     */
    public String getTextToCenter() {
        return textToCenter;
    }

    /**
     * @return terminal width
     */
    public int getTerminalWidth() {
        return terminalWidth;
    }

    /**
     * add padding 1/2 of terminal width to center the text
     */
    public void centerText() {
        int numberOfSpaces = (getTerminalWidth() - getTextToCenter().length()) / 2;
        String paddingCharacter = " ";
        String padding = paddingCharacter.repeat(numberOfSpaces);
        String centeredString = padding + getTextToCenter();
        System.out.println(centeredString);
    }
}
