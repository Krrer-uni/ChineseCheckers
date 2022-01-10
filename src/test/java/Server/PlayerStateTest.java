package Server;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerStateTest {

	@Test
	public void waitToMoveTest() {
		PlayerState state = new PlayerStateWait();
		state = state.nextState();
		assertTrue(state instanceof PlayerStateMove);
		assertTrue(state.getState() instanceof PlayerStateMove);
	}
	
	@Test
	public void moveToWaitTest() {
		PlayerState state = new PlayerStateMove();
		state = state.nextState();
		assertTrue(state instanceof PlayerStateWait);
		assertTrue(state.getState() instanceof PlayerStateWait);
	}
	
	@Test
	public void waitToWinTest() {
		PlayerState state = new PlayerStateWait();
		state = state.winState();
		assertTrue(state instanceof PlayerStateWin);
		assertTrue(state.getState() instanceof PlayerStateWin);
	}
	
	@Test
	public void moveToWinTest() {
		PlayerState state = new PlayerStateMove();
		state = state.winState();
		assertTrue(state instanceof PlayerStateWin);
		assertTrue(state.getState() instanceof PlayerStateWin);
	}
	
	@Test
	public void winIsAlwaysWinTest() {
		PlayerState state = new PlayerStateWin();
		state = state.winState();
		assertTrue(state instanceof PlayerStateWin);
		assertTrue(state.getState() instanceof PlayerStateWin);
		state = state.nextState();
		assertTrue(state instanceof PlayerStateWin);
		assertTrue(state.getState() instanceof PlayerStateWin);
	}
}
