public class coffeeMaker
{
  //create class variables
  room currentRoom;
  boolean playerCream = false;
  boolean playerCoffee = false;
  boolean playerSugar = false;
  String inventory;

  room smallRoom;
  room funnyRoom;
  room refinancedRoom;
  room dumbRoom;
  room blodthirstyRoom;
  room roughRoom;

  //generic constructor setting up the rooms individually.
  public coffeeMaker()
  {
    //make the room objects
    room smallRoom = new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null);
    room funnyRoom = new room(false, false, false, true, true, "You see a funny room.\nIt has a sad record player.\n", "A beige door leads north.\n", "A massive door leads south.", null, smallRoom);
    room refinancedRoom = new room(false, true, false, true, true, "You see a refinanced room.\nIt has a tight pizza.\n", "A dead door leads north.\n", "A smart door leads south.", null, funnyRoom);
    room dumbRoom = new room(false, false, false, true, true, "You see a dumb room.\nIt has a flat energy drink.\n", "A vivacious door leads north.\n", "A slim door lead south.", null, refinancedRoom);
    room bloodthirstyRoom = new room(false, false, false, true, true, "You see a bloodthirsty room.\nIt has a beautiful bag of money.\n", "A purple door leads north.\n", "A sandy door leads south.", null, dumbRoom);
    room roughRoom = new room(false, false, true, false, true, "You see a rough room.\nIt has a perfect air hockey table.\n", null, "A minimalist door leads south.", null, bloodthirstyRoom);

    //Assign northRoom now that all rooms are made
    smallRoom.setNorthRoom(funnyRoom);
    funnyRoom.setNorthRoom(refinancedRoom);
    refinancedRoom.setNorthRoom(dumbRoom);
    dumbRoom.setNorthRoom(bloodthirstyRoom);
    bloodthirstyRoom.setNorthRoom(roughRoom);

    currentRoom = smallRoom;
  }

  //generates a string of the
  public String printInventory(boolean playerCoffee, boolean playerCream, boolean playerSugar)
  {
    StringBuilder sb = new StringBuilder();

    if(playerCoffee)
      sb.append("You have a cup of delicious coffee.\n");
    else
      sb.append("YOU HAVE NO COFFEE!\n");

    if(playerCream)
      sb.append("You have some fresh cream.\n");
    else
      sb.append("YOU HAVE NO CREAM!\n");

    if(playerSugar)
      sb.append("You have some tasty sugar.\n");
    else
      sb.append("YOU HAVE NO Sugar!\n");

    return sb.toString();
  }

  //prints out 'help' dialogue
  public String printHelp()
  {
    return "You can enter one of 6 commands - N, S, L, I, D, or H. Case does not matter.\n" +
        "N stands for 'north.' You will move through the north door, if available.\n" +
        "S stands for 'south.' You will move through the south door, if available.\n" +
        "L stands for 'look.' You will look around you to see if the room has cream, sugar, or coffee, and pick it up if available.\n" +
        "I stands for 'inventory.' You will check your inventory to see what coffee ingredients you have on you.\n" +
        "D stands for 'drink.' You will attempt to drink the coffee. If you have all ingredients, you win! If not, you lose.\n" +
        "H stands for 'help.' You will see this dialogue printed out again if you enter 'h.'\n";
  }

  public room getCurrentRoom()
  {
    return currentRoom;
  }

  public void setCurrentRoom(room currentRoom)
  {
    this.currentRoom = currentRoom;
  }

  public boolean isPlayerCream()
  {
    return playerCream;
  }

  public void setPlayerCream(boolean playerCream)
  {
    this.playerCream = playerCream;
  }

  public boolean isPlayerCoffee()
  {
    return playerCoffee;
  }

  public void setPlayerCoffee(boolean playerCoffee)
  {
    this.playerCoffee = playerCoffee;
  }

  public boolean isPlayerSugar()
  {
    return playerSugar;
  }

  public void setPlayerSugar(boolean playerSugar)
  {
    this.playerSugar = playerSugar;
  }

  public String getInventory()
  {
    return inventory;
  }

  public void setInventory(String inventory)
  {
    this.inventory = inventory;
  }

  public room getSmallRoom()
  {
    return smallRoom;
  }

  public void setSmallRoom(room smallRoom)
  {
    this.smallRoom = smallRoom;
  }

  public room getFunnyRoom()
  {
    return funnyRoom;
  }

  public void setFunnyRoom(room funnyRoom)
  {
    this.funnyRoom = funnyRoom;
  }

  public room getRefinancedRoom()
  {
    return refinancedRoom;
  }

  public void setRefinancedRoom(room refinancedRoom)
  {
    this.refinancedRoom = refinancedRoom;
  }

  public room getDumbRoom()
  {
    return dumbRoom;
  }

  public void setDumbRoom(room dumbRoom)
  {
    this.dumbRoom = dumbRoom;
  }

  public room getBloodthirstyRoom()
  {
    return blodthirstyRoom;
  }

  public void setBloodthirstyRoom(room blodthirstyRoom)
  {
    this.blodthirstyRoom = blodthirstyRoom;
  }

  public room getRoughRoom()
  {
    return roughRoom;
  }

  public void setRoughRoom(room roughRoom)
  {
    this.roughRoom = roughRoom;
  }
}