/**
 * This class is part of the Prison Break application. 
 * Prison Break is a very simple, text based adventure game.
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Mehmet Sofi
 *
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = {
        //dont forget to edit showAll commands everytime you edit or create a new command
        "go", "quit", "help" , "look" , "eat", "map", "exercise", "pick", "drop", "list","items", "picked", "back", "moves", "left", "give", "alexander", "uniform", "charge", "use", "beamer"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println("\n quit = quits the game, look = looks arround the room, eat = eats food, exercise = workout back=moves one step back everytime it's called");
        System.out.println();
        System.out.println("Your two word commands are;\n 1. go direction where direction is either north, south, east or west.");   //explains the commands in the game and how to use them
        System.out.println(" 2. pick item where item is specified");
        System.out.println(" 3.drop item where item is something you can pick.");  
        System.out.println(" 4. moves left = shows you the number of moves you can make before getting caught");
        System.out.println(" 5. charge beamer^ = charges the beamer to the room the player is currently in");
        System.out.println(" 6. use beamer = teleports you to the room beamer was charged in");
        System.out.println("Your three word commands are.\n 1. *list items picked* which lists all the items picked and currently being carried");
        
        System.out.println(" 2. *give alexander uniform* which gives alexander uniform");
        System.out.println();
    }
}
