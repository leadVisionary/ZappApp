package com.quantumaspects

class ParseService extends AbstractParseAPI {
    def retrieveCardsFromParse() {
        this.retrieve("zapCards")
    }
    
    def retrieveUsersFromParse(){
        this.retrieve("User")
    }
}
