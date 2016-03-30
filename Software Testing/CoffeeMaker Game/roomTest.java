import static org.junit.Assert.*;
import org.junit.Test;

public class roomTest
{

  @Test
  /**
   * Although this is not used in our code base, it is defined in our class.
   * This test simply determines if the null constructor does in fact make a
   * null room object.
   * @throws Exception
   */
  public void testEmptyConstructor() throws Exception
  {
	  room testRoom = new room();
	  assertFalse(testRoom.hasCream);
	  assertFalse(testRoom.hasCoffee);
	  assertFalse(testRoom.hasSugar);
	  assertFalse(testRoom.northDoor);
	  assertFalse(testRoom.southDoor);
	  assertNull(testRoom.roomDescription);
	  assertNull(testRoom.northDoorDescription);
	  assertNull(testRoom.southDoorDescription);
	  assertNull(testRoom.northRoom);
	  assertNull(testRoom.southRoom);
  }
  
  @Test
  /**
   * Tests regular constructor that is used more frequently in our program.
   * Determines that all fields are appropriately set for two separate rooms,
   * thereby determining that the constructor works and can make separate objects
   * at the same time.
   * @throws Exception
   */
  public void testRoomConstructor() throws Exception
  {
	  room testRoom1 = new room(true, false, false, true, false, "test room", "test north door", "test south door", null, null);
	  room testRoom2 = new room(false, true, false, false, true, "test second room", "test second north", "test second south", testRoom1, null);
	  assertTrue(testRoom1.hasCream);
	  assertFalse(testRoom1.hasCoffee);
	  assertFalse(testRoom1.hasSugar);
	  assertTrue(testRoom1.northDoor);
	  assertFalse(testRoom1.southDoor);
	  assertEquals("test room", testRoom1.roomDescription);
	  assertEquals("test north door", testRoom1.northDoorDescription);
	  assertEquals("test south door", testRoom1.southDoorDescription);
	  assertNull(testRoom1.northRoom);
	  assertNull(testRoom1.southRoom);
	  assertFalse(testRoom2.hasCream);
	  assertTrue(testRoom2.hasCoffee);
	  assertFalse(testRoom2.hasSugar);
	  assertFalse(testRoom2.northDoor);
	  assertTrue(testRoom2.southDoor);
	  assertEquals("test second room", testRoom2.roomDescription);
	  assertEquals("test second north", testRoom2.northDoorDescription);
	  assertEquals("test second south", testRoom2.southDoorDescription);
	  assertEquals(testRoom1, testRoom2.northRoom);
	  assertNull(testRoom2.southRoom);
  }
	
  @Test
  /**
   * Determines that first northRoom is set to null, and then confirms
   * that our setNorthRoom() function works by assigning northRoom and
   * determining that they are equal.
   * Also checks that room2's northRoom is still null - thus checking that
   * the mutator method only changed the desired room's field.
   * @throws Exception
   */
  public void testSetNorthRoom() throws Exception
  {
	  room room1 = new room();
	  room room2 = new room();
	  assertNull(room1.northRoom);
	  room1.setNorthRoom(room2);
	  assertEquals(room1.northRoom, room2);
	  assertNull(room2.northRoom);
  }

  @Test
  /**
   * Does essentially the same as the test above, but with our southRoom
   * mutator.
   * @throws Exception
   */
  public void testSetSouthRoom() throws Exception
  {
	  room room1 = new room();
	  room room2 = new room();
	  assertNull(room1.southRoom);
	  room1.setSouthRoom(room2);
	  assertEquals(room1.southRoom, room2);
	  assertNull(room2.southRoom);
  }

  @Test
  /**
   * Tests that the fullRoomDescriptor method returns the correct string.
   * Tested edge cases by ensuring that the right description was returned, regardless of if the 
   * room being described has a north door, south door, or both.
   * @throws Exception
   */
  public void testFullRoomDescriptor() throws Exception
  {
	  String testDescription = "You see a small room.\nIt has a quaint sofa.\nA magenta door leads north.";
	  coffeeMaker cm = new coffeeMaker();
	  room testRoom = cm.currentRoom;
	  assertEquals(testDescription, testRoom.fullRoomDescriptor());
	  testDescription = "You see a funny room.\nIt has a sad record player.\nA beige door leads north.\nA massive door leads south.";
	  testRoom = testRoom.northRoom;
	  assertEquals(testDescription, testRoom.fullRoomDescriptor());
	  testDescription = "You see a rough room.\nIt has a perfect air hockey table.\nA minimalist door leads south.";
	  testRoom = testRoom.northRoom;
	  testRoom = testRoom.northRoom;
	  testRoom = testRoom.northRoom;
	  testRoom = testRoom.northRoom;
	  assertEquals(testDescription, testRoom.fullRoomDescriptor());
  }
}