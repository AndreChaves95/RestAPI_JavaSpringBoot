package dev.andrechaves.javaspring.run;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController                 // expects to receive a request and returns a response as a JSON by default
@RequestMapping("/api/runs")    // this way it is not needed to duplicate this part of the route
public class RunController {

    private RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/homepage")
    String defaultPage() {
        return "Homepage!";
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepository.findAll();
    }

    @GetMapping("/{id}")
    Run findById(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()) {
            throw new RunNotFoundException();
        }
        return run.get();
    }

    @ResponseStatus(HttpStatus.CREATED)  // This will make it return 201 with notification of creation
    @PostMapping("")
    void create(@Valid @RequestBody Run run) {  // RequestBody used so it can handle with JSON request
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)  // This will make it return 204 as it works but there is nothing to return
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Run run) {  // RequestBody used so it can handle with JSON request
        runRepository.save(run);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {  // RequestBody used so it can handle with JSON request
        runRepository.delete(runRepository.findById(id).get());
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(location);
    }

    @GetMapping("/location/{location}/{distance}")
    List<Run> findByLocationAndDistance(@PathVariable String location, @PathVariable Integer distance) {
        return runRepository.findAllByLocationAndDistance(location, distance);
    }
}
