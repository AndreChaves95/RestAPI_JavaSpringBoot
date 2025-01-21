package dev.andrechaves.javaspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaspringApplication {

	private static final Logger log = LoggerFactory.getLogger(JavaspringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaspringApplication.class, args);
		log.info("Application started successfully!");
	}

}
