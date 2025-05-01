import java.awt.*;

public class Fire extends Square{

    public Fire(int x1, int x2, int y1, int y2, int num) {
        super.setX1(x1);
        super.setX2(x2);
        super.setY1(y1);
        super.setY2(y2);
        super.setColor(Color.red);
        super.num = num;
    }

    public CollisionInfo yCollision(Player p){
        int px1 = p.getX();
        int px2 = px1 + Player.PLAYERLENGTH;
        int py1 = p.getY();
        int py2 = py1 + Player.PLAYERLENGTH;
        int x1=super.getX1();
        int x2=super.getX2();
        int y1=super.getY1();
        int y2=super.getY2();

        if ((x1 < px1 && px1 < x2) || (x1 < px2 && px2 < x2)){
            if (py1 > y1 && py1 < y2){
                p.setX(p.getxSpawn());
                p.setY(p.getySpawn());
                return new CollisionInfo();
            }
            if (py2 > y1 && py2 < y2){
                p.setX(p.getxSpawn());
                p.setY(p.getySpawn());
                return new CollisionInfo();
            }
        }
        return new CollisionInfo();
    }

    public CollisionInfo xCollision(Player p){
        int px1 = p.getX();
        int px2 = px1 + Player.PLAYERLENGTH;
        int py1 = p.getY();
        int py2 = py1 + Player.PLAYERLENGTH;
        int x1=super.getX1();
        int x2=super.getX2();
        int y1=super.getY1();
        int y2=super.getY2();

        if ((y1 < py1 && py1 < y2) || (y1 < py2 && py2 < y2)){
            if (x1 < px1 && px1 < x2){
                p.setX(p.getxSpawn());
                p.setY(p.getySpawn());
                return new CollisionInfo();
            }
            if (x1 < px2 && px2 < x2){
                p.setX(p.getxSpawn());
                p.setY(p.getySpawn());
                return new CollisionInfo();
            }
        }
        return new CollisionInfo();
    }
}
