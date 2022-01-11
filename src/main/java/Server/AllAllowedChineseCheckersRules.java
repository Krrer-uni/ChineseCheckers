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
 * @author Marek Świergoń
 *
 */
public class AllAllowedChineseCheckersRules implements GameRules {
	
	/**
	 * Board that will be used to check moves
	 */
	private transient Board board;
	
	/**
	 * Constructor for class AllAllowedChineseCheckersRules
	 * @param playerNumber number of players to play the game
	 */
	public AllAllowedChineseCheckersRules(final int playerNumber) {
		if(this.isPlayerNumberGood(playerNumber)) {
			final BoardBuilder boardBuilder = new ChineseCheckersBoardBuilder(new Dimension(100, 100));
	        boardBuilder.buildBoard(playerNumber);
	        board = boardBuilder.getBoard();
		}
	}
	
	/**
	 * Method to access board.
	 * @return instance of Board used on server
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ArrayList<FieldCords> availableMoves (final int startX, final int startY) {
		final ArrayList<FieldCords> tab = new ArrayList<>();
		for(final ArrayList<Field> fieldTab: board.getFieldArray()) {
			for(final Field field: fieldTab) {
				if(field instanceof PlayerField && field.getOwnerId()==0) {
					final int x = fieldTab.indexOf(field);
					final int y = board.getFieldArray().indexOf(fieldTab);
					tab.add(new FieldCords(x, y));
				}
			}
		}
		return tab;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPlayerNumberGood(final int playerNumber) {
		return playerNumber == 2 || playerNumber == 3 || playerNumber == 4 || playerNumber == 6;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMoveGood(final int startX, final int startY, final int endX, final int endY, final int playerId, final int playerCount) {
		final ArrayList<FieldCords> tab = this.availableMoves(startX, startY);
		for(final FieldCords point: tab) {
			System.out.println(point.getX() + "," + point.getY());
			if(point.getX() == endX && point.getY() == endY) {
				
				//here we found out that this move is ok, we save this move on a server-side board
				final Rectangle2D tempFrameSource = board.getFieldArray().get(startY).get(startX).getField().getFrame();
		        final Rectangle2D tempFrameTarget = board.getFieldArray().get(endY).get(endX).getField().getFrame();
		        board.getFieldArray().get(startY).get(startX).getField().setFrame(tempFrameTarget);
		        board.getFieldArray().get(endY).get(endX).getField().setFrame(tempFrameSource);

		        final Field tempField = board.getFieldArray().get(startY).get(startX);
		        board.getFieldArray().get(startY).set(startX, board.getFieldArray().get(endY).get(endX));
		        board.getFieldArray().get(endY).set(endX, tempField);
				return true;
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasEnded(final int playerCount, final int playerId) {
		return false;
	}

}
