package Server;

/**
 * Class implementing PlayerState interface. 
 * Describes a state when a player needs to wait
 * for opponents to move.
 * @author Marek Świergoń
 *
 */
public class PlayerStateWait implements PlayerState{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PlayerState getState() {
		return this;
	}

	/**
	 * @return new PlayerStateMove instance
	 */
	@Override
	public PlayerState nextState() {
		return new PlayerStateMove();
	}
	
	/**
	 * @return new PlayerStateWin instance
	 */
	@Override
	public PlayerState winState() {
		return new PlayerStateWin();
	}
}
