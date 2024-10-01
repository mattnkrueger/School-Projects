// add to package
package game;

// import package dependencies
import entities.*;
import styling.*;

// import java util dependencies
import java.util.Random;
import java.util.ArrayList;

/**
 * Handle game entities and game output messages
 */
public class GameVariables {
    /**
     * is the game finished?
     */
    private static boolean gameFinished;

    /**
     * USER PLAYER
     */
    private final Actor player;

    /**
     * Array of locations present
     */
    private static ArrayList<Location> locations;

    /**
     * Array of actors (bad guys) present
     */
    private static ArrayList<Actor> actors;

    /**
     * Array of obstacles present
     */
    private static ArrayList<Obstacle> obstacles;

    /**
     * GameVariables (non-argument)
     * initialize new game variables
     */
    public GameVariables() {
        gameFinished = false;

        locations = new ArrayList<>();
        Transform diagonAllyTransform = new Transform(50, 50, 20, 0);
        Location diagonAlleyLocation = new Location("Diagon Alley", diagonAllyTransform, "store", "Behind the Leaky Cauldron, London");
        locations.add(diagonAlleyLocation);

        Transform platformNineAndThreeQuartersTransform = new Transform(100, 100, 30, 0);
        Location platformNineAndThreeQuartersLocation = new Location("Platform 9 & 3/4", platformNineAndThreeQuartersTransform, "train", "King's Cross Station, London");
        locations.add(platformNineAndThreeQuartersLocation);

        Transform homeTransform = new Transform(0, 0, 0, 0);
        Location homeLocation = new Location("Home", homeTransform, "home" , "Number 4, Privet Drive, Little Whinging, Surrey");
        locations.add(homeLocation);

        Transform hogwartsTransform = new Transform(3000, 3000, 0, 0);
        Location hogwartsLocation = new Location("Hogwarts", hogwartsTransform, "castle" , "Unknown, Scotland");
        locations.add(hogwartsLocation);

        actors = new ArrayList<>();
        Transform voldemortTransform = new Transform(100, 100, 50, 800);
        Actor voldemortActor = new Actor("Voldemort", voldemortTransform, "wizard-male",1000);
        actors.add(voldemortActor);

        Transform bellatrixLestrangeTransform = new Transform(100, 100, 50, 5);
        Actor bellatrixLestrangeActor= new Actor("Bellatrix Lestrange", bellatrixLestrangeTransform, "wizard-female", 500);
        actors.add(bellatrixLestrangeActor);

        Transform dementorTransform = new Transform(100, 100, 50, 300);
        Actor dementorActor = new Actor("Dementor", dementorTransform, "floating-male", 100);
        actors.add(dementorActor);

        obstacles = new ArrayList<>();
        Transform theVeilTransform = new Transform(-1000, -5000, 32, 0);
        Obstacle theVeilObstacle = new Obstacle("The Veil", theVeilTransform, "rock", 100);
        obstacles.add(theVeilObstacle);

        Transform whompingWillowTransform = new Transform(3000, 3000, 30, 25);
        Obstacle whompingWillowObstacle = new Obstacle("Whomping Willow", whompingWillowTransform, "tree", 1000);
        obstacles.add(whompingWillowObstacle);

        Transform player1Transform = new Transform(100,300,20, 100);
        this.player = new Actor("PLAYER", player1Transform, "player", 1000);
    }

    /**
     * @param gameFinished
     */
    public void setGameFinished(boolean gameFinished) {
        GameVariables.gameFinished = gameFinished;
    }

    /**
     * @return gameFinished
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * @return USER PLAYER
     */
    public Actor getPlayer() {
        return player;
    }

    /**
     * cli message for all entities and their toString representations
     */
    public String getCurrentEntities() {
        String header = "----------- START Current Entities ---------------------------------------\n";
        String players = getPlayers();
        String actors = getActors();
        String obstacles = getObstacles();
        String locations = getLocations();
        String footer = "----------- END Current Entities ---------------------------------------\n";
        String viewAllEntities = header + players + actors + obstacles + locations + footer;
        return viewAllEntities;
    }

