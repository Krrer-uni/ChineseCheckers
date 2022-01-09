package Client;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    Board board;
    BoardBuilder boardBuilder;
    GameServerMediator mediator;
    int playerId;
    int currentPlayer;
    JButton skipbutton;
    JTextArea playerInfo;

    public GamePanel(int windowWidth, int windowHeight) {
        currentPlayer = 0;

        boardBuilder = new BoardBuilder(new Dimension(windowWidth,
                windowHeight));
        boardBuilder.setLayout(2);
        board = boardBuilder.getBoard();
        setBackground(new Color(160, 101, 28));

        setLayout(null);

        skipbutton = new JButton("Skip Turn");
        add(skipbutton);
        skipbutton.setBounds((int) (windowWidth * 0.7), (int) (windowHeight * 0.8),
                (int) (windowWidth * 0.25), (int) (windowHeight * 0.1));

        playerInfo  = new JTextArea("You are a player " + playerId + "\n");
        playerInfo.setEditable(false);
        add(playerInfo);
        playerInfo.setOpaque(false);
        playerInfo.setBorder(null);
        playerInfo.setFont(new Font("Sans", 0,20));
        playerInfo.setBounds((int) (windowWidth * 0.05), (int) (windowHeight * 0.05),
                (int) (windowWidth * 0.25), (int) (windowHeight * 0.1));


        addMouseListener(new GameMouseListener());
        skipbutton.addActionListener(new SkipButtonListener());

        repaint();
    }


    public void setGameServerMediator(GameServerMediator mediator) {
        this.mediator = mediator;
    }

    public void setBoardBuilderLayout(int playerCount) {
        boardBuilder.setLayout(playerCount);
        board = boardBuilder.getBoard();
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
                if(field instanceof PlayerField) graphics2D.fill(field.getField());
                if (field.isActive()) {
                    graphics2D.setPaint(Color.black);
                    graphics2D.draw(field.getField());
                }
            }
        }
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
        String message = "You are a player ";

        message += playerId;
        message += "\nYour color is ";
        if(playerId == 1) message += "blue";
        else if(playerId == 2) message += "red";
        else if(playerId == 3) message += "green";
        else if(playerId == 4) message += "pink";
        else if(playerId == 5) message += "yellow";
        else if(playerId == 6) message += "magenta";
        this.playerInfo.setText(message);

    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void updateBoard(int sourceRow, int sourceColumn, int targetRow, int targetColumn) {
        board.fieldArray.get(sourceRow).get(sourceColumn).setActive(false);
        Rectangle2D tempFrameSource =
                board.fieldArray.get(sourceRow).get(sourceColumn).getField().getFrame();
        Rectangle2D tempFrameTarget =
                board.fieldArray.get(targetRow).get(targetColumn).getField().getFrame();
        board.fieldArray.get(sourceRow).get(sourceColumn).getField().setFrame(tempFrameTarget);
        board.fieldArray.get(targetRow).get(targetColumn).getField().setFrame(tempFrameSource);

        Field tempField = board.fieldArray.get(sourceRow).get(sourceColumn);
        board.fieldArray.get(sourceRow).set(sourceColumn,
                board.fieldArray.get(targetRow).get(targetColumn));
        board.fieldArray.get(targetRow).set(targetColumn, tempField);

        System.out.println("zamieniono z " + targetRow + "," + targetColumn);
        repaint();
    }

    private class GameMouseListener extends MouseInputAdapter {

        int sourceColumn = 0;
        int sourceRow = 0;

        boolean choosingSource = true;

        @Override
        public void mousePressed(MouseEvent e) {
            for (ArrayList<Field> row : board.fieldArray) {
                for (Field field : row) {
                    if (field.getField().contains(e.getX(), e.getY())) {
                        if (field.getOwnerId() == playerId) {
                            System.out.println("wybrano " + row.indexOf(field) + "," + board.fieldArray.indexOf(row));
                            board.fieldArray.get(sourceRow).get(sourceColumn).setActive(false);
                            sourceColumn = row.indexOf(field);
                            sourceRow = board.fieldArray.indexOf(row);
                            choosingSource = false;
                            field.setActive(true);
                            break;
                        } else if (field.getOwnerId() == 0 && !choosingSource && (playerId == currentPlayer)) {
                            int targetColumn = row.indexOf(field);
                            int targetRow = board.fieldArray.indexOf(row);
                            board.fieldArray.get(sourceRow).get(sourceColumn).setActive(false);
                            mediator.sendMove(sourceColumn, sourceRow, targetColumn, targetRow);
                            choosingSource = true;
                            break;
                        }
                    }
                }
            }
            repaint();
        }
    }

    public class SkipButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (playerId == currentPlayer) {
                mediator.sendSkip();
            }
        }
    }


}
