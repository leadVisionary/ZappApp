package com.visionarysoftwaresolutions



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ZapCardService)
@Mock([ZapCard, Zapper])
class ZapCardServiceTests {
    Zapper collaborator = new Zapper(email:"test@you.com", username:"fuck", objectId:"112233") 
    
    void testCreateCardsFromCollection() {
        collaborator.save()
        def collection = [
                            [cardName: "test1", phoneNumber: "555-333-2222", cardOwner:[objectId: collaborator.objectId], objectId:"loory"],
                            [cardName: "test2", phoneNumber: "111-444-1234", cardOwner:[objectId: collaborator.objectId], objectId:"foory"],
                         ]
        service.createCardsFromCollection(collection)
        assert ZapCard.list().size() == 2
    }
    
    
    void testCreateCardFromInvalidInput(){
        def result = service.createCard(null, "blah", "blah")
        assert !result
    }
    
    
    void testCreateCardFromValidInputWithoutParseId(){
        collaborator.save()
        def result = service.createCard(collaborator, "blahma", "(480)593-2428" )
        assert result.validate()
    }
    
    void testCreateUserValidInputAndParseId(){
        collaborator.save()
        def result = service.createCard(collaborator, "blahma", "(480)593-2428", "testy")
        assert result.validate()
    }
}
