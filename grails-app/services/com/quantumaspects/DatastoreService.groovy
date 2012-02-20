package com.quantumaspects

import groovyx.net.http.*

class DatastoreService {
    private final String parseDBName = "Dummy"
    private final String appId = "12345"
    private final String key = "11111"
    
    def collectRemoteData() {
        def result = retrieveFromParse()
        if(result){
            createLocalCards(result)
        }
    }
    
    private def retrieveFromParse() {
        def http = new RESTClient("https://api.parse.com/1")
        http.headers = [ "X-Parse-Application-Id": appId, 
                         "X-Parse-REST-API-Key" : key ]
        http.get(path : '/classes/${parseDBName}')
    }
    
    private def createLocalCards(def result){
        result.each{
            def card = new ZapCard(it.properties)
            if(card.validate()){
                card.save(flush:true)
            }
        }
    }
}
