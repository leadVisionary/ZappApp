package com.visionarysoftwaresolutions

import static org.junit.Assert.*
import org.junit.*

class LiveDemoTests {
    def parseService
    
    
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
        controller.live()
        assert Zapper.list().size() > 0
        assert ZapCard.list().size() > 0
        assert controller.response.text == """<div>
  <p>
    <span>Name</span> - <span>Zapp Appiganigan</span>
  </p>
  <p>
    <span>Phone Number</span> - <span>999-999-9999</span>
  </p>
</div>""" 
        
    }
}
