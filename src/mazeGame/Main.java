package mazeGame;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * This code is for a game where the player needs to collect all treasures randomly placed inside 
 * the maze without being caught by the guard.
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//initializes scanner for manual user input
		Scanner s = new Scanner(System.in);
		
		//gets maze dimensions from the user
		System.out.println("Choose maze size: ");
		int mazeSize = s.nextInt();
		s.nextLine(); //clears the left out ENTER key in the scanner buffer after reading the maze size
		
		//creates the player and places it at (0,0) or at the bottom left of the maze
		Player p1 = new Player(0,0);
		
		//temporarily sets guard's coordinates to (0,0)
		int guardX = 0;
		int guardY = 0;
		//while condition to generate random guard coordinates that is not (0,0) or the starting position
		while(guardX==0 && guardY==0) {
			guardX = (int)(Math.random()*mazeSize);
			guardY = (int)(Math.random()*mazeSize);
		}
		//creates the guard and places it at the randomly generated x and y coordinates
		Guard g1 = new Guard(guardX,guardY);
		
		//generates the maze and passes on the maze size, player, and guard
		Map w1 = new Map(mazeSize, p1, g1);
		
		//coordinates of player and guard
		ArrayList<Entity> entityCoords = new ArrayList<>();
		entityCoords.add(p1);
		entityCoords.add(g1);
		
		//Main Game Loop which runs as long as the player and guard are not in the same tile
		while ( (entityCoords.get(0).getEntityX()!=entityCoords.get(1).getEntityX()) || (entityCoords.get(0).getEntityY()!=entityCoords.get(1).getEntityY()) ) {
			//prints current player attributes (position)
			System.out.println("Player position: "+entityCoords.get(0).getEntityX()+","+entityCoords.get(0).getEntityY());
			//prints current guard attributes (position)
			System.out.println("Guard position: "+entityCoords.get(1).getEntityX()+","+entityCoords.get(1).getEntityY());
			//primary game prompt which asks user to choose movement direction
			System.out.print("\nMove (W/A/S/D/Q): ");
			String playerMove = s.nextLine().toUpperCase(); //accepts both lowercase and uppercase and transforms it to uppercase for consistency
			char move = playerMove.charAt(0); //gets the first character
			
			
			//WASD
			if ((move=='W')||(move=='A')||(move=='S')||(move=='D')) {
				//stores the player's initial x,y coordinates
				int playerX0 = entityCoords.get(0).getEntityX();
				int playerY0 = entityCoords.get(0).getEntityY();
				
				//loops through entities (0 = Player, 1 = Guard)
			    for (int i = 0; i < entityCoords.size(); i++) {
			    	Entity ntt = entityCoords.get(i);
			    	
			    	if (i==0) { //Player's turn to move
			    		//player moves
						ntt.move(move, w1);
						
						//checks if the player has found all treasures and has won the game
						if (w1.hasTreasure(entityCoords.get(0).getEntityX(),entityCoords.get(0).getEntityY())==2) {
							System.out.println("\nCongratulations! You won!");
							s.close();
							return;
						}
			    	} else if (i==1) { //Guard's turn to move
			    		//checks if the player's move has been successful to activate the movement of the guard
						//either player found a treasure but have not collected all or it's a regular tile
						if ( (playerX0!=entityCoords.get(0).getEntityX()) || (playerY0!=entityCoords.get(0).getEntityY()) ) {
							//guard moves
							ntt.move(move, w1);
							//checks if the player and the guard have the same coordinates which signals that the guard has caught the player and therefore lost the game
							if ( (entityCoords.get(0).getEntityX()==entityCoords.get(1).getEntityX()) && (entityCoords.get(0).getEntityY()==entityCoords.get(1).getEntityY()) ) {
								System.out.println("\nYou have been caught by the guard. You lose!!!");
								break;
							}
						}
			    	}
			    }
				
			//QUIT / "Q"	
			} else if (move == 'Q') {
				System.out.println("\nYou've quit the game!");
				break;
			
			//INVALID INPUT
			} else {		
				System.out.println("\nThat's an invalid input bruh! Try again!");
			}
			
		} //while loop

		//closes the scanner
		s.close();
		
	} //main method

} //main class
