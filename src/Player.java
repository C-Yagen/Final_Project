import javax.swing.*;
import java.awt.*;

public class Player {
    public static final int PLAYERLENGTH = 20;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean inAir;
    private Window gameViewer;
    private Color color;
    private int xSpawn;
    private int ySpawn;
    private Square button;

    public Player(Window gameViewer, int x, int y, Color color) {
        this.gameViewer = gameViewer;
        this.y = y;
        this.x = x;
        xSpawn = x;
        ySpawn = y;
        dx = 0;
        dy = 0;
        this.color = color;
        button = new Square(0,0,0,0,0);
    }
    public void addDx(int dx){
        this.dx += dx;
    }
    public void addDy(int dy){
        this.dy += dy;
    }
    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    public void setDx(int dx) {
        this.dx = dx;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void move(){
        x += dx;
        y += dy;

        if (dx > 0){
            dx -= 1;
            dx = Math.min(dx, 4);
            if (dx < 0){
                dx = 0;
            }
        } else if (dx < 0) {
            dx += 1;
            dx = Math.max(dx, -4);
            if (dx > 0){
                dx = 0;
            }
        }
        // increases downward velocity
        dy = Math.min(dy +1, 15);

    }

    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int getxSpawn() {return xSpawn;}
    public int getySpawn() {return ySpawn;}
    public Color getColor() {return color;}

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, PLAYERLENGTH, PLAYERLENGTH);
        g.setColor(Color.magenta);
        g.drawString("X: " + x, x, y-5);
        g.drawString("Y: " + y, x, y+5);

    }
}
