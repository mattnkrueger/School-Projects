// add to package
package test;

// import actions test modules
import actions.ActionJUnitTest;

// import entities test modules
import entities.EntityJUnitTest;
import entities.ActorJUnitTest;
import entities.LocationJUnitTest;
import entities.ObstacleJUnitTest;

// import game test modules
import game.GameVariablesJUnitTest;

// import styling test modules
import styling.CenterCLIMessageJUnitTest;
import styling.ColorDictJUnitTest;
import styling.UnicodeEmojiJUnitTest;

/**
 * Test all modules included in /test
 */
public class JUnitTesterS53 {
    /**
     * call all runAllTests for testing modules in project
     */
    public static void runS12JUnitTests() {
        ActionJUnitTest actionJUnitTest = new ActionJUnitTest();
        EntityJUnitTest entityJUnitTest = new EntityJUnitTest();
        ActorJUnitTest actorJUnitTest = new ActorJUnitTest();
        LocationJUnitTest locationJUnitTest = new LocationJUnitTest();
        ObstacleJUnitTest obstacleJUnitTest = new ObstacleJUnitTest();
        GameVariablesJUnitTest gameVariablesJUnitTest = new GameVariablesJUnitTest();
        CenterCLIMessageJUnitTest centerCLIMessageJUnitTest = new CenterCLIMessageJUnitTest();
        ColorDictJUnitTest colorDictJUnitTest = new ColorDictJUnitTest();
        UnicodeEmojiJUnitTest unicodeEmojiJUnitTest = new UnicodeEmojiJUnitTest();

        System.out.println("running S53 JUnit tests...");
        System.out.println("-----------------------------------------------------");
        actionJUnitTest.runAllTests();
        entityJUnitTest.runAllTests();
        actorJUnitTest.runAllTests();
        locationJUnitTest.runAllTests();
        obstacleJUnitTest.runAllTests();
        gameVariablesJUnitTest.runAllTests();
        centerCLIMessageJUnitTest.runAllTests();
        colorDictJUnitTest.runAllTests();
        unicodeEmojiJUnitTest.runAllTests();
        System.out.println("-----------------------------------------------------");
        System.out.println("Success!");
        System.out.println("S53_GameEngine_Medium: AlL JUnit Tests Passed");
    }

    /**
     * run all tests from inside this module
     * @param args
     */
    public static void main(String[] args) {
        runS12JUnitTests();
    }
}
