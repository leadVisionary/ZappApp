package com.quantumaspects

import static org.junit.Assert.*
import org.junit.*


class DatastoreServiceTests {
    def datastoreService
    
    void testCollectRemoteData() {
        datastoreService.collectRemoteData()
        assert ZapCard.list().size() > 0
    }
}
