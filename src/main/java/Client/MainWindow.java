package Client;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static int windowSize = 1000;

    public MainWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(windowSize,windowSize);
        setLayout(new BorderLayout());
        setResizable(false);
        getContentPane().setForeground(new Color(168, 107, 50));
        GamePanel gamePanel = new GamePanel(getWidth(),getHeight());
        add(gamePanel,BorderLayout.CENTER);
    }
}
