package com.quantumaspects



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
@Mock([ZapCard, DatastoreService])
class DemoControllerTests {

    void testCharacterizeCollect() {
        def control = mockFor(DatastoreService)
        control.demand.collectRemoteData{ -> ['result'] }
        controller.datastoreService = control.createMock()
        def result = controller.collect()
        assert result == ['result']
    }
    
    void testBroadcast(){
        ZapCard dummy = new ZapCard(name:"Visionary Software Solutions")
        mockDomain(ZapCard, [dummy])
        controller.broadcast()
        assert "Visionary Software Solutions" == response.json.name
    }
}
