/**
 * Class Charachter - a charachter in the game
 *
 * @author Mehmet Sofi
 * //do i need to access the room i  item class??
 */
public class Charachter
{
    // instance variables - replace the example below with your own
    private String name;
    private Room firstAppeared;
    private String speech ; 
    
    /**
     * Constructor for objects of class Charachter
     */
    public Charachter(String name, Room firstAppeared, String speech)
    {
        // initialise instance variables
        this.name = name;
        this.firstAppeared = firstAppeared;
        this.speech = speech;
        
    }

    /**
     * A method to access the description of charachter
     */
    public String getName()
    {
        // returns description
        return name;
    }
    
    /**
     * returns speech
     */
    public String getSpeech(){
        return speech;
    }
    
    /**
     * returns firstAppeared
     */
    public Room getFirstAppeared(){
        return firstAppeared;
    }
    
    /**
     * returns the long speech of the charachter
     */
    public String getLongSpeech(){
        String longSpeech = name + ": " + speech;
        return longSpeech;
    }
}
