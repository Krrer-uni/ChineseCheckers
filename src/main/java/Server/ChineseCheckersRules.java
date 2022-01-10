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
	private Board board;
	
	public ChineseCheckersRules(int playerNumber) {
		if(this.isPlayerNumberGood(playerNumber)) {
			BoardBuilder boardBuilder = new ChineseCheckersBoardBuilder(new Dimension(500, 500));
	        boardBuilder.buildBoard(playerNumber);
	        board = boardBuilder.getBoard();
		}
	}
	
	public Board getBoard() {
		return board;
	}
	
	@Override
	public int [][] availableMoves (int start_x, int start_y) {
		int[][] tab = new int[1000][2];
		int i = 0;
		for(ArrayList<Field> field_tab: board.getFieldArray()) {
			for(Field field: field_tab) {
				if(field instanceof PlayerField && field.getOwnerId()==0) {
					int x = field_tab.indexOf(field);
					int y = board.getFieldArray().indexOf(field_tab);
					if((x == start_x+1 || x==start_x-1) && y==start_y ) {
						tab[i][0] = x;
						tab[i][1] = y;
						i++;
					}
					else if(y==start_y+1) {
						if( (start_y%2 == 1) && (x == start_x || x == start_x+1)) {
							tab[i][0] = x;
							tab[i][1] = y;
							i++;
						}
						if( (start_y%2 == 0) && (x == start_x-1 || x == start_x)) {
							tab[i][0] = x;
							tab[i][1] = y;
							i++;
						}
					}
					else if(y==start_y-1) {
						if( (start_y%2 == 1) && (x == start_x || x == start_x+1)) {
							tab[i][0] = x;
							tab[i][1] = y;
							i++;
						}
						if( (start_y%2 == 0) && (x == start_x-1 || x == start_x)) {
							tab[i][0] = x;
							tab[i][1] = y;
							i++;
						}
					}
				}
			}				
		}
		tab[i][0] = -1;
		tab[i][1] = -1;
		tab = checkCombo(tab, start_x, start_y, 0, 1);
		return tab;
	}
	
	public boolean hasThisMove(int [][] tab, int x, int y, int size) {
		for (int i = 0 ; i < size; i++) {
			if(tab[i][0] == x && tab[i][1] == y) {
				return true;
			}
		}
		return false;
	}
	
	public int [][] checkCombo(int [][] tab, int start_x, int start_y, int banned_dir, int depth) {
		/*
		 * DOWN_LEFT -> banned_dir = 1
		 * LEFT -> banned_dir = 2
		 * UP_LEFT -> banned_dir = 3
		 * UP_RIGHT -> banned_dir = 4
		 * RIGHT -> banned_dir = 5
		 * DOWN_RIGHT -> banned_dir = 6
		 */
		//if number of jumps is more than 350, 
		//end combo (longest jumps are shorter than that, proven using algorithms) - to prevent loops
		if(depth > 350) 
			return tab;
		boolean inif = false;
		int i;
		for (i = 0; i < tab.length; i++) {
			if (tab[i][0] == -1) {
			  break;
		  }
		}
		ArrayList<ArrayList<Field>> tab_pom= board.getFieldArray();
		//RIGHT JUMP
		if( banned_dir != 5 && (start_x < tab_pom.get(start_y).size() - 2) && 
				(tab_pom.get(start_y).get(start_x+1) instanceof PlayerField) && (tab_pom.get(start_y).get(start_x+1).getOwnerId() > 0) && 
				(tab_pom.get(start_y).get(start_x+2) instanceof PlayerField) && (tab_pom.get(start_y).get(start_x+2).getOwnerId() == 0) ) {
			if(hasThisMove(tab, start_x+2, start_y, i)) {
				
			}
			else {
				tab[i][0] = start_x+2;
				tab[i][1] = start_y;
				i++;
				tab[i][0] = -1;
				tab[i][1] = -1;
				inif = true;
				tab = checkCombo(tab, start_x+2, start_y, 2, ++depth);
			}
							
		}
		if(inif) {
			for (i = 0; i < tab.length; i++) {
				if (tab[i][0] == -1) {
					break;
				}
			}
		}
		inif = false;
		//LEFT JUMP
		if( banned_dir != 2 && start_x - 1 > 0 && 
				tab_pom.get(start_y).get(start_x-1) instanceof PlayerField && tab_pom.get(start_y).get(start_x-1).getOwnerId() > 0 &&
				tab_pom.get(start_y).get(start_x-2) instanceof PlayerField && tab_pom.get(start_y).get(start_x-2).getOwnerId() == 0) {
			if(hasThisMove(tab, start_x-2, start_y, i)) {
				
			}
			else {
				tab[i][0] = start_x-2;
				tab[i][1] = start_y;
				i++;
				tab[i][0] = -1;
				tab[i][1] = -1;
				inif = true;
				tab = checkCombo(tab, start_x-2, start_y, 5, ++depth);
			}
		}
		if(inif) {
			for (i = 0; i < tab.length; i++) {
				if (tab[i][0] == -1) {
					break;
				}
			}
		}
		inif = false;
		//DOWN-RIGHT JUMP
		if( banned_dir != 6 && start_x+1 < tab_pom.get(start_y).size() && start_y+2 < tab_pom.size()) {
			if( start_y%2 == 0 ) {
				if(tab_pom.get(start_y+1).get(start_x) instanceof PlayerField && tab_pom.get(start_y+1).get(start_x).getOwnerId() > 0 &&
						tab_pom.get(start_y+2).get(start_x+1) instanceof PlayerField && tab_pom.get(start_y+2).get(start_x+1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x+1, start_y+2, i)) {
						
					}
					else {
						tab[i][0] = start_x+1;
						tab[i][1] = start_y+2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x+1, start_y+2, 3, ++depth);
					}		
				}
			}
			else if(start_y%2 == 1) {
				if(tab_pom.get(start_y+1).get(start_x+1) instanceof PlayerField && tab_pom.get(start_y+1).get(start_x+1).getOwnerId() > 0 &&
						tab_pom.get(start_y+2).get(start_x+1) instanceof PlayerField && tab_pom.get(start_y+2).get(start_x+1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x+1, start_y+2, i)) {
						
					}
					else {
						tab[i][0] = start_x+1;
						tab[i][1] = start_y+2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x+1, start_y+2, 3, ++depth);
					}
				}
			}		
		}
		if(inif) {
			for (i = 0; i < tab.length; i++) {
				if (tab[i][0] == -1) {
					break;
				}
			}
		}
		inif = false;
		//DOWN-LEFT JUMP
		if( banned_dir != 1 && start_x > 0 && start_y+2 < tab_pom.size()) {
			if( start_y%2 == 0 ) {
				if(tab_pom.get(start_y+1).get(start_x-1) instanceof PlayerField && tab_pom.get(start_y+1).get(start_x-1).getOwnerId() > 0 &&
						tab_pom.get(start_y+2).get(start_x-1) instanceof PlayerField && tab_pom.get(start_y+2).get(start_x-1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x-1, start_y+2, i)) {
						
					}
					else {
						tab[i][0] = start_x-1;
						tab[i][1] = start_y+2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x-1, start_y+2, 4, ++depth);
					}
				}
			}
			else if(start_y%2 == 1) {
				if(tab_pom.get(start_y+1).get(start_x) instanceof PlayerField && tab_pom.get(start_y+1).get(start_x).getOwnerId() > 0 &&
						tab_pom.get(start_y+2).get(start_x-1) instanceof PlayerField && tab_pom.get(start_y+2).get(start_x-1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x-1, start_y+2, i)) {
						
					}
					else {
						tab[i][0] = start_x-1;
						tab[i][1] = start_y+2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x-1, start_y+2, 4, ++depth);	
					}
				}
			}		
		}
		if(inif) {
			for (i = 0; i < tab.length; i++) {
				if (tab[i][0] == -1) {
					break;
				}
			}
		}
		inif = false;
		//UP-RIGHT JUMP
		if( banned_dir != 4 && start_x+1 < tab_pom.get(start_y).size() && start_y-1 > 0) {
			if( start_y%2 == 0 ) {
				if(tab_pom.get(start_y-1).get(start_x) instanceof PlayerField && tab_pom.get(start_y-1).get(start_x).getOwnerId() > 0 &&
						tab_pom.get(start_y-2).get(start_x+1) instanceof PlayerField && tab_pom.get(start_y-2).get(start_x+1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x+1, start_y-2, i)) {
						
					}
					else {
						tab[i][0] = start_x+1;
						tab[i][1] = start_y-2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x+1, start_y-2, 1, ++depth);	
					}
				}
			}
			else if(start_y%2 == 1) {
				if(tab_pom.get(start_y-1).get(start_x+1) instanceof PlayerField && tab_pom.get(start_y-1).get(start_x+1).getOwnerId() > 0 &&
						tab_pom.get(start_y-2).get(start_x+1) instanceof PlayerField && tab_pom.get(start_y-2).get(start_x+1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x+1, start_y-2, i)) {
						
					}
					else {
						tab[i][0] = start_x+1;
						tab[i][1] = start_y-2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x+1, start_y-2, 1, ++depth);
					}
				}
			}		
		}
		if(inif) {
			for (i = 0; i < tab.length; i++) {
				if (tab[i][0] == -1) {
					break;
				}
			}
		}
		inif = false;
		//UP-LEFT JUMP
		if( banned_dir != 3 && start_x > 0 && start_y-1 > 0) {
			if( start_y%2 == 0 ) {
				if(tab_pom.get(start_y-1).get(start_x-1) instanceof PlayerField && tab_pom.get(start_y-1).get(start_x-1).getOwnerId() > 0 &&
						tab_pom.get(start_y-2).get(start_x-1) instanceof PlayerField && tab_pom.get(start_y-2).get(start_x-1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x-1, start_y-2, i)) {
						
					}
					else {
						tab[i][0] = start_x-1;
						tab[i][1] = start_y-2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x-1, start_y-2, 6, ++depth);	
					}
				}
			}
			else if(start_y%2 == 1) {
				if(tab_pom.get(start_y-1).get(start_x) instanceof PlayerField && tab_pom.get(start_y-1).get(start_x).getOwnerId() > 0 &&
						tab_pom.get(start_y-2).get(start_x-1) instanceof PlayerField && tab_pom.get(start_y-2).get(start_x-1).getOwnerId() == 0) {
					if(hasThisMove(tab, start_x-1, start_y-2, i)) {
						
					}
					else {
						tab[i][0] = start_x-1;
						tab[i][1] = start_y-2;
						i++;
						tab[i][0] = -1;
						tab[i][1] = -1;
						inif = true;
						tab = checkCombo(tab, start_x-1, start_y-2, 6, ++depth);
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
	public boolean isMoveGood(int start_x, int start_y, int end_x, int end_y, int playerId, int playerCount) {
		if ( isInWinningTriangle(start_x, start_y, end_x, end_y, playerId, playerCount ) ) {
			int [][] tab = this.availableMoves(start_x, start_y);
			int i = 0;
			while(tab[i][0] != -1) {
				System.out.printf("%d,%d\n", tab[i][0], tab[i][1]);
				if(tab[i][0] == end_x && tab[i][1] == end_y) {
					Rectangle2D tempFrameSource = board.getFieldArray().get(start_y).get(start_x).getField().getFrame();
			        Rectangle2D tempFrameTarget = board.getFieldArray().get(end_y).get(end_x).getField().getFrame();
			        board.getFieldArray().get(start_y).get(start_x).getField().setFrame(tempFrameTarget);
			        board.getFieldArray().get(end_y).get(end_x).getField().setFrame(tempFrameSource);
	
			        Field tempField = board.getFieldArray().get(start_y).get(start_x);
			        board.getFieldArray().get(start_y).set(start_x, board.getFieldArray().get(end_y).get(end_x));
			        board.getFieldArray().get(end_y).set(end_x, tempField);
					return true;
				}
				i++;
			}
			return false;
		}
		System.out.println("trojkat");
		return false;
	}
	
	public boolean isInWinningTriangle(int start_x, int start_y, int end_x, int end_y, int playerId, int playerCount) {
		WinningTriangle triangle = new WinningTriangle();
		for (FieldCords point : triangle.getTriangle(playerCount, playerId)) {
			int y_pom = point.getY();
			int x_pom = point.getX();
			if(start_x == x_pom && start_y == y_pom)	{
				for (FieldCords point_a : triangle.getTriangle(playerCount, playerId)) {
					int y = point_a.getY();
					int x = point_a.getX();
					if(end_x == x && end_y == y)
						return true;
				}
				return false;
			}
		}
		return true;
	}

	public boolean hasEnded(int playerCount, int playerId) {
		WinningTriangle triangle = new WinningTriangle();
		for (FieldCords point : triangle.getTriangle(playerCount, playerId)) {
			int y_pom = point.getY();
			int x_pom = point.getX();
			if (board.getFieldArray().get(y_pom).get(x_pom) instanceof PlayerField 
					&& board.getFieldArray().get(y_pom).get(x_pom).getOwnerId() == playerId) {
			
			}	
			else 
				return false;
		}
		return true;
	}
}

