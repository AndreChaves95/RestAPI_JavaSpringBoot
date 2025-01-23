package dev.andrechaves.javaspring.user;

import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface UserHttpClient {

    // GetExchange -> tells Spring to make an exchange with another service
    @GetExchange("/users")
    List<User> findAll();

    @GetExchange("/{id}")
    User findById(Integer id);

}