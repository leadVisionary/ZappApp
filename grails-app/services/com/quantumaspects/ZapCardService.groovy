package com.quantumaspects

class ZapCardService {

    def createCardsFromCollection(Collection result){
        result.each{
            def card = new ZapCard( name:it.name, phoneNumber:it.phoneNumber)
            if(card.validate()){
                card.save(flush:true)
            }
        }
    }
}
