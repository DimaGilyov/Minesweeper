public class Slot {
    private Coord coord;
    private Box box;

    Slot(Coord coord, Box box){
        this.coord = coord;
        this.box = box;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
}
