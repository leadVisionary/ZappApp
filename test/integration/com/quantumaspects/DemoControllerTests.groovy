package com.quantumaspects

import static org.junit.Assert.*
import org.junit.*

class DemoControllerTests {

    @Before
    void setUp() {
        // Setup logic here
    }

    @After
    void tearDown() {
        // Tear down logic here
    }

    @Test
    void testBroadcastWhenNoCards(){
        def controller = new DemoController()
        controller.broadcast()
        assert controller.response.json.name
    }
}
