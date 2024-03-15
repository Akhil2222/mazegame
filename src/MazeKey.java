import java.awt.*;
import mickel.io.Key;

public class MazeKey extends MazeObject{
    public static int keysLeft = 0;
    public static int totalKeys = 0;
    private int myPosX, myPosY;
    private Color myColor;
    public Color keyColor = Color.yellow;
    private boolean isDiscovered = false;
    private boolean isChanged = false;
    public MazeKey(int x, int y, Color c){
        super(y/factor, x/factor, 5);
        this.myPosX = x;
        this.myPosY = y;
        this.myColor = c;
        keysLeft++;
        totalKeys++;
    }
    public void act(){
        for(int i = 0;i < 4;i++){
            try{
				if(board[rowPos+(int)Math.round(Math.sin(i*Math.PI/2))][colPos+(int)Math.round(Math.cos(i*Math.PI/2))] == 2){
					myColor = keyColor;
				}
			}catch(Exception e){

			}
        }
        if(board[rowPos][colPos] != 5 && !isDiscovered){
            keysLeft--;
            isDiscovered = true;
        }
        if(isDiscovered) myColor = MazeGameGUI.bgColor;
    }

    public void keyPressed(Key k){
        if(finished || isChanged || Player.moves != 0) return;
		if(k.equals(Key.ONE)){
			myColor = Color.yellow;
			keyColor = Color.yellow;
            isChanged = true;
		}else if(k.equals(Key.TWO)){
			myColor = MazeGameGUI.bgColor;
			keyColor = Color.yellow;
            isChanged = true;
		}else if(k.equals(Key.THREE)){
			myColor = MazeGameGUI.bgColor;
			keyColor = MazeGameGUI.bgColor;
            isChanged = true;
		}
	}

    public void draw(Graphics2D g){
        g.setColor(myColor);
        g.fillOval(myPosX, myPosY, factor, factor);
    }
}
