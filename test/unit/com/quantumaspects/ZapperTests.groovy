package com.quantumaspects



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Zapper)
class ZapperTests {

    void testInvalidBlankEmail() {
       def cad = new Zapper(email:"")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "blank" == cad.errors["email"]
    }
    
    void testInvalidNonUniqueName() {
       def cad = new Zapper(email:"dumb@you.com")
       def cad2 = new Zapper(email:"dumb@you.com")
       mockForConstraintsTests(Zapper, [cad, cad2])
       cad.save()
       assert !cad2.validate()
       assert "unique" == cad2.errors["email"]
    }
    
    void testInvalidEmail() {
       def cad = new Zapper(email:"fuck")
       mockForConstraintsTests(Zapper, [cad])
       assert !cad.validate()
       assert "email" == cad.errors["email"]
    }
    
    void testValidZapper(){
       def cad = new Zapper(email:"honey@comb.net")
       mockForConstraintsTests(Zapper, [cad])
       assert cad.validate()
    }
}
