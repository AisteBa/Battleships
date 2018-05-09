package battleships;

import battleships.data.*;
import battleships.services.GameService;
import battleships.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        new App().startGame();
    }

    private final static GameService GAME_SERVICE = new GameService();
    private final static UserService USER_SERVICE = new UserService();

    private GameBoard gameBoard;
    private GameStatus gameStatus;
    private User as;

    public void startGame(){
        gameBoard = new GameBoard();

        as = USER_SERVICE.createUser("Aiste", "aiste_barzdenaite@yahoo.com");
        gameStatus = GAME_SERVICE.joinSmart(as);


        placeShips();
//        List<Ship> ships = createShipsForGame();
//        gameBoard.addMyShips(createShipsForGame());

        GAME_SERVICE.setup(gameStatus.getId(), as.getId(), createShipsForGame());

//        GAME_SERVICE.setup(gameStatus.getId(), as.getId(), gameBoard.getMyGameTable().getShips());

        while(gameStatus.isGameNotFinished()){
            if (ifMyMove()){
                addNewEvents();
                printGameTables();
                makeMove();
                addNewEvents();
                printGameTables();
            } else {
                waitForMoveAndRefreshStatus();
            }
        }

    }

    private void waitForMoveAndRefreshStatus() {
        try {
            TimeUnit.SECONDS.sleep(2);
            gameStatus = GAME_SERVICE.status(gameStatus);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void makeMove() {
        Scanner sc = new Scanner(System.in);
        //vartotojas ismanus:)
        System.out.println("Please enter column and row of your hit, for example K0");

        String input = sc.next().toUpperCase();

        gameStatus = GAME_SERVICE.turn(gameStatus.getId(), as.getId(), input);

    }

//    private boolean checkInput(String input) {
//        String column = input.substring(0, 1);
//        String rowString = input.substring(1, 2);
//        int row = Integer.parseInt(rowString);
//        for (int i = 0; i < GameConstants.COLUMNS.size(); i++) {
//            if (column.equals(GameConstants.COLUMNS.get(i)) && (row >= 0 || row < 10 )) {
//                return true;
//            }
//        }
//        return false;
//    }

    private void printGameTables() {
        System.out.println("    My game table");
        printGameMapTable(gameBoard.getMyGameTable());
        System.out.println();
        System.out.println("    Opponent's game table");
        printGameMapTable(gameBoard.getOpponentGameTable());
        System.out.println("    " + gameStatus.getEvents().get(0).getDate());
    }

    private void addNewEvents() {
        List<Event> events = gameStatus.getEvents();
        List<Event> myEvents = new ArrayList<Event>();
        List<Event> opponentsEvents = new ArrayList<Event>();

        for (Event event : events){
            if (event.getUserId().equals(as.getId())){
                myEvents.add(event);
            }
            else if (!event.getUserId().equals(as.getId())){
                opponentsEvents.add(event);
            }
        }

        gameBoard.addMyEvents(opponentsEvents);
        gameBoard.addOpponentEvent(myEvents);
    }

    private boolean ifMyMove(){
        return gameStatus.getNextTurnForUserId().equals(as.getId()) && GameStatus.READY_TO_PLAY.equals(gameStatus.getStatus());
    }

    public void printGameMapTable(GameMap gameMap) {

        String header = "   _K_ _I_ _L_ _O_ _M_ _E_ _T_ _R_ _A_ _S_";
        System.out.println(header);
        List<Coordinate> blocks = gameMap.getBlocks();
        for (int i = 0; i < blocks.size(); i++) {
            Coordinate block = blocks.get(i);
            if (i == 0 || i % 10 == 0) {
                System.out.print((i / 10) + " |_" + block.getMarking());
            } else if (i == 9 || i % 10 == 9) {
                System.out.print("_|_" + block.getMarking() + "_|");
                System.out.println();
            } else {
                System.out.print("_|_" + block.getMarking());
            }
        }
    }

    public void placeShips() {
        Scanner scanner = new Scanner(System.in);
        printGameMapTable(gameBoard.getMyGameTable());
        System.out.println("Please enter 10 ships");

        int counter = 0;
        //vartotojas ismanus:)
        while(counter < GameConstants.MAX_SHIPS) {

            System.out.println("Please enter START coordinate of your ship");
            String input = scanner.next().toUpperCase();

            System.out.println("Please enter length of your ship: 1, 2, 3, 4?");
            int length = scanner.nextInt();
            String direction = "H";
            if (length != 1) {
                System.out.println("Is ship horizontal or vertical? Please enter h or v");
                direction = scanner.next().toUpperCase();
            }

            Ship ship = Ship.makeShip(
                    Coordinate.convertInputToCoordinate(input, GameConstants.SHIP), length, direction
            );

            gameBoard.addMyShip(ship);

            printGameMapTable(gameBoard.getMyGameTable());

            counter++;
        }

    }

    public List<Ship> createShipsForGame(){

        final Ship ship0 = Ship.makeShip(new Coordinate("I", 8, GameConstants.SHIP ), 4, GameConstants.HORIZONTAL);
        final Ship ship1 = Ship.makeShip(new Coordinate("E", 1, GameConstants.SHIP ), 3, GameConstants.VERTICAL);
        final Ship ship2 = Ship.makeShip(new Coordinate("R", 3, GameConstants.SHIP ), 3, GameConstants.VERTICAL);
        final Ship ship3 = Ship.makeShip(new Coordinate("I", 2, GameConstants.SHIP ), 2, GameConstants.HORIZONTAL);
        final Ship ship4 = Ship.makeShip(new Coordinate("I", 5, GameConstants.SHIP ), 2, GameConstants.HORIZONTAL);
        final Ship ship5 = Ship.makeShip(new Coordinate("M", 6, GameConstants.SHIP ), 2, GameConstants.HORIZONTAL);
        final Ship ship6 = Ship.makeShip(new Coordinate("A", 0, GameConstants.SHIP ), 1, GameConstants.VERTICAL);
        final Ship ship7 = Ship.makeShip(new Coordinate("S", 3, GameConstants.SHIP ), 1, GameConstants.VERTICAL);
        final Ship ship8 = Ship.makeShip(new Coordinate("A", 7, GameConstants.SHIP ), 1, GameConstants.VERTICAL);
        final Ship ship9 = Ship.makeShip(new Coordinate("S", 9, GameConstants.SHIP ), 1, GameConstants.VERTICAL);

        List<Ship> ships = new ArrayList<Ship>(){{
            add(ship0);
            add(ship1);
            add(ship2);
            add(ship3);
            add(ship4);
            add(ship5);
            add(ship6);
            add(ship7);
            add(ship8);
            add(ship9);
        }};

        return ships;
    }
}
