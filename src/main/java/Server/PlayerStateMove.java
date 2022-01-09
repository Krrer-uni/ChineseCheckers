package Server;

public class PlayerStateMove implements PlayerState{

	@Override
	public PlayerState getState() {
		return this;
	}

	@Override
	public PlayerState nextState() {
		return new PlayerStateWait();
	}
	
	public PlayerState winState() {
		return new PlayerStateWin();
	}
}
