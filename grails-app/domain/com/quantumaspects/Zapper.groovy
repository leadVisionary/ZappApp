package com.quantumaspects

class Zapper {
    String email
    
    static hasMany = [ cards: ZapCard ]
    static constraints = {
        email blank : false, unique : true, email : true
    }
}
