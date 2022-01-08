package Client;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private static int windowSize = 500;
    private GamePanel gamePanel;
    private GameServerMediator mediator; //do wyrzucenia
    
    public MainWindow(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(windowSize,windowSize);
        setLayout(new BorderLayout());
        setResizable(false);
        this.gamePanel = new GamePanel(getWidth(),getHeight());
        add(gamePanel,BorderLayout.CENTER);
    }
    
    public void setGameServerMediator(GameServerMediator mediator) { //nwm czy dobre
    	this.mediator=mediator;
    }
    
    public GamePanel getGamePanel(){
    	return gamePanel;
    }
}
