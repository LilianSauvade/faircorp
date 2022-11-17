package com.emse.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {

    private GreetingService GS;

    @Autowired
    public DummyUserService(GreetingService GS) {
        this.GS = GS;
    }

    public GreetingService getGS() {
        return this.GS;
    }

    public void greetAll() {
        String[] list = {"Elodie", "Charles"};

        for(String name : list) {
            GS.greet(name);
        }
    }
}
