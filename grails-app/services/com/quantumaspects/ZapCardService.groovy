package com.quantumaspects

class ZapCardService {

    def createCardsFromCollection(Collection result){
        result.each{
            def card = new ZapCard( name : it.name, 
                                    phoneNumber : it.phoneNumber, 
                                    owner :  it.owner,
                                    parseObjectId : it.objectId
                                  )
            def moo = [ name : it.name, 
                                    phoneNumber : it.phoneNumber, 
                                    owner :  it.owner,
                                    parseObjectId : it.objectId ]
            if(card.validate()){
                card.save(flush:true)
            }
        }
    }
}
