package com.esme.spring.faircorp.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DummyUserService implements UserService {
    @Autowired
    private GreetingService gs;

    @Override
    public void greetAll(){
        String name[] = {"Elodie","Charles"};
        gs.greet(name[0]);
        gs.greet(name[1]);
    }
}
