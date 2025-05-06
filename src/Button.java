import java.awt.*;

public class Button extends Square {
    private boolean activated;
    private boolean permanent;
    private Square square;

    public Button(int x1, int x2, int y1, int y2, Square square, boolean permanent, int num) {
        super(x1, x2, y1, y2, num);
        activated = false;
        this.square = square;
        this.permanent = permanent;
    }

    public boolean Collision(Player p) {
        if (permanent && activated){
            return true;
        }
        int px1 = p.getX();
        int px2 = px1 + Player.PLAYERLENGTH;
        int py1 = p.getY();
        int py2 = py1 + Player.PLAYERLENGTH;
        int x1 = super.getX1();
        int x2 = super.getX2();
        int y1 = super.getY1();
        int y2 = super.getY2();
        super.setColor(Color.CYAN);

        if ((x1 < px1 && px1 < x2) || (x1 < px2 && px2 < x2)) {
            if (py1 > y1 && py1 < y2) {
                return true;
            }
            if (py2 > y1 && py2 < y2) {
               return true;
            }
        }
        return false;
    }

    public boolean isActivated() {
        return activated;
    }

    public Square getSquare() {
        return square;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}