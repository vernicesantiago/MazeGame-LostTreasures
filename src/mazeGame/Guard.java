package mazeGame;

import java.util.ArrayList;
import java.util.Random;

/*
 * Creating Guard class to represent the guard in the maze
 * Handles movement logic
 */

/*
 * Attributes: Guard's coordinates
 * Methods: Attribute getters, Random Movements (up, down, left, right)
 */

//Creating child class containing attributes and methods that inherited from the parent Entity class
public class Guard extends Entity {
	
	//Constructor to initialize guard attributes
	Guard (int guardX, int guardY) {
		super (guardX, guardY); // initializes these attributes in the parent Entity class which stores coordinates
		System.out.println("\nA guard has been randomly placed inside the maze."); //prints to signal that a guard has been created
//		System.out.println("Guard is placed at "+guardX+","+guardY); //CHECKER: Guard's starting position
	}
	
	//Random Movement Method (up, down, left, right)
	//Overrides the move method in Entity parent class
	//Uses protected attributes from Entity parent class
	@Override
	public void move (char dir, Map map) {
		//creates an array which stores 0, 1, 2, and 3 to represent W, S, D, and A moves, respectively
		ArrayList<Integer> directions = new ArrayList<>(); 
		directions.add(1);
		directions.add(2);
		directions.add(3);
		directions.add(4);
		
		//for randomizing the directions
		Random rand = new Random();
		
		//while loop which runs the guard's possible moves
		//if target tile is not a wall, then movement is allowed
		//otherwise, it loops again to check a random move among the other directions left in the array
		while (directions.size()>0) {
			int randomDir = rand.nextInt(directions.size()); // picks a random index to choose a direction
			int chosen = directions.get(randomDir); //chosen direction is selected using the random index
			directions.remove(randomDir); //removes chosen direction from the options to avoid being used in the next loop if it turns out to be a wall
			
			//maximum index in the maze to avoid leaving the grid
			int moveLimit = map.getSize() - 1;
			
			//Movement Logic: Check boundaries > check if target tile is a wall > move
			
			//Movement: UP / "W"
			if (chosen == 1) {
				//boundary check: ensures the player is not at the top edge
				if (entityY>=0 && entityY<moveLimit) {
					//checks if the target tile is a wall
					if (map.isWall(entityX,entityY+1)==true) {
						continue; //target tile is a wall so the loop is run again
					} else { //otherwise allow movement
						entityY = entityY + 1;
						break;
					}	
				} else {
					continue; //target tile is a boundary so the loop is run again
				}
				
			//Movement: DOWN / "S"
			} else if (chosen == 2) {
				//boundary check: ensures the player is not at the bottom edge
				if (entityY>0 && entityY<=moveLimit) {
					//checks if the target tile is a wall
					if (map.isWall(entityX,entityY-1)==true) {
						continue; //target tile is a wall so the loop is run again
					} else { //otherwise allow movement
						entityY = entityY - 1;
						break;
					}	
				} else {	
					continue; //target tile is a boundary so the loop is run again
				}
			
			//Movement: RIGHT / "D"
			} else if (chosen == 3) {
				//boundary check: ensures the player is not at the rightmost edge
				if (entityX>=0 && entityX<moveLimit) {
					//checks if the target tile is a wall
					if (map.isWall(entityX+1,entityY)==true) {
						continue; //target tile is a wall so the loop is run again
					} else { //otherwise allow movement
						entityX = entityX + 1;
						break;
					}	
				} else {	
					continue; //target tile is a boundary so the loop is run again
				}
				
			//Movement: LEFT / "A"
			} else if (chosen == 4) {
				//boundary check: ensures the player is not at the leftmost edge
				if (entityX>0 && entityX<=moveLimit) {
					//checks if the target tile is a wall
					if (map.isWall(entityX-1,entityY)==true) {
						continue; //target tile is a wall so the loop is run again
					} else { //otherwise allow movement
						entityX = entityX - 1;
						break;
					}	
				} else {	
					continue; //target tile is a boundary so the loop is run again
				}
			} else {
				System.out.println("\nError in guard's direction!");
			}
		}
	}
	
}