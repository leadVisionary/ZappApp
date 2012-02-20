package com.quantumaspects
import grails.converters.*

class DemoController {
    def datastoreService
    
    def collect() { 
        datastoreService.collectRemoteData()
    }
    
    def broadcast(){
        def card = ZapCard.get(1)
        if(card){
            render card as JSON
        }
    }
}
