import java.awt.*;
import java.awt.geom.*;

public class Trap extends MazeObject{
    private int myPosX, myPosY;
    private boolean isDiscovered;
    public Trap(int x, int y){
        super(y/factor,x/factor, 3);
        this.myPosX = x;
        this.myPosY = y;
    }

    public void act(){
        isDiscovered |= finished;
        int[] playpos = player.getExpected();
        if(player == null) return;
        if(playpos[0] == rowPos && playpos[1] == colPos && Wall.wallMode != 3){
            isDiscovered = true;
        }
    }

    public void draw(Graphics2D g){
        g.setColor(isDiscovered?Color.blue:MazeGameGUI.bgColor);
        g.fillOval(myPosX,myPosY,factor,factor);
    }

    public Shape getShape(){
        return new Ellipse2D.Double(myPosX,myPosY,factor,factor);
    }
}
