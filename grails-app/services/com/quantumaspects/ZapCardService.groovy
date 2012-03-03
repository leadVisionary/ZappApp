package com.quantumaspects

class ZapCardService {

    def createCardsFromCollection(Collection result){
        result.each{
            def card = new ZapCard( name : it.name, 
                                    phoneNumber : it.phoneNumber, 
                                    owner :  Zapper.findByParseObjectId(it.cardOwner.objectId),
                                    parseObjectId : it.objectId
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
                              String parseObjectId){
        new ZapCard(
                    owner:owner, 
                    name: name,
                    phoneNumber:phoneNumber,
                    parseObjectId: parseObjectId).save(flush:true)
    }
}
