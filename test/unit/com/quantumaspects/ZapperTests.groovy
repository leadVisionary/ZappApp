package com.quantumaspects

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Zapper)
class ZapperTests {

    void testInvalidBlankEmail() {
       def cad = new Zapper(email:"", parseObjectId:"blah")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "blank" == cad.errors["email"]
    }
    
    void testInvalidNonUniqueEmail() {
       def cad = new Zapper(email:"dumb@you.com", parseObjectId:"blah")
       def cad2 = new Zapper(email:"dumb@you.com", parseObjectId:"blah")
       mockForConstraintsTests(Zapper, [cad, cad2])
       cad.save()
       assert !cad2.validate()
       assert "unique" == cad2.errors["email"]
    }
    
    void testInvalidEmail() {
       def cad = new Zapper(email:"fuck", parseObjectId:"blah")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "email" == cad.errors["email"]
    }
    
    void testInvalidBlankParseObjectId() {
       def cad = new Zapper(email:"dumb@you.com", parseObjectId:"")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "blank" == cad.errors["parseObjectId"]
    }
    
    void testInvalidNonUniqueParseObjectId() {
       def cad = new Zapper(email:"dumb@you.com",parseObjectId:"blah")
       def cad2 = new Zapper(email:"dumb@me.com", parseObjectId:"blah")
       mockForConstraintsTests(Zapper, [cad, cad2])
       cad.save()
       assert !cad2.validate()
       assert "unique" == cad2.errors["parseObjectId"]
    }
    
    void testValidZapper(){
       def cad = new Zapper(email:"honey@comb.net",parseObjectId:"blah")
       mockForConstraintsTests(Zapper, [cad])
       assert cad.validate()
    }
}
