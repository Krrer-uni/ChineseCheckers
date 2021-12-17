package Client;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400,400);
        setLayout(new BorderLayout());
        setResizable(false);
        getContentPane().setForeground(new Color(168, 107, 50));
        add(new GamePanel(),BorderLayout.CENTER);
    }
}
