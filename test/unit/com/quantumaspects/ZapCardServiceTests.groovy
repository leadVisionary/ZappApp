package com.quantumaspects



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ZapCardService)
@Mock([ZapCard, Zapper])
class ZapCardServiceTests {

    void testCreateCardsFromCollection() {
        def zapper = new Zapper(email:"test@you.com", parseObjectId:"112233").save() 
        def collection = [
                            ["name": "test1", phoneNumber: "555-333-2222", owner:zapper, objectId:"loory"],
                            ["name": "test2", phoneNumber: "111-444-1234", owner:zapper, objectId:"foory"],
                         ]
        service.createCardsFromCollection(collection)
        assert ZapCard.list().size() == 2
    }
}