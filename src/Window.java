import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Window extends JFrame {
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game game;

    public Window(Game game) {
        this.game = game;


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
        repaint();
    }

    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        int gs = game.getGameState();
        if (gs == -1){
            return;
        }
        ArrayList<Square> squares = game.getLevels().get(gs);
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(g);
        }

        game.getP1().draw(g);
        game.getP2().draw(g);


    }
}
