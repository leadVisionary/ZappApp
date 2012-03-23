package com.visionarysoftwaresolutions;

import java.util.Map;

public interface ParseAPI extends DatastoreAPI {
    public String createUser(String username, String password);
    public String createObject(String parseEntity, Map args);
    public void pushNotification(String channel, Integer expiry, String type, Map data);
}
