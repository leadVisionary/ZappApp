package com.quantumaspects



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ZapCard)
class ZapCardTests {

    void testInvalidBlankName() {
       def card = new ZapCard(name:"")
       mockForConstraintsTests(ZapCard, [card])
       assert !card.validate()
       assert "blank" == card.errors["name"]
    }
    
    void testInvalidNonUniqueName() {
       def card = new ZapCard(name:"dumb")
       def card2 = new ZapCard(name:"dumb")
       mockForConstraintsTests(ZapCard, [card, card2])
       card.save()
       assert !card2.validate()
       assert "unique" == card2.errors["name"]
    }
    
    void testValidCard(){
       def card = new ZapCard(name:"Dumass& Dumass")
       mockForConstraintsTests(ZapCard, [card])
       assert card.validate()
    }
}
