package Server;

/**
 * Class implementing PlayerState interface. 
 * Describes a state when a player has ended (possibly won) the game.
 * @author Marek Świergoń
 *
 */
public class PlayerStateWin implements PlayerState{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlayerState getState() {
		return this;
	}

	/**
	 * @return new PlayerStateWin instance
	 */
	@Override
	public PlayerState nextState() {
		return new PlayerStateWin();
	}

	/**
	 * @return current PlayerStateWin instance
	 */
	@Override
	public PlayerState winState() {
		return this;
	}

}
