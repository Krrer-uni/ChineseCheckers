package Server;

/**
 * A simple class to implement Factory pattern for easy new GameRules creation
 * That way it is easier to change GameRules, 
 * as they all implement the same interface,
 * so it doesn't have to be hard-coded. 
 * 
 * @author Marek Świergoń
 *
 */
public class GameRulesFactory {

	/**
	 * Method that is making new GameRules instances
	 * @param ruleName specific name of rules to be created
	 * @param playerNumber number of players willing to play
	 * @return new GameRules instance (could be many different rules)
	 */
	public GameRules getGameRules(final String ruleName, final int playerNumber) {
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
