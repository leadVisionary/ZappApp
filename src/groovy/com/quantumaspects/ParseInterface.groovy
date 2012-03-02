package com.quantumaspects

import groovyx.net.http.*
import net.sf.json.JSONArray
import net.sf.json.JSONSerializer


abstract class ParseInterface implements ParseAPI {
    public Collection retrieveAll(String parseEntity){
        return retrieveFromParse(parseEntity)
    }
    
    public String createUser(String username, String password){
        def client = buildRequest()
        def userParams = toJSON([ 'username' : username, 'password' : password ])
        def response = client.post( path:'users', body : userParams, requestContentType : URLENC )
        if(response.status != 201){
            throw new Exception("User creation failed! Response was ${response.status} ${response.body}")
        }
        return response.data.objectId
    }
    
    public String createObject(String parseEntity, Map args){
        def client = buildRequest()
        client.post(path:"classes/${parseEntity}", 
                    body:toJSON(args), 
                    requestContentType : URLENC )
        if(response.status != 201){
            throw new Exception("Object creation failed! Response was ${response.status} ${response.body}")
        }
        return response.data.objectId
    }
    
    public String queryForObject(String parseEntity, Map constraints){
        def client = buildRequest()
        String clause = "where=" + toJSON(constraints)
        client.get(path:"classes/${parseEntity}", 
                    body:clause, 
                    requestContentType : URLENC )
        if(response.status != 201){
            throw new Exception("Object query failed! Response was ${response.status} ${response.body}")
        }
        return response.data.objectId    
    }
    
    public void pushNotification(String channel = "", 
                                 Integer expiry = Integer.MAX_VALUE, 
                                 String type = "",
                                 Map data)
    {
        def client = buildRequest()
        def notificationBody = [
                                "channel" : channel, 
                                "expiry" : expiry, 
                                "type" : type, 
                                "data" : data
                               ]
        def response = client.post( path : "push", body : notificationBody )
        if(response.status != 201){
            throw new Exception("Notification failed! Response was ${response.status} ${response.body}")
        }
    }
    
    private def retrieveFromParse(String parseEntity) {
        def client = buildRequest()
        def url = (parseEntity == "User")? "users" : "classes/${parseEntity}"
        def response = client.get(path : url)
        return JSONArray.toCollection(response.data.results)
    }
    
    public RESTClient buildRequest(){
        def http = new RESTClient("${API_URL}")
        http.headers = [ 
                         "X-Parse-Application-Id": applicationId, 
                         "X-Parse-REST-API-Key" : key,
                         "Content-Type" : 'application/json'
                       ]
        return http
    }
    
    public toJSON(Map target){
        return JSONSerializer.toJSON(target)
    }
}