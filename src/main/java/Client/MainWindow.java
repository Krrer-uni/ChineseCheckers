package Client;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static int windowSize = 500;
    private GamePanel gamePanel;
    
    public MainWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(windowSize,windowSize);
        setLayout(new BorderLayout());
        setResizable(false);
        this.gamePanel = new GamePanel(getWidth(),getHeight());
        add(gamePanel,BorderLayout.CENTER);
    }

    
    public GamePanel getGamePanel(){
    	return gamePanel;
    }
}
