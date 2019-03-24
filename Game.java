import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack; 
import java.util.Iterator;

/**
 *  Welcome to Prison Break"
 *  Prison Break is a new, incredibly boring text based adventure game.
 *  You are Stanislav Mogilevich. You have been convicted of a murder you didn't commit.
 *  After 5 years in prison, you have been so fed up of living in a cell and decided to escape the prison.
 *    
 *  To play this game, create an instance of this class and call the "play" method
 *  or use the main method from GameMain class
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Mehmet Sofi 
 * 
 */

public class Game 
{
    private Parser parser;
    private Room breakArea, prisonCell, guardsRoom, kitchen, outside, corridor, teleporterRoom, uniformRoom, trapRoom, currentRoom; //creates some rooms as fields of class game
    private Item key, gun, uniform, beamer, item, bag, clock, table; //stores items as fields so they can be used in other methodss
    private Charachter prisonMate;  //stores charachters as fields so they can be used in other methods  
    private HashMap<Item, Room> itemRoom = new HashMap<>();//creates an hasmap with key as item and room as value
    private ArrayList<Item> items;    //stores items created
    private int weightLimit ; // creates an field to set weight limit
    private int weightCarried  ; //weight og the items carried
    private int movesLimit; //stores the maximun number of moves that can be made by user
    private Room charachtersCurrentRoom ;
    private int movesMade; //stores the number of moves made by the user
    private ArrayList<Item> itemsCarried = new ArrayList<>() ;    //stores the items carried by charachter 
    private ArrayList<String> namesOfItemsCarried = new ArrayList<>();
    private ArrayList<Room> teleportRooms = new ArrayList<>(); //Arraylist to store the rooms that the teleport room can teleport to.
    private ArrayList<Item> itemsCarriedByCharachter = new ArrayList<>(); //arraylist to store the objects that the charachter is carrying with him
    private HashMap<Item, String> itemNames = new HashMap<>(); // in order to access item names in string form
    private HashMap<String, Item> itemNamesStringToItem = new HashMap<>(); // in order to acces items from string
    private HashMap<Room, String> charachterPositionString; //cerates an hashMap to convert rooms into string
    private Stack<Room> previousRooms = new Stack<>();   //creates a stack to store previous rooms
    private Room beamerRoom ; //creates a field to store the room beamer was charged in
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        itemNames = new HashMap<>();
        itemRoom = new HashMap<>();
        itemNamesStringToItem = new HashMap<>();
        charachterPositionString = new HashMap<>();
        items = new ArrayList<>();
        itemsCarried = new ArrayList<>();
        namesOfItemsCarried = new ArrayList<>();
        teleportRooms = new ArrayList<>();
        itemsCarriedByCharachter = new ArrayList<>();   
        createItems();                             //creates the desired items that were specified in createItems method
        createRooms();                             //creates the desired rooms that were specified in createRooms method
        createCharachters();                       //chreates the desired charachter 
        weightLimit = 30 ;                         //set the weight limit of items that the player can carry with him
        weightCarried = 0 ;                        // sets the initial weight carried to zero
        movesLimit = 11;                           // sets the number of maximun movements that can be made by the player
        movesMade = 0;                             // when the game starts, moves made is set to zero
        parser = new Parser();                     // creates a parser everytime a game is created 
        previousRooms = new Stack();               // a tack to store previous rooms made by user
    }

    /**
     * creates Items in the game
     * initiliases 3 hash Maps in order to access use an itemm both in string from and item form 
     */
    public void createItems()
    {
          

        //create items with description and weight
        gun = new Item("Pick the gun, You need this gun to kill the guards", 25);
        key = new Item("A key, you need this key to open a door", 1);
        uniform = new Item("This uniform will ensure your prison mate is not spotted", 6);
        bag = new Item(" Dont Forget your Bag, It contains your items : clothes, food, your wife's photograph ", 20);
        table = new Item("This is a kitchen table, you can eat while you are here:)", 200);
        beamer = new Item("this beamer can teleport you to the room it was charged in", 5);
        clock = new Item("There is a clock on the wall, it shows the time", 2);
        
        //adds items to an arraylist created to store every item created
        items.add(gun);
        items.add(key);
        items.add(uniform);
        items.add(bag);
        items.add(table);
        items.add(beamer);
        items.add(clock);
        //to initialise the hashMap itemNames
        itemNames.put(gun, "gun");
        itemNames.put(key, "key");
        itemNames.put(uniform, "uniform");
        itemNames.put(bag, "bag");
        itemNames.put(table, "table");
        itemNames.put(beamer, "beamer");
        itemNames.put(clock, "clock");
        
        // to initialise the hashmap itemNameStringToItem
        itemNamesStringToItem.put("gun", gun);
        itemNamesStringToItem.put("key", key);
        itemNamesStringToItem.put("uniform", uniform);
        itemNamesStringToItem.put("bag", bag);
        itemNamesStringToItem.put("table", table);
        itemNamesStringToItem.put("beamer", beamer);
        itemNamesStringToItem.put("clock", clock);

    }
    
    /**
     * creates charachters and links them to an original start position
     */
    public void createCharachters(){
        
        prisonMate = new Charachter("Alexander Mirkovic", prisonCell, "Today is the day we escape brother, we must be quick!!!");
        
        // prisonMate.setCharachterName(prisonMate, "Alexander Mirkovic");
        
        charachtersCurrentRoom = prisonMate.getFirstAppeared();
        
    }

    /**
     * Create all the rooms and link their exits together.
     * puts items in to particular rooms
     */
    private void createRooms()
    {
        Room  office, weaponRoom;

        // create the rooms
        prisonCell = new Room(" in prison cell no 007");
        office = new Room("in guard's office but you are a lucky man he is sleeping!");
        kitchen = new Room("in prisoner's kitchen");
        corridor = new Room("in the corridor. Well done , you got out of prisoner area. Now watch out for guards!");
        outside = new Room("outside of the prison. YOU HAVE ESCAPED!!! NOW YOU ARE A FREE MAN!!!");
        weaponRoom = new Room("in the weapon room, collect a weapon");
        guardsRoom = new Room("in guard's room, you have to kill all the guard or you are caught");
        uniformRoom = new Room("in unifrom room, full of prisoners clothes and also one police uniform");
        breakArea = new Room("in the break Area, you can exercise here");
        teleporterRoom = new Room("in teleporter room. This room teleports you to a random room within prisoner area.\n You have been teleported");
        trapRoom = new Room("in trap room, you have been trapped");
        
        // initialise room exits
        prisonCell.setExit("east", office);
        prisonCell.setExit("west", kitchen);
        prisonCell.setExit("south", teleporterRoom);

        office.setExit("west", prisonCell);
        office.setExit("east", trapRoom);
        
        kitchen.setExit("east", prisonCell);
        kitchen.setExit("north", corridor);
        kitchen.setExit("west", breakArea);

        corridor.setExit("north", weaponRoom);
        corridor.setExit("east", guardsRoom);
        corridor.setExit("south", kitchen);
        corridor.setExit("west", uniformRoom);

        weaponRoom.setExit("south", corridor);

        guardsRoom.setExit("east", outside);
        guardsRoom.setExit("west", corridor);

        breakArea.setExit("east", kitchen);

        uniformRoom.setExit("east", corridor);

        teleporterRoom.setExit("north", prisonCell);
        
        trapRoom.setExit("west", office);

        currentRoom = prisonCell;  // start game in prisonCell
        
        //stores rooms in string format
        prisonCell.setRoomName(prisonCell, "prisonCell");
        
        office.setRoomName(office, "office");
        
        kitchen.setRoomName(kitchen, "kitchen");
        
        corridor.setRoomName(corridor, "corridor");
        
        weaponRoom.setRoomName(weaponRoom, "weapon room");
        
        guardsRoom.setRoomName(guardsRoom, "guards room");
        
        breakArea.setRoomName(breakArea, "break Area");
        
        uniformRoom.setRoomName(uniformRoom, "uniform Room");
        
        teleporterRoom.setRoomName(teleporterRoom, "teleporter room");
        
        trapRoom.setRoomName(trapRoom, "trap room");
        
        //initialises the rooms in which items are located
        
        setItemRoom(items.get(0), weaponRoom);  // gun
        setItemRoom(items.get(1), office);  //key
        setItemRoom(items.get(2), uniformRoom);  //uniform
        setItemRoom(items.get(3), prisonCell); //bag
        setItemRoom(items.get(4), kitchen);  //kitchen table
        setItemRoom(items.get(5), breakArea); //beamer
        setItemRoom(items.get(6), prisonCell); //clock
        
        //initiliases an arraylist for the rooms the teleporter can transport you
        teleportRooms.add(kitchen);
        teleportRooms.add(office);
        teleportRooms.add(breakArea);
        teleportRooms.add(prisonCell);
    }


     /**
     * Define the item as key for room
     * 
     */
    public void setItemRoom(Item item, Room room) 
    {
        itemRoom.put(item, room);
    }
    
    /**
     *  Main play routine.  Loops until end of play.
     *  ends the loop if the objective of game is reached(to escape the prison) or
     *  ends the loop if the player reaches the number of moves allowed
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            
            finished = processCommand(command);
            if(currentRoom.equals(outside)){
                System.out.println("You have escaped the Prison,You Win!!!. ");
                gameOver();
                finished = true;
            }
            else if(movesMade > movesLimit){
                System.out.println("You couldnt escape the prison in time.\n The guards have caught you.\n YOU LOST!!!");
                finished = true;
            }
            if(currentRoom.equals(trapRoom)){
                System.out.println("You werent meant to visit this room. That was a big mistake.\n The guards have caught you.\n YOU LOST!!!");
                finished = true;
            }
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Prison Break");
        System.out.println("Prison Break is a new, incredibly boring text based adventure game.");
        System.out.println("");
        System.out.println("You are Stanislav Mogilevich. You have been convicted of a murder you didn't commit.");
        System.out.println("After 5 years in prison, you have been so fed up of living in a cell and decided to escape the prison with your cellmate Alexander Mirkovic ");
        System.out.println("");
        System.out.println("You have " + movesLimit + " moves to finish the game.\n You lose the game if you are not able to finish the game in " + movesLimit + " moves.");
        System.out.println("A move is made when a player moves from one room to an another. Picking items, dropping items, eating and etc does not count as a move.\n");
        System.out.println("Type Help to see the valid commands in the game");
        System.out.println(""); 
        System.out.println(currentRoom.getLongDescription());  //prints out long description of the current room when the game is started
        printItemsInTheRoom();  //prints the items in the current room
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        //if command is not in the list, it prints out text stating this and returns false wherever it's called
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();  // accesses the second command word from command class 
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")){
            look();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("eat")){
            printEat();
        }
        else if (commandWord.equals("map")){
            printMap();
        }
        else if(commandWord.equals("exercise")){
            printExercise();
        }
        else if(commandWord.equals("pick")){
            pick(command);
        }
        else if (commandWord.equals("drop")){
            dropItems(command);
        }
        else if(commandWord.equals("list")){
            listAllItems(command);
        }
        else if(commandWord.equals("back")){
            back();
        }
        else if(commandWord.equals("moves")){
            movesLeft(command);
        }
        else if(commandWord.equals("give")){
            giveCharachterItem(command);
        }
        else if(commandWord.equals("charge")){
            chargeBeamer(command);
        }
        else if(commandWord.equals("use")){
            useBeamer(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are in prison for a crime you didn't commit.");
        System.out.println("You are trying to escape, but how?");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else if(currentRoom.equals(kitchen) && nextRoom.equals(corridor)){   //conditional statement for first challenge
            if(itemsCarried.contains(key)){     //if player is carrying the item let him go in to the corridor
                System.out.println("You have unclocked the door!!!");
                previousRooms.push(currentRoom);  //previous room pused into stack 
                currentRoom = nextRoom;
                look(); 
            }
            else{
                System.out.println("The door is locked, find the key to open the door");    
            }
        }
        else if (currentRoom.equals(corridor) && nextRoom.equals(guardsRoom)){  //conditional statement for 2nd challenge
            if(itemsCarried.contains(gun) && itemsCarriedByCharachter.contains(uniform)){         //if the player is carrying a gun, let him go into the guards room and kill the guards
                System.out.println("You have killed the guard.\n Now there is nothing in front of you that can stop you from escaping");
                previousRooms.push(currentRoom);  //pushes in the stack currentRoom on top before being changed
                currentRoom = nextRoom;
                look();  
            }
            else if(itemsCarried.contains(gun)){
                System.out.println("yes, you have a gun but what about Alexander, find him a uniform so that he is not spotted");
            }
            else if(itemsCarriedByCharachter.contains(uniform)){
                System.out.println("The room is full of guards, find a weapon and come back.");
            }
            else{
                System.out.println("The room is full of guards, find a weapon and come back.");
            }
        }
        else if(currentRoom.equals(prisonCell) && nextRoom.equals(teleporterRoom)){     //conditional statement for teleporting the user when entered the teleporter room
            previousRooms.push(currentRoom);  //pushes in the stack currentRoom on top before being changed
            currentRoom = nextRoom;  
            System.out.println(currentRoom.getLongDescription());
            teleport();  //calls the teleporter method when the player is in teleporter room
        }
        else {
            previousRooms.push(currentRoom);  //pushes in the stack currentRoom on top before being changed
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            printItemsInTheRoom();
        }
        movesMade ++;  //increases the number of moves made by 1
        charachtersCurrentRoom = currentRoom;  //sets charachters current position same aa the player
        
    }

    /**
     * one of the criteria of the game is to finish it in the number of moves allowed
     * so the user can check how many moves she/he has left until losing the game by using this two word command 
     * 
     * @param 2nd command entered by user
     */
    private void movesLeft(Command command){
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what moves means...
            System.out.println("moves???");
            return;
        }

        String secondWord = command.getSecondWord();
        
        if(secondWord.equals("left")){
            getMovesLeft(); // gets the value from the method that calculates moves left
        }
        else{
            System.out.println("What do you mean");
        }
    }
    /**
     * beamer is a device that you can charge in a room, and whenever you decide to use it, it teleports you to the place it was charged
     * this method charges the beamer so that it stores the rooom it was charged in
     * checks if the beamer is carried by the player than executes than charges it
     */
    private void chargeBeamer(Command command){
        if(itemsCarried.contains(beamer)){
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't what to charge...
                System.out.println("use???");
                return;
            }
            
            String secondWord = command.getSecondWord();
            
            if(secondWord.equals("beamer")){ 
                beamerRoom = currentRoom; //stores the room where command charge beamer is called
                String beamerRoomString = currentRoom.getRoomName(beamerRoom);  // stores room's name for printing out purposes
                System.out.println("beamer charged in room " + beamerRoomString + ".");
            }
            else{
                System.out.println("charge what???"); //if second word written is not beamer,  we dont now what to charge
            }
        }
        else{
            System.out.println("you need to carry the beamer to use it"); //if beamer is not carried, we cannot use it
        }
    }
    
    /**
     * a method to teleport to the room the beamer was charged in 
     * this method checks if the beamer is carried, whether or not it was charged 
     * before executing the command
     */
    private void useBeamer(Command command){
        if(itemsCarried.contains(beamer)){
            if(!command.hasSecondWord()) {
                // if there is no second word, we don't what to use...
                System.out.println("use???");
                return;
            }
            
            String secondWord = command.getSecondWord();
            
            if(secondWord.equals("beamer")){
                if(beamerRoom != null){  //checks so that beamer is actually charged
                    previousRooms.push(currentRoom) ; 
                    currentRoom = beamerRoom; //teleports you to the room beamer was charged in
                    String beamerRoomString = currentRoom.getRoomName(currentRoom); //pritns out the current room  so player understand he/she is teleported
                    System.out.println("Beamer teleported you to "+ beamerRoomString + ". Charge it again to be able to use it."); 
                    beamerRoom = null; //sets it to null so that beamer cannot be used to teleport to the room it was charged in again
                    movesMade++; //using the beamer counts as a move
                    charachtersCurrentRoom = currentRoom; //teleports the charachter prison mate with the user
                }
                else{
                    System.out.println("Beamer not charged, charge beamer first than use it"); //if beamer is not charged, we cant use it
                }
            }
            else{
                System.out.println("use what???");
            }
        }
        else{
            System.out.println("You need to carry the beamer to use it"); //beamer must be carried to use this command
        }
    }
    
    /**
     * lits all the items that are carried by the player
     */
    private void listAllItems(Command command){
        if(!command.hasSecondWord()){
            //if there is no second word, we dont know what to do
            System.out.println("List what?");
        }
        if(!command.hasThirdWord()){
            //if there is no third word, we don't know what to do
            System.out.println("List what?");
        }
        
        String items = command.getSecondWord();    
        String picked = command.getThirdWord();
        
        if(items != null && picked !=null){  
            if(items.equals("items") && picked.equals("picked")){
                getNamesOfItemsCarried(); //calls the method get names of items carried which prints out names of items carried
            }
            else{
            System.out.println("List What??? I don't get what you mean.");
        }
        }
        
    }
    
    /**
     * gives the charachter uniform
     * 
     * this 3 word command uses second command and the third user has entered to gove charachter an item 
     * which in this case is uniform 
     * 
     * uniform is give usig give alexander uniform
     */
    private void giveCharachterItem(Command command){
        if(!command.hasSecondWord()){
            System.out.println("give what?");
        }
        if(!command.hasThirdWord()){
            System.out.println("give alexander what?");
        }
        
        String charachter = command.getSecondWord();
        String item = command.getThirdWord();
        
        if(charachter != null && item !=null){
            if(charachter.equals("alexander") && item.equals("uniform")){
                if(itemsCarriedByCharachter.contains(uniform) != true ){
                    if(itemRoom.get(uniform).equals(uniformRoom) && currentRoom.equals(uniformRoom)){
                        itemsCarriedByCharachter.add(uniform);
                        System.out.println("You have given Alexander uniform, now he wont be spotted");
                    }
                    else{
                        System.out.println("there is no uniform in this room");
                    }
                }
                else{
                    System.out.println("You have already given Alexander uniform, now get going");
                }
            }
            else{
                System.out.println("Alexander cannot carry anything, he is suffering from an injury. Find a method so he doesnt get spotted");
            }
        }
        
    }
    
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
       * look is a command to print out the description of room and exits
     */
    private void look(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println("You are in the same room with " + prisonMate.getName());
        System.out.println(prisonMate.getLongSpeech());
        printItemsInTheRoom();
    }

    /**
     * eat command makes the player eat so he is not hungry
     */
    private void printEat(){
        if(currentRoom.equals(kitchen)){
            System.out.println("you have eaten enough, now you can continue your adventure");
            movesMade = movesMade +5; 
        }
        else{
            System.out.println("Go to the kitchen to eat Food");
        }
    }

    /**
     * exercise command allows the player to exercise if and only he is in the break area
     * however this method exercise has no importance in finishing the game
     */
    public void printExercise()
    {
        if(currentRoom.equals(breakArea))
        {
            System.out.println("Now you have exercised, continue your journey");   
        }
        else{
            System.out.println("Go to the break Area to exercise");
        }
    }

    /**
     * pick <item> method allows the user to pick items
     * user can pick multiple items given that the weight limit is not passed
     * @param second command entered by user
     */
    public void pick(Command command)
    {
        String secondWord = command.getSecondWord();

        if(itemNamesStringToItem.get(secondWord) != null){
            item = itemNamesStringToItem.get(secondWord); 
            weightCarried += item.getWeight();
            String itemName = itemNames.get(item) ; 
            if(itemRoom.get(item).equals(currentRoom)){
                if(weightCarried < weightLimit && (itemsCarried.contains(item) == false) && item.getWeight()<weightLimit){
                    itemsCarried.add(item);
                    System.out.println(itemName + " picked up");
                    namesOfItemsCarried.add(itemName);
                    int canCarry = weightLimit - weightCarried; //integer to store how much more the player can carry
                    System.out.println("You can now only carry up to " + canCarry+ " kg");
                }
                else if(weightCarried>weightLimit && (itemsCarried.contains(item) == false) && item.getWeight()<weightLimit){
                    System.out.println("You cannot carry more than " + weightLimit+ " kg.\n" + "Remember you can drop items");
                    weightCarried -=item.getWeight();
                }
                else if(itemsCarried.contains(item) == true && weightCarried<weightLimit){
                    System.out.println("You have already picked up " + itemName +".");
                    weightCarried -=item.getWeight();
                }
                else if(item.getWeight()>weightLimit){
                    System.out.println("You can not pick this item up, it's too heavy!!!");
                    weightCarried -=item.getWeight();
                }
                
            }
            else{
                System.out.println("there are no such items in this Room");
                weightCarried -=item.getWeight();
            }
        }
        else{
            System.out.println("There are no such items in this room");
        }
    }

    /**
     * a method to drop items
     * 
     * @param item which you want to drop
     */
    public void dropItems(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what item to drop
            System.out.println("Drop what?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = itemNamesStringToItem.get(itemName);

        if(namesOfItemsCarried.contains(itemName) == true){

            System.out.println(itemName + " dropped.\n Go back to " + itemName +"'s original room to pick it up again");
            weightCarried -= item.getWeight();
            namesOfItemsCarried.remove(itemName);
            itemsCarried.remove(item);

        }
        else{
            System.out.println("You did not pick an item called " + itemName + ".");
        }
    }

    /**
     * returns all the items that the user is carrying
     */
    public void getNamesOfItemsCarried()
    {
        if(itemsCarried.size()>0){   //if any items are carried, we continue to the loop
            for(int i = 0; i<itemsCarried.size(); i++){  //prints out all the items in arratlist by checking all elements of the arraylist one by one 
                String nameOfItem = namesOfItemsCarried.get(i); 
                System.out.println(nameOfItem);
            }
        }
        else{
            System.out.println("You have not picked up any items");
        }
    }

    /** A method so that user is able to go back
     * the user can also go back for many steps as a stack is used to store the rooms he has previously been
     * stack used has the name previousRooms
     * .pop command in a stack takes the Item that is on top of the stack and than deletes it
     */
    public void back()
    {
        if(previousRooms.empty() != true){
            Room previousRoom = previousRooms.pop() ;  //sets previous rooom equal to the last item that is entered into the Stack and deletes item from the stack
            currentRoom = previousRoom ;  //makes the user go back one step 
            movesMade ++;   //using the command back counts as a move
            System.out.println(currentRoom.getLongDescription());
        }
        else{
            System.out.println("You have not made any moves before this point.");
        }
    }

    /**
     * a method created to print all items in the room if there exits an item in the room
     * if a room contains more than one item it checks them one by one to ensure there exists no item that is in the room 
     * and not prtinted out
     */
    public void printItemsInTheRoom(){
        Room searchRoom = currentRoom;
        if(itemRoom.get(key).equals(searchRoom) && itemsCarried.contains(key) == false)  {
            System.out.println(key.getLongDescription());
        }
        else if((itemRoom.get(bag).equals(searchRoom) && itemsCarried.contains(bag) == false) || (itemRoom.get(clock).equals(searchRoom) && itemsCarried.contains(clock))){
            if((itemRoom.get(bag).equals(searchRoom) && itemsCarried.contains(bag) == false) || (itemRoom.get(clock).equals(searchRoom) && itemsCarried.contains(clock))){
                System.out.println(bag.getLongDescription());
                System.out.println(clock.getLongDescription());
            }
            else if(itemRoom.get(bag).equals(searchRoom) && itemsCarried.contains(bag) == false){
                System.out.println(bag.getLongDescription());
            }
            else if(itemRoom.get(clock).equals(searchRoom) && itemsCarried.contains(clock)){
                System.out.println(clock.getLongDescription());
            }
        }
        else if(itemRoom.get(beamer).equals(searchRoom) && itemsCarried.contains(beamer) == false){
            System.out.println(beamer.getLongDescription());
        }
        else if(itemRoom.get(table).equals(searchRoom) && itemsCarried.contains(table) == false){
            System.out.println(table.getLongDescription());
        }
        else if(itemRoom.get(uniform).equals(searchRoom) && itemsCarried.contains(uniform) == false){
            System.out.println(uniform.getLongDescription());
        }
        else if(itemRoom.get(key).equals(searchRoom) && itemsCarried.contains(key) == false){
            System.out.println(key.getLongDescription());
        }
        else if(itemRoom.get(gun).equals(searchRoom) && itemsCarried.contains(gun) == false){
            System.out.println(gun.getLongDescription());
        }
    }
    
    /**
     * a method to print out charachters that are in the room with you
     * this method was created for testing purposes to see if the charachter was able to follow the player
     */
    public void printCharachtersCurrentRoom(){
        String charachterRoomString = charachtersCurrentRoom.getRoomName(charachtersCurrentRoom);
        System.out.println("the charachter is in " + charachterRoomString);
    }
    
    /**
     * teleports you into a random room
     * it doesnt transfer you to all rooms though
     * because that would have made the game much easier and player could have completed the game ,
     * without even collecting items and just transferring himself to outside by using the teleporter(many times because the process is random)
     */
    public void teleport( )
    {
        Random random = new Random();  //creates a new random number generator
        //restricts the max random number to the size of arraylist that contains the rooms to which teleporter telepors
        //which means a random numbe between 0(inclusive) and size of arraylist teleport rooms(exclusive) will be generated
        int index = random.nextInt(teleportRooms.size());   
        currentRoom = teleportRooms.get(index); //teleports the user to the room with index that was randomly generated
        System.out.println(currentRoom.getLongDescription()); //prints out which room the user is teleported to
    }

    /**
     * calculates the number of moves left by subtracting moves made from moves limit( set in constructor )
     */
    public void getMovesLeft(){
        int movesLeft = movesLimit - movesMade;
        System.out.println("You have " + movesLeft + " moves left to complete the game.");
    }

    /**
     * a simple method to print the map of the game a
     * it also shows the name of each room 
     */
    public void printMap(){
        System.out.println("             _______                                                | ");
        System.out.println("            |   W   |                                               |");
        System.out.println("             ___ ___                                                |");
        System.out.println(" _______     __| |__             ________                           |");
        System.out.println("|      |____|       |____________|      |___________________________|");
        System.out.println("|  U    ____    C    ____________   GR   ___________________________         OUTSIDE...");
        System.out.println("|______|    |___  __|            |______|                           |");
        System.out.println("               |  |                                                 |");
        System.out.println("             __|_ |__                  _______     _____            |");
        System.out.println(" ___________|         |                |       |__|     |           |");
        System.out.println("|       ____     K     ________________|  GO    __   SR |           |");
        System.out.println("|   B   |   |          _______  _______|       |  |_____|           |");
        System.out.println("|_______|   |_________|      |  |      |_______|                    |");
        System.out.println("                         ____|  |____                               |");
        System.out.println("                         |    PC    |                               |");
        System.out.println("                         |____  ____|                               |");
        System.out.println("                         ____|  |____                               |");
        System.out.println("                         |    TR    |                               |");
        System.out.println("                         |__________|                               |");
        System.out.println("                                                                    |");
        System.out.println("PC = Prison Cell, K = Kitchen, GO= Guard's Office, B = Break Area, U= Uniform Room, C = Corridor \nW = Weapon Room, GR = Guards Room, TR = Teleporter Room, SR = secret room");

    }

    /**
     * A simple method to print game over 
     */
    public void gameOver()
    {
        System.out.println(".........     ........     .           .    .........           .........   .           .   .........  .......  ");
        System.out.println(".             .      .     ..         ..    .                   .       .    .         .    .          .      . ");
        System.out.println(".             .      .     . .       . .    .                   .       .     .       .     .          .      . ");
        System.out.println(".   .....     ........     .  .     .  .    ........            .       .      .     .      ........   .......  ");
        System.out.println(".       .     .      .         .   .   .    .                   .       .       .   .       .          . . .    ");
        System.out.println(".       .     .      .     .    . .    .    .                   .       .        . .        .          .     .  ");
        System.out.println(".........     .      .     .     .     .    ........            .........         .         ........   .      . ");
    }

}