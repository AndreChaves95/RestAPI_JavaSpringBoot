package dev.andrechaves.javaspring;

import org.springframework.stereotype.Component;

@Component
public class WelcomeMessage {

    // This class is marked as @Component
    // to be available to Spring

    public String getWelcomeMessage() {
        return "Welcome to my Project of Java with Spring Boot";
    }
}
