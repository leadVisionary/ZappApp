package com.quantumaspects

class DatastoreService {
    def parseService
    def zapCardService
    
    void collectRemoteData() {
        def result = parseService.retrieveCardsFromParse()
        if(result){
            zapCardService.createCardsFromCollection(result)
        }
    }
}
