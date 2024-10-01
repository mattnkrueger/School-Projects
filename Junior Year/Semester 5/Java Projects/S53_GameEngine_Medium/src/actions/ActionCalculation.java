// add to package
package actions;

// import package dependencies
import entities.*;
import styling.*;

// import java util dependencies
import java.util.Random;
import java.util.ArrayList;

/**
 * Handle action logic and create cli ouputs for Action
 */
public class ActionCalculation {
    /**
     * all EAT action health modifiers
     */
    private final float[] eatStats = {2, 4, 8, 16, 32, 64, -5, -10, -25};

    /**
     * all ATTACK action health modifiers
     */
    private final int[] attackStats = {10, 50, 100, 200, 300};

    /**
     * all SPEAK action options
     */
    private final String[] speakStats = {"Expelliarmus", "Alohomora", "Accio Broomstick!", "You're a Wizard, Harry", "I want Butter Beer! \uD83C\uDF7A", "Where am I?"};

    /**
     * all MOVE xPos action modifiers
     */
    private final int[] xPosStats = {4, 8, -2, -1, 1, 2, 5, 3};

    /**
     * all MOVE yPos action modifiers
     */
    private final int[] yPosStats = {4, 8, -2, -1, 1, 2, 5, 3};

    /**
     * all MOVE rotation action modifiers
     */
    private final float[] rotationStats = {1.5f, 0.1f, 1.333f, 4.2f, -1.1f, -2.5f};

    /**
     * all MOVE speed action modifiers
     */
    private final int[] speedStats = {10, 200, 400, 100, 50, 1000};

    /**
     * USER PLAYER (action perpetrator)
     */
    private final Actor player;

    /**
     * USER PLAYER or Actor (badguy) (action target)
     */
    private final Actor target;

    /**
     * type of action chosen
     */
    private final String actionType;

    /**
     * ActionCalculation (non-argument)
     * calls triadic constructor with defaults: player = 'new Actor()', target = 'new Actor()', actionType = 'None'
     */
    public ActionCalculation() {
        this(new Actor(), new Actor(), "None");
    }

    /**
     * ActionCalculation (triadic)
     * initializes ActionCalculation attributes to user input
     *
     * @param player
     * @param target
     * @param actionType
     */
    public ActionCalculation(Actor player, Actor target, String actionType) {
        this.player = player;
        this.target = target;
        this.actionType = actionType;
    }

    /**
     * 1. calculate EAT action
     * 2. print cli stats
     * @return string cli messgae with EAT stats
     */
    public String calculateEatStatsAndActionString() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();
        String cannedFoodEmoji = unicodeEmoji.getEmoji("canned-food");
        String binderEmoji = unicodeEmoji.getEmoji("binder");

        float currentHealth;
        float newHealth;
        String outcomeMessage;
        float randomHealth;
        int randomIndex;

        Random random = new Random();
        randomIndex = random.nextInt(eatStats.length);
        randomHealth = eatStats[randomIndex];

        currentHealth = player.getHealth();
        newHealth = currentHealth + randomHealth;
        player.setHealth(newHealth);

        if (randomHealth > 0) {
            outcomeMessage = " Yum Yum Yum ";
        } else {
            outcomeMessage = " Ouch ";
        }

        String eatingString;
        String eatStatementString = "\n" + cannedFoodEmoji + outcomeMessage + cannedFoodEmoji;
        String playerHealth = "\n|__ " + "PLAYER gained " + randomHealth + " health";
        eatingString = eatStatementString + playerHealth;

