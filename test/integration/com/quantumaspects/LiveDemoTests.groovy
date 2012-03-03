package com.quantumaspects

import static org.junit.Assert.*
import org.junit.*

class LiveDemoTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testLiveDemo(){
        def controller = new DemoController()
        controller.params.email = "hoodoo@roo.do"
        controller.params.name = "Mr. Moose"
        controller.params.phoneNumber = "(480)555-3987"
        def model = controller.live()
        assert "Zapp Appiganigan" == model.card.name
        assert "999-999-9999" == model.card.phoneNumber
        assert Zapper.list().size() > 0
        assert ZapCard.list().size() > 0
    }
}
