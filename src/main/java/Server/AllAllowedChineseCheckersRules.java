package Server;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Client.Board;
import Client.BoardBuilder;
import Client.ChineseCheckersBoardBuilder;
import Client.Field;
import Client.PlayerField;

/**
 * Class that represents rules,
 * where each player can move at any free space on Board.
 * Simple GameRules implementation
 * @author swmar
 *
 */
public class AllAllowedChineseCheckersRules implements GameRules {
	private transient Board board;
	public AllAllowedChineseCheckersRules(int playerNumber) {
		if(this.isPlayerNumberGood(playerNumber)) {
			BoardBuilder boardBuilder = new ChineseCheckersBoardBuilder(new Dimension(100, 100));
	        boardBuilder.buildBoard(playerNumber);
	        board = boardBuilder.getBoard();
		}
	}
	
	public Board getBoard() {
		return board;
	}
	
	@Override
	public ArrayList<FieldCords> availableMoves (int startX, int startY) {
		ArrayList<FieldCords> tab = new ArrayList<FieldCords>();
		for(ArrayList<Field> fieldTab: board.getFieldArray()) {
			for(Field field: fieldTab) {
				if(field instanceof PlayerField && field.getOwnerId()==0) {
					int x = fieldTab.indexOf(field);
					int y = board.getFieldArray().indexOf(fieldTab);
					tab.add(new FieldCords(x, y));
				}
			}
		}
		return tab;
	}

	@Override
	public boolean isPlayerNumberGood(int playerNumber) {
		if(playerNumber == 2 || playerNumber == 3 || playerNumber == 4 || playerNumber == 6) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isMoveGood(int startX, int startY, int endX, int endY, int playerId, int playerCount) {
		ArrayList<FieldCords> tab = this.availableMoves(startX, startY);
		for(FieldCords point: tab) {
			System.out.println(point.getX() + "," + point.getY());
			if(point.getX() == endX && point.getY() == endY) {
				Rectangle2D tempFrameSource = board.getFieldArray().get(startY).get(startX).getField().getFrame();
		        Rectangle2D tempFrameTarget = board.getFieldArray().get(endY).get(endX).getField().getFrame();
		        board.getFieldArray().get(startY).get(startX).getField().setFrame(tempFrameTarget);
		        board.getFieldArray().get(endY).get(endX).getField().setFrame(tempFrameSource);

		        Field tempField = board.getFieldArray().get(startY).get(startX);
		        board.getFieldArray().get(startY).set(startX, board.getFieldArray().get(endY).get(endX));
		        board.getFieldArray().get(endY).set(endX, tempField);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasEnded(final int playerCount, final int playerId) {
		return false;
	}

}
