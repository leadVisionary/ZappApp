package com.visionarysoftwaresolutions

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import net.sf.json.JSONArray
import net.sf.json.JSONSerializer


abstract class ParseInterface implements ParseAPI {
    public Collection retrieveAll(String parseEntity){
        def url = (parseEntity == "User")? "users" : "classes/${parseEntity}"
        def all = retrieveFromParse(url)
        JSONArray.toCollection(all.results)
    }
    
    public String createUser(String email, String password){
        def jsoned = toJSON([ 'username' : email, "email": email, 'password' : password ])
        log.error "sending ${jsoned}"
        def resp = sendToParse('users', ContentType.JSON, jsoned)
        log.error "got back ${resp}"
        return resp?.objectId ?: ""
    }
    
    public String createObject(String parseEntity, Map args){
        def jsoned = toJSON(args)
        log.error "sending ${jsoned}"
        def resp = sendToParse("classes/${parseEntity}", ContentType.JSON, jsoned) 
        log.error "got back ${resp}"
        return resp?.objectId ?: ""
    }
    
    public String queryForObject(String parseEntity, Map constraints){
        def url = (parseEntity == "User")? "users" : "classes/${parseEntity}"
        String clause = "where=" + toJSON(constraints)
        def resp = retrieveFromParse("${url}?${clause}}", ContentType.URLENC)
        return resp?.objectId ?: ""    
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
        sendToParse("push", ContentType.JSON, toJSON(notificationBody))
    }
    
    private def sendToParse(String url, ContentType contentType=ContentType.JSON, def envelope={}){
        def client = buildRequest()
        def response
        try{
            response = client.post(path:url, body:envelope, requestContentType:contentType)
        }
        catch(HttpResponseException ex){
            log.error "Response was ${ex.response.status} ${ex.response.data}"
        }
        catch(Exception e){
            log.error "Post failed! Tried to hit ${url}%n with ${envelope}%n problem was ${e.message}%n"
        }
        return response?.data
    }
    
    private def retrieveFromParse(String url, def contentType=JSON) {
        def client = buildRequest()
        def response = client.get(path : url, requestContentType:contentType)
        return response.data
    }
    
    private RESTClient buildRequest(){
        def http = new RESTClient("${API_URL}")
        http.headers = [ 
                         "X-Parse-Application-Id": applicationId, 
                         "X-Parse-REST-API-Key" : key,
                         "Content-Type" : 'application/json'
                       ]
        return http
    }
    
    private toJSON(def target){
        return JSONSerializer.toJSON(target)
    }
    
    protected def createParseUserLink(String objectId){
        return [ "__type" : "Pointer", "className" : "_User", "objectId" : objectId ]
    }
    
    protected def createParseObjectLink(String objectId, String parseEntity){
        return [ "__type": "Pointer", "className" : parseEntity, "objectId" : objectId ]
    }
}