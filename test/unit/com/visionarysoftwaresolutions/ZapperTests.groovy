package com.visionarysoftwaresolutions

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Zapper)
class ZapperTests {

    void testInvalidBlankUsername() {
       def cad = new Zapper(email:"fuck@you.com", username:"", objectId:"blah")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "blank" == cad.errors["username"]
    }
    
    void testInvalidNonUniqueUsername() {
       def cad = new Zapper(email:"dumb@you.com", username:"fuck", objectId:"blah")
       def cad2 = new Zapper(email:"dumb@fuck.com", username:"fuck", objectId:"wah")
       mockForConstraintsTests(Zapper, [cad, cad2])
       cad.save()
       assert !cad2.validate()
       assert "unique" == cad2.errors["username"]
    }
    
    void testInvalidBlankEmail() {
       def cad = new Zapper(email:"", username:"fuck", objectId:"blah")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "blank" == cad.errors["email"]
    }
    
    void testInvalidNonUniqueEmail() {
       def cad = new Zapper(email:"dumb@you.com", username:"fuck", objectId:"blah")
       def cad2 = new Zapper(email:"dumb@you.com", username:"fuck", objectId:"blah")
       mockForConstraintsTests(Zapper, [cad, cad2])
       cad.save()
       assert !cad2.validate()
       assert "unique" == cad2.errors["email"]
    }
    
    void testInvalidEmail() {
       def cad = new Zapper(email:"fuck", username:"fuck", objectId:"blah")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "email" == cad.errors["email"]
    }
    
    void testInvalidBlankParseObjectId() {
       def cad = new Zapper(email:"dumb@you.com", username:"fuck", objectId:"")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "blank" == cad.errors["objectId"]
    }
    
    void testInvalidNonUniqueParseObjectId() {
       def cad = new Zapper(email:"dumb@you.com", username:"fuck", objectId:"blah")
       def cad2 = new Zapper(email:"dumb@me.com", username:"fuck", objectId:"blah")
       mockForConstraintsTests(Zapper, [cad, cad2])
       cad.save()
       assert !cad2.validate()
       assert "unique" == cad2.errors["objectId"]
    }
    
    void testValidZapper(){
       def cad = new Zapper(email:"honey@comb.net", username:"fuck", objectId:"blah")
       mockForConstraintsTests(Zapper, [cad])
       assert cad.validate()
    }
}
