package Client;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * builder of boards in chinesecheckers
 */
public class ChineseCheckersBoardBuilder implements BoardBuilder {
    /**
     * distance between fields on board
     */
    private final int fieldDistance;
    /**
     * dimension of Fields
     */
    private final Dimension FieldDimensions;
    /**
     * offset for all fields in x dim
     */
    private final int xBoardOffset;
    /**
     * offset for all fields in y dim
     */
    private final int yBoardOffset;
    /**
     * number of rows of the board
     */
    int rows;
    /**
     * number of columns of the board
     */
    int columns;
    /**
     * 2dim arrayList that stores layout used in building new boards.
     * each number corresponds to different type of field:
     * -1 blank space
     * 0 empty field
     * 1-6 field that belongs to player with corresponding id
     */
    private ArrayList<ArrayList<Integer>> layout;

    /**
     * Constructor set values to declared variables
     * It calculates correct spacing and size of fields
     * depending on provided window dimensions
     *
     * @param windowDimension window dimension of frame in which board would exist
     */
    public ChineseCheckersBoardBuilder(Dimension windowDimension) {
        rows = 17;
        columns = 13;
        layout = new ArrayList<>();
        fieldDistance = windowDimension.height / (rows + 1);
        FieldDimensions = new Dimension(windowDimension.height / (3 * rows) * 2,
                windowDimension.height / (3 * rows) * 2);
        xBoardOffset = (rows - columns) / 2 * fieldDistance;
        yBoardOffset = 20;
    }

    /**
     * builder of the board according to the chosen layout
     *
     * @return created board
     */
    public Board getBoard() {
        Board board = new Board();
        for (int i = 0; i < rows; i++) {
            board.fieldArray.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                int fieldDistanceX = fieldDistance * j + (i % 2 == 1 ?
                        fieldDistance / 2 : 0) + xBoardOffset;
                int fieldDistanceY = fieldDistance * i + yBoardOffset;
                if (layout.get(i).get(j) == -1) {
                    board.fieldArray.get(i).add(new EmptyField());
                } else if (layout.get(i).get(j) == 0) {
                    board.fieldArray.get(i).add(new PlayerField(0,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            FieldDimensions));
                } else if (layout.get(i).get(j) == 1) {
                    board.fieldArray.get(i).add(new PlayerField(1,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            FieldDimensions));
                } else if (layout.get(i).get(j) == 2) {
                    board.fieldArray.get(i).add(new PlayerField(2,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            FieldDimensions));
                } else if (layout.get(i).get(j) == 3) {
                    board.fieldArray.get(i).add(new PlayerField(3,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            FieldDimensions));
                } else if (layout.get(i).get(j) == 4) {
                    board.fieldArray.get(i).add(new PlayerField(4,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            FieldDimensions));
                } else if (layout.get(i).get(j) == 5) {
                    board.fieldArray.get(i).add(new PlayerField(5,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            FieldDimensions));
                } else if (layout.get(i).get(j) == 6) {
                    board.fieldArray.get(i).add(new PlayerField(6,
                            new Point2D.Float(fieldDistanceX, fieldDistanceY),
                            FieldDimensions));
                }
            }
        }
        return board;
    }

    /**
     * creates layout for board that is used in a game
     * according to number of players
     *
     * @param playerCount playercount for which the board should be built
     */
    public void buildBoard(int playerCount) {
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
        } else if (playerCount == 6) {
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
                    1, 1, 1, 1, 0, 0, 0, 0, 0, 5, 5, 5, 5
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    1, 1, 1, 0, 0, 0, 0, 0, 0, 5, 5, 5, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 5, 5, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 5, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 2, 0, 0, 0, 0, 0, 0, 0, 0, 4, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, 2, 2, 0, 0, 0, 0, 0, 0, 0, 4, 4, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    2, 2, 2, 0, 0, 0, 0, 0, 0, 4, 4, 4, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    2, 2, 2, 2, 0, 0, 0, 0, 0, 4, 4, 4, 4
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, 3, 3, 3, 3, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 3, 3, 3, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, 3, 3, -1, -1, -1, -1, -1, -1
            )));
            layout.add(new ArrayList<>(Arrays.asList(
                    -1, -1, -1, -1, -1, -1, 3, -1, -1, -1, -1, -1, -1
            )));
        }
    }

    //reference image showing how the layout looks like
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
