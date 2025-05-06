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
        if (gs == -2){
            //won
            return;
        }
        ArrayList<Square> squares = game.getLevels().get(gs);
        for (int i = 0; i < squares.size(); i++) {
            squares.get(i).draw(g);
        }
        ArrayList<Button> buttons = game.getButtons();
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).draw(g);
            if (buttons.get(i).isActivated()){
                buttons.get(i).getSquare().draw(g);
            }
        }
        game.getRedExit().draw(g);
        game.getGreenExit().draw(g);
        game.getP1().draw(g);
        game.getP2().draw(g);


    }
}
