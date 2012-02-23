package com.quantumaspects

class ZapCard {
    String name
    String phoneNumber
    
    static constraints = {
        name blank:false, unique:true
        phoneNumber matches:/^[01]?\s*[\(\.-]?(\d{3})[\)\.-]?\s*(\d{3})[\.-](\d{4})$/
    }
}
