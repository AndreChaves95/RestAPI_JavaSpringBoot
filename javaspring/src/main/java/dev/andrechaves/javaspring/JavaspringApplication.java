package dev.andrechaves.javaspring;

import dev.andrechaves.javaspring.run.Run;
import dev.andrechaves.javaspring.run.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class JavaspringApplication {

	private static final Logger log = LoggerFactory.getLogger(JavaspringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaspringApplication.class, args);
		log.info("Application started successfully!");
	}

}
