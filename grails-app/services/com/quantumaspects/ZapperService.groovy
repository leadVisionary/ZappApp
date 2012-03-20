package com.quantumaspects

class ZapperService {

    def createUsersFromCollection(Collection result){
        result.each{
            try {
                def zapper = new Zapper(  email : it.email,
                    username : it.username,
                    objectId : it.objectId
                )
                if(zapper.validate()){
                    zapper.save(flush:true)
                }
            }
            catch(Exception e){
                log.error "got problem ${e.message}"
            }
        }
    }
    
    public Zapper createUser(String email, String username){
        createUser(email, username, "dummy")
    }
    
    public Zapper createUser(String email, String username, String objectId){
        new Zapper(email:email, username:username, objectId:objectId).save(flush:true)
    }
}
