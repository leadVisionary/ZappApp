package com.visionarysoftwaresolutions

import static org.junit.Assert.*
import org.junit.*

class SynchronizationTests {
    def synchronizerService
    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testRetrieveUsersFromParseService(){
        synchronizerService.retrieveUsersFromParse()
        assert Zapper.list().size() > 0
    }
    
    @Test
    void testRetrieveCardsFromParseService(){
        synchronizerService.retrieveCardsFromParse()
        assert ZapCard.list().size() > 0
    }
    
    @Test
    void testCollectRemoteData(){
        synchronizerService.collectRemoteData()
        assert Zapper.list().size() > 0
        assert ZapCard.list().size() > 0
    }
}
