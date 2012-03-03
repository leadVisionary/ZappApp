package com.quantumaspects

abstract class ParseEntity {
    String objectId
    
    static constraints = {
        objectId blank : false, unique : true
    }
}
