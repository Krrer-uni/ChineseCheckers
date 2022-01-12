package Client;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * main gamePanel in which the game happens
 */
public class GamePanel extends JPanel {
    /**
     * Board on which game is played
     */
    Board board;
    /**
     * board builder
     */
    BoardBuilder boardBuilder;
    /**
     * mediator that allows communication with server
     */
    ChineseCheckerMediator mediator;
    /**
     * id assigned to the player
     */
    int playerId;
    /**
     * player whos turn is this
     */
    int currentPlayer;
    /**
     * button to skip your turn
     */
    JButton skipButton;
    /**
     * text that displays information about the player
     */
    JTextArea playerInfo;
    /**
     * text that changes throughout the game and displays useful info
     */
    JTextArea gameInfo;

    /**
     * constructor of Game Panel
     * @param windowWidth width of window
     * @param windowHeight height of window
     */
    public GamePanel(int windowWidth, int windowHeight) {
        currentPlayer = 0;

        boardBuilder = new ChineseCheckersBoardBuilder(new Dimension(windowWidth,
                windowHeight));
        boardBuilder.buildBoard(2);
        board = boardBuilder.getBoard();
        setBackground(new Color(160, 101, 28));

        setLayout(null);

        skipButton = new JButton("Skip Turn");
        add(skipButton);
        skipButton.setBounds((int) (windowWidth * 0.7), (int) (windowHeight * 0.8),
                (int) (windowWidth * 0.25), (int) (windowHeight * 0.1));

        playerInfo  = new JTextArea("You are a player " + playerId + "\n");
        playerInfo.setEditable(false);
        add(playerInfo);
        playerInfo.setOpaque(false);
        playerInfo.setBorder(null);
        playerInfo.setFont(new Font("Sans", Font.PLAIN,20));
        playerInfo.setBounds((int) (windowWidth * 0.05), (int) (windowHeight * 0.05),
                (int) (windowWidth * 0.35), (int) (windowHeight * 0.1));

        gameInfo = new JTextArea("Waiting for players...");
        gameInfo.setEditable(false);
        gameInfo.setOpaque(false);
        gameInfo.setBorder(null);
        gameInfo.setFont(new Font("Sans", Font.PLAIN, 30));
        gameInfo.setBounds((int) (windowWidth * 0.55), (int) (windowHeight * 0.05),
                (int) (windowWidth * 0.4), (int) (windowHeight * 0.1));
        add(gameInfo);

        addMouseListener(new GameMouseListener());
        skipButton.addActionListener(new SkipButtonListener());

        repaint();
    }

    /**
     * setter of mediator
     * @param mediator mediator
     */
    public void setGameServerMediator(ChineseCheckerMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * function that builds a new board according to the nubmer of players
     * @param playerCount number of players
     */
    public void setBoardBuilderLayout(int playerCount) {
        boardBuilder.buildBoard(playerCount);
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

    /**
     * setter for player id that also changes
     * the text of playerInfo text box
     * @param playerId id of player to be asigned
     */
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

    /**
     * sets a player whose turn starts, also
     * changes text of game info
     * @param currentPlayer player whose turn started
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;

        gameInfo.setText("Player " + currentPlayer + " turn");
        gameInfo.setVisible(true);
    }

    /**
     * sets text of gameInfo to display place
     * that the player finished on
     * @param place place of the player
     */
    public void gameFinished(int place){
        if(place == 1){
            gameInfo.setFont(new Font("Sans", Font.PLAIN, 50));
            gameInfo.setText("YOU WON");
        } else {
            gameInfo.setText("You placed " + place);
            if (place == 2) gameInfo.append("nd");
            else if (place == 3) gameInfo.append("rd");
            else gameInfo.append("th");
        }
        gameInfo.setVisible(true);
    }

    /**
     * Function that changes position of two fields on the board
     * @param sourceRow row of first field
     * @param sourceColumn column of first field
     * @param targetRow row of second field
     * @param targetColumn column of second field
     */
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
                    if (field instanceof PlayerField && field.getField().contains(e.getX(), e.getY())) {
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

    /**
     * getter fo player id
     * @return id
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * getter for current player
     * @return current player
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * getter for text area object
     * @return text area object
     */
    public JTextArea getPlayerInfo() {
        return playerInfo;
    }

    /**
     * getter of game info object
     * @return game info object
     */
    public JTextArea getGameInfo() {
        return gameInfo;
    }
}
