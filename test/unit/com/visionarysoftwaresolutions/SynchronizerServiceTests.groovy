package com.visionarysoftwaresolutions



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(SynchronizerService)
class SynchronizerServiceTests {

    void testRemoteDataCollected() {
        def control = mockFor(ZapperService)
        control.demand.createUsersFromCollection{ collection ->
            
        }
        service.zapperService = control.createMock()
        control = mockFor(ZapCardService)
        control.demand.createCardsFromCollection{ collection ->
            
        }
        service.zapCardService = control.createMock()
        service.collectRemoteData()
    }
}
