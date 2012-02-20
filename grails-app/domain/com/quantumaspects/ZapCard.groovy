package com.quantumaspects

class ZapCard {
    String name
    
    static constraints = {
        name blank:false, unique:true
    }
}
