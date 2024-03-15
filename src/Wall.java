import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.*;
import java.awt.geom.*;
import mickel.io.Key;

// TODO: Add to the statement below to indicate that a Wall is a type of MazeObject
public class Wall extends MazeObject
{
	// FIELDS
	// ------------------------------------------------------------
	private int myPosX;		// X-coordinate of this Wall on the GUI
	private int myPosY;		// Y-coordinate of this Wall on the GUI
	private int mySize;		// Length and Height of this Wall on the GUI
	public Color myColor;	// Color of this Wall
	public static Color wallColor = Color.white;
	public boolean isFound = false;
	public static int wallMode = 2;
	private boolean isChanged = false;
	public static boolean didChange = false;
	public static double modeMultiplier = 0.5;
	// CONSTRUCTORS
	// ------------------------------------------------------------
	/**
	 * WALL CONSTRUCTOR
	 * 	1. Place the location of this Wall on the board maintained in the parent class by invoking the MazeObject constructor.
	 *    	NOTE: Remember that on the board the y coordinate should be entered before the x coordinate
	 *    	since 2D arrays take the row first and then the column.
	 *    	
	 *    	EXAMPLE: If this Wall has a x coordinate of 80 and a y coordinate of 160 on the GUI,
	 *    	the position of this wall should be (160/UNIT, 80/UNIT) or (4, 2) on the 2D array called board.
	 *    	
	 *    	TYPE: We will use 1 to denote a Wall on the board.
	 *    
	 * 	2. Initialize myPosX, myPosY, mySize, and myColor for this Wall using the given parameters.
	 * 
	 * NEXT STEP: 
	 *  Click the link to go to the draw method and complete its implementation {@link #draw()}	
	 * 
	 * @param x		x-coordinate of the object's location
	 * @param y		y-coordinate of the object's location
	 * @param size	length/height of the object
	 * @param c		color of the object
	 */
	public Wall(int x, int y, int size, Color c) 
	{
		super(y/factor,x/factor, 1);
		this.myPosX = x;
		this.myPosY = y;
		this.mySize = size;
		this.myColor = c;
		// TODO: Implement the algorithm listed above	
	}


	// METHODS
	// ------------------------------------------------------------
	/**
	 * Come back here when you are ready to start changing the visibility of the walls 
	 * in phase 3 of the project.
	 * 
	 * FIRST STEP:
	 *  Go to the MazeGameGUI and change the Color of each Wall to the same color as 
	 *  the background of the Stage when the Wall is instantiated.
	 *  
	 * ACT
	 *  This method runs frequently automatically in the background of the program. Any 
	 *  code written here will be checked automatically as a result. We will use it to 
	 *  check to see whether the Player is adjacent to this Wall. If the Player is adjacent
	 *  to this wall, we will turn to Color of the Wall to make it visible to the user of
	 *  the program.
	 * 1. Is the Player on the square above this Wall?
	 * 		If subtracting a unit from myPosY results in a non-negative number, and if the board
	 *      contains a 2 in the square above this Wall, then...
	 * 			- change the Color of this Wall to any Color other than Color of this Stage's background
	 * 2. Is the Player on the square below this Wall?
	 * 		If adding a unit from myPosY results in a number less than the height of this Player's Stage, 
	 *      and if the board contains a 2 in the square below this Wall, then...
	 * 			- change the Color of this Wall to any Color other than Color of this Stage's background
	 * 3. Is the Player on the square to the left this Wall?
	 * 		If subtracting a unit from myPosX results in a non-negative number, and if the board
	 *      contains a 2 in the square to the left of this Wall, then...
	 * 			- change the Color of this Wall to any Color other than Color of this Stage's background
	 * 4. Is the Player on the square to the right this Wall?
	 * 		If adding a unit from myPosX results in a number less than the width of this Player's Stage, 
	 *      and if the board contains a 2 in the square to the right of this Wall, then...
	 * 			- change the Color of this Wall to any Color other than Color of this Stage's background 		
	 */
	public void act()
	{
		boolean wallsFound = true;
		for(int i = 0;i < 4;i++){
			try{
				if(board[rowPos+(int)Math.round(Math.sin(i*Math.PI/2))][colPos+(int)Math.round(Math.cos(i*Math.PI/2))] == 2){
					myColor = wallColor;
					isFound = true;
				}
				if(!isFound && finished && wallMode != 1){
					myColor = Color.green;
				}
			}catch(Exception e){

			}
		}
	}
	
	public void keyPressed(Key k){
		if(finished || isChanged || Player.moves != 0) return;
		if(k.equals(Key.ONE)){
			myColor = Color.white;
			wallColor = Color.white;
			wallMode = 1;
			modeMultiplier = 0.5;
			isChanged = true;
			didChange = true;
		}else if(k.equals(Key.TWO)){
			myColor = MazeGameGUI.bgColor;
			wallColor = Color.white;
			wallMode = 2;
			modeMultiplier = 1;
			isChanged = true;
			didChange = true;
		}else if(k.equals(Key.THREE)){
			myColor = MazeGameGUI.bgColor;
			wallColor = MazeGameGUI.bgColor;
			wallMode = 3;
			modeMultiplier = 1.5;
			isChanged = true;
			didChange = true;
		}
		
	}
	/**
	 * DRAW 
	 * 	1. Set the Color of g to be this Wall's color
	 * 	2. Paint a filled rectangle onto g that is located at (myPosX, myPosY) with a width and height of mySize. 
	 * 
	 * NEXT STEP: 
	 *  Click the link to go to the getShape method and complete its implementation {@link #getShape()}	
	 *  
	 * @param g		Graphics2D object
	 */
	public void draw(Graphics2D g)
	{
		// TODO: Implement the algorithm listed above
		g.setColor(myColor);
		g.fillRect(myPosX, myPosY, mySize, mySize);
	}

	
	/** 
	 * GET SHAPE
	 *	1. Return a new Rectangle2D.Double object that uses myPosX and myPosY for the x and y parameters
	 *  	and mySize for the width and height parameters.
	 *  
	 * NEXT STEP: 
	 *  Click the link to go to the addWalls method in the MazeGameGUI class and complete its implementation {@link MazeGameGUI#addWalls()}
	 */
	public Shape getShape()
	{
		return new Rectangle2D.Double(myPosX,myPosY,mySize,mySize); // TODO: Replace null with the appropriate implementation.
	}
}