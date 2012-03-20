package com.quantumaspects

class Zapper extends ParseEntity {
    String email
    String username
    
    static hasMany = [ cards: ZapCard ]
    static constraints = {
        email blank : false, unique : true, email : true
        username blank : false, unique : true
    }
    
    @Override
    public String toString() { "${username} - ${email} " }
}
