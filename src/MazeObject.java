import mickel.anim.Sprite;

// TODO: Add to the statement below to indicate that a MazeObject is type of Sprite
public class MazeObject extends Sprite
{
	public static final int UNIT = 40; 	// You may use UNIT in all MazeObject subclasses
	
	public static final int rows = 50;
	public static final int columns = 80;
	public static int factor;
	protected static boolean finished = false;
	protected int rowPos;
	protected int colPos;
	protected static Player player;
	/**
	 * CREATE A 2D INTEGER ARRAY CALLED BOARD
	 * 	1. Declare a reference to a new 2D int array called board. 
	 * 		NOTE:	The board can be directly accessed by classes that extend MazeObjects.
	 * 				As a result it should be declared as protected instead of private.
	 * 				We want one board for all maze objects (as opposed to one board per MazeObject).
	 *				Consequently, the board should be defined as static.
	 * 	2. Initialize the board as a new 2D int array. 
	 * 		NOTE: The board may have no fewer than 10 rows and 10 columns and should be initially filled with 0s.
	 */
	// TODO: Declare and initialize the variable described above
	
	protected static int[][] board = new int[rows][columns];
	
	
	/**
	 * MAZE OBJECT CONSTRUCTOR NOTES
	 * 	Each MazeObject will have a location on the board as well as a type. Type can be one of five values: 0, 1, 2, 3, or 4. 
	 * 	Type meanings are as follows:
	 * 		0 -	no object at this location
	 * 		1 - wall at this location
	 * 		2 - player at this location
	 * 		3 - trap at this location
	 * 		4 - finish at this location
	 * 
	 * CONSTRUCTOR IMPLEMENTATION
	 * 	1. Initialize the board at the given row and column to the given type of MazeObject.
	 * 
	 * 	2. Call the printBoard() method -- you will need to uncomment the method at the bottom of the file
	 * 
	 * NEXT STEP: 
	 *  Click the link to go to the Wall class and complete its implementation {@link Wall}	
	 * 
	 * @param row		the row of the board where the MazeObject will be created
	 * @param col		the column of the board where the MazeObject will be created
	 * @param type		indicates the type of the MazeObject
	 */
	public MazeObject(int row, int col, int type)
	{
		// TODO: Implement the algorithm listed above
		this.rowPos = row;
		this.colPos = col;
		board[row][col] = type;
		//printBoard();
	}

	
	public static void reset(){
		board = new int[rows][columns];
		System.gc();
	}
	/**
	 * PRINTBOARD
	 * 	Prints the 2D array to the console.
	 * 
	 * 	TODO: Remove the block comment below to make the method accessible within this class and to other classes
	 */
	
	public void printBoard()
	{
		for (int row = 0; row < board.length; row++)
		{
			for (int col = 0; col < board[row].length; col++)
			{
				System.out.print(new char[]{' ','■','p','t','f','k'}[board[row][col]]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
}