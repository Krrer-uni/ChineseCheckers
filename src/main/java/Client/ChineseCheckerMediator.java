package Client;

/**
 * Mediator between game and server side of Client
 */
public class ChineseCheckerMediator implements GameServerMediator {
    /**
     * variable that holds reference to server
     */
    private Client2ServerConnection server;
    /**
     * variable that hold reference to gamePanel
     */
    private GamePanel gamePanel;

    /**
     * basic constructor that sets up variables
     *
     * @param server    server
     * @param gamePanel game Panel
     */
    public ChineseCheckerMediator(Client2ServerConnection server, GamePanel gamePanel) {
        this.server = server;
        this.gamePanel = gamePanel;
    }

    /**
     * interface for client to update board
     *
     * @param rowSource    source row
     * @param columnSource source column
     * @param rowTarget    target row
     * @param columnTarget target column
     */
    public void updateBoard(int rowSource, int columnSource, int rowTarget, int columnTarget) {
        gamePanel.updateBoard(rowSource, columnSource, rowTarget, columnTarget);
    }

    /**
     * interface for game to send move to server
     *
     * @param rowSource    source row of move
     * @param columnSource source column of move
     * @param rowTarget    target row of move
     * @param columnTarget target column of move
     */
    public void sendMove(int rowSource, int columnSource, int rowTarget, int columnTarget) {
        server.sendMove(rowSource, columnSource, rowTarget, columnTarget);
    }

    /**
     * interface for client to initialize the game
     *
     * @param playerCount number of players in game
     * @param playerId    id of the player assigned to the client
     */
    public void gameInit(int playerCount, int playerId) {
        gamePanel.setBoardBuilderLayout(playerCount);
        gamePanel.setPlayerId(playerId);
    }

    /**
     * interface for client to change which player has its turn
     *
     * @param nextPlayer id of player that turn starts
     */
    public void setCurrentPlayer(int nextPlayer) {
        gamePanel.setCurrentPlayer(nextPlayer);
    }

    /**
     * interface for game to send skip message to server
     */
    public void sendSkip() {
        server.sendSkip();
    }

    /**
     * interface for client to end the game tell player which place he got
     *
     * @param place place on which the player finished the game
     */
    public void gameFinished(int place) {
        gamePanel.gameFinished(place);
    }
}
