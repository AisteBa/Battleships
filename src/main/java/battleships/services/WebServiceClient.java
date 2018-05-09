package battleships.services;

import java.io.InputStream;

public class WebServiceClient {
    public static final String SERVER = "http://miskoverslas.lt/laivu_musis/";

    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}