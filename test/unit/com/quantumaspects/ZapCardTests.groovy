package com.quantumaspects

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ZapCard)
@Mock(Zapper)

class ZapCardTests {

    void testInvalidBlankName() {
       def card = new ZapCard(name:"", phoneNumber:"(555)555-5555", parseObjectId:"blah")
       mockForConstraintsTests(ZapCard, [card])
       assert !card.validate()
       assert "blank" == card.errors["name"]
    }
    
    void testInvalidNonUniqueName() {
       def card = new ZapCard(name:"dumb", phoneNumber:"(555)555-5555", parseObjectId:"blah")
       def card2 = new ZapCard(name:"dumb", phoneNumber:"(555)555-5555", parseObjectId:"blah")
       mockForConstraintsTests(ZapCard, [card, card2])
       card.save()
       assert !card2.validate()
       assert "unique" == card2.errors["name"]
    }
    
    void testInvalidPhoneNumber() {
       def card = new ZapCard(name:"dumb", , phoneNumber:"(555)555-555", parseObjectId:"blah")
       mockForConstraintsTests(ZapCard, [card])
       assert !card.validate()
       assert "matches" == card.errors["phoneNumber"]
    }
    
    
    void testValidCard(){
       def zapper = new Zapper(email:"moo@you.com",parseObjectId:"112233")
       def card = new ZapCard(name:"Dumass& Dumass", phoneNumber:"(555)555-5555",owner:zapper, parseObjectId:"blah")
       mockForConstraintsTests(ZapCard, [card])
       assert card.validate()
    }
}
