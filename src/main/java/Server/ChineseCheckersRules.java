package Server;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Client.Board;
import Client.BoardBuilder;
import Client.ChineseCheckersBoardBuilder;
import Client.Field;
import Client.PlayerField;

public class ChineseCheckersRules implements GameRules {
	private transient Board board;
	
	public ChineseCheckersRules(final int playerNumber) {
		if(this.isPlayerNumberGood(playerNumber)) {
			final BoardBuilder boardBuilder = new ChineseCheckersBoardBuilder(new Dimension(500, 500));
	        boardBuilder.buildBoard(playerNumber);
	        board = boardBuilder.getBoard();
		}
	}
	
	public Board getBoard() {
		return board;
	}
	
	@Override
	public ArrayList<FieldCords> availableMoves (final int startX, final int startY) {
		ArrayList<FieldCords> tab = new ArrayList<FieldCords>();
		for(ArrayList<Field> fieldTab: board.getFieldArray()) {
			for(Field field: fieldTab) {
				if(field instanceof PlayerField && field.getOwnerId()==0) {
					int x = fieldTab.indexOf(field);
					int y = board.getFieldArray().indexOf(fieldTab);
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
	
	public boolean hasThisMove(final ArrayList<FieldCords> tab, final int x, final int y) {
		for (int i = 0 ; i < tab.size(); i++) {
			if(tab.get(i).getX() == x && tab.get(i).getY() == y) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<FieldCords> checkCombo(ArrayList<FieldCords> tab, final int startX, final int startY, final int bannedDir, int depth) {
		/*
		 * DOWN_LEFT -> bannedDir = 1
		 * LEFT -> bannedDir = 2
		 * UP_LEFT -> bannedDir = 3
		 * UP_RIGHT -> bannedDir = 4
		 * RIGHT -> bannedDir = 5
		 * DOWN_RIGHT -> bannedDir = 6
		 */
		//if number of jumps is more than 350, 
		//end combo (longest jumps are shorter than that, proven using algorithms) - to prevent loops
		if(depth > 350) {
			return tab;
		}

		ArrayList<ArrayList<Field>> tabPom = board.getFieldArray();
		//RIGHT JUMP
		if( bannedDir != 5 && (startX < tabPom.get(startY).size() - 2) && 
				(tabPom.get(startY).get(startX+1) instanceof PlayerField) && (tabPom.get(startY).get(startX+1).getOwnerId() > 0) && 
				(tabPom.get(startY).get(startX+2) instanceof PlayerField) && (tabPom.get(startY).get(startX+2).getOwnerId() == 0) ) {
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
	public boolean isPlayerNumberGood(int playerNumber) {
		if(playerNumber == 2 || playerNumber == 3 || playerNumber == 4 || playerNumber == 6) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isMoveGood(final int startX, final int startY, final int endX, final int endY, final int playerId, final int playerCount) {
		if ( isInWinningTriangle(startX, startY, endX, endY, playerId, playerCount ) ) {
			ArrayList<FieldCords> tab = this.availableMoves(startX, startY);
			for(FieldCords point: tab) {
				System.out.printf("%d,%d\n", point.getX(), point.getY());
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
		System.out.println("trojkat");
		return false;
	}
	
	public boolean isInWinningTriangle(int startX, int startY, int endX, int endY, int playerId, int playerCount) {
		WinningTriangle triangle = new WinningTriangle();
		for (FieldCords point : triangle.getTriangle(playerCount, playerId)) {
			int yPom = point.getY();
			int xPom = point.getX();
			if(startX == xPom && startY == yPom)	{
				for (FieldCords pointPom : triangle.getTriangle(playerCount, playerId)) {
					int y = pointPom.getY();
					int x = pointPom.getX();
					if(endX == x && endY == y) {
						return true;
					}
				}
				return false;
			}
		}
		return true;
	}

	public boolean hasEnded(int playerCount, int playerId) {
		WinningTriangle triangle = new WinningTriangle();
		for (FieldCords point : triangle.getTriangle(playerCount, playerId)) {
			int yPom = point.getY();
			int xPom = point.getX();
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

