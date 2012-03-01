package com.quantumaspects
import grails.converters.*

class DemoController {
    def datastoreService
    
    def index(){
        
    }
    
    def live(){
        def data = populate(params.email, params.name, params.phoneNumber)
        def zapp = Zapper.findByEmail("zapp@zapapp.com")
        def zappCard = ZapCard.findByOwner(zapp)
        datastoreService.exchangeCards(data.demoZapper, data.card, zapp)
        return [ card : zappCard ]
    }
    
    private def populate(String email, String name, String phoneNumber){
        def parseData = [
            card : ZapCard.findByPhoneNumber(phoneNumber),
            demoZapper : Zapper.findByEmail(email)
        ]
        
        if(!parseData.card && !parseData.demoZapper){
            parseData = populateFromRemote(email,name, phoneNumber)
        }
        return parseData
    }
    
    private def populateFromRemote(String email, String name, String phoneNumber){
        datastoreService.collectRemoteData()
        def demoZapper = Zapper.findByEmail(email) ?:
                         datastoreService.createUser(email)
        def card = ZapCard.findByPhoneNumber(phoneNumber) ?:
                         datastoreService.createCard(demoZapper,name, phoneNumber)
        return [ card : card , demoZapper : demoZapper ]
    }
}
