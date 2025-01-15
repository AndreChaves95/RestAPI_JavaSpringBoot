package dev.andrechaves.javaspring.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

// Record already includes: getters for all fields, equals, hasCode, toString
public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive
        Integer distance,
        String location) {

    // Validation of data inserted by the user
    public Run {
        if(completedOn.isBefore(startedOn)) {
            throw new IllegalArgumentException("Completed On can not be previous than Started On!");
        }
    }
}


