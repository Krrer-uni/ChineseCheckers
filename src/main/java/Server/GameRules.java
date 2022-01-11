package Server;

import java.util.ArrayList;

import Client.Field;

/**
 * Interface describing methods that a good game rules need to have
 * @author Marek Świergoń
 *
 */
public interface GameRules {
	/**
	 * Method to check if a game could be played with specified number of players
	 * @param playerNumber number of players willing to play
	 * @return true if game is suitable for this amount of players, otherwise false
	 */
	boolean isPlayerNumberGood (int playerNumber);
	
	/**
	 * Method to check all available moves that could be done from a specific point
	 * @param startX x coordinate of a point to move from
	 * @param startY y coordinate of a point to move from
	 * @return a two-dimension array of all available moves (x,y respectively)
	 * all available moves are separated from zeros by -1,-1 record
	 */
	ArrayList<FieldCords> availableMoves (int startX, int startY);
	
	/**
	 * Method to check if a specific move is proper
	 * @param startX x coordinate of a point to move from
	 * @param startY y coordinate of a point to move from
	 * @param endX x coordinate of a point to move to
	 * @param endY y coordinate of a point to move to
	 * @param playerId an id of a player making this move
	 * @param playerCount number of players that this game had playing from start
	 * @return true if a move is OK, otherwise false
	 */
	boolean isMoveGood (int startX, int startY, int endX, int endY, int playerId, int playerCount);
	
	/**
	 * Method to check if a specific player 
	 * has met conditions to end a game (possibly win)
	 * @param playerCount number of players that this game had playing from start
	 * @param playerId an id of a player to check
	 * @return true if the player has ended a game, otherwise false
	 */
	boolean hasEnded(int playerCount, int playerId);
}
