// import package dependencies
import game.GameVariables;
import styling.*;
import entities.Actor;
import actions.Action;
import test.JUnitTesterS53;

// import java util dependencies
import java.util.Scanner;

/**
 * Main: ENTRY POINT
 */
public class Main {
    /**
     * Handle game logic
     * @param args
     */
    public static void main(String[] args) {
        // initialize new game
        GameVariables game = new GameVariables();
        game.startMessage();

        // cli coloring
        ColorDict colorCode = new ColorDict();
        String selectionCode = colorCode.getCLIColor("SELECTION");
        String resetCode = colorCode.getCLIColor("RESET");

        // create player
        Actor player1 = game.getPlayer();

        // game logic & method mapping
        Scanner scanner = new Scanner(System.in);
        int actionChosen;
        while (!(game.isGameFinished())) {
            System.out.println(selectionCode + "Choose an Action:\n   1. Attack\n   2. Move\n   3. Speak\n   4. Eat\n   5. View Current Entities\n   6. Run JUnit Testing\n   7. END GAME\n Input: ");
            actionChosen = scanner.nextInt();
            if (actionChosen == 1) {
                Actor actorToAttack = game.getRandomActor();
                Action attack = new Action(player1, actorToAttack, "ATTACK");
            } else if (actionChosen == 2) {
                Action move = new Action(player1, player1, "MOVE");
            } else if (actionChosen == 3) {
                Action speak = new Action(player1, player1, "SPEAK");
            } else if (actionChosen == 4) {
                Action move = new Action(player1, player1, "EAT");
            } else if (actionChosen == 5) {
                System.out.println(game.getCurrentEntities());
            } else if (actionChosen == 6) {
                JUnitTesterS53.runS12JUnitTests();
            } else if (actionChosen == 7) {
                game.setGameFinished(true);
            } else {
                System.out.println("Enter valid Action");
            }
            System.out.println(resetCode);
            game.updateEntities();
        }
        // game over message
        game.endMessage();
    }
}