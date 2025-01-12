package dev.andrechaves.javaspring.run;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController                 // expects to receive a request and returns a response as a JSON by default
@RequestMapping("/api/runs")    // this way its not needed to duplicate this part of the route
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
        return runRepository.findById(id);
    }

    //@PostMapping("/{id}")

    //@DeleteMapping("/{id}")
}
