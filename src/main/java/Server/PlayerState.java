package Server;

public interface PlayerState {
	PlayerState getState();
	PlayerState nextState();
	PlayerState winState();
}
