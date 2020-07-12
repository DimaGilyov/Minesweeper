import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameWindow extends JFrame {
    public static final int COLS = 9;
    public static final int ROWS = 9;
    public static final int BOMBS_COUNT = 10;
    public static final int IMAGE_SIZE = 50;
    private static String state;
    private GamePanel panel;

    public void start() {
        panel = new GamePanel();
        state = panel.getGameMap().getState().toString();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setTitle("Minesweeper");
                String urlIcon = "icon.png";
                Image image = Loader.getLuckyImage(urlIcon);
                setIconImage(image);
                setVisible(true);
                setResizable(true);
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                JMenuBar menuBar = new JMenuBar();
                JMenu menu = new JMenu("Game");
                JMenuItem newGameItem = new JMenuItem("New Game");
                JMenuItem exitGameItem = new JMenuItem("Exit");
                menu.add(newGameItem);
                menu.add(exitGameItem);
                menuBar.add(menu);
                setJMenuBar(menuBar);
                getContentPane().add(panel);
                panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
                JLabel status = new JLabel(state);
                JPanel statusPanel = new JPanel();
                statusPanel.add(status,BorderLayout.CENTER);
                getContentPane().add(statusPanel, BorderLayout.NORTH);
                pack();


                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        state = panel.getGameMap().getState().toString();
                        status.setText(state);
                    }
                });
                newGameItem.addActionListener(e -> {
                    getContentPane().remove(panel);
                    panel = new GamePanel();
                    getContentPane().add(panel);
                    panel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
                    state = panel.getGameMap().getState().toString();
                    status.setText(state);
                    pack();

                    panel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            state = panel.getGameMap().getState().toString();
                            status.setText(state);
                        }
                    });
                });
                exitGameItem.addActionListener(e -> {
                    System.exit(0);
                });
            }
        });
    }
}

