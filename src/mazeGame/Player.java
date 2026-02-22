package mazeGame;

/*
 * Creating Player class to represent the player in the maze
 * Handles movement logic
 */

/*
 * Attributes: Player's coordinates
 * Methods: Attribute getters, Movements (up, down, left, right, quit, invalid move)
 */

//Creating child class containing attributes and methods that inherited from the parent Entity class
public class Player extends Entity{
	
	//Constructor to initialize player attributes
	Player (int playerX, int playerY) {
		super (playerX, playerY); //initializes these attributes in the parent Entity class which stores coordinates
		System.out.println("\nA new player has joined the game."); //prints to signal that a player has been created
//		System.out.println("Player is placed at "+playerX+","+playerY); //CHECKER: Player's starting position
	}
	
	//Movement Method (up, down, left, right, quit, invalid move)
	//Overrides the move method in Entity parent class
	//Uses protected attributes from Entity parent class
	@Override
	public void move (char dir, Map map) {
		//maximum index in the maze to avoid leaving the grid
		int moveLimit = map.getSize() - 1;
		
		//Movement Logic: Check boundaries > check if target tile is a wall or treasure > move
		
		//Movement: UP / "W"
		if (dir == 'W') {
			//boundary check: ensures the player is not at the top edge
			if (entityY>=0 && entityY<moveLimit) {
				//checks if the target tile is a wall
				if (map.isWall(entityX,entityY+1)==true) {
					System.out.println("\nYou bumped into a wall!");
				} else { //otherwise allow movement
					entityY = entityY + 1;	
				}
			} else {	
				System.out.println("\nYou've hit the boundary! Try again!");
			}

		//Movement: DOWN / "S"
		} else if (dir == 'S') {
			//boundary check: ensures the player is not at the bottom edge
			if (entityY>0 && entityY<=moveLimit) {
				//checks if the target tile is a wall
				if (map.isWall(entityX,entityY-1)==true) {
					System.out.println("\nYou bumped into a wall!");
				} else { //otherwise allow movement
					entityY = entityY - 1;
				}
			} else {	
				System.out.println("\nYou've hit the boundary! Try again!");
			}
			
		//Movement: RIGHT / "D"
		} else if (dir == 'D') {
			//boundary check: ensures the player is not at the rightmost edge
			if (entityX>=0 && entityX<moveLimit) {
				//checks if the target tile is a wall
				if (map.isWall(entityX+1,entityY)==true) {
					System.out.println("\nYou bumped into a wall!");
				} else { //otherwise allow movement
					entityX = entityX + 1;
				}
			} else {	
				System.out.println("\nYou've hit the boundary! Try again!");
			}
			
		//Movement: LEFT / "A"	
		} else if (dir == 'A') {
			//boundary check: ensures the player is not at the leftmost edge
			if (entityX>0 && entityX<=moveLimit) {
				//checks if the target tile is a wall
				if (map.isWall(entityX-1,entityY)==true) {
					System.out.println("\nYou bumped into a wall!");
				} else { //otherwise allow movement
					entityX = entityX - 1;
				}
			} else {	
				System.out.println("\nYou've hit the boundary! Try again!");
			}

		}
		
	}
	
}