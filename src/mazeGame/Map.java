package mazeGame;

import java.util.ArrayList;
import java.util.Collections;

/*
 * Creating Map class to represent the maze's grid
 * Generates a grid with a specific number of random wall and treasure tiles
 */

/*
 * Attributes: Maze size, grid, number of wall and treasure tiles, list of possible coordinates for walls and treasures, 
 * list of selected wall coordinates, list of selected treasure coordinates, treasure count, game status
 * Methods: Attribute getters for maze size, isWall, hasTreasure, removeTreasure
 */

public class Map {
	
	//Defining maze attributes
	private int size; //maze size
	private int num; //number of wall and treasure tiles
	private boolean[][][] grid; //maze grid
	private ArrayList<int[]> gridCoords = new ArrayList<>(); //list of all possible wall and treasure coordinates
	private ArrayList<int[]> wallCoords = new ArrayList<>(); //list of selected wall coordinates
	private ArrayList<int[]> treasureCoords = new ArrayList<>(); //list of selected treasure coordinates
	private int treasureCount; //counter for treasures found
	
	//Constructor to initialize maze attributes
	//Sets up the maze grid, generates random walls and treasures
	Map (int size, Player p1, Guard g1) { //requires maze size, player, and guard as input
		this.size = size; //sets maze size
		System.out.println("\nA maze of size "+this.size+" has been created.\n\n------- G A M E  S T A R T S -------"); //prints to signal that the maze has been created
		this.treasureCount = 0; //intializes number of treasures found to 0
				
		//for loop to list coordinates that is not the starting position of the player nor the guard
		//gridCoords is the pool of possible coordinates for walls and treasures
		for (int i=0; i < this.size; i++) {
			for (int j=0; j < this.size; j++) {
				if ((i==0 && j==0) || (i==g1.getEntityX() && j==g1.getEntityY())) {
		            continue;
				}
				this.gridCoords.add(new int[] {i,j});
			}
		}
		
		//shuffles the coordinates in gridCoords list
		Collections.shuffle(this.gridCoords);
		
		//sets the number of wall and treasure tiles as size-2
		this.num = size-2;
		
		//picks the first n coordinates from gridCoords list to become walls
		//where n = num
		//wallCoords is the list of wall coordinates
		for (int k=0; k < this.num; k++) {
			this.wallCoords.add(this.gridCoords.get(k));
		}
		
		//picks the next n coordinates from gridCoords list to become treasures
		//where n = num
		//treasureCoords is the list of treasure coordinates
		for (int k=this.num; k < this.num*2; k++) {
			this.treasureCoords.add(this.gridCoords.get(k));
		}
		
		//initializes the grid array
		this.grid = new boolean[size][size][2];
		
		//for loop to set all tiles to (false,false) or not wall and not treasure
		for (int i=0; i < this.size; i++) {
			for (int j=0; j < this.size; j++) {
				this.grid[i][j][0] = false; //not wall
				this.grid[i][j][1] = false; //not treasure
			}
		}
		
		//for loop to place the wall tiles into the grid based on the selected coordinates in wallCoords list
		for (int k=0; k < this.wallCoords.size(); k++) {
			int[] coords = this.wallCoords.get(k);
		    int x = coords[0];
		    int y = coords[1];
		    this.grid[x][y][0] = true; //is a wall
			this.grid[x][y][1] = false; //not treasure
//		    System.out.println("Wall placed at " + x + "," + y + " -> "+ this.grid[x][y][0] + "," + this.grid[x][y][1]); //CHECKER: WALL LOCATION
		}	
		
		
		//for loop to place the treasure tiles into the grid based on the selected coordinates in treasureCoords list
		for (int k=0; k < this.treasureCoords.size(); k++) {
			int[] coords = this.treasureCoords.get(k);
		    int x = coords[0];
		    int y = coords[1];
		    this.grid[x][y][0] = false; //not a wall
			this.grid[x][y][1] = true; //is a treasure
//		    System.out.println("Treasure placed at " + x + "," + y + " -> "+ this.grid[x][y][0] + "," + this.grid[x][y][1]); //CHECKER: TREASURE LOCATION
		}	
	} //maze constructor
	
	//Attribute getter
	
	public int getSize() {
		return this.size;
	}
	
	public boolean isWall(int x, int y) {
		return this.grid[x][y][0]; //True for wall; False for a regular tile
	}
	
	public int hasTreasure(int x, int y) {
		//if target tile contains a treasure
		if (this.grid[x][y][1] == true) {
			this.treasureCount = this.treasureCount +1; //adds 1 to record the discovery of treasure
			//if all treasures have been found
			if (this.treasureCount == this.num) {
				System.out.println("\nTreasure Status: Found "+treasureCount+" out of "+this.num+" treasure/s.");
				removeTreasure(x,y);
				return 2; //returns 2 for finding the last treasure which secures the win
			} else {
				System.out.println("\nTreasure Status: Found "+treasureCount+" out of "+this.num+" treasure/s.");
				removeTreasure(x,y); //removes treasure that has been found already
				return 1; //returns 1 for finding 1 treasure
			}
		}
		System.out.println("\nTreasure Status: Found "+treasureCount+" out of "+this.num+" treasure/s.");
		return 0; //returns 0 for no treasure
	}
	
	public void removeTreasure(int x, int y) {
		this.grid[x][y][1] = false; //Sets treasure to false to remove it
		return;
	}
	
}
