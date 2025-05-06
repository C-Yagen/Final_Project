import java.awt.*;

public class Exit extends Square{

    public Exit(int x1, int x2, int y1, int y2, Color color, int num) {
        super(x1,x2,y1,y2,color,num);
    }

    public boolean Collision(Player p){
        int px1 = p.getX();
        int px2 = px1 + Player.PLAYERLENGTH;
        int py1 = p.getY();
        int py2 = py1 + Player.PLAYERLENGTH;
        int x1=super.getX1();
        int x2=super.getX2();
        int y1=super.getY1();
        int y2=super.getY2();
        if (py1 == y1 && px1 == x1 && p.getColor().equals(super.getColor())){
            return true;
        }
        return false;
    }

}
