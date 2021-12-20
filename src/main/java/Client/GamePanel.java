package Client;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    Board board;
    BoardBuilder boardBuilder;
    GameServerMediator mediator;

    public GamePanel(int windowWidth, int windowHeight) {

        boardBuilder = new BoardBuilder(new Dimension(windowWidth, windowHeight));
        boardBuilder.setLayout(2);
        board = boardBuilder.getBoard();
        setBackground(new Color(160, 101, 28));
        repaint();
        addMouseListener(new MyMouseListener());
    }
    
    public void setGameServerMediator(GameServerMediator mediator) {
    	this.mediator=mediator;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.BLUE);
        for (ArrayList<Field> row : board.fieldArray) {
            for (Field field : row) {
                if (field.getOwnerId() == 0) graphics2D.setPaint(new Color(132, 88, 17));
                else if (field.getOwnerId() == 1) graphics2D.setPaint(Color.BLUE);
                else if (field.getOwnerId() == 2) graphics2D.setPaint(Color.RED);
                else if (field.getOwnerId() == 3) graphics2D.setPaint(Color.GREEN);
                else if (field.getOwnerId() == 4) graphics2D.setPaint(Color.PINK);
                else if (field.getOwnerId() == 5) graphics2D.setPaint(Color.YELLOW);
                else if (field.getOwnerId() == 6) graphics2D.setPaint(Color.MAGENTA);
                graphics2D.fill(field.getField());
            }
        }
    }

    public void updateBoard(int rowSource, int columnSource, int rowTarget, int columnTarget){
        Rectangle2D tempFrameSource = board.fieldArray.get(rowSource).get(columnSource).getField().getFrame();
        Rectangle2D tempFrameTarget = board.fieldArray.get(rowTarget).get(columnTarget).getField().getFrame();
        board.fieldArray.get(rowSource).get(columnSource).getField().setFrame(tempFrameTarget);
        board.fieldArray.get(rowTarget).get(columnTarget).getField().setFrame(tempFrameSource);

        Field tempField = board.fieldArray.get(rowSource).get(columnSource);
        board.fieldArray.get(rowSource).set(columnSource, board.fieldArray.get(rowTarget).get(columnTarget));
        board.fieldArray.get(rowTarget).set(columnTarget, tempField);
    }

    private class MyMouseListener extends MouseInputAdapter {

        int sourceColumn;
        int sourceRow;

        boolean choosingTarget = true;

        @Override
        public void mousePressed(MouseEvent e) {
            if (choosingTarget) {
                for (ArrayList<Field> row : board.fieldArray) {
                    for (Field field : row) {
                        if (field.getField().contains(e.getX(), e.getY())) {
                            System.out.println("wybrano " + row.indexOf(field) + "," + board.fieldArray.indexOf(row));
                            sourceColumn = row.indexOf(field);
                            sourceRow = board.fieldArray.indexOf(row);
                            choosingTarget = false;
                            break;
                        }
                    }
                }
            } else for (ArrayList<Field> row : board.fieldArray) {
                for (Field field : row) {
                    if (field.getField().contains(e.getX(), e.getY())) {
                        System.out.println("zamieniono z " + row.indexOf(field) + "," + board.fieldArray.indexOf(row));
                        int targetColumn = row.indexOf(field);
                        int targetRow = board.fieldArray.indexOf(row);
//
//                        Rectangle2D tempBounds = field.getField().getFrame();
//                        double x,y,w,h;
//                        x = tempBounds.getX();
//                        y = tempBounds.getY();
//                        w = tempBounds.getWidth();
//                        h = tempBounds.getHeight();
//
//                        Field tempField = board.fieldArray.get(chosenRow).get(chosenColumn);
//
//                        Rectangle2D tempBounds2 = tempField.getField().getFrame();
//                        double x2,y2,w2,h2;
//                        x2 = tempBounds.getX();
//                        y2 = tempBounds.getY();
//                        w2 = tempBounds.getWidth();
//                        h2 = tempBounds.getHeight();
//
////                        Field tempField = board.fieldArray.get(chosenRow).get(chosenColumn);
//                        board.fieldArray.get(targetRow).get(targetColumn).getField().setFrame(x2,y2,w2,h2);
////                        field.getField()
//                        board.fieldArray.get(chosenRow).get(chosenColumn).getField().setFrame(x,y,w,h);
                        
                        mediator.sendMove(sourceRow,sourceColumn,targetRow,targetColumn);
                        


                        repaint();
                        choosingTarget = true;
                        break;
                    }
                }
            }
        }
    }
}
