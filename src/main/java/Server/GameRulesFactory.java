package Server;

public class GameRulesFactory {
	
	public GameRules getGameRules(String ruleName, int playerNumber) {
		if(ruleName.equalsIgnoreCase("CHINESE CHECKERS")) {
			return new ChineseCheckersRules(playerNumber);
		}
		else if(ruleName.equalsIgnoreCase("ALL ALLOWED")) {
			return new AllAllowedChineseCheckersRules(playerNumber);
		}
		else
			return null;
	}
	
}
