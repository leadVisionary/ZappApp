package com.quantumaspects
import grails.converters.*

class DemoController {
    def parseService
    
    def index(){
        
    }
    
    def live(){
        def data = populate(params.email, params.name, params.phoneNumber)
        def zapp = Zapper.findByEmail("zapp@zapapp.com")
        def zappCard = ZapCard.findByOwner(zapp)
        parseService.exchangeCards(data.demoZapper, data.card, zapp)
        return [ card : zappCard ]
    }
    
    private def populate(String email, String name, String phoneNumber){
        def parseData = 
        [
            card : ZapCard.findByPhoneNumber(phoneNumber),
            demoZapper : Zapper.findByEmail(email)
        ]
        
        if(!parseData.card && !parseData.demoZapper){
            parseData = populateFromRemote(email,name, phoneNumber)
        }
        return parseData
    }
    
    private def populateFromRemote(String email, String name, String phoneNumber){
        parseService.collectRemoteData()
        def demoZapper = Zapper.findByEmail(email) ?:
                         parseService.createUser(email)
        def card = ZapCard.findByPhoneNumber(phoneNumber) ?:
                         parseService.createCard(demoZapper,name, phoneNumber)
        return [ card : card , demoZapper : demoZapper ]
    }
}
