package Client;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    Board board;
    BoardBuilder boardBuilder;

    public GamePanel(int windowWidth, int windowHeight) {
        boardBuilder = new BoardBuilder(new Dimension(windowWidth, windowHeight));
        boardBuilder.setLayout(2);
        board = boardBuilder.getBoard();
        setBackground(new Color(199, 133, 26));
        repaint();
        addMouseListener(new MyMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setPaint(Color.BLUE);
        for (ArrayList<Field> row : board.fieldArray) {
            for (Field field : row) {
                if (field.getOwnerId() == 0) graphics2D.setPaint(new Color(128, 86, 19));
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

    private class MyMouseListener extends MouseInputAdapter {

        int chosenColumn;
        int chosenRow;

        boolean choosingTarget = true;

        @Override
        public void mousePressed(MouseEvent e) {
            if (choosingTarget) {
                for (ArrayList<Field> row : board.fieldArray) {
                    for (Field field : row) {
                        if (field.getField().contains(e.getX(), e.getY())) {
                            System.out.println("wybrano " + row.indexOf(field) + "," + board.fieldArray.indexOf(row));
                            chosenColumn = row.indexOf(field);
                            chosenRow = board.fieldArray.indexOf(row);
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

                        Rectangle2D tempBounds = field.getField().getFrame();
                        double x,y,w,h;
                        x = tempBounds.getX();
                        y = tempBounds.getY();
                        w = tempBounds.getWidth();
                        h = tempBounds.getHeight();

                        Field tempField = board.fieldArray.get(chosenRow).get(chosenColumn);

                        Rectangle2D tempBounds2 = tempField.getField().getFrame();
                        double x2,y2,w2,h2;
                        x2 = tempBounds.getX();
                        y2 = tempBounds.getY();
                        w2 = tempBounds.getWidth();
                        h2 = tempBounds.getHeight();

//                        Field tempField = board.fieldArray.get(chosenRow).get(chosenColumn);
                        board.fieldArray.get(targetRow).get(targetColumn).getField().setFrame(x2,y2,w2,h2);
//                        field.getField()
                        board.fieldArray.get(chosenRow).get(chosenColumn).getField().setFrame(x,y,w,h);




                        repaint();
                        choosingTarget = true;
                        break;
                    }
                }
            }
        }
    }
}
