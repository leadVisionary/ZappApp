import com.quantumaspects.*

class BootStrap {

    def init = { servletContext ->
        createZapp()
    }
    def destroy = {
    }
    
    private void createZapp(){
        def zapp = Zapper.findByEmail("zapp@zapapp.com") ?:
                   new Zapper(email:"zapp@zapapp.com",parseObjectId:"M2VnuACvfh").save(failOnError:true)
        def card = ZapCard.findByName("Zapp Appiganigan") ?:
                   new ZapCard( owner: zapp,
                               name:"Zapp Appiganigan", 
                               phoneNumber:"999-999-9999",
                               parseObjectId:"ny88sqNv3Z").save(failOnError:true)
    }
}
