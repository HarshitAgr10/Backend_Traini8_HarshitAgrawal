package dev.harshit.traini8.repositories;

import dev.harshit.traini8.models.TrainingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {

    /**
     * Retrieves a training center by its center code.
     *
     * @param centerCode the unique code of the training center
     * @return an Optional containing the found TrainingCenter or empty if not found
     */
    Optional<TrainingCenter> findByCenterCode(String centerCode);


    /**
     * Finds training centers based on the provided city and state.
     * If either parameter is null, it will not filter by that parameter.
     */
    @Query("SELECT t FROM TrainingCenter AS t WHERE " +
            "(:city IS NULL OR t.address.city = :city) AND " +
            "(:state IS NULL OR t.address.state = :state)")
    List<TrainingCenter> findTrainingCentersByLocation(String city, String state);


    /**
     * Saves a training center to the repository.
     * @return the saved TrainingCenter object
     */
    TrainingCenter save(TrainingCenter trainingCenter);


    /**
     * Retrieves all training centers from the repository.
     *
     * @return a list of all TrainingCenter objects
     */
    List<TrainingCenter> findAll();
}
