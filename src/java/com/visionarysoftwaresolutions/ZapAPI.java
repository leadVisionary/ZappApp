package com.visionarysoftwaresolutions;

public interface ZapAPI {
    Zapper createUser(String email);
    ZapCard createCard(Zapper owner, String name, String phoneNumber);
    void exchangeCards(Zapper sender, ZapCard zappCard, Zapper recipient);
}
