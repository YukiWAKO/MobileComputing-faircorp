package com.esme.spring.faircorp;

import com.esme.spring.faircorp.hello.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaircorpApplicationConfig {
    @Bean
    public CommandLineRunner greetingCommandLine(GreetingService gs){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("******************************************");
                gs.greet("Spring");
            }
        };
    }
}
