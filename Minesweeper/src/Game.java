import java.util.ArrayList;

public class Game {
    private int ROWS;
    private int COLS;
    private int bombsCount;
    private LowerLever lowerLeverMap;
    private UpperLevel upperLeverMap;
    private Slot[][] map;
    private GameState state;
    private ArrayList<Slot> openMapCoordsList;
    private int openSlotsCount = 0;
    private int openSlotsCountForWin;

    Game(int ROWS, int COLS, int bombsCount) {
        this.ROWS = ROWS;
        this.COLS = COLS;
        this.bombsCount = bombsCount;
        this.openMapCoordsList = new ArrayList<>();
        this.state = GameState.IN_GAME;
        openSlotsCountForWin = ROWS * COLS - bombsCount;
        initStartGame();
    }

    public Slot[][] getMap() {
        return map;
    }

    public GameState getState() {
        return state;
    }

    private void initStartGame() {
        lowerLeverMap = new LowerLever(ROWS, COLS, bombsCount);
        upperLeverMap = new UpperLevel(ROWS, COLS);

        map = upperLeverMap.getMap();
    }

    private void checkGameState(int openSlotsCount, int openSlotsCountForWin) {
        if (openSlotsCount == openSlotsCountForWin) {
            state = GameState.WIN;
            map = lowerLeverMap.getMap();
        }
    }

    public void openSlot(int x, int y) {
        if (state == GameState.IN_GAME) {
            if (x < 0 || x >= GameWindow.COLS || y < 0 || y >= GameWindow.ROWS) {
                return;
            }

            map[x][y] = lowerLeverMap.getMap()[x][y];
            if (openMapCoordsList.contains(map[x][y])) {
                return;
            }

            openSlotsCount++;
            checkGameState(openSlotsCount, openSlotsCountForWin);
            openMapCoordsList.add(map[x][y]);
            if (map[x][y].getBox() == Box.ZERO) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        openSlot(x + j, y + i);
                    }
                }
            }
            if (map[x][y].getBox() == Box.BOMB) {
                state = GameState.GAME_OVER;
                map = lowerLeverMap.getMap();
                map[x][y].setBox(Box.BOMBED);
            }
        }
    }

    public Slot getSlot(int x, int y) {
        return map[x][y];
    }

    public void setFlag(int x, int y, Box box) {
        map[x][y] = upperLeverMap.getFlag(x, y, box);
    }
}
