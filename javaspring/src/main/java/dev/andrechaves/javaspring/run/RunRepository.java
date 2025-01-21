package dev.andrechaves.javaspring.run;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

    // Here we can add some custom methods

    List<Run> findAllByLocation(String location);

    List<Run> findAllByLocationAndDistance(String location, Integer distance);

    @Query   // Allows to create own queries
    List<Run> findByDistance(Integer distance);
}
