package Server;

public class AllAllowedChineseCheckersRules implements GameRules{

	@Override
	public boolean playerCanMove(int playerId) {
		return true;
	}

	@Override
	public boolean isPlayerNumberGood(int playerNumber) {
		if(playerNumber == 2 || playerNumber == 3 || playerNumber == 4 || playerNumber == 6) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isMoveGood(int start_x, int start_y, int end_x, int end_y) {
		return true;
	}

}
