package Server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChineseCheckersRulesTest {

	@Test
	public void nullBoardForWrongPlayerNumber() {
		ChineseCheckersRules rules = new ChineseCheckersRules(7);
		assertEquals(rules.getBoard(), null);
	}
	
	@Test
	public void hasBoardForGoodPlayerNumber() {
		ChineseCheckersRules rules = new ChineseCheckersRules(6);
		assertFalse(rules.getBoard() == null);
		rules = new ChineseCheckersRules(4);
		assertFalse(rules.getBoard() == null);
		rules = new ChineseCheckersRules(3);
		assertFalse(rules.getBoard() == null);
		rules = new ChineseCheckersRules(2);
		assertFalse(rules.getBoard() == null);
	}
	
	
	//simple moves that don't lead to victory - in fact all pawns go back to the beginning
	@Test
	public void simpleMoveTest() {
		ChineseCheckersRules rules = new ChineseCheckersRules(2);
		//down-left player one
		assertTrue(rules.isMoveGood(5, 3, 5, 4, 1, 2));
		//up-left player two
		assertTrue(rules.isMoveGood(6, 13, 6, 12, 2, 2));
		//down-right player one
		assertTrue(rules.isMoveGood(5, 4, 5, 5, 1, 2));
		//up-right player two
		assertTrue(rules.isMoveGood(6, 12, 6, 11, 2, 2));
		//left player one
		assertTrue(rules.isMoveGood(5, 5, 4, 5, 1, 2));
		//left player two
		assertTrue(rules.isMoveGood(6, 11, 5, 11, 2, 2));
		//right player one
		assertTrue(rules.isMoveGood(4, 5, 5, 5, 1, 2));
		//right player two
		assertTrue(rules.isMoveGood(5, 11, 6, 11, 2, 2));
		//up-left player one
		assertTrue(rules.isMoveGood(5, 5, 5, 4, 1, 2));
		//down-left player two
		assertTrue(rules.isMoveGood(6, 11, 6, 12, 2, 2));
		//up-right player one
		assertTrue(rules.isMoveGood(5, 4, 5, 3, 1, 2));
		//down-right player two
		assertTrue(rules.isMoveGood(6, 12, 6, 13, 2, 2));
		//have we won?
		assertFalse(rules.hasEnded(2, 1));
		assertFalse(rules.hasEnded(2, 2));
	}
	
	//some jumps to test
	@Test
	public void JumpsTest() {
		ChineseCheckersRules rules = new ChineseCheckersRules(2);
		//down-left jump
		assertTrue(rules.isMoveGood(5, 2, 4, 4, 1, 2));
		//up-left jump
		assertTrue(rules.isMoveGood(6, 14, 5, 12, 2, 2));
		//down-right jump
		assertTrue(rules.isMoveGood(6, 2, 7, 4, 1, 2));
		//up-right jump
		assertTrue(rules.isMoveGood(5, 14, 6, 12, 2, 2));
		//combo jump : down-left + right
		assertTrue(rules.isMoveGood(7, 2, 8, 4, 1, 2));
		//left jump
		assertTrue(rules.isMoveGood(6, 12, 4, 12, 2, 2));
		//combo jump : down-left + down-right
		assertTrue(rules.isMoveGood(6, 0, 6, 4, 1, 2));
		//up-left jump
		assertTrue(rules.isMoveGood(7, 14, 6, 12, 2, 2));
		//combo jump up-leftx2 + down-left
		assertTrue(rules.isMoveGood(8, 4, 5, 2, 1, 2));
		//up-left jump
		assertTrue(rules.isMoveGood(6, 13, 5, 11, 2, 2));
		//combo jump up-right + down-right
		assertTrue(rules.isMoveGood(6, 4, 8, 4, 1, 2));
		//up-left jump
		assertTrue(rules.isMoveGood(6, 12, 5, 10, 2, 2));
		//down-left jump
		assertTrue(rules.isMoveGood(4, 3, 3, 5, 1, 2));
		//up-right move
		assertTrue(rules.isMoveGood(5, 10, 5, 9, 2, 2));
		//down-right jump
		assertTrue(rules.isMoveGood(7, 3, 8, 5, 1, 2));
		//up-left jump
		assertTrue(rules.isMoveGood(5, 13, 4, 11, 2, 2));
		//combo jump right + down-left
		assertTrue(rules.isMoveGood(5, 3, 6, 5, 1, 2));
		//up-left jump
		assertTrue(rules.isMoveGood(5, 12, 4, 10, 2, 2));
		//combo jump down-right + right
		assertTrue(rules.isMoveGood(6, 3, 9, 5, 1, 2));
		//up-right move
		assertTrue(rules.isMoveGood(4, 10, 4, 9, 2, 2));
		//down-left jump
		assertTrue(rules.isMoveGood(4, 4, 3, 6, 1, 2));
		//up-right move
		assertTrue(rules.isMoveGood(4, 9, 5, 8, 2, 2));
		//right move
		assertTrue(rules.isMoveGood(9, 5, 10, 5, 1, 2));
		//up-left move
		assertTrue(rules.isMoveGood(5, 11, 5, 10, 2, 2));
		//right move
		assertTrue(rules.isMoveGood(10, 5, 11, 5, 1, 2));
		//up-left move
		assertTrue(rules.isMoveGood(5, 10, 4, 9, 2, 2));
		//left move
		assertTrue(rules.isMoveGood(3, 5, 2, 5, 1, 2));
		//GRANDE FINALE - JUMP WITH INFINITE LOOP (DEPTH CONDITION USED) (up-right jump)
		assertTrue(rules.isMoveGood(4, 12, 5, 10, 2, 2));
	}
		//additional cases that have been missed
	@Test
	public void MoreJumpsTest() {
		ChineseCheckersRules rules = new ChineseCheckersRules(2);
		assertTrue(rules.isMoveGood(6, 14, 7, 12, 2, 2));
		assertTrue(rules.isMoveGood(7, 3, 7, 4, 1, 2));
		assertTrue(rules.isMoveGood(6, 13, 7, 11, 2, 2));
	}
}
