package Server;

public class PlayerStateWait implements PlayerState{

	@Override
	public PlayerState getState() {
		return this;
	}

	@Override
	public PlayerState nextState() {
		return new PlayerStateMove();
	}

	public PlayerState winState() {
		return new PlayerStateWin();
	}
}
