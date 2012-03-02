package com.quantumaspects

abstract class ParseIdentity {
    String objectId
    
    static constraints = {
        objectId blank : false, unique : true
    }
}
