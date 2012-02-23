package com.quantumaspects

import groovyx.net.http.*
import net.sf.json.JSONArray


abstract class AbstractParseAPI implements ParseAPI {
    
    public Collection retrieve(String parseEntity){
        return retrieveFromParse(parseEntity)
    }
    
    private def retrieveFromParse(String parseEntity) {
        def client = buildRequest()
        def url = (parseEntity == "User")? "users" : "classes/${parseEntity}"
        def response = client.get(path : url)
        JSONArray.toCollection(response.data.results)
    }
    
    private def buildRequest(){
        def http = new RESTClient("${API_URL}")
        http.headers = [ 
                         "X-Parse-Application-Id": applicationId, 
                         "X-Parse-REST-API-Key" : key,
                         "Content-Type" : 'application/json'
                       ]
        return http
    }
}

