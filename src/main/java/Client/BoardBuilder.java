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
    private Board board;
    private int playerId;
    private Dimension windowDimension;
    private int fieldDistance;
    private int emptyFieldDistanceCorrection;
    private Dimension emptyFieldDimension;
    private Dimension nonEmptyFieldDimension;
    private ArrayList<ArrayList<Integer>> layout;

    public BoardBuilder(Dimension windowDimension) {
        this.windowDimension = windowDimension;

        board = new Board();
        rows = 17;
        columns = 13;
        layout = new ArrayList<>();
        fieldDistance = windowDimension.width / rows;
        nonEmptyFieldDimension = new Dimension(windowDimension.width / (2 * rows),
                windowDimension.height / (2 * rows));
        setLayout(2);

        emptyFieldDistanceCorrection = nonEmptyFieldDimension.width / 2;
        emptyFieldDimension = new Dimension(windowDimension.width / (2 * rows) / 2,
                windowDimension.height / (2 * rows) / 2);
    }

    public Board getBoard() {
        for (int i = 0; i < rows; i++) {
            board.fieldArray.add(new ArrayList<>());
            for (int j = 0; j < columns; j++) {
                if (layout.get(i).get(j) == 0) {
                    board.fieldArray.get(i).add(new EmptyField(0,
                            new Point2D.Float(fieldDistance * j + (i % 2 == 1 ?
                                    fieldDistance / 2 : 0) + emptyFieldDistanceCorrection,
                                    fieldDistance * i + emptyFieldDistanceCorrection),
                            emptyFieldDimension));
                } else if (layout.get(i).get(j) == 1) {
                    board.fieldArray.get(i).add(new PlayerField(1,
                            new Point2D.Float(fieldDistance * j + (i % 2 == 1 ?
                                    fieldDistance / 2 : 0), fieldDistance * i),
                            nonEmptyFieldDimension));
                } else if (layout.get(i).get(j) == 2) {
                    board.fieldArray.get(i).add(new PlayerField(2,
                            new Point2D.Float(fieldDistance * j + (i % 2 == 1 ?
                                    fieldDistance / 2 : 0), fieldDistance * i),
                            nonEmptyFieldDimension));
                }
            }
        }
        return board;
    }


    private void setLayout(int playerCount) {
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
