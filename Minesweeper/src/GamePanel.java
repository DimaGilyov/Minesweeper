import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GamePanel extends JPanel {
    int coorX;
    int coorY;
    Game gameMap;

    public GamePanel() {
        gameMap = new Game(GameWindow.ROWS, GameWindow.COLS, GameWindow.BOMBS_COUNT);
        click();
        setOpaque(false);
    }

    public Game getGameMap() {
        return gameMap;
    }

    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < GameWindow.ROWS; i++) {
            for (int j = 0; j < GameWindow.COLS; j++) {
                Slot[][] map = gameMap.getMap();
                Image image = map[i][j].getBox().image;
                Coord coord = map[i][j].getCoord();
                g.drawImage(image, coord.x * GameWindow.IMAGE_SIZE, coord.y * GameWindow.IMAGE_SIZE, this);
            }
        }
    }

    public void click() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                coorX = e.getX() / GameWindow.IMAGE_SIZE;
                coorY = e.getY() / GameWindow.IMAGE_SIZE;


                if (e.getButton() == MouseEvent.BUTTON1) {
                    Box box = gameMap.getSlot(coorX, coorY).getBox();
                    if (box != Box.FLAGED && box != Box.INFORM) {
                        gameMap.openSlot(coorX, coorY);
                        repaint();
                    }

                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    Box box = gameMap.getSlot(coorX, coorY).getBox();
                    if (box == Box.CLOSED || box == Box.FLAGED || box == Box.INFORM) {
                        if (box == Box.INFORM) {
                            gameMap.setFlag(coorX, coorY, Box.CLOSED);
                        } else {
                            gameMap.setFlag(coorX, coorY, box.getNextNumber());
                        }
                    }

                    repaint();
                }
            }
        });
    }

}
