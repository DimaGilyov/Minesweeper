import java.awt.*;

public class Loader {
    static Loader rl = new Loader();

    public static Image getLuckyImage(String filename) {
        return Toolkit.getDefaultToolkit().getImage(rl.getClass().getResource(filename));
    }
}
