package com.quantumaspects

class ZapCardService {

    def createCardsFromCollection(Collection result){
        result.each{
            def card = new ZapCard( name : it.name, 
                                    phoneNumber : it.phoneNumber, 
                                    owner :  Zapper.findByObjectId(it.cardOwner.objectId),
                                    objectId : it.objectId
                                  )
            if(card.validate()){
                card.save(flush:true)
            }
        }
    }
    
    public ZapCard createCard(Zapper owner, String name, String phoneNumber){
        createCard(owner, name, phoneNumber, "dummy")
    }
    
    public ZapCard createCard(Zapper owner, 
                              String name, 
                              String phoneNumber, 
                              String objectId){
        new ZapCard(
                    owner:owner, 
                    name: name,
                    phoneNumber:phoneNumber,
                    objectId: objectId).save(flush:true)
    }
}
