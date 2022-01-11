package Server;

import java.util.ArrayList;

public class WinningTriangle {

    private ArrayList<ArrayList<FieldCords>> bigList;

    public WinningTriangle(){
        bigList = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            bigList.add(new ArrayList<>());
        }
        bigList.get(1).add(new FieldCords(6,0));
        bigList.get(1).add(new FieldCords(6,1));
        bigList.get(1).add(new FieldCords(5,1));
        bigList.get(1).add(new FieldCords(5,2));
        bigList.get(1).add(new FieldCords(6,2));
        bigList.get(1).add(new FieldCords(7,2));
        bigList.get(1).add(new FieldCords(7,3));
        bigList.get(1).add(new FieldCords(6,3));
        bigList.get(1).add(new FieldCords(5,3));
        bigList.get(1).add(new FieldCords(4,3));

        bigList.get(2).add(new FieldCords(10,7));
        bigList.get(2).add(new FieldCords(10,6));
        bigList.get(2).add(new FieldCords(9,5));
        bigList.get(2).add(new FieldCords(9,4));
        bigList.get(2).add(new FieldCords(11,6));
        bigList.get(2).add(new FieldCords(10,5));
        bigList.get(2).add(new FieldCords(10,4));
        bigList.get(2).add(new FieldCords(11,4));
        bigList.get(2).add(new FieldCords(11,5));
        bigList.get(2).add(new FieldCords(12,4));

        bigList.get(3).add(new FieldCords(12,12));
        bigList.get(3).add(new FieldCords(11,11));
        bigList.get(3).add(new FieldCords(11,12));
        bigList.get(3).add(new FieldCords(11,10));
        bigList.get(3).add(new FieldCords(10,11));
        bigList.get(3).add(new FieldCords(10,12));
        bigList.get(3).add(new FieldCords(10,9));
        bigList.get(3).add(new FieldCords(10,10));
        bigList.get(3).add(new FieldCords(9,11));
        bigList.get(3).add(new FieldCords(9,12));

        bigList.get(4).add(new FieldCords(7,13));
        bigList.get(4).add(new FieldCords(6,13));
        bigList.get(4).add(new FieldCords(5,13));
        bigList.get(4).add(new FieldCords(4,13));
        bigList.get(4).add(new FieldCords(7,14));
        bigList.get(4).add(new FieldCords(6,14));
        bigList.get(4).add(new FieldCords(5,14));
        bigList.get(4).add(new FieldCords(6,15));
        bigList.get(4).add(new FieldCords(5,15));
        bigList.get(4).add(new FieldCords(6,16));

        bigList.get(5).add(new FieldCords(1,9));
        bigList.get(5).add(new FieldCords(2,10));
        bigList.get(5).add(new FieldCords(2,11));
        bigList.get(5).add(new FieldCords(3,12));
        bigList.get(5).add(new FieldCords(2,12));
        bigList.get(5).add(new FieldCords(1,11));
        bigList.get(5).add(new FieldCords(1,10));
        bigList.get(5).add(new FieldCords(0,11));
        bigList.get(5).add(new FieldCords(1,12));
        bigList.get(5).add(new FieldCords(0,12));

        bigList.get(6).add(new FieldCords(0,4));
        bigList.get(6).add(new FieldCords(1,4));
        bigList.get(6).add(new FieldCords(0,5));
        bigList.get(6).add(new FieldCords(2,4));
        bigList.get(6).add(new FieldCords(1,5));
        bigList.get(6).add(new FieldCords(1,6));
        bigList.get(6).add(new FieldCords(3,4));
        bigList.get(6).add(new FieldCords(2,5));
        bigList.get(6).add(new FieldCords(2,6));
        bigList.get(6).add(new FieldCords(1,7));





    }

    public ArrayList<FieldCords> getTriangle(final int playerCount, final int playerId){
    	ArrayList<FieldCords> tab = new ArrayList<FieldCords>();
        if(playerCount == 2) {
            if(playerId == 1) {
            	tab = bigList.get(4);
            }
            else if(playerId == 2) {
            	tab = bigList.get(1);
            }
        } 
        else if(playerCount == 3) {
            if(playerId == 1) {
            	tab = bigList.get(3);
            }
            else if(playerId == 2) {
            	tab = bigList.get(1);
            }
            else if(playerId == 3) {
            	tab = bigList.get(5);
            }
        }
        else if(playerCount == 4) {
            if(playerId == 1) {
            	tab = bigList.get(3);
            }
            else if(playerId == 2) {
            	tab = bigList.get(2);
            }
            else if(playerId == 3) {
            	tab = bigList.get(6);
            }
            else if(playerId == 4) {
            	tab = bigList.get(5);
            }
        }
        else if(playerCount == 6) {
            if(playerId == 1) {
            	tab = bigList.get(3);
            }
            else if(playerId == 2) {
            	tab = bigList.get(2);
            }
            else if(playerId == 3) {
            	tab = bigList.get(1);
            }
            else if(playerId == 4) {
            	tab = bigList.get(6);
            }
            else if(playerId == 5) {
            	tab = bigList.get(5);
            }
            else if(playerId == 6) {
            	tab = bigList.get(4);
            }
        }
        return tab;
    }

}
