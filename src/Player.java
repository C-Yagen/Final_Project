import javax.swing.*;
import java.awt.*;

public class Player {
    private final int playerLength = 20;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean inAir;
    private Window gameViewer;
    private Color color;

    public Player(Window gameViewer, int x, int y, Color color) {
        this.gameViewer = gameViewer;
        this.y = y;
        this.x = x;
        dx = 0;
        dy = 0;
        this.color = color;
    }
    public void addDx(int dx){
        this.dx += dx;
    }
    public void addDy(int dy){
        if (!inAir){
            this.dy += dy;
        }

    }
    public void setInAir(boolean inAir) {
        this.inAir = inAir;
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

        dy = dy + 1;
        if (y > 200){
            dy = 0;
            y = 200;
            inAir = false;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x - playerLength/2, y + playerLength, playerLength, playerLength);

    }
}
