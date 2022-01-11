package Server;

/**
 * Interface that has declarations of basic methods used by states, 
 * implementing State pattern.
 * @author swmar
 *
 */
public interface PlayerState {
	
	/**
	 * Get current state instance
	 * @return current state
	 */
	PlayerState getState();
	
	/**
	 * Get next state instance (to be assigned)
	 * @return next state 
	 */
	PlayerState nextState();
	
	/**
	 * Get instance of PlayerWinState, as all states can be changed to it
	 * @return PlayerWinState instance
	 */
	PlayerState winState();
}
