package Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    Dimension windowDimensions;
    Board board;
    BoardBuilder boardBuilder;
    public GamePanel(int windowWidth, int windowHeight){
        boardBuilder = new BoardBuilder(new Dimension(windowWidth,windowHeight));
        board = boardBuilder.getBoard();
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.BLUE);
        for(ArrayList<Field> row : board.fieldArray){
            for(Field field : row){
                if(field.getOwnerId() == 0) graphics2D.setPaint(Color.BLACK);
                if(field.getOwnerId() == 1) graphics2D.setPaint(Color.BLUE);
                if(field.getOwnerId() == 2) graphics2D.setPaint(Color.RED);
                if(field.getOwnerId() == 3) graphics2D.setPaint(Color.GREEN);
                if(field.getOwnerId() == 4) graphics2D.setPaint(Color.PINK);
                if(field.getOwnerId() == 5) graphics2D.setPaint(Color.YELLOW);
                if(field.getOwnerId() == 6) graphics2D.setPaint(Color.MAGENTA);
                graphics2D.fill(field.getField());
            }
        }
    }
}
