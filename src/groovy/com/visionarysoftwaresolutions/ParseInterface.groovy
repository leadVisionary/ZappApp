package com.visionarysoftwaresolutions

import net.sf.json.JSONArray
import groovyx.net.http.*

abstract class ParseInterface implements ParseAPI {
    public static Collection retrieveAll(String parseEntity){
        def url = (parseEntity == "User")? "users" : "classes/${parseEntity}"
        def all = ParseConnector.retrieveFromParse(url)
        JSONArray.toCollection(all?.results)
    }
    
    public String createUser(String email, String password){
        def jsoned = ParseConnector.toJSON([ 'username' : email, "email": email, 'password' : password ])
        def response = create('users', jsoned)
        return response?.objectId ?: ""
    }
    
    public String createObject(String parseEntity, Map args){
        def jsoned = ParseConnector.toJSON(args)
        def response = create("classes/${parseEntity}", jsoned)
        return response?.objectId ?: ""
    }
    
    public void pushNotification(String message){
        pushNotification("", 50000, "", [data:message])
    }
    public void pushNotification(String channel = "", 
                                 Integer expiry = Integer.MAX_VALUE, 
                                 String type = "",
                                 Map data)
    {
        def notificationBody = [
                                "channel" : channel, 
                                "expiry" : expiry, 
                                "data" : data
                               ]
        def response = create("push", ParseConnector.toJSON(notificationBody))
    }
    
    private def create(String url, def data){
        def response
        try{
            response = ParseConnector.sendToParse(url, ContentType.JSON,  data)
        }
        catch(HttpResponseException ex){
            log.error "Response was ${ex.response.status} ${ex.response.data}"
        }
        catch(Exception e){
            log.error "creating an object failed! problem was ${e.message}%n"
        }
    }
}