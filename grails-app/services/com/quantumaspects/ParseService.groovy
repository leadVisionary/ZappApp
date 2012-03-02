package com.quantumaspects

class ParseService extends ParseInterface implements ZapAPI  {
    def zapperService
    def zapCardService
    
    public Zapper createUser(String email){
        def zapper = this.createUserInParse(zapper) 
        if(zapper){
            zapperService.createUser(email, zapper)
        }
    }
    
    private String createUserInParse(Zapper user){
        this.createUser(user.email, "temp")
    }
    
    public ZapCard createCard(Zapper owner, String name, String phoneNumber){
        def card = this.createCardInParse(owner,card) 
        if(card){
            zapCardService.createCard(owner, card)
        }
        return card
    }
    
    private String createCardInParse(Zapper owner, ZapCard card){
        def cardData = [ name: card.name, phoneNumber: card.phoneNumber, cardOwner : owner.parseObjectId ]
        this.createObject("zapCards", cardData)
    }
    
    public void exchangeCards(Zapper sender, ZapCard card, Zapper recipient){
        def exchangeData = [ user : sender.parseObjectId, 
                             cardRecipient : recipient.parseObjectId,
                             zapCard : card.parseObjectId]
        this.createObject("exchangedCards", exchangeData)
        this.pushNotification( exchangeData )
    }
    
    void collectRemoteData() {
        retrieveUsersFromParse()
        retrieveCardsFromParse()
    }
    
    def retrieveUsersFromParse(){
        def result = this.retrieve("User")
        zapperService.createUsersFromCollection(result)
    }
    
    def retrieveCardsFromParse() {
        def result = this.retrieve("zapCards")
        zapCardService.createCardsFromCollection(result)
    }
}