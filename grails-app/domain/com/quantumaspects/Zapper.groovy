package com.quantumaspects

class Zapper extends ParseEntity {
    String email
    
    static hasMany = [ cards: ZapCard ]
    static constraints = {
        email blank : false, unique : true, email : true
    }
    
    @Override
    public String toString() { "${email} - ${cards}" }
}
