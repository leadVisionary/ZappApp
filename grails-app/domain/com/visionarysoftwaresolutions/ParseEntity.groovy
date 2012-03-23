package com.visionarysoftwaresolutions

abstract class ParseEntity {
    String objectId
    
    static constraints = {
        objectId blank : false, unique : true
    }
}
