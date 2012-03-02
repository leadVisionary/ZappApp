package com.quantumaspects

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ZapperService)
@Mock(Zapper)
class ZapperServiceTests {

    void testCreateCardsFromCollection() {
        def collection = [
                            [ email : "moo@you.com", objectId:"loory" ],
                            [ email : "roo@you.net", objectId:"foory" ],
                         ]
        service.createUsersFromCollection(collection)
        assert Zapper.list().size() == 2
    }
    
    void testCreateUserFromInvalidEmail(){
        def result = service.createUser("blah")
        assert !result
    }
    
    void testCreateUserFromValidEmail(){
        def result = service.createUser("blah@roo.com")
        assert result.validate()
    }
    
    void testCreateUserFromEmailAndParseId(){
        def result = service.createUser("blah@roo.com", "foogah")
        assert result.validate()
    }
}