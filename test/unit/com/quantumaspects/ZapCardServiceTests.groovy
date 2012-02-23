package com.quantumaspects



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ZapCardService)
@Mock(ZapCard)
class ZapCardServiceTests {

    void testCreateCardsFromCollection() {
        def collection = [
                            ["name": "test1", phoneNumber: "555-333-2222"],
                            ["name": "test2", phoneNumber: "111-444-1234"],
                         ]
        service.createCardsFromCollection(collection)
        assert ZapCard.list().size() == 2
    }
}
