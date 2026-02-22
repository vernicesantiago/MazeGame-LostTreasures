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
		ArrayList<int[]> entityCoords = new ArrayList<>();
		entityCoords.add(new int[] {p1.getEntityX(),p1.getEntityY()});
		entityCoords.add(new int[] {g1.getEntityX(),g1.getEntityY()});
		
		//Main Game Loop which runs as long as the player and guard are not in the same tile
		while ( (p1.getEntityX()!=g1.getEntityX()) || (p1.getEntityY()!=g1.getEntityY()) ) {
			//prints current player attributes (position)
			System.out.println("Player position: "+p1.getEntityX()+","+p1.getEntityY());
			//prints current guard attributes (position)
			System.out.println("Guard position: "+g1.getEntityX()+","+g1.getEntityY());
			//primary game prompt which asks user to choose movement direction
			System.out.print("\nMove (W/A/S/D/Q): ");
			String playerMove = s.nextLine().toUpperCase(); //accepts both lowercase and uppercase and transforms it to uppercase for consistency
			char move = playerMove.charAt(0); //gets the first character
			
			
			//WASD
			if ((move=='W')||(move=='A')||(move=='S')||(move=='D')) {
				//stores the player's initial x,y coordinates
				int playerX0 = p1.getEntityX();
				int playerY0 = p1.getEntityY();
				
				//player moves
				p1.move(move, w1);
				
				//stores the player's new x,y coordinates after the move
				int playerX1 = p1.getEntityX();
				int playerY1 = p1.getEntityY();
				
				//checks if the player has found all treasures and has won the game
				if (w1.hasTreasure(playerX1,playerY1)==2) {
					System.out.println("\nCongratulations! You won!");
					break;
				}
				
				//checks if the player's move has been successful to activate the movement of the guard
				//either player found a treasure but have not collected all or it's a regular tile
				if ( (playerX0!=playerX1) || (playerY0!=playerY1) ) {
					//guard moves
					g1.move(move, w1);
					//checks if the player and the guard have the same coordinates which signals that the guard has caught the player and therefore lost the game
					if ( (p1.getEntityX()==g1.getEntityX()) && (p1.getEntityY()==g1.getEntityY()) ) {
						System.out.println("\nYou have been caught by the guard. You lose!!!");
						break;
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
			
		}

		//closes the scanner
		s.close();
		
	} //main method

} //main class
