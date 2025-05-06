import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements ActionListener, KeyListener {
    private Player p1;
    private Player p2;
    private Window gameViewer;
    private static final int SLEEP_TIME = 30;
    private int gameState;
    private boolean wPressed;
    private boolean aPressed;
    private boolean dPressed;
    private boolean upPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private int tick;
    private ArrayList<Square> squares;
    private ArrayList<Button> buttons;
    private ArrayList<ArrayList<Square>> levels;
    private final int XMIDDLE = 500;
    private Exit greenExit;
    private Exit redExit;


    public Game() {
        gameState = -1;
        squares = new ArrayList<Square>();
        buttons = new ArrayList<Button>();
        levels = new ArrayList<ArrayList<Square>>();
        gameViewer = new Window(this);
        gameViewer.addKeyListener(this);
        p1 = new Player(gameViewer, XMIDDLE-450, 680, Color.green);
        p2 = new Player(gameViewer, XMIDDLE+450, 680, Color.red);
        greenExit = new Exit(955, 975, 680, 700, Color.green, 0);
        redExit = new Exit(25, 45, 680, 700, Color.red, 0);
        gameState = 0;
        tick = 0;
        squares.add(new Fire(0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT-25, Window.WINDOW_HEIGHT,1));
        squares.add(new Square(0, 25, 0, Window.WINDOW_HEIGHT,2));
        squares.add(new Square(Window.WINDOW_WIDTH-25, Window.WINDOW_WIDTH, 0, Window.WINDOW_HEIGHT,3));
        squares.add(new Square(485, 515, 200, Window.WINDOW_HEIGHT,4));
        squares.add(new Square(XMIDDLE-500, XMIDDLE-400, 700, 725,5));
        squares.add(new Square(XMIDDLE-310, XMIDDLE-250, 600, 625,6));
        squares.add(new Square(XMIDDLE-310, XMIDDLE-250, 475, 500,7));
        squares.add(new Square(XMIDDLE-310, XMIDDLE-250, 320, 350,9));
        squares.add(new Square(XMIDDLE-90, XMIDDLE, 225, 250,25));

        squares.add(new Square(XMIDDLE+100, XMIDDLE+500, 700, 725,10));
        squares.add(new Square(XMIDDLE+100, XMIDDLE+500, 600, 625,11));

        squares.add(new Fire(XMIDDLE+300, XMIDDLE+325, 675, 700,13));
        squares.add(new Fire(XMIDDLE+215, XMIDDLE+260, 615, 650,14));
        squares.add(new Fire(XMIDDLE+150, XMIDDLE+175, 675, 700,15));
        squares.add(new Fire(550, 580, 460, 490,16));
        squares.add(new Square(670, 700, 430, 460,17));
        squares.add(new Fire(770, 800, 390, 420,18));
        squares.add(new Fire(850, 880, 550, 580,19));
        squares.add(new Square(880, 910, 500, 530,20));
        squares.add(new Fire(550, 580, 320, 350,22));
        squares.add(new Square(670, 700, 290, 320,23));
        squares.add(new Square(860, 890, 480-155, 510-155,24));

        levels.add(squares);

        buttons.add(new Button(730, 751,695, 705, new Square(XMIDDLE-90, XMIDDLE, 400, 425,8), false, 0));
        buttons.add(new Button(210, 231, 315, 330, new Square(800, 830, 480, 510,21), false, 0));
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
        // checks for the win
        if (redExit.Collision(p2) && greenExit.Collision(p1)){
            gameState = -2;
            System.out.println("Won!");
            return;
        }
        // code for buttons
        int numAdded = 0;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).Collision(p2) || buttons.get(i).Collision(p1)){
                buttons.get(i).setActivated(true);
            }
            else {
                buttons.get(i).setActivated(false);
            }
        }
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).isActivated()){
                squares.add(buttons.get(i).getSquare());
                numAdded++;
            }
        }
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
        for (int i = 0; i < numAdded; i++) {
            squares.remove(squares.size()-1);
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

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public Exit getGreenExit() {
        return greenExit;
    }

    public Exit getRedExit() {
        return redExit;
    }

    public static void main(String[] args) {
        Game a = new Game();
        Timer clock = new Timer(SLEEP_TIME, a);
        clock.start();
    }
}
