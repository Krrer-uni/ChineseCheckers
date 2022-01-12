package Client;

import java.util.ArrayList;

/**
 * board of chinesse checkers
 */
public class Board {
    /**
     * 2dim arraylist of fields, it stores state of the board
     */
    ArrayList<ArrayList<Field>> fieldArray;

    /**
     * basic constructor
     */
    public Board() {
        fieldArray = new ArrayList<>();
    }

    /**
     * fieldArray getter
     *
     * @return board as arraylist
     */
    public ArrayList<ArrayList<Field>> getFieldArray() {
        return fieldArray;
    }
}
