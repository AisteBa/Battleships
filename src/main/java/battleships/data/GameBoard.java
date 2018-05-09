package battleships.data;

import java.util.List;

public class GameBoard {

    private GameMap myGameTable;
    private GameMap opponentGameTable;

    public GameBoard() {
        this(new GameMap(), new GameMap());
    }

    public GameBoard(GameMap myGameTable, GameMap opponentGameTable) {
        this.myGameTable = myGameTable;
        this.opponentGameTable = opponentGameTable;
    }

    public GameMap getMyGameTable() {
        return myGameTable;
    }

    public void setMyGameTable(GameMap myGameTable) {
        this.myGameTable = myGameTable;
    }

    public GameMap getOpponentGameTable() {
        return opponentGameTable;
    }

    public void setOpponentGameTable(GameMap opponentGameTable) {
        this.opponentGameTable = opponentGameTable;
    }

    public void addMyShip(Ship ship) {
        this.myGameTable.addShip(ship);
    }

    public void addMyShips(List<Ship> ships) {
        this.myGameTable.addShips(ships);
    }

    public void addMyEvents(List<Event> events) {
        this.myGameTable.addEvents(events);
    }

    public void addOpponentEvent(List<Event> events) {
        this.opponentGameTable.addEvents(events);
    }

}
