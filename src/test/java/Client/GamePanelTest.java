package Client;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GamePanelTest {
    @Test
    public void setPlayerIdTest(){
        GamePanel panel = new GamePanel(1000,1000);
        panel.setPlayerId(1);
        assertEquals(panel.getPlayerId(),1);
    }

    @Test
    public void setCurrentPlayersTest(){
        GamePanel panel = new GamePanel(1000,1000);
        panel.setCurrentPlayer(4);
        assertEquals(panel.getCurrentPlayer() ,4);
    }

    @Test
    public void gameFinishTest(){
        GamePanel panel = new GamePanel(1000,1000);
        panel.gameFinished(1);
        assertEquals(panel.getGameInfo().getText(), "YOU WON");
    }

    @Test
    public void updateBoardTest(){
        GamePanel panel = new GamePanel(1000,1000);
        panel.setBoardBuilderLayout(2);
        int id1 = panel.board.getFieldArray().get(1).get(6).getOwnerId();
        int id2 = panel.board.getFieldArray().get(14).get(6).getOwnerId();
        panel.updateBoard(1,6,14,6);
        assertEquals(id1 , panel.board.getFieldArray().get(14).get(6).getOwnerId());
        assertEquals(id2 , panel.board.getFieldArray().get(1).get(6).getOwnerId());
    }

    @Test
    public void setPlayerInfoTest(){

            GamePanel panel = new GamePanel(1000,1000);
            panel.setPlayerId(1);
            assertEquals(panel.getPlayerInfo().getText(),"You are a player 1\nYour color is blue");
    }

    @Test
    public void setMediatorTest(){
        GamePanel panel = new GamePanel(100,100);
        Client2ServerConnection con = null;
        ChineseCheckerMediator mediator = new ChineseCheckerMediator(con,panel);
        panel.setGameServerMediator(mediator);
    }


//    class MediatorMock implements GameServerMediator{
//
//
//        @Override
//        public void updateBoard(int rowSource, int columnSource, int rowTarget, int columnTarget) {
//
//        }
//
//        @Override
//        public void sendMove(int rowSource, int columnSource, int rowTarget, int columnTarget) {
//
//        }
//
//        @Override
//        public void gameInit(int playerCount, int playerId) {
//
//        }
//
//        @Override
//        public void setCurrentPlayer(int startingPlyer) {
//
//        }
//
//        @Override
//        public void sendSkip() {
//
//        }
//
//        @Override
//        public void gameFinished(int place) {
//
//        }
//    }
}
