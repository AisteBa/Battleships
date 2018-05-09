package battleships.data;

public class Coordinate {

    private String column;
    private Integer row;
    private String marking;

    public Coordinate(String column, Integer row, String marking) {
        this.column = column;
        this.row = row;
        this.marking = marking;
    }

    public Integer getColumnAsInt() {
        return GameConstants.COLUMNS.indexOf(column);
    }

    public String getRowAsString(){
        return Integer.toString(row);
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getRow() {
        return row;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public static Coordinate convertInputToCoordinate(String input, String marking) {
        String column = input.substring(0, 1);
        String row = input.substring(1, 2);
        return new Coordinate(column, Integer.valueOf(row), marking);
    }

    public static Coordinate fromIndex(int index, String marking){
        int row = index % 10;
        String col = GameConstants.COLUMNS.get(index / 10);

        return new Coordinate(col, row, marking);
    }

    public static int convertCoordinateToIndex(Coordinate coordinate){
        return GameConstants.COLUMNS.indexOf(coordinate.getColumn()) + coordinate.getRow() * 10;
    }

    public static String stepColumn(String column, int step) {
        return GameConstants.COLUMNS.get(GameConstants.COLUMNS.indexOf(column)+step);
    }


    @Override
    public String toString() {
        return "Coordinate{" +
                "column='" + column + '\'' +
                ", row=" + row +
                ", marking='" + marking + '\'' +
                '}';
    }

}

