package dev.andrechaves.javaspring.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)   // Makes it return a 404
public class RunNotFoundException extends RuntimeException{

    public RunNotFoundException() {
        super("Instance Not Found !!!");
    }
}
