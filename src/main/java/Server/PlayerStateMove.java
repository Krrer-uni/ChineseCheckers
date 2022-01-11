package Server;

/**
 * Class implementing PlayerState interface. 
 * Describes a state when a player can move.
 * @author swmar
 *
 */
public class PlayerStateMove implements PlayerState{

	/**
	  * {@inheritDoc}
	  */
	@Override
	public PlayerState getState() {
		return this;
	}

	/**
	 * @return new PlayerStateWait instance
	 */
	@Override
	public PlayerState nextState() {
		return new PlayerStateWait();
	}
	
	/**
	 * @return new PlayerStateWin instance
	 */
	@Override
	public PlayerState winState() {
		return new PlayerStateWin();
	}
}
