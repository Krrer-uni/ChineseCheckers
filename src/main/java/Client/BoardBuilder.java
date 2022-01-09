package Client;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardBuilder {

    int rows;
    int columns;
    ArrayList<Integer> boardLayout;
    int playerCount;
    private int playerId;
    private Dimension windowDimension;
    private final int fieldDistance;
    private int emptyFieldDistanceCorrection;
    private Dimension emptyFieldDimension;
    private final Dimension nonEmptyFieldDimension;

    private final int xBoardOffset;
    private ArrayList<ArrayList<Integer>> layout;

    public BoardBuilder(Dimension windowDimension) {
        this.windowDimension = windowDimension;


        rows = 17;
        columns = 13;

        layout = new ArrayList<>();
        fieldDistance = windowDimension.height / (rows +1 ) ;

        nonEmptyFieldDimension = new Dimension(windowDimension.height / (2 * rows),
                windowDimension.height / (2 * rows));

        emptyFieldDistanceCorrection = nonEmptyFieldDimension.width / 2;

        emptyFieldDimension = new Dimension(windowDimension.height / (2 * rows) / 2,
                windowDimension.height / (2 * rows) / 2);

        xBoardOffset = (rows - columns)/2 * fieldDistance;
    }


    public Board getBoard() {
        Board board = new Board();
        for (int i = 0; i < rows; i++) {
            board.fieldArray.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                int fieldDistanceX = fieldDistance * j + (i % 2 == 1 ?
                        fieldDistance / 2 : 0) + xBoardOffset;
                int fieldDistanceY = fieldDistance * i + 20;
                if (layout.get(i).get(j) == -1) {
                    board.fieldArray.get(i).add(new EmptyField());
                } else if (layout.get(i).get(j) == 0) {
                    board.fieldArray.get(i).add(new PlayerField(0,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            nonEmptyFieldDimension));
                } else if (layout.get(i).get(j) == 1) {
                    board.fieldArray.get(i).add(new PlayerField(1,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            nonEmptyFieldDimension));
                } else if (layout.get(i).get(j) == 2) {
                    board.fieldArray.get(i).add(new PlayerField(2,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            nonEmptyFieldDimension));
                }else if (layout.get(i).get(j) == 3) {
                    board.fieldArray.get(i).add(new PlayerField(3,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            nonEmptyFieldDimension));
                }else if (layout.get(i).get(j) == 4) {
                    board.fieldArray.get(i).add(new PlayerField(4,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            nonEmptyFieldDimension));
                }else if (layout.get(i).get(j) == 5) {
                    board.fieldArray.get(i).add(new PlayerField(5,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            nonEmptyFieldDimension));
                }else if (layout.get(i).get(j) == 6) {
                    board.fieldArray.get(i).add(new PlayerField(6,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            nonEmptyFieldDimension));
                }
            }
        }
        return board;
    }


    public void setLayout(int playerCount) {
        layout = new ArrayList<>();
        if (playerCount == 2) {
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 1, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 1, 1, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 1, 1, 1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 1, 1, 1, 1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 2, 2, 2, 2, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 2, 2, 2, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 2, 2, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1
            )));
        } else if (playerCount == 3) {
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    1, 1, 1, 1, 0, 0, 0, 0, 0, 3, 3, 3, 3
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    1, 1, 1, 0, 0, 0, 0, 0, 0, 3, 3, 3, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 3, 3, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 2, 2, 2, 2, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 2, 2, 2, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 2, 2, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 2, -1, -1, -1, -1, -1, -1
            )));
        } else if (playerCount == 4) {
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    1, 1, 1, 1, 0, 0, 0, 0, 0, 4, 4, 4, 4
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    1, 1, 1, 0, 0, 0, 0, 0, 0, 4, 4, 4, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 4, 4, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 3, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 3, 3, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    2, 2, 2, 0, 0, 0, 0, 0, 0, 3, 3, 3, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    2, 2, 2, 2, 0, 0, 0, 0, 0, 3, 3, 3, 3
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1
            )));
        }  else if (playerCount == 6) {
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 6, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 6, 6, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 6, 6, 6, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 6, 6, 6, 6, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    1, 1, 1, 1, 0, 0, 0, 0, 0, 4, 4, 4, 4
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    1, 1, 1, 0, 0, 0, 0, 0, 0, 4, 4, 4, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 4, 4, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 3, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 3, 3, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    2, 2, 2, 0, 0, 0, 0, 0, 0, 3, 3, 3, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    2, 2, 2, 2, 0, 0, 0, 0, 0, 3, 3, 3, 3
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 5, 5, 5, 5, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 5, 5, 5, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 5, 5, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 5, -1, -1, -1, -1, -1, -1
            )));
        }
    }


    /*
     - - - - - - 1 - - - - - -
      - - - - - 1 1 - - - - - -
     - - - - - 1 1 1 - - - - -
      - - - - 1 1 1 1 - - - - -
     0 0 0 0 0 0 0 0 0 0 0 0 0
      0 0 0 0 0 0 0 0 0 0 0 0 -
     - 0 0 0 0 0 0 0 0 0 0 0 -
      - 0 0 0 0 0 0 0 0 0 0 - -
     - - 0 0 0 0 0 0 0 0 0 - -
      - 0 0 0 0 0 0 0 0 0 0 - -
     - 0 0 0 0 0 0 0 0 0 0 0 -
      0 0 0 0 0 0 0 0 0 0 0 0 -
     0 0 0 0 0 0 0 0 0 0 0 0 0
      - - - - 2 2 2 2 - - - - -
     - - - - - 2 2 2 - - - - -
      - - - - - 2 2 - - - - - -
     - - - - - - 2 - - - - - - -
     */

}
