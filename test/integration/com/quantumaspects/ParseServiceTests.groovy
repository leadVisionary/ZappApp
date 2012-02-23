package com.quantumaspects

import static org.junit.Assert.*
import org.junit.*

class ParseServiceTests {
    def parseService
    
    void testRetrieveCards() {
        def result = parseService.retrieveCardsFromParse()
        assert result.size() > 0
    }
    
    void testRetrieveUsers() {
        def result = parseService.retrieveUsersFromParse()
        assert result.size() > 0
    }
}
