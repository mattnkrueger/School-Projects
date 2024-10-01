// add to package
package styling;

// import java util dependencies
import java.util.HashMap;

/**
 * Hold ANSI Escape representations for colors used in cli
 */
public class ColorDict {
    /**
     * hash map holding escape codes
     */
    public static HashMap<String, String> colorDict;

    /**
     * ColorDict (non-argument)
     * initializes the colorDict hashmap
     */
    public ColorDict() {
        this.buildColorDict();
    }

    /**
     * initialize color dict (hash map)
     */
    public void buildColorDict() {
        colorDict = new HashMap<>();

        colorDict.put("EAT", "\u001b[1m \u001B[40m \u001B[33m");
        colorDict.put("MOVE", "\u001b[1m \u001B[40m \u001B[35m");
        colorDict.put("ATTACK", "\u001b[1m \u001B[40m \u001B[36m");
        colorDict.put("SPEAK", "\u001b[1m \u001B[40m \u001b[34m");

        colorDict.put("COUNTER", "\u001B[40m \u001B[47m");
        colorDict.put("SELECTION", "\u001B[47m \u001B[30m");
        colorDict.put("VIEW", "\u001B[40m");
        colorDict.put("END", "\u001B[40m \u001B[31m");
        colorDict.put("START", "\u001B[40m \u001B[32m");

        colorDict.put("RESET", "\u001B[0m");
    }

    /**
     * @param colorCode
     * @return return color code for user input
     */
    public String getCLIColor(String colorCode) {
        return colorDict.getOrDefault(colorCode, colorDict.get("RESET"));
    }
}
