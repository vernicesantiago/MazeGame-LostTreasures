package mazeGame;

/*
 * Creating Entity class to represent the entities (player and guard) in the maze
 * Tracks position and declares move method
 */

/*
 * Attributes: Coordinates of the entity
 * Methods: Attribute getters, generic Move method
 */

//Creating abstract parent class containing attributes and methods that can be inherited by the child classes
public abstract class Entity {
	
	//Defining entity attributes as protected so subclasses can access it
	protected int entityX; //entity's x coordinate
	protected int entityY; //entity's y coordinate
	
	//Constructor to initialize entity attributes
	Entity (int x, int y) {
		this.entityX = x;
		this.entityY = y;
	}
	
	//Attribute getters
	
	public int getEntityX() {
		return this.entityX;
	}	
	
	public int getEntityY() {
		return this.entityY;
	}

	//Generic Move Method (for player and for guard) to be overridden by the child classes
	public void move (char dir, Map map) {
		//empty
	}
	
}