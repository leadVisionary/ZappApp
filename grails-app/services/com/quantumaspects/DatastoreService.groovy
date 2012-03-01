package com.quantumaspects

class DatastoreService {
    def parseService
    def zapCardService
    
    public Zapper createUser(String email){
        parseService.createUser(email)   
    }
    
    public ZapCard createCard(Zapper owner, String name, String phoneNumber){
        parseService.createCard(owner,name,phoneNumber)
    }
    
    void collectRemoteData() {
        def users = parseService.retrieveUsersFromParse()
        if(users){
            zapUserService.createUsersFromCollection(users)
        }
        def cards = parseService.retrieveCardsFromParse()
        if(cards){
            zapCardService.createCardsFromCollection(cards)
        }
    }
    
    void exchangeCards(Zapper sender, ZapCard zappCard, Zapper recipient){
        parseService.exchange(sender, zappCard, recipient)
    }
}
