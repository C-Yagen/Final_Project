import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener, KeyListener {
    private Player p1;
    private Player p2;
    private Window gameViewer;
    private static final int SLEEP_TIME = 15;
    private int gameState;
    private boolean wPressed;
    private boolean aPressed;
    private boolean dPressed;


    public Game() {
        gameState = 0;
        gameViewer = new Window(this);
        gameViewer.addKeyListener(this);
        p1 = new Player(gameViewer, 100, 100, Color.green);
        //p2 = new Player(gameViewer, 0, 0, Color.red);
        gameState = 1;
    }

    public void actionPerformed(ActionEvent e){
        if (wPressed){
            p1.addDy(-18);
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

                break;
            case KeyEvent.VK_LEFT:

                break;
            case KeyEvent.VK_DOWN:

                break;
            case KeyEvent.VK_RIGHT:

                break;

        }
        //gameViewer.repaint();
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

    public static void main(String[] args) {
        Game a = new Game();
        Timer clock = new Timer(SLEEP_TIME, a);
        clock.start();
    }
}
