package battleships.services;

import battleships.data.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameService extends WebServiceClient {

    public static final String METHOD_JOIN = "join?";
    public static final String METHOD_JOIN_SIMPLE = "join_simple?";
    public static final String METHOD_JOIN_SMART = "join_smart?";
    public static final String METHOD_SETUP = "setup?";
    public static final String METHOD_TURN = "turn?";
    public static final String METHOD_STATUS = "status?";

    public GameStatus join(User user) {
        String url = SERVER + METHOD_JOIN + "user_id=" + user.getId();
        return makeRequest(url);
    }

    public GameStatus joinSimple(User user) {
        String url = SERVER + METHOD_JOIN_SIMPLE + "user_id=" + user.getId();
        return makeRequest(url);
    }

    public GameStatus joinSmart(User user) {
        String url = SERVER + METHOD_JOIN_SMART + "user_id=" + user.getId();
        return makeRequest(url);
    }

    public GameStatus status(GameStatus status) {
        String url = SERVER + METHOD_STATUS + "game_id=" + status.getId();
        return makeRequest(url);
    }

    public GameStatus setup(String gameId, String userId, List<Ship> ships) {
        String data = Ship.getShipsForServer(ships);
        String url = SERVER + METHOD_SETUP + "game_id=" + gameId + "&user_id=" + userId + "&data=" + data;
        return makeRequest(url);
    }

    public GameStatus turn(String gameId, String userId, String input){
        String url = SERVER + METHOD_TURN + "game_id=" + gameId + "&user_id="+userId+"&data=" + input;
        return makeRequest(url);
    }

    private GameStatus makeRequest(String url) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response = client.execute(request);
            String body = convertStreamToString(response.getEntity().getContent());
            if (response.getStatusLine().getStatusCode() == 200) {
                return convertResponce(body);
            }
            System.out.println("Klaida is serverio:" + body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GameStatus convertResponce(String body) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(body);

        List<Event> events = new ArrayList<Event>();

        if (obj instanceof JSONObject) {

            JSONObject jsonObject = (JSONObject) obj;

            String id = (String) jsonObject.get("id"); //game id (GameStatus)
            String nextTurnForUserId = (String) jsonObject.get("nextTurnForUserId");
            String status = (String) jsonObject.get("status");
            String winnerUserId = (String) jsonObject.get("winnerUserId");

            JSONArray eventsJson = (JSONArray) jsonObject.get("events");
            for (Object eventObj : eventsJson) {
                Event event = convertToEvent((JSONObject)eventObj);
                events.add(event);
            }

            return new GameStatus(id, nextTurnForUserId, status, winnerUserId, events);
        }
        return null;
    }

    private Event convertToEvent(JSONObject eventJsonObj){

        Date createdDate = new Date((Long) eventJsonObj.get("date"));
        String userId = (String) eventJsonObj.get("userId");
        Boolean hit = (Boolean) eventJsonObj.get("hit");

        Coordinate coordinate = convertToCoordinate((JSONObject) eventJsonObj.get("coordinate"), hit);
        Event event = new Event(createdDate, coordinate, hit, userId);

        return event;
    }

    private Coordinate convertToCoordinate(JSONObject coordJsonObj, Boolean hit){

        String coordColumn = (String) coordJsonObj.get("column");
        Long coordRow = (Long) coordJsonObj.get("row");
        Coordinate coordinate = new Coordinate(coordColumn, coordRow.intValue(), hit ? GameConstants.SHIP_HIT : GameConstants.EMPTY_HIT);

        return coordinate;
    }
}
