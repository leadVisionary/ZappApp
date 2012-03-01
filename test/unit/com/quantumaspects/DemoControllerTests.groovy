package com.quantumaspects

import grails.test.mixin.*
import org.junit.*
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
@Mock([ ZapCard, Zapper ])

class DemoControllerTests {
    void setUp(){
        def zapp = Zapper.findByEmail("zapp@zapapp.com") ?:
                   new Zapper(email:"zapp@zapapp.com",parseObjectId:"boobah").save()
        def card = ZapCard.findByName("Zapp Appiganigan") ?:
                   new ZapCard( owner: zapp,
                               name:"Zapp Appiganigan", 
                               phoneNumber:"999-999-9999",
                               parseObjectId:"loory").save()
        
    }
    
    private Zapper createZapper(){ new Zapper(email:"moo@you.com", parseObjectId:"112233") }
    private ZapCard createCard(Zapper zapper) {
        new ZapCard(
            name:"Visionary Software Solutions",
            phoneNumber: "(480)111-2222",
            owner:zapper,
            parseObjectId:"FOOTCHIE"
        )
    }
    
    private void setupMock(def configure){
        def control = mockFor(DatastoreService)
        configure(control)
        control.demand.exchangeCards { Zapper o, String n, String pn ->
            return new ZapCard(name:n, phoneNumber:pn, owner:o, parseObjectId: "dummy")
        }
        controller.datastoreService = control.createMock()
    }
    
    private void testLive(Zapper zapper, ZapCard newCard){
        controller.params.email = zapper.email
        controller.params.name = newCard.name
        controller.params.phoneNumber = newCard.phoneNumber
        def model = controller.live()
        assert "Zapp Appiganigan" == model.card.name
        assert "999-999-9999" == model.card.phoneNumber
    }
    
    void testLiveWhenCardAlreadyLocal(){
        setupMock({})
        def zapper = createZapper()
        zapper.save()
        def card = createCard(zapper)
        card.save()
        testLive(zapper, card)
    }
    
    void testLiveWhenCardAndUserExistInParse(){
        def zap = createZapper()
        def configure ={ it ->
            it.demand.collectRemoteData { ->
                def pap = createZapper()
                pap.save()
                def card = createCard(pap)
                card.save()
            }
        }
        setupMock(configure)
        testLive(zap, createCard(zap))
    }
    
    void testLiveWhenCardInParse(){
        def zap = createZapper()
        def configure ={ it ->
             
            it.demand.collectRemoteData{ ->
                def card = createCard(zap)
                card.save()
            }
            it.demand.createUser{ String email ->
                zap.save(flush:true)
            }
        }
        setupMock(configure)
        testLive(zap, createCard(zap))
    }
    
    void testLiveWhenUserInParse(){
        def zap = createZapper()
        def configure ={ it ->
            it.demand.collectRemoteData{ ->
                zap.save(flush:true) 
            }
            it.demand.createCard{ o, p,u ->
                createCard(zap)
            }
        }
        setupMock(configure)
        testLive(zap, createCard(zap))
    }
    
    void testLiveWhenNothingInParse(){
        def zap = createZapper()
        def configure ={ it ->
            
            it.demand.collectRemoteData{ -> }
            it.demand.createUser{ String email ->
                zap
            }
            it.demand.createCard{ o, p,u ->
                createCard(zap)
            }
        }
        setupMock(configure)
        testLive(zap, createCard(zap))
    }
}