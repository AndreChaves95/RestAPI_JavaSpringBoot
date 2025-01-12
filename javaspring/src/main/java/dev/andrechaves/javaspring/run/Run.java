package dev.andrechaves.javaspring.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer distance,
        Location location) {}

// Record already includes: getters for all fields, equals, hasCode, toString
