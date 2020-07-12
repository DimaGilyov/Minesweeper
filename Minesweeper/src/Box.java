import java.awt.*;

public enum Box {
    ZERO("zero"),
    NUM1("num1"),
    NUM2("num2"),
    NUM3("num3"),
    NUM4("num4"),
    NUM5("num5"),
    NUM6("num6"),
    NUM7("num7"),
    NUM8("num8"),
    BOMB("bomb"),
    OPENED("opened"),
    BOMBED("bombed"),
    CLOSED("closed"),
    FLAGED("flaged"),
    INFORM("inform"),

    NOBOMB("nobomb");

    Image image;

    Box(String name) {
        //String url = "Minesweeper/img/" +  name.toLowerCase() + ".png";
        //this.image = new ImageIcon(url).getImage();

        String url = name.toLowerCase() + ".png";
        this.image = Loader.getLuckyImage(url);
    }


    public Box getNextNumber() {
        return values()[ordinal() + 1];
    }
}
