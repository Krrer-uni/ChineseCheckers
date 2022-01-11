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
 * Class that implements GameRules and describes standard
 * chineses checkers rules.
 * @author Marek Świergoń
 *
 */
public class ChineseCheckersRules implements GameRules {
	
	/**
	 * Board that will be used to check moves
	 */
	private transient Board board;
	
	/**
	 * Constructor for class ChineseCheckersRules
	 * @param playerNumber number of players to play the game
	 */
	public ChineseCheckersRules(final int playerNumber) {
		if(this.isPlayerNumberGood(playerNumber)) {
			final BoardBuilder boardBuilder = new ChineseCheckersBoardBuilder(new Dimension(500, 500));
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
	 * This method calculates available moves without jumps,
	 * only on neighboring tiles. Then it calls recursive method
	 * {@link #checkCombo(ArrayList, int, int, int, int)} to search
	 * for all possible jumps
	 */
	@Override
	public ArrayList<FieldCords> availableMoves (final int startX, final int startY) {
		ArrayList<FieldCords> tab = new ArrayList<>();
		for(final ArrayList<Field> fieldTab: board.getFieldArray()) {
			for(final Field field: fieldTab) {
				if(field instanceof PlayerField && field.getOwnerId()==0) {
					final int x = fieldTab.indexOf(field);
					final int y = board.getFieldArray().indexOf(fieldTab);
					if((x == startX+1 || x==startX-1) && y==startY ) {
						tab.add(new FieldCords(x, y));
					}
					else if(y==startY+1) {
						if( (startY%2 == 1) && (x == startX || x == startX+1)) {
							tab.add(new FieldCords(x, y));
						}
						if( (startY%2 == 0) && (x == startX-1 || x == startX)) {
							tab.add(new FieldCords(x, y));
						}
					}
					else if(y==startY-1) {
						if( (startY%2 == 1) && (x == startX || x == startX+1)) {
							tab.add(new FieldCords(x, y));
						}
						if( (startY%2 == 0) && (x == startX-1 || x == startX)) {
							tab.add(new FieldCords(x, y));
						}
					}
				}
			}				
		}
		tab = checkCombo(tab, startX, startY, 0, 1);
		return tab;
	}
	
	/**
	 * Method to check if in an ArrayList of available moves
	 * specific move already exists (to prevent looping)
	 * @see FieldCords
	 * @param tab ArrayList of coordinates of possible moves 
	 * @param x : x coordinate of a point
	 * @param y : y coordinate of a point
	 * @return
	 */
	public boolean hasThisMove(final ArrayList<FieldCords> tab, final int x, final int y) {
		for (final FieldCords point : tab) {
			if(point.getX() == x && point.getY() == y) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<FieldCords> checkCombo(ArrayList<FieldCords> tab, final int startX, final int startY, final int bannedDir, int depth) {
		/*	bannedDir is used to prevent simple looping 
		 * (not to check direction from which we went on)
		 * TYPES OF JUMPS: 
		 * 
		 * DOWN_LEFT -> bannedDir = 1
		 * LEFT -> bannedDir = 2
		 * UP_LEFT -> bannedDir = 3
		 * UP_RIGHT -> bannedDir = 4
		 * RIGHT -> bannedDir = 5
		 * DOWN_RIGHT -> bannedDir = 6
		 */
		
		//if number of jumps is more than 350, 
		//end combo (longest jumps are shorter than that, proven using algorithms)
		if(depth > 350) {
			return tab;
		}

		ArrayList<ArrayList<Field>> tabPom = board.getFieldArray();
		
		//RIGHT JUMP
		if( bannedDir != 5 && startX < tabPom.get(startY).size() - 2 && 
				tabPom.get(startY).get(startX+1) instanceof PlayerField && tabPom.get(startY).get(startX+1).getOwnerId() > 0 && 
				tabPom.get(startY).get(startX+2) instanceof PlayerField && tabPom.get(startY).get(startX+2).getOwnerId() == 0 ) {
			if(!hasThisMove(tab, startX+2, startY)) {
				tab.add(new FieldCords(startX+2, startY));
				tab = checkCombo(tab, startX+2, startY, 2, ++depth);
			}						
		}

		//LEFT JUMP
		if( bannedDir != 2 && startX - 1 > 0 && 
				tabPom.get(startY).get(startX-1) instanceof PlayerField && tabPom.get(startY).get(startX-1).getOwnerId() > 0 &&
				tabPom.get(startY).get(startX-2) instanceof PlayerField && tabPom.get(startY).get(startX-2).getOwnerId() == 0) {
			if(!hasThisMove(tab, startX-2, startY)) {
				tab.add(new FieldCords(startX-2, startY));
				tab = checkCombo(tab, startX-2, startY, 5, ++depth);
			}
		}

		//DOWN-RIGHT JUMP
		if( bannedDir != 6 && startX+1 < tabPom.get(startY).size() && startY+2 < tabPom.size()) {
			if( startY%2 == 0 ) {
				if(tabPom.get(startY+1).get(startX) instanceof PlayerField && tabPom.get(startY+1).get(startX).getOwnerId() > 0 &&
						tabPom.get(startY+2).get(startX+1) instanceof PlayerField && tabPom.get(startY+2).get(startX+1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX+1, startY+2)) {
						tab.add(new FieldCords(startX+1, startY+2));
						tab = checkCombo(tab, startX+1, startY+2, 3, ++depth);
					}		
				}
			}
			else {
				if(tabPom.get(startY+1).get(startX+1) instanceof PlayerField && tabPom.get(startY+1).get(startX+1).getOwnerId() > 0 &&
						tabPom.get(startY+2).get(startX+1) instanceof PlayerField && tabPom.get(startY+2).get(startX+1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX+1, startY+2)) {
						tab.add(new FieldCords(startX+1, startY+2));
						tab = checkCombo(tab, startX+1, startY+2, 3, ++depth);
					}
				}
			}		
		}

		//DOWN-LEFT JUMP
		if( bannedDir != 1 && startX > 0 && startY+2 < tabPom.size()) {
			if( startY%2 == 0 ) {
				if(tabPom.get(startY+1).get(startX-1) instanceof PlayerField && tabPom.get(startY+1).get(startX-1).getOwnerId() > 0 &&
						tabPom.get(startY+2).get(startX-1) instanceof PlayerField && tabPom.get(startY+2).get(startX-1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX-1, startY+2)) {
						tab.add(new FieldCords(startX-1, startY+2));
						tab = checkCombo(tab, startX-1, startY+2, 4, ++depth);
					}
				}
			}
			else {
				if(tabPom.get(startY+1).get(startX) instanceof PlayerField && tabPom.get(startY+1).get(startX).getOwnerId() > 0 &&
						tabPom.get(startY+2).get(startX-1) instanceof PlayerField && tabPom.get(startY+2).get(startX-1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX-1, startY+2)) {
						tab.add(new FieldCords(startX-1, startY+2));
						tab = checkCombo(tab, startX-1, startY+2, 4, ++depth);	
					}
				}
			}		
		}

		//UP-RIGHT JUMP
		if( bannedDir != 4 && startX+1 < tabPom.get(startY).size() && startY-1 > 0) {
			if( startY%2 == 0 ) {
				if(tabPom.get(startY-1).get(startX) instanceof PlayerField && tabPom.get(startY-1).get(startX).getOwnerId() > 0 &&
						tabPom.get(startY-2).get(startX+1) instanceof PlayerField && tabPom.get(startY-2).get(startX+1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX+1, startY-2)) {
						tab.add(new FieldCords(startX+1, startY-2));
						tab = checkCombo(tab, startX+1, startY-2, 1, ++depth);
					}
				}
			}
			else {
				if(tabPom.get(startY-1).get(startX+1) instanceof PlayerField && tabPom.get(startY-1).get(startX+1).getOwnerId() > 0 &&
						tabPom.get(startY-2).get(startX+1) instanceof PlayerField && tabPom.get(startY-2).get(startX+1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX+1, startY-2)) {
						tab.add(new FieldCords(startX+1, startY-2));
						tab = checkCombo(tab, startX+1, startY-2, 1, ++depth);
					}
				}
			}		
		}

