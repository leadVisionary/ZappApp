package com.quantumaspects

import grails.test.mixin.*
import org.junit.*
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DemoController)
@Mock([ZapCard,DatastoreService])

class DemoControllerTests {
    void testBroadcastWhenCardAlreadyLocal(){
        new ZapCard(
            name:"Visionary Software Solutions",
            phoneNumber: "(480)111-2222"
        ).save()
        controller.broadcast()
        assert "Visionary Software Solutions" == response.json.name
    }
}
