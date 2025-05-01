public class CollisionInfo {
    private boolean pLeft;
    private boolean pAbove;
    private boolean collision;
    private Square square;
    private int diff;

    public CollisionInfo(boolean pLeft, boolean pAbove, boolean collision, Square s, int diff) {
        this.pLeft = pLeft;
        this.pAbove = pAbove;
        this.collision = collision;
        this.diff = diff;
        square = s;
    }

    public CollisionInfo(){
        collision = false;
    }

    public boolean ispLeft() {
        return pLeft;
    }

    public boolean ispAbove() {
        return pAbove;
    }

    public boolean isCollision() {
        return collision;
    }

    public Square getSquare() {
        return square;
    }

    public int getDiff() {
        return diff;
    }
}
