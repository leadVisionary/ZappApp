package com.quantumaspects

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(DatastoreService)
class DatastoreServiceTests {

    void testCharacterizeCollectData() {
        def result = service.collectRemoteData()
    }
}
