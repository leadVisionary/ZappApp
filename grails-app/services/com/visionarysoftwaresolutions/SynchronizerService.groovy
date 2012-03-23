package com.visionarysoftwaresolutions

class SynchronizerService {
    def zapperService
    def zapCardService
    
    public void collectRemoteData() {
        retrieveUsersFromParse()
        retrieveCardsFromParse()
    }
    
    private def retrieveUsersFromParse(){
        def result = ParseInterface.retrieveAll("User")
        zapperService.createUsersFromCollection(result)
    }
    
    private def retrieveCardsFromParse() {
        def result = ParseInterface.retrieveAll("zapCards")
        zapCardService.createCardsFromCollection(result)
    }
}
