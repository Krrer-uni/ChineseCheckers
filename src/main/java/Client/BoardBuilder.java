package Client;

/**
 * interface for boardbuilder
 */
public interface BoardBuilder {
    /**
     * Function that returns built board
     *
     * @return board as 2dim arraylist
     */
    Board getBoard();

    /**
     * construction of board
     *
     * @param playerCount playercount for which the board should be built
     */
    void buildBoard(int playerCount);
}
