package dev.andrechaves.javaspring.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(JdbcClientRunRepository.class)     // This is needed because of Autowired
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class JdbcClientRunRepositoryTest {

    @Autowired
    JdbcClientRunRepository jdbcRepository;

    @BeforeEach
    void setUp() {
        jdbcRepository.create(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                "Indoor",
                null));

        jdbcRepository.create(new Run(2,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                "Indoor",
                null));
    }

    @Test
    void shouldFindAllRuns() {
        List<Run> runs = jdbcRepository.findAll();
        assertEquals(2, runs.size());
    }

    @Test
    void shouldFindRunWithValidId() {
        var run = jdbcRepository.findById(1).get();
        assertEquals("Monday Morning Run", run.title());
        assertEquals(3, run.distance());
    }

    @Test
    void shouldNotFindRunWithInvalidId() {
        var run = jdbcRepository.findById(3);
        assertTrue(run.isEmpty());
    }

    @Test
    void shouldCreateNewRun() {
        jdbcRepository.create(new Run(3,
                "Friday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                "Indoor",
                null));
        List<Run> runs = jdbcRepository.findAll();
        assertEquals(3, runs.size());
    }

    @Test
    void shouldDeleteRun() {
        jdbcRepository.delete(1);
        List<Run> runs = jdbcRepository.findAll();
        assertEquals(1, runs.size());
    }

}