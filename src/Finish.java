import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

// TODO: Add to the statement below to indicate that a Finish object is a type of MazeObject
public class Finish extends MazeObject
{
	private int myPosX;		// X-coordinate on the GUI of this Finish
	private int myPosY;		// Y-coordinate on the GUI of this Finish
	private int mySize;		// Length and Height of this Finish
	private Color myColor;	// Color of this Finish object
	
	/** 
	 * FINISH CONSTRUCTOR
	 *  1. Place the location of the maze's finish on the board maintained in the parent class by invoking the MazeObject constructor.
	 *    	NOTE: Remember that on the board the y coordinate should be entered before the x coordinate
	 *    		since 2D arrays take the row first and then the column.
	 *    
	 *    	EXAMPLE: If your finish has a x coordinate of 40 and a y coordinate of 0 on the GUI,
	 *    		the position of finish should be (0/UNIT, 40/UNIT) or (0, 1) on the 2D array called board.
	 *    
	 *    	TYPE: We will use 4 to denote the Finish on the board.  
	 * 
	 *  2. Initialize myPosX, myPosY,mySize, and myColor for this Finish.
	 *  
	 * NEXT STEP: 
	 *  Click the link to go to the draw method and complete its implementation {@link #draw()}
	 *  
	 * @param x		x-coordinate of the object's location
	 * @param y		y-coordinate of the object's location
	 * @param size	length/height of the object
	 * @param c		color of the object
	 */
	public Finish(int x, int y, int size, Color c)
	{
		// TODO: Implement the algorithm listed above
		super(y/factor,x/factor,4);
		this.myPosX = x;
		this.myPosY = y;
		this.mySize = size;
		this.myColor = c;
	}
	
	
	/**
	 * DRAW
	 * 	1. Set the Color of g to be this Finish's color
	 * 	2. Paint a filled oval onto g that is located at (myPosX, myPosY) with a width and height of mySize. 
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
		g.fillOval(myPosX,myPosY,mySize,mySize);
	}
	
	
	/** 
	 * GET SHAPE
	 *	1. Return a new Ellipse2D.Double object that uses myPosX and myPosY for the x and y parameters
	 *  	and mySize for the width and height parameters.
	 * 
	 * 	 * NEXT STEP: 
	 *  Click the link to go to the addFinish method in the MazeGameGUI and complete its implementation {@link MazeGameGUI#addFinish()}
	 */
	public Shape getShape()
	{
		return null; // TODO: Replace null with the appropriate implementation.
	}
}
