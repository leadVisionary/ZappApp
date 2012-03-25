package com.visionarysoftwaresolutions
import grails.converters.*

class DemoController {
    def parseService
    def synchronizerService
    
    def index(){
    
    }
    
    def live(){
        def zapp = Zapper.findByEmail("zapp@zapapp.com")
        def zappCard = ZapCard.findByOwner(zapp)
        try{
            def data = populate(params.email, params.name, params.phoneNumber)
            parseService.exchangeCards(data.demoZapper, data.card, zapp)
        }
        catch(ex){
            flash.message = "Card exchange failed because ${ex.message}"
        }
        render template:'card', model: [ card : zappCard ]
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
        synchronizerService.collectRemoteData()
        def demoZapper = Zapper.findByEmail(email) ?:
                         parseService.createUser(email)
        if(!demoZapper){
            throw new Exception("Couldn't create user ! ${demoZapper}")
        }
        def card = ZapCard.findByPhoneNumber(phoneNumber) ?: 
                   parseService.createCard(demoZapper, name, phoneNumber)
        if(!card){
            throw new Exception("Couldn't create card ! ${card}")
        }                 
        return [ card : card , demoZapper : demoZapper ]
    }
}
