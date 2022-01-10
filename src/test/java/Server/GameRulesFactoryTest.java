package Server;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameRulesFactoryTest {

	@Test
	public void factoryMakesProperObjects() {
		GameRulesFactory factory = new GameRulesFactory();
		assertTrue(factory.getGameRules("Chinese Checkers", 6) instanceof ChineseCheckersRules);
		assertTrue(factory.getGameRules("All Allowed", 6) instanceof AllAllowedChineseCheckersRules);
		assertTrue(factory.getGameRules("???", 6) == null);
	}
}
