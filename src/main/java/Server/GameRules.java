package Server;

public interface GameRules {
	public boolean playerCanMove (int playerId);
	public boolean isPlayerNumberGood (int playerNumber);
	public boolean isMoveGood (int start_x, int start_y, int end_x, int end_y);
}
