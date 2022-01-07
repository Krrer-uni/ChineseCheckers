package Client;

import java.util.ArrayList;

public class Board {
    ArrayList<ArrayList<Field>> fieldArray;
    public Board(){
        fieldArray = new ArrayList<>();
    }
    
    public ArrayList<ArrayList<Field>> getFieldArray(){
    	return fieldArray;
    }
}
