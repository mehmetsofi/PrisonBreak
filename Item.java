import java.util.HashMap;
/**
 * Class Item - an Item in the game
 * 
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all items in the game.
 *
 * @author Mehmet Sofi
 * 
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int weight ;
    private String description;
    private int weightLimit ; 
    // HashMap<Item, String> items = new HashMap<>();   //an HashMap to store item's description
    HashMap<Item, Integer> itemsWeight = new HashMap<>(); // an HashMap to store items and their weights

    /**
     * Constructor for objects of class Item
     * creates an item with
     * @param weight and description
     */
    public Item(String description, int weight)
    {
        this.description = description;
        this.weight = weight;
        // items = new HashMap<>();
        itemsWeight = new HashMap<>();
        weightLimit = 40;   //sets the limit of weight that can be carried with the player
    }

    /**
     * Set weight of the item
     * @param item for which weight wants to be set
     * @param weight of the item.
     */
    public void setWeight(Item item, int weight) 
    {
        itemsWeight.put(item, weight);
    }

    /**
     * @return The short description of the item
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the item in the form:
     * eg;
     *     This item is is a sword.
     *     This item weights 10 kilograms
     * @return A long description of this item
     */
    public String getLongDescription()
    {
        return description + ".\n" + "This item weighs " + getWeight() + "kilograms.\n" + "You can only carry a maximum of " + weightLimit + " kg." ;
    }

    /**
     * @return the weight of the item
     */
    public int getWeight()
    {
        return weight;   
    }
    
    /**
     * @return the weightLimit of the items that can be carried at a time
     */
    public int getWeightLimit()
    {
        return weightLimit;
    }

}
