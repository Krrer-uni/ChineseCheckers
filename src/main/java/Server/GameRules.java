package Server;


public interface GameRules {	
	public boolean isPlayerNumberGood (int playerNumber);
	public int [][] availableMoves (int start_x, int start_y);
	public boolean isMoveGood (int start_x, int start_y, int end_x, int end_y);
	public boolean hasEnded(int playerCount, int playerId);
}
