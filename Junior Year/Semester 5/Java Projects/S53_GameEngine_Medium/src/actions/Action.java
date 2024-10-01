// add to package
package actions;

// import package dependencies
import entities.*;
import styling.*;

/**
 * User Action in game
 */
public class Action {
    /**
     * tally of number of actions taken
     */
    private static int actionCount = 0;

    /**
     * USER PLAYER. Action perpetrator
     */
    private final Actor player;

    /**
     * USER PLAYER or Actor (bad guy). Action target
     */
    private final Actor target;

    /**
     * type of action chosen
     */
    private final String actionType;

    /**
     * Action (non-argument)
     * constructor calls triadic constructor with defaults: player = 'new Actor()', target = 'new Actor()', actionType = 'None'
     */
    public Action() {
        this(new Actor(), new Actor(), "None");
    }

    /**
     * Action (triadic)
     * constructor initializes Action attributes to user input
     *
     * @param player
     * @param target
     * @param actionType
     */
    public Action(Actor player, Actor target, String actionType) {
        this.player = player;
        this.target = target;
        this.actionType = actionType;
        actionCount ++;
        createAndPrintAction();
    }

    /**
     * @return action perpetrator actor (USER PLAYER)
     */
    public Entity getPlayer() {
        return player;
    }

    /**
     * @return action target actor
     */
    public Entity getTarget() {
        return target;
    }

    /**
     * @return action type chosen
     */
    public String getActionType() {
        return actionType;
    }

    /**
     *  createAndPrintAction:
     *  1. map actionType to ActionCalculation methods
     *  2. print action output from ActionCalculation, wrapped in ansi escape code for color
     *
     *  See ActionCalculation.java
     */
    public void createAndPrintAction() {
        UnicodeEmoji unicodeEmojiDict = new UnicodeEmoji();
        ColorDict colorCode = new ColorDict();

        String emojiToPrint = unicodeEmojiDict.getRandomEmoji(getActionType());
        String whiteCode = colorCode.getCLIColor("COUNTER");
        String actionCode = colorCode.getCLIColor(getActionType());
        String resetCode = colorCode.getCLIColor("RESET");
        String statsCode = colorCode.getCLIColor("STATS");
        String actionHeader = unicodeEmojiDict.getEmoji("checkmark") + " " + "New Action!";
        String actionFooter = unicodeEmojiDict.getEmoji("stop-sign") + " " + "End Action!";

        System.out.println(whiteCode + " ACTION #" + actionCount +  " " + actionCode);
        System.out.println(actionHeader);
        ActionCalculation actionCalculator = new ActionCalculation(player, target, getActionType());
        String actionStats = switch (actionType) {
            case "ATTACK" -> actionCalculator.calculateAttackStatsAndActionString();
            case "MOVE" -> actionCalculator.calculateMoveStatsAndActionString();
            case "EAT" -> actionCalculator.calculateEatStatsAndActionString();
            case "SPEAK" -> actionCalculator.calculateSpeakActionString();
            default -> "ERROR: invalid action passed to Action";
        };
        System.out.println(actionFooter);
        printActionStats(actionStats, emojiToPrint);
    }

    private void printActionStats(String actionStats, String emoji) {
        System.out.println("|__ " + actionType + " " + emoji);
        System.out.println(actionStats);
    }
}
