import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class GameCanvas extends JPanel {
    private static int[][] glass;
    Shape shape;


    GameCanvas() {
        glass = new int[Window.HEIGHT + 1][Window.WIDTH + 2];
        Arrays.fill(glass[Window.HEIGHT], 1);
        for (int i = 0; i < glass.length; i++) {
            glass[i][0] = 1;
            glass[i][Window.WIDTH + 1] = 1;
        }
    }

    public static int[][] getGlass() {
        return glass;
    }

    public void run() {
        int i = 0;
        shape = new Shape(0, 3);

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                shape.fallDown();
                if (shape.isTouchGround()) {
                    shape = new Shape(0, 3);
                }

                repaint();
            }
        };

        timer.schedule(task, Window.SPEED * 3, 3);
    }

    @Override
    public void paint(Graphics g) {
        shape.paint(g);
        //прорисовка контуров
        for (int i = 0; i < glass.length; i++) {
            for (int y = 0; y < glass[0].length; y++) {
                g.setColor(Color.GRAY);
                g.fill3DRect(0, i * Block.getSize(), Block.getSize(), Block.getSize(), true);
                g.fill3DRect((Window.WIDTH - 1) * Block.getSize(), i * Block.getSize(), Block.getSize(), Block.getSize(), true);
                g.fill3DRect((y) * Block.getSize(), (Window.HEIGHT - 1) * Block.getSize(), Block.getSize(), Block.getSize(), true);
            }
        }
    }
}
