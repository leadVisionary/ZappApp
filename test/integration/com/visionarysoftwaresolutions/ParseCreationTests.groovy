package com.visionarysoftwaresolutions

import static org.junit.Assert.*
import org.junit.*

class ParseCreationTests {
    def parseService
    def synchronizerService
    
    @Test
    void testCreateAParseUser(){
        def result = ""
        result = parseService.createUser("testo@test.com", "test")
        assert result
    }
    
    @Test
    void testCreateAZapCard(){
        def zapp = Zapper.findByUsername("9999999")
        def result = ""
        result = parseService.createCard(zapp,"Zapp Appiganigan", "123-456-7890")
        assert result
    }
    
    @Test
    void testCreateAnExchangedCard(){
        synchronizerService.collectRemoteData()
        def zapp = Zapper.findByUsername("9999999")
        def testDude = Zapper.findByObjectId("V8OlVbUWcu")
        def card = ZapCard.findByOwner(zapp)
        def result = ""
        result = parseService.exchangeCards(zapp,card, testDude)
        assert result
    }
    
    @Test
    void testCreateANotification(){
        def message = "coo coo cachoo"
        def result = parseService.pushNotification(message)
        assert result
    }
}
