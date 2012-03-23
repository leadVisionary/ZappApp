import com.visionarysoftwaresolutions.*

class BootStrap {

    def init = { servletContext ->
        createZapp()
    }
    def destroy = {
    }
    
    private void createZapp(){
        def zapp = Zapper.findByEmail("zapp@zapapp.com") ?:
                   new Zapper(email:"zapp@zapapp.com",
                              "username": "9999999",
                              objectId:"3fptmWPn9D").save(failOnError:true)
        def card = ZapCard.findByName("Zapp Appiganigan") ?:
                   new ZapCard( owner: zapp,
                               name:"Zapp Appiganigan", 
                               phoneNumber:"999-999-9999",
                               objectId:"h2kHrjZWcb").save(failOnError:true)
    }
}
