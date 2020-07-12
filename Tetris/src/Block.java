public class Block {
    private static int size;
    private int x;
    private int y;


    Block(int x, int y){
        this.x = x;
        this.y = y;
        this.size = Window.BLOCK_SIZE;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static int getSize() {
        return size;
    }
}
