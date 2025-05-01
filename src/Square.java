import java.awt.*;

public class Square {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Color color;
    public int num;

    public Square(){}
    public Square(int x1, int x2, int y1, int y2, Color color, int num) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
        this.num = num;
    }
    public Square(int x1, int x2, int y1, int y2, int num) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = Color.BLACK;
        this.num = num;
    }

    public CollisionInfo yCollision(Player p){
        int px1 = p.getX();
        int px2 = px1 + Player.PLAYERLENGTH;
        int py1 = p.getY();
        int py2 = py1 + Player.PLAYERLENGTH;

        if ((x1 < px1 && px1 < x2) || (x1 < px2 && px2 < x2)){
            if (py1 > y1 && py1 < y2){
                return new CollisionInfo(false,false,true, this, y2-py1);
            }
            if (py2 > y1 && py2 < y2){
                return new CollisionInfo(false,true,true, this, py2-y1);
            }
        }

        return new CollisionInfo();
    }

    public CollisionInfo xCollision(Player p){
        int px1 = p.getX();
        int px2 = px1 + Player.PLAYERLENGTH;
        int py1 = p.getY();
        int py2 = py1 + Player.PLAYERLENGTH;


        if ((y1 < py1 && py1 < y2) || (y1 < py2 && py2 < y2)){
            if (x1 < px1 && px1 < x2){
                return new CollisionInfo(false,false,true, this, x2-px1);
            }
            if (x1 < px2 && px2 < x2){
                return new CollisionInfo(true,false,true, this, px2-x1);
            }
        }

        return new CollisionInfo();
    }


    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public Color getColor() {
        return color;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        int width = x2 - x1;
        int length = y2 - y1;
        g.fillRect(x1, y1, width, length);
        g.setColor(Color.magenta);
        g.drawString(Integer.toString(num),x1,y1);
    }
}
