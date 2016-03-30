import java.util.Scanner;

public class coffeeQuestDriver
{
  public static void main(String[] args)
  {
    coffeeMaker cm = new coffeeMaker();
    Scanner keyboard = new Scanner(System.in);
    String input;

    //loop through the game, only exits program when player attempts to drink coffee
    while(true)
    {
      System.out.println(cm.currentRoom.fullRoomDescriptor());
      System.out.print("\nInstructions (N, S, L, I, D, H) > ");
      input = keyboard.nextLine();
      System.out.println();
      if(input.toLowerCase().equals("n"))
      {
        if(cm.currentRoom.northRoom != null)
          cm.currentRoom = cm.currentRoom.northRoom;
        else
          System.out.println("There is no room to the north!\n");
      }
      else if(input.toLowerCase().equals("s"))
      {
        if(cm.currentRoom.southRoom != null)
          cm.currentRoom = cm.currentRoom.southRoom;
        else
          System.out.println("There is no room to the south!\n");
      }
      else if(input.toLowerCase().equals("l"))
      {
        System.out.println("You look around the room.");
        if(cm.currentRoom.hasCoffee)
        {
          System.out.println("You found coffee!\n");
          cm.playerCoffee = true;
          cm.currentRoom.hasCoffee = false;
        }
        else if(cm.currentRoom.hasCream)
        {
          System.out.println("You found cream!\n");
          cm.playerCream = true;
          cm.currentRoom.hasCream = false;
        }
        else if(cm.currentRoom.hasSugar)
        {
          System.out.println("You found sugar!\n");
          cm.playerSugar = true;
          cm.currentRoom.hasSugar = false;
        }
        else
        {
          System.out.println("You found nothing out of the ordinary.\n");
        }
      }
      else if(input.toLowerCase().equals("i"))
      {
        cm.inventory = cm.printInventory(cm.playerCoffee, cm.playerCream, cm.playerSugar);
        System.out.println(cm.inventory);
      }
      else if(input.toLowerCase().equals("d"))
      {
        if(cm.playerCoffee && cm.playerCream && cm.playerSugar)
        {
          System.out.println("You drink the coffee. Delicious!");
          System.out.println("You win!");
          keyboard.close();
          System.exit(0);
        }
        else
        {
          System.out.println("You don't have the three ingredients! This...drink, if you can call it that, is horrible.");
          System.out.println("You lose.");
          keyboard.close();
          System.exit(0);
        }
      }
      else if(input.toLowerCase().equals("h"))
        System.out.println(cm.printHelp());
      else
        System.out.println("What?\n");
    }
  }
}