package battleships.data;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private List<Coordinate> layout; //all blocks where is a ship
    private String direction;
    private Integer length;

    public Ship(List<Coordinate> layout, String direction, Integer length) {
        this.layout = layout;
        this.direction = direction;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public List<Coordinate> getLayout() {
        return layout;
    }

    public void setLayout(List<Coordinate> layout) {
        this.layout = layout;
    }

    public static String getShipsForServer(List<Ship> ships) {
        StringBuilder sb = new StringBuilder();

        for (Ship ship : ships) {
            Coordinate shipStartCoord = ship.getLayout().get(0);
            Coordinate shipLastCoord = ship.getLayout().get(ship.getLayout().size() -1);
            sb.append(shipStartCoord.getColumn()).append(shipStartCoord.getRow())
                    .append("-")
                .append(shipLastCoord.getColumn()).append(shipLastCoord.getRow())
                    .append("!");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public static Ship makeShip(Coordinate start, int length, String direction){
        return new Ship(fillShip(start, length, direction), direction, length);
    }

    public static List<Coordinate> fillShip(Coordinate start, int length, String direction) {
        List<Coordinate> shipLayout = new ArrayList<Coordinate>();
        shipLayout.add(start);
        if (length > 1){
            if (GameConstants.HORIZONTAL.equals(direction)) {
                for(int i=1; i<length; i++){
                    shipLayout.add(new Coordinate(Coordinate.stepColumn(start.getColumn(),i), start.getRow(), start.getMarking()));
                }
            } else if (GameConstants.VERTICAL.equals(direction)) {
                for(int i=1; i<length; i++){
                    shipLayout.add(new Coordinate(start.getColumn(), start.getRow()+i, start.getMarking()));
                }
            } else {
                throw new IllegalStateException("Bad ship");
            }
        }
        return shipLayout;
    }


    @Override
    public String toString() {
        return "Ship{" +
                "layout=" + layout +
                ", direction='" + direction + '\'' +
                ", length=" + length +
                '}';
    }
}

