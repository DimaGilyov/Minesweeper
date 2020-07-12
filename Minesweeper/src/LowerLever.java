import java.util.ArrayList;
import java.util.Random;

public class LowerLever {
    private int ROWS;
    private int COLS;
    private int bombsCount;
    private Slot[][] map;

    LowerLever(int ROWS, int COLS, int bombsCount) {
        this.ROWS = ROWS;
        this.COLS = COLS;
        this.bombsCount = bombsCount;
        initMap();
    }

    public Slot[][] getMap() {
        return map;
    }

    private void initMap() {
        //Инициализация ZERO
        map = new Slot[ROWS][COLS];
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                map[x][y] = (new Slot(new Coord(x, y), Box.ZERO));
            }
        }

        //Расстановка бомб
        ArrayList<Coord> bombsCoordsList = getBombsCoord(bombsCount);
        for (Coord coord : bombsCoordsList) {
            map[coord.x][coord.y] = (new Slot(new Coord(coord.x, coord.y), Box.BOMB));
        }

        //Расставить цифры вокруг бомб
        ArrayList<Coord> numbersCoord = getNumbers(bombsCoordsList);
        for (Coord coord : numbersCoord) {
            Box num = Box.ZERO;
            for (Coord coord2 : numbersCoord) {
                if (coord.equals(coord2)){
                    num = num.getNextNumber();
                }
            }

            map[coord.x][coord.y] = (new Slot(new Coord(coord.x, coord.y), num));
        }
    }

    private ArrayList<Coord> getBombsCoord(int bombsCount) {
        ArrayList<Coord> bombsCoordsList = new ArrayList<>();

        for (int i = 0; i < bombsCount; ) {
            Random randomX = new Random();
            Random randomY = new Random();
            Coord coord = new Coord(randomX.nextInt(ROWS), randomY.nextInt(COLS));

            boolean isDoubleCoord = false;
            for (Coord coordItem : bombsCoordsList) {
                if (coord.equals(coordItem)) {
                    isDoubleCoord = true;
                    break;
                }
            }

            if (!isDoubleCoord) {
                bombsCoordsList.add(coord);
                i++;
            }
        }

        return bombsCoordsList;
    }

    private ArrayList<Coord> getNumbers(ArrayList<Coord> bombsCoordsList) {
        ArrayList<Coord> numbersListTemp = new ArrayList<>();

        for (Coord bombCoord : bombsCoordsList) {
            int coordX = bombCoord.x;
            int coordY = bombCoord.y;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    numbersListTemp.add(new Coord(coordX + i, coordY + j));
                }
            }
        }

        ArrayList<Coord> numbersListTemp2 = new ArrayList<>();
        for (Coord numberCoord : numbersListTemp) {
            boolean isDoubleCoord = false;

            for (Coord bombCoord : bombsCoordsList) {
                if (numberCoord.equals(bombCoord)) {
                    isDoubleCoord = true;
                    break;
                }
            }

            if (!isDoubleCoord) {
                numbersListTemp2.add(numberCoord);
            }
        }

        ArrayList<Coord> numbersList = new ArrayList<>();
        for (Coord coord : numbersListTemp2) {
            if (coord.x >= 0 && coord.y >= 0 && coord.x < GameWindow.ROWS && coord.y < GameWindow.COLS) {
                numbersList.add(coord);
            }
        }

        return numbersList;
    }
}

