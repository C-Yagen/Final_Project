public class Player {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private boolean inAir;
    private Window gameViewer;

    public Player(Window gameViewer, int x, int y) {
        this.gameViewer = gameViewer;
        this.y = y;
        this.x = x;
        dx = 0;
        dy = 0;
    }


}
