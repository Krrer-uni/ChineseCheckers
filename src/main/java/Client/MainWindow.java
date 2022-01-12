package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Main window of the application
 */
public class MainWindow extends JFrame {
    /**
     * Panel with game
     */
    private final GamePanel gamePanel;

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int windowSize = 700;
        setSize(windowSize, windowSize);
        setLayout(new BorderLayout());
        setResizable(false);
        this.gamePanel = new GamePanel(getWidth(), getHeight());
        add(gamePanel, BorderLayout.CENTER);
    }

    /**
     * getter for game panel
     *
     * @return game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
