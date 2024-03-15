
import mickel.anim.*;
import java.awt.*;
import java.util.*;

/** The primary GUI window of the MazeGame application.
 */
public class MazeGameGUI
{
	// FIELDS
	// ------------------------------------------------------------
	private static Stage myStage;			// The base window for the app.
	public static Stage getMyStage() {
		return myStage;
	}


	private Dimension myScreenSize;
	public static final Color bgColor = Color.black;
	
	// CONSTRUCTORS
	// ------------------------------------------------------------
	/** 
	 * CREATE THE STAGE
	 *  1. Declare an int variable called stageWidth and initialize to a number of pixels. Choose a multiple of unit (unit * 10, for example).
	 *	2. Declare an int variable called stageHeight and initialize to a number of pixels. Choose a multiple of unit.
	 *  3. Initialize myStage as a new Stage entitled "Maze Game" with a width of stageWidth and a height of stageHeight.
	 *  4. Declare and create a new Color (R, G, B, A) for the background of your maze.
	 *  5. Set the background of myStage to be the Color you created.
	 *  6. Remove the block comments below and don't alter the code =)
	 *  
	 * CALL METHODS TO CREATE SPRITES
	 *  7. Invoke this object's addWalls() method.
	 *  8. Invoke this object's addFinish() method.
	 *  9. Invoke this object's addPlayer() method
	 *  
	 * OPEN WINDOW & START GAME
	 *  10. Tell myStage to open its window.
	 *  11. Tell myStage to start its animation cycle.
	 *  
	 * NEXT STEP: 
	 *  Click the link to go to the MazeObject class and complete its implementation {@link MazeObject}
	 *  
	 */
	public MazeGameGUI()
	{
		// TODO: Implement steps 1 - 6 of the algorithm above
		
		
		myScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = myScreenSize.width;
		int height = myScreenSize.height;
		
		int factor = Math.min(width/(MazeObject.columns), (height-200)/(MazeObject.rows));
		int stageWidth = factor * MazeObject.columns;
		int stageHeight = factor * MazeObject.rows;

		MazeObject.factor = factor;
		
		myStage = new Stage("Maize Game", stageWidth, stageHeight);
		myStage.setLocation(width/2 - stageWidth/2, height/2 - stageHeight/2);
		myStage.setBackground(bgColor);

		generate();
		
		myStage.openWindow();
		myStage.start();
		// TODO: Implement steps 7 - 11 of the algorithm above
	}

	private static int[][] path = new int[MazeObject.rows][MazeObject.columns];
	private static int[][] startToEnd = new int[MazeObject.rows][MazeObject.columns];

	private void generate(){
		int rows = MazeObject.rows, columns = MazeObject.columns;
		for(int i = 0;i < rows;i++) {
			for(int j = 0;j < columns;j++) {
				path[i][j] = Math.random()<0.05?3:1;
			}
		}
		int playerRow = (int) Math.floor(Math.random() * rows);
		int playerCol = (int) Math.floor(Math.random() * columns);
		path[playerRow][playerCol] = 2;
				
		int chances = 1000;
		int moves = 0;
		ArrayDeque<int[]> calls = new ArrayDeque();
		for(int i = 0;i < rows*columns/5;i++) {
			double angle = Math.PI/2 * Math.floor(Math.random()*4);
			int randRow = 2*(int)Math.round(Math.sin(angle));
			int randCol = 2*(int)Math.round(Math.cos(angle));
			System.out.println(randRow + "," + randCol + " " + playerRow + "," + playerCol + " " + i + " " + chances);
			
			if(chances == 0) break;
			if(Math.random() > 0.3){
				calls.add(new int[]{playerRow, playerCol});
			}

			if((playerRow + randRow < 0 || playerRow + randRow >= rows) || 
				(playerCol + randCol < 0 || playerCol + randCol >= columns) ||
				(path[playerRow + randRow][playerCol + randCol] != 1)) {
				i--;
				chances--;
				continue;
			}
			playerRow += randRow;
			playerCol += randCol;
			
			path[playerRow][playerCol] = 0;
			path[playerRow-randRow/2][playerCol-randCol/2] = 0;
			startToEnd[playerRow][playerCol] = 1;
			startToEnd[playerRow-randRow/2][playerCol-randCol/2] = 1;
			chances = 1000;
			moves+=2;
		}
		
		path[playerRow][playerCol] = 4;
		Player.minMoves = moves;
		calls.removeLast();

		for(int[] i : calls){
			branch(i[0], i[1]);
		}

		render();
	}
	private void branch(int row, int col){
		int chances = 10;
		for(int i = 0;i < MazeObject.rows*MazeObject.columns/10;i++){
			double angle = Math.PI/2 * Math.floor(Math.random()*4);
			int randRow = 2*(int)Math.round(Math.sin(angle));
			int randCol = 2*(int)Math.round(Math.cos(angle));
			if(chances < 0){break;}
			if((row + randRow < 0 || row + randRow >= MazeObject.rows) || 
			(col + randCol < 0 || col + randCol >= MazeObject.columns) ||
			(path[row + randRow][col + randCol] != 1)){
				i--;
				chances--;
				continue;
			}

			row += randRow;
			col += randCol;

			if(Math.random() > 0.5){
				branch(row, col);
			}

			path[row][col] = 0;
			path[row-randRow/2][col-randCol/2] = 0;
			
			if(Math.random() < 0.005){
				path[row][col] = 5;
			}

			chances = 10;
		}
	}
	
	public void render(){
		int factor = MazeObject.factor;
		int xPlay = 0, yPlay = 0;
		for(int i = 0;i < path.length;i++){
			for(int j = 0;j < path[i].length;j++){
				switch (path[i][j]){
					case (0):
						break;
					case (1):
						myStage.add(new Wall(j*factor, i*factor, factor, bgColor));
						break;
					case (2):
						xPlay = j * factor;
						yPlay = i * factor;
						break;
					case (3):
						myStage.add(new Trap(j*factor,i*factor));
						break;
					case (4):
						myStage.add(new Finish(j * factor, i * factor, factor, Color.red));
						break;
					case (5):
						myStage.add(new MazeKey(j * factor, i * factor, bgColor));
						break;
				}
			}
		}
		
		myStage.add(new Player(xPlay, yPlay, factor, Color.green));
		
	}

	public static void showStartToEnd(){
		System.out.println(startToEnd);
		for(int i = 0;i < startToEnd.length;i++){
			for(int j = 0;j < startToEnd[i].length;j++){
				if(startToEnd[i][j] == 1){
					final int a = j;
					final int b = i;
					myStage.add(new Sprite(){
						public void draw(Graphics2D g){
							g.setColor(new Color(255,255,255,80));
							g.fillRect(a*MazeObject.factor,b*MazeObject.factor,MazeObject.factor,MazeObject.factor);
						}
					});
				}
			}
		}
	}
	public static void drawText(Graphics2D g, String text, int x, int y,Color c, int size, boolean centered)
	{
		g.setFont(new Font("Arial", Font.BOLD, size));
		FontMetrics metrics = g.getFontMetrics();
		if (centered)
		{
		x -= metrics.stringWidth(text) / 2;
		y += metrics.getAscent() / 2;
		}
		
		g.setColor(Color.BLACK);
		g.drawString(text, x + 1, y + 1);
		g.drawString(text, x + 1, y - 1);
		g.drawString(text, x - 1, y + 1);
		g.drawString(text, x - 1, y - 1);
		
		g.setColor(c);
		g.drawString(text, x, y);
		
		g.setColor(new Color(255, 255, 255, 127));
		g.drawString(text, x, y);
	}
}