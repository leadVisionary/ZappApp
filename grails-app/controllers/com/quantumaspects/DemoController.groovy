package com.quantumaspects
import grails.converters.*

class DemoController {
    def datastoreService
    
    def broadcast(){
        def cards = ZapCard.list()
        if(!cards){
            datastoreService.collectRemoteData()
            cards = ZapCard.list()
        }
        render cards[0] as JSON
    }
}
