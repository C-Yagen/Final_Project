import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game implements ActionListener, KeyListener {
    private Player p1;
    private Player p2;
    private Window gameViewer;

    public Game() {
        gameViewer = new Window(this);
        p1 = new Player(gameViewer, 0, 0);
        p2 = new Player(gameViewer, 0, 0);
    }

    public void actionPerformed(ActionEvent e){

    }

    public void keyTyped(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        switch(e.getKeyCode())
        {
            // WASD movement for p1
            case KeyEvent.VK_W:

                break;
            case KeyEvent.VK_A:

                break;
            case KeyEvent.VK_S:

                break;
            case KeyEvent.VK_D:

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
        gameViewer.repaint();
    }
}
