package battleships.data;

import java.util.Date;

public class Event {

    private Date date;
    private Coordinate coordinate;
    private Boolean hit;
    private String userId;

    public Event(Date date, Coordinate coordinate, Boolean hit, String userId) {
        this.date = date;
        this.coordinate = coordinate;
        this.hit = hit;
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
