//Room object, connects to adjacent rooms and contains all necessary fields
public class room
{
    //all necessary fields for room object
    boolean hasCream, hasCoffee, hasSugar;
    boolean northDoor, southDoor;
    String roomDescription;
    String northDoorDescription;
    String southDoorDescription;
    public room northRoom;
    public room southRoom;

    //blank constructor
    public room()
    {

    }

    //full constructor for room object
    public room(boolean cream, boolean coffee, boolean sugar, boolean north, boolean south, String describeRoom, String describeNorth, String describeSouth, room roomNorth, room roomSouth)
    {
      hasCream = cream;
      hasCoffee = coffee;
      hasSugar = sugar;
      northDoor = north;
      southDoor = south;
      roomDescription = describeRoom;
      northDoorDescription = describeNorth;
      southDoorDescription = describeSouth;
      northRoom = roomNorth;
      southRoom = roomSouth;
    }

    //Mutator method for northRoom
    public void setNorthRoom(room north)
    {
      this.northRoom = north;
    }

    //Mutator method for southRoom
    public void setSouthRoom(room south)
    {
      this.southRoom = south;
    }

    //prints out description of room and any doors
    public String fullRoomDescriptor()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(roomDescription);

      if(northDoor)
        sb.append(northDoorDescription);

      if(southDoor)
        sb.append(southDoorDescription);

      return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
      if(this == o) return true;
      if(o == null || getClass() != o.getClass()) return false;

      room room = (room) o;

      if(hasCream != room.hasCream) return false;
      if(hasCoffee != room.hasCoffee) return false;
      if(hasSugar != room.hasSugar) return false;
      if(northDoor != room.northDoor) return false;
      if(southDoor != room.southDoor) return false;
      if(roomDescription != null ? !roomDescription.equals(room.roomDescription) : room.roomDescription != null)
        return false;
      if(northDoorDescription != null ? !northDoorDescription.equals(room.northDoorDescription) : room.northDoorDescription != null)
        return false;
      if(southDoorDescription != null ? !southDoorDescription.equals(room.southDoorDescription) : room.southDoorDescription != null)
        return false;
      if(northRoom != null ? !northRoom.equals(room.northRoom) : room.northRoom != null) return false;
      return southRoom != null ? southRoom.equals(room.southRoom) : room.southRoom == null;

    }
}