    /**
     * view all locations toString representation
     */
    private String getLocations() {
        StringBuilder locationsBuilder = new StringBuilder();

        String header = "Locations:\n--------------------------------------------------------------------\n";
        locationsBuilder.append(header);
        for (Location location : locations) {
            locationsBuilder.append(location.toString()).append("\n");
        }
        return locationsBuilder.toString();
    }

    /**
     * view all actors toString representation
     */
    private String getActors() {
        StringBuilder actorsBuilder = new StringBuilder();

        String header = "\nActors:\n--------------------------------------------------------------------\n";
        actorsBuilder.append(header);
        for (Actor actor : actors) {
            actorsBuilder.append(actor.toString()).append("\n");
        }
        return actorsBuilder.toString();
    }

    /**
     * view all obstacles toString representation
     */
    private String getObstacles() {
        StringBuilder obstaclesBuilder = new StringBuilder();

        String header = "Obstacles:\n--------------------------------------------------------------------\n";
        for (Obstacle obstacle : obstacles) {
            obstaclesBuilder.append(obstacle.toString()).append("\n");
        }
        return obstaclesBuilder.toString();
    }

    /**
     * view USER PLAYER toString representation
     */
    private String getPlayers() {
        String header = "\nPlayer:\n--------------------------------------------------------------------\n";
        String player = this.getPlayer().toString();
        return header + player;
    }

    /**
     * cli output for game starting
     */
    public void startMessage() {
        UnicodeEmoji unicodeEmojiDict = new UnicodeEmoji();
        ColorDict colorDict = new ColorDict();
        String startEmoji = unicodeEmojiDict.getEmoji("start-game");
        String startTextColoring = colorDict.getCLIColor("START");
        String resetColoring = colorDict.getCLIColor("RESET");
        String startMessage = startTextColoring + startEmoji + " STARTING GAME " + startEmoji + resetColoring;
        CenterCLIMessage centerCLIMessage = new CenterCLIMessage(startMessage, 100);
        centerCLIMessage.centerText();
    }

    /**
     * clie output for game ending
     */
    public void endMessage() {
        UnicodeEmoji unicodeEmojiDict = new UnicodeEmoji();
        ColorDict colorDict = new ColorDict();
        String endEmoji = unicodeEmojiDict.getEmoji("end-game");
        String endTextColoring = colorDict.getCLIColor("END");
        String resetColoring = colorDict.getCLIColor("RESET");
        String endMessage = endTextColoring + endEmoji + " ENDING GAME " + endEmoji + resetColoring;
        CenterCLIMessage centeredEndMessage = new CenterCLIMessage(endMessage, 100);
        centeredEndMessage.centerText();
    }

    /**
     * @return random actor from array of actors (bad guys)
     */
    public Actor getRandomActor() {
        Actor targetActor;
        int randomIndex;
        Random random = new Random();
        randomIndex = random.nextInt(actors.size());
        targetActor = actors.get(randomIndex);
        return targetActor;
    }

    /**
     * game logic post Action. Check for win/loss and remove any actors if eliminated
     */
    public void updateEntities() {
        if (this.player.getHealth() <= 0) {
            gameFinished = true;
            printLoseMessage();
        }
        actors.removeIf(actor -> actor.getHealth() <= 0);
        if (actors.size() == 0) {
            gameFinished = true;
            printWinMessgage();
        }
    }

    /**
     * cli message for game won
     */
    public void printWinMessgage() {
        UnicodeEmoji unicodeEmojiDict = new UnicodeEmoji();
        String winEmoji = unicodeEmojiDict.getEmoji("win");
        String messageToCenter = winEmoji + " Congrats - PLAYER won! " + winEmoji;
        CenterCLIMessage centerCLIMessage = new CenterCLIMessage(messageToCenter, 80);
        centerCLIMessage.centerText();
    }

    /**
     * cli message for game loss
     */
    public void printLoseMessage() {
        UnicodeEmoji unicodeEmojiDict = new UnicodeEmoji();
        String loseEmoji = unicodeEmojiDict.getEmoji("loss");
        String messageToCenter = loseEmoji + " Sorry - PLAYER lost! " + loseEmoji;
        CenterCLIMessage centerCLIMessage = new CenterCLIMessage(messageToCenter, 80);
        centerCLIMessage.centerText();
    }
}
