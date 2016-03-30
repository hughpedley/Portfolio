import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class coffeeMakerTest
{
  @Test
  /**
   * Checks that current room is not null, and then checks that our setCurrentRoom method is functional by
   * changing the current room to a null object.
   * @throws Exception
   */
  public void testSetCurrentRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNotNull(cm.currentRoom);
    cm.setCurrentRoom(null);
    assertNull(cm.currentRoom);
  }

  @Test
  /**
   * Confirms that the getCurrentRoom method picks up the object within coffee maker known as current room,
   * and works appropriately.
   * @throws Exception
   */
  public void testGetCurrentRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertEquals(cm.getCurrentRoom(), cm.currentRoom);
  }


  @Test
  /**
   * This mutator method is supposed to change the boolean value associated with whether or not the player has cream.
   * We confirmed that it worked by first making the coffeemaker object and double checking that the player does not, by default,
   * have cream. We then changed it so that the player does have cream, and checked that it changed the appropriate value.
   * @throws Exception
   */
  public void testSetPlayerCream() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertFalse(cm.playerCream);
    cm.setPlayerCream(true);
    assertTrue(cm.playerCream);
  }


  @Test
  /**
   * This is the exact same as above, except with coffee instead of cream.
   * @throws Exception
   */
  public void testSetPlayerCoffee() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertFalse(cm.playerCoffee);
    cm.setPlayerCoffee(true);
    assertTrue(cm.playerCoffee);
  }


  @Test
  /**
   * This is the exact same as above, except with sugar instead of coffee or cream.
   * @throws Exception
   */
  public void testSetPlayerSugar() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertFalse(cm.playerSugar);
    cm.setPlayerSugar(true);
    assertTrue(cm.playerSugar);
  }

  @Test
  /**
   * This accessor method is supposed to return a boolean based on whether or not the player has cream.
   * We checked that the player does not have cream at the beginning of the game, and then
   * used our previously tested mutator method to change it so that the player does have cream. We then
   * used our accessor once again to confirm that the appropriate value was returned.
   * @throws Exception
   */
  public void testIsPlayerCream() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertFalse(cm.isPlayerCream());
    cm.setPlayerCream(true);
    assertTrue(cm.isPlayerCream());
  }


  @Test
  /**
   * This is the exact same as above, except with coffee instead of cream.
   * @throws Exception
   */
  public void testIsPlayerCoffee() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertFalse(cm.isPlayerCoffee());
    cm.setPlayerCoffee(true);
    assertTrue(cm.isPlayerCoffee());
  }


  @Test
  /**
   * This is the exact same as above, except with sugar instead of coffee or cream.
   * @throws Exception
   */
  public void testIsPlayerHasSugar() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertFalse(cm.isPlayerSugar());
    cm.setPlayerSugar(true);
    assertTrue(cm.isPlayerSugar());
  }

  @Test
  /**
   * The inventory starts as null in our game, and so we confirmed with this accessor method that the
   * associated value is in fact null.
   * @throws Exception
   */
  public void testGetInventory() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNull(cm.getInventory());
  }

  @Test
  /**
   * This test of a mutator method confirms that setInventory works by taking the (previously tested) accessor method
   * and confirming that the inventory is no longer returning a null value.
   * @throws Exception
   */
  public void testSetInventory() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    cm.setInventory("hello");
    assertNotNull(cm.getInventory());
  }

  @Test
  /**
   * This accessor method is specifically for the first room in our game. We confirmed that it worked properly by
   * checking that the room returned was the exact same as a new one made to the same specifications.
   * @throws Exception
   */
  public void testGetSmallRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    cm.smallRoom = new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null);
    assertEquals(cm.getSmallRoom(), new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null));
  }

  @Test
  /**
   * This is the same as above, except with the second room.
   * @throws Exception
   */
  public void testGetFunnyRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    cm.funnyRoom = new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null);
    assertEquals(cm.getFunnyRoom(), new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null));
  }

  @Test
  /**
   * This is the same as above, except with the third room.
   * @throws Exception
   */
  public void testGetRefinancedRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    cm.refinancedRoom = new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null);
    assertEquals(cm.getRefinancedRoom(), new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null));
  }


  @Test
  /**
   * This is the same as above, except with the fourth room.
   * @throws Exception
   */
  public void testGetDumbRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    cm.dumbRoom = new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null);
    assertEquals(cm.getDumbRoom(), new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null));
  }


  @Test
  /**
   * This is the same as above, except with the fifth room.
   * @throws Exception
   */
  public void testGetBloodthirstyRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    cm.blodthirstyRoom = new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null);
    assertEquals(cm.getBloodthirstyRoom(), new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null));
  }


  @Test
  /**
   * This is the same as above, except with the sixth room.
   * @throws Exception
   */
  public void testGetRoughRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    cm.roughRoom = new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null);
    assertEquals(cm.getRoughRoom(), new room(true, false, false, true, false, "You see a small room.\nIt has a quaint sofa.\n", "A magenta door leads north.", null, null, null));
  }

  @Test
  /**
   * This is a mutator method specific to the first room. It is initialized within the cm variable, and is then set
   * to null. The test confirms that the setter method changed the small room.
   * @throws Exception
   */
  public void testSetSmallRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNotNull(cm.smallRoom);
    cm.setSmallRoom(null);
    assertNull(cm.smallRoom);
  }

  @Test
  /**
   * This is the same as above except for the second room.
   * @throws Exception
   */
  public void testSetFunnyRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNotNull(cm.funnyRoom);
    cm.setFunnyRoom(null);
    assertNull(cm.funnyRoom);
  }
  
  @Test
  /**
   * This is the same as above except for the third room.
   * @throws Exception
   */
  public void testSetRefinancedRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNotNull(cm.refinancedRoom);
    cm.setRefinancedRoom(null);
    assertNull(cm.refinancedRoom);
  }
  
  @Test
  /**
   * This is the same as above except for the fourth room.
   * @throws Exception
   */
  public void testSetDumbRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNotNull(cm.dumbRoom);
    cm.setDumbRoom(null);
    assertNull(cm.dumbRoom);
  }
  
  @Test
  /**
   * This is the same as above except for the fifth room.
   * @throws Exception
   */
  public void testSetBlodthirstyRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNotNull(cm.blodthirstyRoom);
    cm.setBloodthirstyRoom(null);
    assertNull(cm.blodthirstyRoom);
  }
  
  @Test
  /**
   * This is the same as above except for the sixth room.
   * @throws Exception
   */
  public void testSetRoughRoom() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertNotNull(cm.roughRoom);
    cm.setRoughRoom(null);
    assertNull(cm.roughRoom);
  }

  @Test
  /**
   * This confirms that the printHelp function prints out the correct help string by assuring that they are equivalent strings.
   * @throws Exception
   */
  public void testPrintHelp() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertEquals(cm.printHelp(), "You can enter one of 6 commands - N, S, L, I, D, or H. Case does not matter.\n" +
        "N stands for 'north.' You will move through the north door, if available.\n" +
        "S stands for 'south.' You will move through the south door, if available.\n" +
        "L stands for 'look.' You will look around you to see if the room has cream, sugar, or coffee, and pick it up if available.\n" +
        "I stands for 'inventory.' You will check your inventory to see what coffee ingredients you have on you.\n" +
        "D stands for 'drink.' You will attempt to drink the coffee. If you have all ingredients, you win! If not, you lose.\n" +
        "H stands for 'help.' You will see this dialogue printed out again if you enter 'h.'\n");
  }

  @Test
  /**
   * This confirms that the printInventory function works by double checking it against both a FULL and EMPTY inventory.
   * It is accomplished by using the printInventory function with the appropriate set of variables and comparing to the
   * filled in strings by us, as desired.
   * @throws Exception
   */
  public void testPrintInventory() throws Exception
  {
    coffeeMaker cm = new coffeeMaker();
    assertEquals(cm.printInventory(true, true, true), "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n");
    assertEquals(cm.printInventory(false, false, false), "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO Sugar!\n");

  }

  @Test
  /**
   * This confirms that the empty constructor creates a room with null and false values, as appropriate,
   * so that effectively nothing is initialized.
   * @throws Exception
   */
  public void testNewRoom() throws Exception
  {
    room r = new room();
    assertEquals(r, new room(false, false, false, false, false, null, null, null, null, null));
  }

}