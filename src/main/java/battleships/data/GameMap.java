package battleships.data;

import java.util.ArrayList;
import java.util.List;

public class GameMap {

    public static final int MAX_BLOCKS = 100;

    private List<Ship> ships = new ArrayList<Ship>();
    private List<Event> events = new ArrayList<Event>();
    private List<Coordinate> blocks = new ArrayList<Coordinate>();

    public GameMap(){
        this.blocks = emptyBlocks();
    }

    private List<Coordinate> emptyBlocks() {
        List<Coordinate> eBlocks = new ArrayList<Coordinate>();
        for (int i = 0; i < MAX_BLOCKS; i++) {
            eBlocks.add(Coordinate.fromIndex(i, GameConstants.EMPTY));
        }
        return eBlocks;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Coordinate> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Coordinate> blocks) {
        this.blocks = blocks;
    }

    public void addShip(Ship ship){
        this.ships.add(ship);
        this.replaceBlocks(ship.getLayout());
    }

    public void addShips(List<Ship> ships) {
        for (Ship ship : ships){
            this.addShip(ship);
        }
    }

    public void addEvent(Event event) {
        this.events.add(event);
        this.replaceBlock(event.getCoordinate());
    }

    public void addEvents(List<Event> events) {
        for (Event event : events){
            this.addEvent(event);
        }
    }

    private void replaceBlock(Coordinate coordinate){
        int indexToReplace = Coordinate.convertCoordinateToIndex(coordinate);
        this.blocks.remove(indexToReplace);
        this.blocks.add(indexToReplace, coordinate);
    }

    private void replaceBlocks(List<Coordinate> coordinates){
        for (Coordinate coordinate : coordinates){
            this.replaceBlock(coordinate);
        }
    }

//    public List<Coordinate> getBusyCoordinates(){
//        List<Coordinate> coordinates = new ArrayList<Coordinate>();
//        coordinates.addAll(getShipsCoordinates());
//        coordinates.addAll(getEventsCoordinates());
//        return coordinates;
//    }
//
//    public List<Coordinate> getShipsCoordinates(){
//        List<Coordinate> coordinates = new ArrayList<Coordinate>();
//        for (Ship ship : getShips()){
//            coordinates.addAll(ship.getLayout());
//        }
//        return coordinates;
//    }
//
//    public List<Coordinate> getEventsCoordinates(){
//        List<Coordinate> coordinates = new ArrayList<Coordinate>();
//        for (Event ship : getEvents()){
//            coordinates.add(ship.getCoordinate());
//        }
//        return coordinates;
//    }
}
