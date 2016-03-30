CS 1632 - Software Quality Assurance

Deliverable 2

For this assignment, your group will write code and unit tests for an authorized reproduction of Coffee Maker Quest. Groups can be made up of two people, selected by yourselves. If you prefer to work alone, you can also do that.

Requirements for this program are in the requirements.txt file in this directory. In case of ambiguity, please see the original program as an example of what to display and how the system should work. However, you should not code in defects!

All code and tests should be on GitHub by the beginning of class on the due date.

Code coverage should be at an absolute minimum of 80%.

Tests should be written in JUnit. You should test every public method except main. For each method that you test, ensure that you get a variety of different equivalence classes! Think about different edge and base cases, what the happy path is, etc.

Requirements for Coffee Maker Quest

FUN-ITERATION - At each iteration of the game, the user shall be able enter one of six commands - "N" to go North, "S" to go South, "L" to Look for items, "I" for Inventory, "H" for Help, or "D" to Drink.

FUN-UNKNOWN-COMMAND - If a player enters a command not specified by FUN-ITERATION, the system shall respond with the phrase "What?".

FUN-INPUT-CAPS - The system shall be case-insensitive in regards to input values; that is, it shall accept capital and lower-case letters and treat them as equivalent.

FUN-MOVE - The system shall allow a player to move North only if a door exists going North, and South only if a door exists going South.

FUN-WIN - The player shall win the game if and only if Coffee, Sugar, and Cream have been collected by the player and then drunk.

FUN-LOSE - The player shall lose the game if and only if the player Drinks but has not collected all of the items (Coffee, Sugar, and Cream).

FUN-INVENTORY - Upon entering "I" for inventory, the player shall be informed of the items that he/she has collected (consisting of Coffee, Sugar, and Cream).

FUN-LOOK - Upon entering "L" for Look, the player shall collect any items in the room and those items will be added to the player's inventory.

FUN-HELP - Upon entering "H" for Help, the player shall be shown a listing of possible commands and what their effects are.

FUN-UNIQ-ROOM - Each room in the house shall have a unique adjective describing it.

FUN-UNIQ-ROOM-FURNISHING - Each room in the house shall have one and only one unique furnishing visible to the user upon entering the room.
