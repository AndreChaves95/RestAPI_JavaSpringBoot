package dev.andrechaves.javaspring.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Component // so it can be Spring to manage this
public class JdbcClientRunRepository {

    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);

    //Using as Dependency Injection
    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM Run")
                .query(Run.class)
                .list();
    }

    // Here: returning an Optional because it allows to operate on objects without having a null value present
    Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM Run WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run) {
        var created = jdbcClient.sql("INSERT INTO Run (id, title, started_on, completed_on, distance, location)\n" +
                "VALUES (?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.distance(), run.location()))
                .update();

        // Using this as it is expected that only one row is affected (creation of 1 Run)
        Assert.state(created == 1, "Failed to create new Run: " + run.title());
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE Run SET title=?, started_on=?, completed_on=?, distance=?, location=?, version=? WHERE id=?")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.distance(), run.location(), run.version()))
                .update();

        Assert.state(updated == 1, "Failed to update Run: " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Run WHERE id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete Run with id: " + id);
    }

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("SELECT * FROM Run WHERE location = :location")
                .param("location", location)
                .query(Run.class)
                .list();
    }
}
