package com.quantumaspects

class ZapperService {

    def createUsersFromCollection(Collection result){
        result.each{
            def zapper = new Zapper(  email : it.email ?: "", 
                                      objectId : it.objectId ?: ""
                                  )
            if(zapper.validate()){
                zapper.save(flush:true)
            }
        }
    }
    
    public Zapper createUser(String email){
        createUser(email, "dummy")
    }
    
    public Zapper createUser(String email, String objectId){
        new Zapper(email:email, objectId:objectId).save(flush:true)
    }
}
