package com.quantumaspects

class ParseService  {
    private ParseInterface parseConnector = new ParseInterface()
    
    public ParseService(ParseInterface connector){
        parseConnector = connector
    }
    
    public Zapper createUser(String email){
        def zapper = createUserInParse(zapper) 
        if(zapper){
            new Zapper(email:email, parseObjectId:zapper).save(flush:true)
        }
    }
    
    private void createUserInParse(Zapper user){
        parseConnector.createUser(user.email, "temp")
    }
    
    public ZapCard createCard(Zapper owner, String name, String phoneNumber){
        def card = new ZapCard(owner:demoZapper, 
                               name: name,
                               phoneNumber:phoneNumber).save(flush:true)
        if(card){
            createCardInParse(owner, card)
        }
        return card
    }
    
    private void createCardInParse(Zapper owner, ZapCard card){
        def cardData = [ name: card.name, phoneNumber: card.phoneNumber, cardOwner : owner.parseObjectId ]
        parseConnector.createObject("zapCards", cardData)
    }
    
    public void exchange(Zapper sender, ZapCard card, Zapper recipient){
        println "Got ${sender} ${card} ${recipient}"
        def exchangeData = [ user : sender.parseObjectId, 
                             cardRecipient : recipient.parseObjectId,
                             zapCard : card.parseObjectId]
        parseConnector.createObject("exchangedCards", exchangeData)
        parseConnector.pushNotification( exchangeData )
    }
    
    def retrieveCardsFromParse() {
        this.retrieve("zapCards")
    }
    
    def retrieveUsersFromParse(){
        this.retrieve("User")
    }
}