package Client;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class BoardFactory {

    private Board board;
    private int playerId;

    public BoardFactory(){
        board = new Board();
    }

    public Board getBoard(){
        for (int i = 0; i < 10; i++){
            board.fieldArray.add(new ArrayList<>());
            for(int j = 0; j < 10; j++){
                board.fieldArray.get(i).add(new PlayerField(1,new Point2D.Float(40*i + (j%2 == 0 ? 20 : 0),40*j), new Dimension(20,20)));
            }
        }
        return board;
    }
}
