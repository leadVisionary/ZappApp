package com.visionarysoftwaresolutions

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ZapperService)
@Mock(Zapper)
class ZapperServiceTests {

    void testCreateUsersFromCollection() {
        def collection = [
                            [ email : "moo@you.com", username:"fox", objectId:"loory" ],
                            [ email : "roo@you.net", username:"rox", objectId:"foory" ],
                         ]
        service.createUsersFromCollection(collection)
        assert Zapper.list().size() == 2
    }
    
    void testCreateUserFromInvalidEmail(){
        def result = service.createUser("blah", "blah")
        assert !result
    }
    
    void testCreateUserFromValidEmail(){
        def result = service.createUser("blah@roo.com", "blah")
        assert result.validate()
    }
    
    void testCreateUserFromEmailAndParseId(){
        def result = service.createUser("blah@roo.com", "blah", "foogah")
        assert result.validate()
    }
}