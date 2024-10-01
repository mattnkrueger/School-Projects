// add to package
package styling;

// import java util dependencies
import java.util.HashMap;
import java.util.Random;

/**
 * Hold unicode representations for emojis used in cli
 */
public class UnicodeEmoji {
    /**
     * hash map to hold emojis
     */
    public static HashMap<String, String> unicodeDict;

    /**
     * emojis common to EAT action
     */
    private final String[] eatEmojis = {"chicken", "cake", "beer"};

    /**
     * emojis common to ATTACK action
     */
    private final String[] attackEmojis = {"fire-spell", "water-spell", "leaves-spell"};

    /**
     * emojis common to MOVE action
     */
    private final String[] moveEmojis = {"broom", "walk"};

    /**
     * emojis common to SPEAK action
     */
    private final String[] speakEmojis = {"speech-bubble", "speak"};

    /**
     * UnicodeEmoji (non-argument)
     * initialize unicodeDict hashmap
     */
    public UnicodeEmoji() {
        unicodeDict = new HashMap<>();
        this.buildUnicodeDict();
    }

    /**
     * populate unicode dictionary (hashmap)
     */
    private void buildUnicodeDict() {
        unicodeDict.put("fist", "\uD83E\uDD1C");
        unicodeDict.put("sword", "\uD83D\uDDE1\uFE0F");
        unicodeDict.put("fire-spell", "\uD83E\uDE84 \uD83D\uDD25");
        unicodeDict.put("water-spell", "\uD83E\uDE84 \uD83C\uDF0A");
        unicodeDict.put("leaves-spell", "\uD83E\uDE84 \uD83C\uDF43");

        unicodeDict.put("broom", "\uD83E\uDDF9");
        unicodeDict.put("walk", "\uD83D\uDEB6");
        unicodeDict.put("car", "\uD83D\uDE97");

        unicodeDict.put("chicken", "\uD83C\uDF57");
        unicodeDict.put("cake", "\uD83C\uDF82");
        unicodeDict.put("beer", "\uD83C\uDF7A");
        unicodeDict.put("canned-food", "🥫");

        unicodeDict.put("speech-bubble", "\uD83D\uDCAC");
        unicodeDict.put("speak", "\uD83D\uDDE3\uFE0F");

        unicodeDict.put("wizard-male", "\uD83E\uDDD9\u200D♂\uFE0F");
        unicodeDict.put("wizard-female", "\uD83E\uDDD9");
        unicodeDict.put("floating-male", "\uD83D\uDD74\uFE0F");
        unicodeDict.put("player", "\uD83E\uDD37\u200D♂\uFE0F ⭐");
        unicodeDict.put("rock", "\uD83E\uDEA8");
        unicodeDict.put("tree", "\uD83C\uDF33");
        unicodeDict.put("stadium", "\uD83C\uDFDF\uFE0F");
        unicodeDict.put("default-red", "\uD83D\uDFE5");

        unicodeDict.put("home", "\uD83C\uDFE1");
        unicodeDict.put("castle", "\uD83C\uDFF0");
        unicodeDict.put("store", "\uD83C\uDFEC");
        unicodeDict.put("train", "\uD83D\uDE86");

        unicodeDict.put("arrow", "➡\uFE0F");
        unicodeDict.put("checkmark", "✅");
        unicodeDict.put("stop-sign", "\uD83D\uDED1");
        unicodeDict.put("binder", "\uD83D\uDCD2");
        unicodeDict.put("red-circle", "⛔\uFE0F");

        unicodeDict.put("start-game", "\uD83C\uDFAC");
        unicodeDict.put("end-game", "\uD83D\uDED1");
        unicodeDict.put("win", "\uD83C\uDFC6");
        unicodeDict.put("loss", "\uD83D\uDC4E");
    }

    /**
     * @param actionType
     * @return random emoji from action type emoji array
     */
    public String getRandomEmoji(String actionType) {
        String emojiToPrint;
        int randomIndex;
        String randomEmoji;
        Random random = new Random();

        if (actionType.equals("EAT")) {
            randomIndex = random.nextInt(this.eatEmojis.length);
            randomEmoji = this.eatEmojis[randomIndex];
        } else if (actionType.equals("MOVE")) {
            randomIndex = random.nextInt(this.moveEmojis.length);
            randomEmoji = this.moveEmojis[randomIndex];
        } else if (actionType.equals("ATTACK")) {
            randomIndex = random.nextInt(this.attackEmojis.length);
            randomEmoji = this.attackEmojis[randomIndex];
        } else if (actionType.equals("SPEAK")) {
            randomIndex = random.nextInt(this.speakEmojis.length);
            randomEmoji = this.speakEmojis[randomIndex];
        } else {
            randomEmoji = null;
        }

        emojiToPrint = unicodeDict.get(randomEmoji);
        return emojiToPrint;
    }

    /**
     * @param emoji
     * @return emoji of user choice
     */
    public String getEmoji(String emoji) {
        if (unicodeDict.isEmpty()) {
            buildUnicodeDict();
            return unicodeDict.getOrDefault(emoji, unicodeDict.get("default-red"));

        } else {
            return unicodeDict.getOrDefault(emoji, unicodeDict.get("default-red"));
        }
    }
}
