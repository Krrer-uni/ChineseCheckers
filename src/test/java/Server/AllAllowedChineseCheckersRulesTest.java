package Server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import Client.Board;
import Client.Field;
import Client.PlayerField;

public class AllAllowedChineseCheckersRulesTest {
	
	@Test
	public void nullBoardForWrongPlayerNumber() {
		AllAllowedChineseCheckersRules rules = new AllAllowedChineseCheckersRules(7);
		assertEquals(rules.getBoard(), null);
	}
	
	@Test
	public void hasBoardForGoodPlayerNumber() {
		AllAllowedChineseCheckersRules rules = new AllAllowedChineseCheckersRules(6);
		assertTrue(rules.getBoard() instanceof Board);
		rules = new AllAllowedChineseCheckersRules(4);
		assertTrue(rules.getBoard() instanceof Board);
		rules = new AllAllowedChineseCheckersRules(3);
		assertTrue(rules.getBoard() instanceof Board);
		rules = new AllAllowedChineseCheckersRules(2);
		assertTrue(rules.getBoard() instanceof Board);
	}
	
	@Test
	public void canMoveAnywhereFree() {
		AllAllowedChineseCheckersRules rules = new AllAllowedChineseCheckersRules(2);
		for(ArrayList<Field> field_tab: rules.getBoard().getFieldArray()) {
			for(Field field: field_tab) {
				if(field instanceof PlayerField)
					assertEquals((field.getOwnerId()==0), 
							rules.isMoveGood(6, 0, field_tab.indexOf(field), rules.getBoard().getFieldArray().indexOf(field_tab), 1, 2)); 
			}
		}
	}
	
	@Test
	public void nooneEnds() {
		AllAllowedChineseCheckersRules rules = new AllAllowedChineseCheckersRules(2);
		assertFalse(rules.hasEnded(2, 1));
		assertFalse(rules.hasEnded(2, 2));
	}
}
