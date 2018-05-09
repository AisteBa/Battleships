package battleships.data;

import java.util.List;

public class GameStatus {
    public final static String READY_FOR_SECOND_PLAYER = "READY_FOR_SECOND_PLAYER";
    public final static String READY_FOR_SHIPS = "READY_FOR_SHIPS";
    public final static String READY_TO_PLAY = "READY_TO_PLAY";
    public final static String FINISHED = "FINISHED";
    private String id; // game id
    private String nextTurnForUserId;
    private String status;
    private String winnerUserId;
    private List<Event> events;

    public GameStatus(String id, String nextTurnForUserId, String status, String winnerUserId, List<Event> events) {
        this.id = id;
        this.nextTurnForUserId = nextTurnForUserId;
        this.status = status;
        this.winnerUserId = winnerUserId;
        this.events = events;
    }

    public boolean isGameNotFinished(){
        return !GameStatus.FINISHED.equals(getStatus());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNextTurnForUserId() {
        return nextTurnForUserId;
    }

    public void setNextTurnForUserId(String nextTurnForUserId) {
        this.nextTurnForUserId = nextTurnForUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWinnerUserId() {
        return winnerUserId;
    }

    public void setWinnerUserId(String winnerUserId) {
        this.winnerUserId = winnerUserId;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
