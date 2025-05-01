public class Button extends Square {

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


                return new CollisionInfo();
            }
            if (py2 > y1 && py2 < y2){


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

                return new CollisionInfo();
            }
            if (x1 < px2 && px2 < x2){

                return new CollisionInfo();
            }
        }
        return new CollisionInfo();
    }
}
