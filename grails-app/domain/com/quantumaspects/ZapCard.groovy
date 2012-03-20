package com.quantumaspects

class ZapCard extends ParseEntity {
    String name
    String phoneNumber
    
    static belongsTo = [ owner : Zapper ]
    
    static constraints = {
        name blank:false, unique:true
        phoneNumber matches:/^[01]?\s*[\(\.-]?(\d{3})[\)\.-]?\s*(\d{3})[\.-](\d{4})$/
    }
    
    @Override
    public String toString(){ "${name} - ${phoneNumber}" }
}