        String playerString = getPlayerString();
        String updatedValues = "\n\n" + binderEmoji + " Updated Player " + binderEmoji;
        String actionStats = eatingString + updatedValues + playerString;
        return actionStats;
    }

    /**
     * 1. calculate ATTACK action
     * 2. print cli stats
     * @return string cli messgae with ATTACK stats
     */
    public String calculateAttackStatsAndActionString() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();
        String swordEmoji = unicodeEmoji.getEmoji("sword");
        String binderEmoji = unicodeEmoji.getEmoji("binder");
        float playerDamageToTarget = calculatePlayerOnTargetAttack();
        float targetDamageToPlayer = calculateTargetOnPlayerAttack();

        String damageString = "\n" + swordEmoji + " Damage Dealt " + swordEmoji;
        String playerAttack = "\n|__ " + "PLAYER dealt " + playerDamageToTarget + " damage\n";
        String targetAttack = "|__ " + target.getName() + " dealt " + targetDamageToPlayer + " damage";
        String damageDealtString = damageString + playerAttack + targetAttack;

        String updatedValues = "\n\n" + binderEmoji + " Updated Actors " + binderEmoji;
        String playerString = getPlayerString();
        String villainString = getVillainString();
        String upatedActorsString = updatedValues + playerString + villainString;

        String playerDeathString = getPlayerDeathString();
        String targetDeathString = getTargetDeathString();

        String actionStats = damageDealtString + upatedActorsString + playerDeathString + targetDeathString;
        return actionStats;
    }

    /**
     * 1. calculate ATTACK action
     * 2. print cli stats
     * @return string cli messgae with ATTACK stats
     */
    public String calculateMoveStatsAndActionString() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();
        String carEmoji = unicodeEmoji.getEmoji("car");
        String binderEmoji = unicodeEmoji.getEmoji("binder");
        ArrayList<Number> newTransform = new ArrayList<>();
        int newXPos = calculateMoveXPos();
        int newYPos = calculateMoveYPos();
        float newRotation = calculateMoveRotation();
        int newSpeed = calculateMoveSpeed();

        String transformString;
        String moveString = "\n" + carEmoji + " Movement Transformation " + carEmoji;
        String xPosString = "\n|__ " + "New xPos: " + newXPos;
        String yPosString = "\n|__ " + "New yPos: " + newYPos;
        String rotationString = "\n|__ " + "New rotation: " + newRotation;
        String speedString = "\n|__ " + "New speed: " + newSpeed;
        transformString = moveString + xPosString + yPosString + rotationString + speedString;

        String updatedValues = "\n\n" + binderEmoji + " Updated Player" + binderEmoji;
        String playerString = getPlayerString();
        String actionStats = transformString + updatedValues + playerString;
        return actionStats;
    }

    /**
     * @return attack value by perpetrator on target
     */
    private float calculatePlayerOnTargetAttack() {
        float currentTargetHealth = target.getHealth();
        float newTargetHealth;
        float randomAttack;

        int randomIndex;
        Random random = new Random();

        randomIndex = random.nextInt(attackStats.length);
        randomAttack = attackStats[randomIndex];

        newTargetHealth = currentTargetHealth - randomAttack;

        if (newTargetHealth <= 0) {
            target.setHealth(0);
        } else {
            target.setHealth(newTargetHealth);
        }

        return randomAttack;
    }

    /**
     * @return attack value by target on perpetrator
     */
    private float calculateTargetOnPlayerAttack() {
        float currentPlayerHealth = player.getHealth();
        float newPlayerHealth;
        float randomAttack;

        int randomIndex;
        Random random = new Random();

        randomIndex = random.nextInt(attackStats.length);
        randomAttack = attackStats[randomIndex];

        if (target.getName().equals("Voldemort")) {
            randomAttack = 2.0f * randomAttack;
        } else if (target.getName().equals("Bellatrix LeStrange")) {
            randomAttack = randomAttack;
        } else if (target.getName().equals("Dementor")) {
            randomAttack = 0.5f * randomAttack;
        }

        newPlayerHealth = currentPlayerHealth - randomAttack;

        if (newPlayerHealth <= 0) {
            player.setHealth(0);
        } else {
            player.setHealth(newPlayerHealth);
        }

        return randomAttack;
    }

    /**
     * @return modified xPos from MOVE action
     */
    private int calculateMoveXPos() {
        int currentXPos;
        int newXPos;
        int randomXPos;
        Random random = new Random();
        int randomXPosIndex = random.nextInt(xPosStats.length);
        randomXPos = xPosStats[randomXPosIndex];

        currentXPos = player.getTransform().getXPos();
        newXPos = randomXPos + currentXPos;
        player.getTransform().setXPos(newXPos);
        return newXPos;
    }

    /**
     * @return modified yPos from MOVE action
     */
    private int calculateMoveYPos() {
        int currentYPos;
        int newYPos;
        int randomYPos;
        Random random = new Random();
        int randomYPosIndex = random.nextInt(yPosStats.length);
        randomYPos = yPosStats[randomYPosIndex];

        currentYPos = player.getTransform().getYPos();
        newYPos = randomYPos + currentYPos;
        player.getTransform().setYPos(newYPos);
        return newYPos;
    }

    /**
     * @return modified rotation from MOVE action
     */
    private float calculateMoveRotation() {
        float currentRotation;
        float newRotation;
        float randomRotation;
        Random random = new Random();
        int randomRotationIndex = random.nextInt(rotationStats.length);
        randomRotation = rotationStats[randomRotationIndex];

        currentRotation = player.getTransform().getRotation();
        newRotation = randomRotation * currentRotation;
        player.getTransform().setRotation(newRotation);
        return newRotation;
    }

    /**
     * @return modified speed from MOVE action
     */
    private int calculateMoveSpeed() {
        int currentSpeed;
        int newSpeed;
        int randomSpeed;
        Random random = new Random();
        int randomSpeedIndex = random.nextInt(speedStats.length);
        randomSpeed = speedStats[randomSpeedIndex];

        currentSpeed = player.getTransform().getSpeed();
        newSpeed = randomSpeed;
        player.getTransform().setSpeed(newSpeed);
        return newSpeed;
    }

    /**
     * @return speak string from SPEAK action
     */
    public String calculateSpeakActionString() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();
        String binderEmoji = unicodeEmoji.getEmoji("binder");
        String speechBubbleEmoji = unicodeEmoji.getEmoji("speech-bubble");
        String randomSpeak;

        Random random = new Random();
        int randomSpeakIndex = random.nextInt(speakStats.length);
        randomSpeak = speakStats[randomSpeakIndex];

        String speakString = "\n" + speechBubbleEmoji + " shhh... " + speechBubbleEmoji;
        String playerSpeech = "\n|__ " + "PLAYER says: " + randomSpeak;

        String updatedValues = "\n\n" + binderEmoji + " Updated Player " + binderEmoji;
        String playerString = getPlayerString();
        String actionStats = speakString + playerSpeech + updatedValues + playerString;
        return actionStats;
    }

    /**
     * @return string with player stats to be shown in cli
     */
    private String getPlayerString() {
        String attackAdditionalSeparator;
        if (actionType.equals("ATTACK")) {
            attackAdditionalSeparator = "|";
        } else {
            attackAdditionalSeparator = "";
        }
        String playerString = "\n|__ PLAYER:\n" + attackAdditionalSeparator + "   |__ Health: " + player.getHealth() + "\n" + attackAdditionalSeparator + "   |__ xPos: " + player.getTransform().getXPos() + "\n" + attackAdditionalSeparator + "   |__ yPos: " + player.getTransform().getYPos() + "\n" + attackAdditionalSeparator + "   |__ Rotation: " + player.getTransform().getRotation() + "\n" + attackAdditionalSeparator + "   |__ Speed: " + player.getTransform().getSpeed();
        return playerString;
    }

    /**
     * @return string with actor (villain) stats to be shown in cli
     */
    private String getVillainString() {
        String targetString = "\n|" + "\n|__ " + target.getName() +  ": \n " + "   |__ Health: " + target.getHealth() + "\n  " + "  |__ xPos: " + target.getTransform().getXPos() + "\n  " + "  |__ yPos: " + target.getTransform().getYPos()+ "\n  " + "  |__ yPos: " + target.getTransform().getYPos() + "\n  " + "  |__ Rotation: " + target.getTransform().getRotation() + "\n  " + "  |__ Speed: " + target.getTransform().getSpeed();
        return targetString;
    }

    /**
     * @return string with player death statement to be shown in cli
     */
    private String getPlayerDeathString() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();
        String redCircle = unicodeEmoji.getEmoji("red-circle");
        String playerDeathString;
        if (player.getHealth() <= 0) {
            playerDeathString = "\n\n" + redCircle + " PLAYER 1 Eliminated by " + target.getName() + "!";
        } else {
            playerDeathString = "";
        }
        return playerDeathString;
    }

    /**
     * @return string with player death statement to be shown in cli
     */
    private String getTargetDeathString() {
        UnicodeEmoji unicodeEmoji = new UnicodeEmoji();
        String redCircle = unicodeEmoji.getEmoji("red-circle");
        String targetDeathString;
        if (target.getHealth() <= 0) {
            targetDeathString = "\n\n" + redCircle +  " " + target.getName() + " Eliminated by PLAYER!";
        } else {
            targetDeathString = "";
        }
        return targetDeathString;
    }
}
