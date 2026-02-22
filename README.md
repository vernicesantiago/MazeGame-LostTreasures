# 🧩 Java Console Maze Game - Lost Treasures Edition

A strategic, console-based maze navigation game built with Java. The player must navigate a grid to collect all hidden treasures while being pursued by an AI Guard.

## 🚀 How to Play
1. **Run the program**: Execute the `Main.java` file.
2. **Choose your difficulty**: Enter a maze size (e.g., `8` creates an 8x8 grid).
3. **Setup**: The game initializes a fixed maze grid containing walls, treasures, a player, and a guard.
4. **Navigate**: Use the following keys to move:
   - `W`: Up
   - `S`: Down
   - `A`: Left
   - `D`: Right
   - `Q`: Quit
5. **Objective**: Collect all **Treasures** scattered across the map.
6. **Challenge**: Avoid the **Guard**. If the Guard moves onto your tile, the game ends immediately.

## 🛠️ Technical Features
- **Inheritance & Hierarchy**: Both the Player and Guard classes extend the abstract Entity parent class. This allows them to share core attributes like X and Y coordinates while maintaining their own unique behaviors.
- **Polymorphism**: Both the Player and Guard utilize a move() method. However, the logic is "overridden" in each subclass—the Player moves based on user input, while the Guard moves according to its automated logic.
- **Collision Detection**: The game "looks ahead" to prevent the entities from walking into walls or out of the map boundaries.
- **Object-Oriented Design**:
  - `Map`: Manages the grid array, treasure and wall placement, and treasure detection logic.
  - `Entity` : The abstract base class that provides the foundation for all moving characters.
  - `Player & Guard`: Specific implementations of entities with different movement rules.

## 💻 Logic Breakdown
- **Balanced Map Generation**: To keep the game fair but challenging, the code automatically calculates the number of Walls and Treasures based on the maze size (specifically Size - 2). This ensures that as the maze gets larger, the challenge and rewards scale appropriately.
- **Validation**:
  - Confirms the player is still inside the grid before moving to avoid "off-map" errors.
  - The logic "looks ahead" to ensure players cannot walk through walls.
  - Prevents walls from spawning on the player's and the guard's start position.
- **Turn-Based Interaction**: The Guard's movement is gated by a success check. The g1.move method only executes if the Player's coordinates have actually changed, ensuring the Guard doesn't get "extra turns" if the player walks into a wall.
- **Treasure & Win System**: The Map class manages treasure status via the hasTreasure method. The game tracks collection progress, triggering a "Win" state once the method returns a value indicating all treasures have been successfully gathered.
- **Loss Condition**: A coordinate comparison is performed after the Guard moves. If the Player and Guard occupy the same tile (p1.getEntityX() == g1.getEntityX()), the main loop breaks and displays a "Lose" message.
