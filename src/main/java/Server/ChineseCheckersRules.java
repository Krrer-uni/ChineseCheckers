package Server;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Client.Board;
import Client.BoardBuilder;
import Client.Field;
import Client.PlayerField;

public class ChineseCheckersRules implements GameRules {
	Board board;
	
	public ChineseCheckersRules(int playerNumber) {
		if(this.isPlayerNumberGood(playerNumber)) {
			BoardBuilder boardBuilder = new BoardBuilder(new Dimension(100, 100));
	        boardBuilder.setLayout(playerNumber);
	        board = boardBuilder.getBoard();
		}
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
					}
					else if(y==start_y+1 || y==start_y-1) {
						tab[i][0] = x;
						tab[i][1] = y;
					}
					i++;
				}
			}
		}
		tab[i][0] = -1;
		tab[i][1] = -1;
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
	public boolean isMoveGood(int start_x, int start_y, int end_x, int end_y) {
		int [][] tab = this.availableMoves(start_x, start_y);
		int i = 0;
		while(tab[i][0] != -1) {
			System.out.printf("%d-%d,%d-%d\n", tab[i][0], end_x, tab[i][1], end_y);
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
	
	public void checkCombo() {
	  
	}
}