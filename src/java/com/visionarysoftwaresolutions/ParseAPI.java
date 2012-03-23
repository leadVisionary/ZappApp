package com.visionarysoftwaresolutions;

import java.util.Map;

public interface ParseAPI extends DatastoreAPI {
    final String API_URL = "https://api.parse.com/1/";
    String applicationId = "1E8OSPnGon2fwhowEKeOItYHqr25oYh6MaDFjbaI";
    String key = "eEwOWh2OxEWnxKYcI0LQILF3JD6GNPMAHKsIU5QF";
    
    public String createUser(String username, String password);
    public String createObject(String parseEntity, Map args);
    public String queryForObject(String parseEntity, Map constraints);
    public void pushNotification(String channel, Integer expiry, String type, Map data);
}
