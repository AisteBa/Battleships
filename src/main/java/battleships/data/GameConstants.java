package battleships.data;

import java.util.ArrayList;

public class GameConstants {

    //game board markers
    public static final String EMPTY = " ";
    public static final String SHIP = "S";
    public static final String SHIP_HIT = "X";
    public static final String EMPTY_HIT = "*";

    //ship directions
    public static final String HORIZONTAL = "H";
    public static final String VERTICAL = "V";

    //ship counters
    public static final int MAX_SHIPS = 10;

    //game map columns
    public static final ArrayList<String> COLUMNS = new ArrayList<String>(){{
        add("K");
        add("I");
        add("L");
        add("O");
        add("M");
        add("E");
        add("T");
        add("R");
        add("A");
        add("S");
    }};
}