import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements ActionListener, KeyListener {
    private Player p1;
    private Player p2;
    private Window gameViewer;
    private static final int SLEEP_TIME = 15;
    private int gameState;
    private boolean wPressed;
    private boolean aPressed;
    private boolean dPressed;
    private boolean upPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private int tick;
    private ArrayList<Square> squares;
    private ArrayList<ArrayList<Square>> levels;
    private final int XMIDDLE = 500;


    public Game() {
        gameState = -1;
        squares = new ArrayList<Square>();
        levels = new ArrayList<ArrayList<Square>>();
        gameViewer = new Window(this);
        gameViewer.addKeyListener(this);
        p1 = new Player(gameViewer, XMIDDLE-450, 680, Color.green);
        p2 = new Player(gameViewer, XMIDDLE+450, 680, Color.red);
        gameState = 0;
        tick = 0;
        squares.add(new Fire(0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT-25, Window.WINDOW_HEIGHT,1));
        squares.add(new Square(0, 25, 0, Window.WINDOW_HEIGHT,2));
        squares.add(new Square(Window.WINDOW_WIDTH-25, Window.WINDOW_WIDTH, 0, Window.WINDOW_HEIGHT,3));
        squares.add(new Square(485, 515, 200, Window.WINDOW_HEIGHT,4));
        squares.add(new Square(XMIDDLE-500, XMIDDLE-400, 700, 725,5));
        squares.add(new Square(XMIDDLE-300, XMIDDLE-250, 600, 625,6));
        squares.add(new Square(XMIDDLE-300, XMIDDLE-250, 475, 500,7));
        squares.add(new Square(XMIDDLE-100, XMIDDLE, 400, 425,8));
        squares.add(new Square(XMIDDLE-300, XMIDDLE-250, 325, 350,9));

        squares.add(new Square(XMIDDLE+100, XMIDDLE+500, 700, 725,10));
        squares.add(new Square(XMIDDLE+100, XMIDDLE+500, 600, 625,11));
        squares.add(new Square(XMIDDLE, XMIDDLE+400, 500, 525,12));
        squares.add(new Fire(XMIDDLE+300, XMIDDLE+325, 665, 700,13));
        squares.add(new Fire(XMIDDLE+215, XMIDDLE+260, 615, 655,14));
        squares.add(new Fire(XMIDDLE+150, XMIDDLE+175, 665, 700,15));


        levels.add(squares);
    }

    public void actionPerformed(ActionEvent e){
        if (wPressed && !p1.isInAir()){
            p1.addDy(-17);
            p1.setInAir(true);
        }
        if (aPressed){
            if (p1.isInAir()){
                p1.addDx(1);
            }
            p1.addDx(-3);
        }
        if (dPressed){
            if (p1.isInAir()){
                p1.addDx(-1);
            }
            p1.addDx(3);
        }
        p1.move();
        if (upPressed && !p2.isInAir()){
            p2.addDy(-17);
            p2.setInAir(true);
        }
        if (leftPressed){
            if (p2.isInAir()){
                p2.addDx(1);
            }
            p2.addDx(-3);
        }
        if (rightPressed){
            if (p2.isInAir()){
                p2.addDx(-1);
            }
            p2.addDx(3);
        }
        p2.move();
        for (int i = 0; i < squares.size(); i++) {
            CollisionInfo colX = squares.get(i).xCollision(p1);
            CollisionInfo colY = squares.get(i).yCollision(p1);
            // no collision -> skip iteration
            if (!colX.isCollision() && !colY.isCollision()){
                continue;
            }
            // if both, correct the smaller difference
            if (colX.getDiff() < colY.getDiff()){
                if (colX.ispLeft()){
                    p1.setX(colX.getSquare().getX1()-Player.PLAYERLENGTH);
                    p1.setDx(0);
                }
                else {
                    p1.setX(colX.getSquare().getX2());
                    p1.setDx(0);
                }
            }
            else {
                if (colY.ispAbove()){
                    p1.setY(colY.getSquare().getY1()-Player.PLAYERLENGTH);
                    p1.setDy(0);
                    p1.setInAir(false);
                }
                else {
                    p1.setY(colY.getSquare().getY2());
                    p1.setDy(0);
                }
            }
        }
        // Same thing but for p2
        for (int i = 0; i < squares.size(); i++) {
            CollisionInfo colX = squares.get(i).xCollision(p2);
            CollisionInfo colY = squares.get(i).yCollision(p2);
            // no collision -> skip iteration
            if (!colX.isCollision() && !colY.isCollision()){
                continue;
            }
            // if both, correct the smaller difference
            if (colX.getDiff() < colY.getDiff()){
                if (colX.ispLeft()){
                    p2.setX(colX.getSquare().getX1()-Player.PLAYERLENGTH);
                    p2.setDx(0);
                }
                else {
                    p2.setX(colX.getSquare().getX2());
                    p2.setDx(0);
                }
            }
            else {
                if (colY.ispAbove()){
                    p2.setY(colY.getSquare().getY1()-Player.PLAYERLENGTH);
                    p2.setDy(0);
                    p2.setInAir(false);
                }
                else {
                    p2.setY(colY.getSquare().getY2());
                    p2.setDy(0);
                }
            }
        }
        gameViewer.repaint();
    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                wPressed = false;
                break;
            case KeyEvent.VK_A:
                aPressed = false;
                break;
            case KeyEvent.VK_D:
                dPressed = false;
                break;
            case KeyEvent.VK_UP:
                upPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = false;
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        switch(e.getKeyCode())
        {
            // WASD movement for p1
            case KeyEvent.VK_W:
                wPressed = true;
                break;
            case KeyEvent.VK_A:
                aPressed = true;
                break;
            case KeyEvent.VK_D:
                dPressed = true;
                break;
            // Arrows for p2
            case KeyEvent.VK_UP:
                upPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed = true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed = true;
                break;

        }
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public int getGameState() {
        return gameState;
    }

    public ArrayList<ArrayList<Square>> getLevels() {
        return levels;
    }

    public ArrayList<Square> getSquares() {
        return squares;
    }

    public static void main(String[] args) {
        Game a = new Game();
        Timer clock = new Timer(SLEEP_TIME, a);
        clock.start();
    }
}
