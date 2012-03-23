package com.visionarysoftwaresolutions

class ParseService extends ParseInterface implements ZapAPI  {
    def zapperService
    def zapCardService
    
    public Zapper createUser(String email){
        def zapper = this.createUserInParse(email) 
        if(zapper){
            zapper = zapperService.createUser(email, zapper)
        }
        return zapper
    }
    
    private String createUserInParse(String email){
        this.createUser(email, "temp")
    }
    
    public ZapCard createCard(Zapper owner, String name, String phoneNumber){
        def card = this.createCardInParse(owner,name, phoneNumber) 
        if(card){
            card = zapCardService.createCard(owner, name, phoneNumber, card)
        }
        return card
    }
    
    private String createCardInParse(Zapper owner, String name, String phoneNumber){
        def cardData = [ 
                         name: name,
                         phoneNumber: phoneNumber, 
                         cardOwner :  ParseConnector.createParseUserLink(owner?.objectId)
                       ]
        return this.createObject("zapCards", cardData)
    }
    
    public void exchangeCards(Zapper sender, ZapCard card, Zapper recipient){
        def usr = ParseConnector.createParseUserLink(sender.objectId)
        def recp = ParseConnector.createParseUserLink(recipient.objectId)
        def trade  = ParseConnector.createParseObjectLink(card.objectId, "zapCards")
        log.debug "got ${usr} ${recp} ${trade}"
        def exchangeData = [ user : usr, 
                             cardRecipient : recp,
                             zapCard : trade ]
        def result = this.createObject("exchangedCards", exchangeData)
        def message = "${sender.username} sent ${recipient.username} a ZapCard ${card.name}"
        this.pushNotification( message )
    }
    
    void collectRemoteData() {
        retrieveUsersFromParse()
        retrieveCardsFromParse()
    }
    
    def retrieveUsersFromParse(){
        def result = this.retrieveAll("User")
        zapperService.createUsersFromCollection(result)
    }
    
    def retrieveCardsFromParse() {
        def result = this.retrieveAll("zapCards")
        zapCardService.createCardsFromCollection(result)
    }
}