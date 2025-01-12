package dev.andrechaves.javaspring.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component // so it can be Spring to manage this
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Run findById(int id) {
        return runs.stream()
                .filter(run -> run.id() == id)
                .findFirst()
                .get();
    }

    @PostConstruct //used on a method that needs to be executed after dependency injection is done to perform any initialization
    private void init() {
        Run run1 = new Run(1, "Title1", LocalDateTime.now(), LocalDateTime.now().plusMinutes(40), 10, Location.OUTDOOR);
        runs.add(run1);
        Run run2 = new Run(2, "Title2", LocalDateTime.now(), LocalDateTime.now().plusMinutes(40), 11, Location.INDOOR);
        runs.add(run2);
        Run run3 = new Run(3, "Title3", LocalDateTime.now(), LocalDateTime.now().plusMinutes(40), 12, Location.OUTDOOR);
        runs.add(run3);
    }
}
