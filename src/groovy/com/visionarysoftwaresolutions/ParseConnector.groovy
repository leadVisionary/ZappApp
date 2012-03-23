package com.visionarysoftwaresolutions

import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import net.sf.json.JSONSerializer

class ParseConnector {
    final static String API_URL = "https://api.parse.com/1/";
    final static String APPLICATION_ID = "1E8OSPnGon2fwhowEKeOItYHqr25oYh6MaDFjbaI";
    final static String APPLICATION_KEY = "eEwOWh2OxEWnxKYcI0LQILF3JD6GNPMAHKsIU5QF";
    
    public static def sendToParse(String url, ContentType contentType=ContentType.JSON, def envelope={}){
        def client = buildRequest()
        def response = client.post(path:url, body:envelope, requestContentType:contentType)
        return response?.data
    }
    
    public static def retrieveFromParse(String url, def contentType=JSON) {
        def client = buildRequest()
        def response = client.get(path : url, requestContentType:contentType)
        return response?.data
    }
    
    private static RESTClient buildRequest(){
        def http = new RESTClient("${API_URL}")
        http.headers = [ 
                         "X-Parse-Application-Id": APPLICATION_ID, 
                         "X-Parse-REST-API-Key" : APPLICATION_KEY,
                         "Content-Type" : 'application/json'
                       ]
        return http
    }
    
    public static def toJSON(def target){
        return JSONSerializer.toJSON(target)
    }
    
    public static def createParseUserLink(String objectId){
        return [ "__type" : "Pointer", "className" : "_User", "objectId" : objectId ]
    }
    
    public static def createParseObjectLink(String objectId, String parseEntity){
        return [ "__type": "Pointer", "className" : parseEntity, "objectId" : objectId ]
    }	
}

