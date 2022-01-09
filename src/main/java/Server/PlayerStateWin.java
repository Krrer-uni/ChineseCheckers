package Server;

public class PlayerStateWin implements PlayerState{

	@Override
	public PlayerState getState() {
		return this;
	}

	@Override
	public PlayerState nextState() {
		return new PlayerStateWin();
	}

	@Override
	public PlayerState winState() {
		return this;
	}

}
