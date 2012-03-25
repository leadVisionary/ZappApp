package com.visionarysoftwaresolutions

import static org.junit.Assert.*
import org.junit.*

class ParseConnectorTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testRetrieveAllUsersFromParse(){
        def results = []
        results = ParseConnector.retrieveFromParse("users")
        assert results != null
        assert results.size() > 0
    }
    
    @Test
    void testRetrieveAllZapCardsFromParse(){
        def results = []
        results = ParseConnector.retrieveFromParse("classes/zapCards")
        assert results != null
        assert results.size() > 0
    }
    
    @Test
    void testRetrieveAllExchangedCardsFromParse(){
        def results = []
        results = ParseConnector.retrieveFromParse("classes/exchangedCards")
        assert results != null
        assert results.size() > 0
    }
    
    @Test
    void testToJSON(){
        def poodle = ["dog" : ["mindy": ["isCute" : true] ] ]
        def result = ParseConnector.toJSON(poodle)
        assert result == '{ "dog" : { "mindy" : [ "isCute" : true ] } }'
    }
    
    @Test
    void testCreateParseUserLink(){
        def result = ParseConnector.createParseUserLink("mooch")
        assert result == [ "__type" : "Pointer", "className" : "_User", "objectId" : "mooch" ]
    }
    
    @Test
    void testCreateParseObjectLink(){
        def result = ParseConnector.createParseObjectLink("mooch", "zapCards")
        assert result == [ "__type": "Pointer", "className" : "zapCards", "objectId" : "mooch" ]
    }
}
