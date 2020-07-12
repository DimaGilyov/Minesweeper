import javax.swing.*;
import java.awt.*;

public class Window {
    private final String TITLE = "TETRIS";
    public static final int WIDTH = 12;
    public static final int HEIGHT = 21;
    public static final int BLOCK_SIZE = 40;
    public static int SPEED = 1000;
    public static boolean gameOver = false;

    public void start() {
        JFrame jFrame = new JFrame(TITLE);
        jFrame.setSize(WIDTH * BLOCK_SIZE + 18, HEIGHT * BLOCK_SIZE + BLOCK_SIZE);
        jFrame.setLocation(500, 120);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        GameCanvas canvas = new GameCanvas();
        canvas.setBackground(Color.BLACK);
        jFrame.getContentPane().add(canvas);
        canvas.run();
    }
}
