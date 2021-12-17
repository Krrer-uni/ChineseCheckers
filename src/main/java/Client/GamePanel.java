package Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    Board board;
    BoardFactory boardFactory ;
    public GamePanel(){
        boardFactory = new BoardFactory();
        board = boardFactory.getBoard();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.BLUE);
        for(ArrayList<Field> row : board.fieldArray){
            for(Field field : row){
                graphics2D.draw(field.getField());
            }
        }
    }
}
