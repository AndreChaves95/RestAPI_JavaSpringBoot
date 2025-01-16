package dev.andrechaves.javaspring.run;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component  // Need this to Spring knows it has to deal with it
public class RunsJsonDataLoader implements CommandLineRunner {
    // This runs after Application context has been created (all Beans created)

    private static final Logger log = LoggerFactory.getLogger(RunsJsonDataLoader.class);
    private final RunRepository runRepository;
    private final ObjectMapper objectMapper;

    // Here using Constructor Injection -> Spring adds an instance of RunRepository
    public RunsJsonDataLoader(RunRepository runRepository, ObjectMapper objectMapper) {
        this.runRepository = runRepository;
        this.objectMapper = objectMapper; // Using ObjectMapper to map JSON received and deserialize into the objects
    }

    @Override
    public void run(String... args) throws Exception {
        if(runRepository.findAll().isEmpty()) {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                // Deserialize into a List of Run instances
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                runRepository.saveAll(allRuns.runs());
            } catch(IOException exception) {
                throw new RuntimeException("Failed to read JSON data: ", exception);
            }
        } else {
            log.info("Not loading JSON because already contains data!");
        }
    }
}
