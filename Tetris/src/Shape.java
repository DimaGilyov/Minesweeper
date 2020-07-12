import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Shape {
    private Color color;
    private int[][] matrix;
    private ArrayList<Block> coordinates;
    private int height;
    private int width;

    Shape(int height, int width) {
        this.height = height;
        this.width = width;

        Random random = new Random();
        int type = random.nextInt(Figure.values().length);
        matrix = Figure.values()[type].shape;
        color = Figure.values()[type].color;

        addStartingCoordinates(matrix);
        setLocation(height, width);
    }

    public Color getColor() {
        return color;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public ArrayList<Block> getCoordinates() {
        return coordinates;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private void addStartingCoordinates(int[][] figure) {
        coordinates = new ArrayList<>();
        for (int x = 0; x < figure.length; x++) {
            for (int y = 0; y < figure.length; y++) {
                if (figure[x][y] == 1) {
                    coordinates.add(new Block(x, y));
                }
            }
        }
    }

    public void setLocation(int height, int width) {
        int i = 0;
        for (Block block : coordinates) {
            block.setX(block.getX() + height);
            block.setY(block.getY() + width);
            coordinates.set(i, block);
            i++;
        }
    }

    public boolean isTouchGround() {
        for (Block block : coordinates) {
            if (GameCanvas.getGlass()[block.getX() + 1][block.getY()] == 1) {
                return true;
            }
        }

        return false;
    }

    public void fallDown() {
        if (!isTouchGround()) {
            int stepDown = 1;
            setLocation(stepDown, 0);
        }
    }

    public void paint(Graphics g) {
        g.setColor(color);
        for (Block block : coordinates) {
            for (int x = 0; x < GameCanvas.getGlass()[0].length; x++) {
                for (int y = 0; y < GameCanvas.getGlass().length; y++) {
                    if (x == block.getY() && y == block.getX()) {
                        g.fill3DRect(x * Block.getSize(), y * Block.getSize(), Block.getSize(), Block.getSize(), true);
                    }
                }
            }
        }
    }
}
