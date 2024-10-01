// add to package
package entities;

/**
 * Location entity in game
 * Extends Entity
 */
public class Location extends Entity {
    /**
     * address of the location
     */
    private String address;

    /**
     * Location (non-argument constructor)
     * calls tetradic constructor with defaults: name = 'Location', transform = 'new Transform()', emoji = 'default-red', address = 'Address'
     */
    public Location() {
        this("Location", new Transform(), "default-red", "Address") ;
    }

    /**
     * Location (tetradic constructor)
     * calls super (entity) and initializes additional Location attributes
     *
     * @param name
     * @param transform
     * @param emoji
     * @param address
     */
    public Location(String name, Transform transform, String emoji, String address) {
        super(name, transform, emoji);
        this.address = address;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.address = address;
    }

    /**
     * @return location address
     */
    public String getLocation() {
        return address;
    }

    /**
     * OVERRIDE: Entity .toString
     * @return string representation of location
     */
    @Override
    public String toString() {
        String locationName = getName();
        String address = getLocation();
        String emoji = getEmojiUnicode();
        String transformString = getTransformString();
        String locationFormat = "Location: %s %s\n";
        String addressFormat = "Address: %s";
        String locationString = String.format(locationFormat, locationName, emoji);
        String addressString = String.format(addressFormat, address);
        String locationAndTransformString = locationString + "|_ " + transformString + "\n|_ " + addressString + "\n";
        return locationAndTransformString;
    }
}
