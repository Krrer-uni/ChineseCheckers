package Server;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class that is responsible for the logic of the game.
 * Decides about move order, starting and controlling the game.
 * @author Marek Świergoń
 *
 */
public class Game {
	
	/**
	 * instance of GameRules required to check 
	 * f.eg. moves with the rules
	 * @see GameRules
	 */
	private GameRules gameRules;
	
	/**
	 * Number of players playing the game
	 */
	private transient int numberPlayers;
	
	/**
	 * ArrayList of Player object, each representing a player
	 * that is playing the game (connected to a server)
	 * @see Player
	 */
	private transient final ArrayList<Player> players;
	
	/**
	 * Number of players that finished the game
	 */
	private transient int numberPlayersFinished;
	
	/**
	 * Constructor of a Game class
	 * @param gameRules rules the game will use
	 * @see GameRules
	 */
	public Game(final GameRules gameRules) {
		this.gameRules = gameRules;
		players = new ArrayList<>();
		this.numberPlayersFinished = 0;
	}
	
	/**
	 * Method to change rules of the game
	 * @param gameRules new rules
	 * @see GameRules
	 */
	public void setGameRules(final GameRules gameRules) {
		this.gameRules=gameRules;
	}
	
	/**
	 * Method to get current rules of the game
	 * @return current rules of the game
	 * @see GameRules
	 */
	public GameRules getGameRules() {
		return gameRules;
	}
	
	/**
	 * Method to set number of players playing the game,
	 * has to be compatible with the rules
	 * @param pl number of players playing the game
	 * @throws WrongPlayerNumber exception where a game cannot
	 * be played for a given number of players
	 */
	public void setPlayers(final int pl) throws WrongPlayerNumber{
		if (gameRules.isPlayerNumberGood(pl)) {
			this.numberPlayers=pl;
		}
		else {
			throw new WrongPlayerNumber();
		}
	}
	
	/**
	 * @return number of players playing the game
	 */
	public int getNumberPlayers() {
		return numberPlayers;
	}
	
	/**
	 * Method to add a new player to the game
	 * @param p new Player instance to be add to game
	 * @see Player
	 */
	public void addPlayer(final Player p) {
		players.add(p);
	}
	
	/**
	 * @return an ArrayList of players playing the game
	 * @see Player
	 */
	public ArrayList<Player> getPlayerList() {
		return players;
	}
	
	/**
	 * @return number of players that have finished the game
	 */
	public int getNumberPlayersFinished() {
		return numberPlayersFinished;
	}
	
	/**
	 * A method to start the game, it chooses a random player
	 * and sets it to Move state
	 */
	public void start() {
		final Random random = new Random();
		final int playerToStart = random.nextInt(numberPlayers) + 1;
		players.get(playerToStart-1).goNextState();
	}
	
	/**
	 * A method to check if a move is correct in terms of
	 * rules of the game.
	 * @param xStart coordinate x of a starting point
	 * @param yStart coordinate y of a starting point
	 * @param xEnd coordinate x of a destination point
	 * @param yEnd coordinate y of a destination point
	 * @param playerId id of a player making the move
	 * @return true if a move is good and made by a player
	 * that is allowed to move
	 */
	public boolean move (final int xStart, final int yStart, final int xEnd, final int yEnd, final int playerId) {
		if(players.get(playerId-1).getState() instanceof PlayerStateMove) {
			if(gameRules.isMoveGood(xStart, yStart, xEnd, yEnd, playerId, numberPlayers)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that determines if a player has ended the game,
	 * according to rules
	 * @param playerId id of a player to check
	 * @return true if the player has ended the game, otherwise false
	 * @see GameRules
	 */
	public boolean hasPlayerEnded(final int playerId) {
		if(gameRules.hasEnded(numberPlayers, playerId)) {
			numberPlayersFinished++;
			return true;
		}
		return false;			
	}
	
	/**
	 * Method that sets a specific player to WinState
	 * (used when player has just finished the game, to 
	 * exclude him from playing players.
	 * @param playerId id of a player who has just won
	 */
	public void setWin(final int playerId) {
		players.get(playerId-1).goWinState();
	}
	
	/**
	 * Method used to determine and set a next player to move.
	 * It looks for the next player that has not won to switch
	 * his state into MoveState. If there are no players left,
	 * it sets the remaining player to WinState (end of game).
	 * @param previousId id of a player that has just made a move
	 */
	public void nextToMove(final int previousId) {
		players.get(previousId-1).goNextState();
		int i = (previousId + 1) % numberPlayers;
		if (i == 0) {
			i = numberPlayers;
		}
		while (i != previousId) {
			if(players.get(i-1).getState() instanceof PlayerStateWait) {
				if(numberPlayers - numberPlayersFinished == 1) {
					players.get(i-1).goWinState();
					players.get(i-1).sendEndInfo(++numberPlayersFinished);
					System.out.println("THE END OF GAME");
				}
				players.get(i-1).goNextState();
				break;
			}
			i = (i + 1) % numberPlayers;
			if (i == 0) {
				i = numberPlayers;
			}
		}	
	}
	
	/**
	 * Class extending exception to specify an exception of wrong player number
	 * @author Marek Świergoń
	 *
	 */
	class WrongPlayerNumber extends Exception {

		private static final long serialVersionUID = 1L;
	}

}