		//UP-LEFT JUMP
		if( bannedDir != 3 && startX > 0 && startY-1 > 0) {
			if( startY%2 == 0 ) {
				if(tabPom.get(startY-1).get(startX-1) instanceof PlayerField && tabPom.get(startY-1).get(startX-1).getOwnerId() > 0 &&
						tabPom.get(startY-2).get(startX-1) instanceof PlayerField && tabPom.get(startY-2).get(startX-1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX-1, startY-2)) {
						tab.add(new FieldCords(startX-1, startY-2));
						tab = checkCombo(tab, startX-1, startY-2, 6, ++depth);	
					}
				}
			}
			else {
				if(tabPom.get(startY-1).get(startX) instanceof PlayerField && tabPom.get(startY-1).get(startX).getOwnerId() > 0 &&
						tabPom.get(startY-2).get(startX-1) instanceof PlayerField && tabPom.get(startY-2).get(startX-1).getOwnerId() == 0) {
					if(!hasThisMove(tab, startX-1, startY-2)) {
						tab.add(new FieldCords(startX-1, startY-2));
						tab = checkCombo(tab, startX-1, startY-2, 6, ++depth);
					}
				}
			}		
		}
		return tab;
		
	}

	@Override
	public boolean isPlayerNumberGood(final int playerNumber) {
		return playerNumber == 2 || playerNumber == 3 || playerNumber == 4 || playerNumber == 6;
	}

	@Override
	public boolean isMoveGood(final int startX, final int startY, final int endX, final int endY, final int playerId, final int playerCount) {
		if ( isInWinningTriangle(startX, startY, endX, endY, playerId, playerCount ) ) {
			final ArrayList<FieldCords> tab = this.availableMoves(startX, startY);
			for(final FieldCords point: tab) {
				//System.out.printf("%d,%d\n", point.getX(), point.getY());
				if(point.getX() == endX && point.getY() == endY) {
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
		System.out.println("Illegal move - moving out of winning triangle (player " + playerId + ")");
		return false;
	}
	
	/**
	 * Method to check if a potential move is contained within 
	 * a winning triangle of a player making move (or is completely outside it)
	 * @param startX coordinate x of a starting point
	 * @param startY coordinate y of a starting point
	 * @param endX coordinate x of a destination point
	 * @param endY coordinate y of a destination point
	 * @param playerId id of a player making the move
	 * @param playerCount number of players playing the game
	 * @return - true if a potential move is legal in terms of a rule that
	 * you can't move out of a winning triangle
	 * - false if a potential move is starting in a winning triangle and ending
	 * outside a plyer's winning triangle
	 */
	public boolean isInWinningTriangle(final int startX, final int startY, final int endX, final int endY, final int playerId, final int playerCount) {
		final WinningTriangle triangle = new WinningTriangle();
		for (final FieldCords point : triangle.getTriangle(playerCount, playerId)) {
			final int yPom = point.getY();
			final int xPom = point.getX();
			if(startX == xPom && startY == yPom)	{
				for (final FieldCords pointPom : triangle.getTriangle(playerCount, playerId)) {
					final int y = pointPom.getY();
					final int x = pointPom.getX();
					if(endX == x && endY == y) {
						return true;
					}
				}
				return false;
			}
		}
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasEnded(final int playerCount, final int playerId) {
		//here a condition to win is to have the opposite triangle
		//filled up with your pawns
		final WinningTriangle triangle = new WinningTriangle();
		for (final FieldCords point : triangle.getTriangle(playerCount, playerId)) {
			final int yPom = point.getY();
			final int xPom = point.getX();
			if (board.getFieldArray().get(yPom).get(xPom) instanceof PlayerField 
					&& board.getFieldArray().get(yPom).get(xPom).getOwnerId() == playerId) {
			
			}	
			else {
				return false;				
			}

		}
		return true;
	}
}

