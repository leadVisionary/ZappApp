package com.visionarysoftwaresolutions

import grails.test.mixin.*
import org.junit.*
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
@Mock([ ZapCard, Zapper ])

class DemoControllerTests {
    void setUp(){
        def zapp = new Zapper(email:"zapp@zapapp.com", 
                              username:"9999999999", 
                              objectId:"boobah").save(flush:true)
        def card = new ZapCard( owner: zapp,
                               name:"Zapp Appiganigan", 
                               phoneNumber:"999-999-9999",
                               objectId:"loory").save(flush:true)
        println "zapp ${zapp} card ${card}"
    }
    
    private Zapper createZapper(){ new Zapper(email:"moo@you.com", username:"Boogie", objectId:"112233") }
    private ZapCard createCard(Zapper zapper) {
        new ZapCard(
            name:"Visionary Software Solutions",
            phoneNumber: "(480)111-2222",
            owner:zapper,
            objectId:"FOOTCHIE"
        )
    }
    
    private void testLive(Zapper zapper, ZapCard newCard){
        controller.params.email = zapper.email
        controller.params.name = newCard.name
        controller.params.phoneNumber = newCard.phoneNumber
        def model = controller.live()
        assert response.text == """<div>
  <p>
    <span>Name</span> - <span>Zapp Appiganigan</span>
  </p>
  <p>
    <span>Phone Number</span> - <span>999-999-9999</span>
  </p>
</div>"""
    }
    
    void testLiveWhenCardAlreadyLocal(){
        def control = mockFor(SynchronizerService)
        control.demand.collectRemoteData { -> }
        controller.synchronizerService = control.createMock()
        control = mockFor(ParseService)
        control.demand.exchangeCards { Zapper o, String n, String pn ->
            
        }
        controller.parseService = control.createMock()
        
        def zapper = createZapper()
        zapper.save(flush:true)
        def card = createCard(zapper)
        card.save(flush:true)
        //wat?
        println "sending ${zapper} ${card}"
        testLive(zapper, card)
    }
    
    void testLiveWhenCardAndUserExistInParse(){
        def zap = createZapper()
        def control = mockFor(SynchronizerService)
        control.demand.collectRemoteData { ->
                def pap = createZapper()
                pap.save(flush:true)
                def card = createCard(pap)
                card.save(flush:true)
        }
        controller.synchronizerService = control.createMock()
        control = mockFor(ParseService)
        control.demand.exchangeCards { Zapper o, String n, String pn ->
            
        }
        controller.parseService = control.createMock()
        testLive(zap, createCard(zap))
    }
    
    void testLiveWhenCardInParse(){
        def zap = createZapper()
        def control = mockFor(SynchronizerService)
        control.demand.collectRemoteData { ->
                def card = createCard(zap)
                card.save(flush:true)
        }
        controller.synchronizerService = control.createMock()
        control = mockFor(ParseService)
        control.demand.createUser{ String email ->
                zap.save(flush:true)
        }
        control.demand.exchangeCards { Zapper o, String n, String pn ->
            
        }
        controller.parseService = control.createMock()
        testLive(zap, createCard(zap))
    }
    
    void testLiveWhenUserInParse(){
        def zap = createZapper()
        def control = mockFor(SynchronizerService)
        control.demand.collectRemoteData { ->
                zap.save(flush:true)
        }
        controller.synchronizerService = control.createMock()
        control = mockFor(ParseService)
        control.demand.createCard{ o, p,u ->
                createCard(zap)
        }
        control.demand.exchangeCards { Zapper o, String n, String pn ->
            
        }
        controller.parseService = control.createMock()
        testLive(zap, createCard(zap))
    }
    
    void testLiveWhenNothingInParse(){
        def zap = createZapper()
        def control = mockFor(SynchronizerService)
        control.demand.collectRemoteData { -> }
        controller.synchronizerService = control.createMock()
        control = mockFor(ParseService)
        control.demand.createUser{ String email ->
                zap
            }
        control.demand.createCard{ o, p,u ->
                createCard(zap)
            }
        control.demand.exchangeCards { Zapper o, String n, String pn ->
            
        }
        controller.parseService = control.createMock()
        testLive(zap, createCard(zap))
    }
}