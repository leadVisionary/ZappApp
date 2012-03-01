package com.quantumaspects

abstract class ParseEntity {
    String parseObjectId
    
    static constraints = {
        parseObjectId blank : false, unique : true
    }
}
