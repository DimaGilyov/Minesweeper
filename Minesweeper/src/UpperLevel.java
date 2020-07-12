public class UpperLevel {
    private int ROWS;
    private int COLS;
    private Slot[][] map;

    UpperLevel(int ROWS, int COLS) {
        this.ROWS = ROWS;
        this.COLS = COLS;
        initMap();
    }

    public Slot[][] getMap() {
        return map;
    }

    private void initMap() {
        map = new Slot[ROWS][COLS];
        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map.length; y++) {
                map[x][y] = new Slot(new Coord(x, y), Box.CLOSED);
            }
        }
    }

    public Slot getFlag(int x, int y, Box box) {
        map[x][y] = new Slot(new Coord(x, y), box);
        return map[x][y];
    }
}
