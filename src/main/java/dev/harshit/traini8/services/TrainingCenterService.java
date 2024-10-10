package dev.harshit.traini8.services;

import dev.harshit.traini8.exceptions.DuplicateTrainingCenterException;
import dev.harshit.traini8.models.TrainingCenter;
import dev.harshit.traini8.repositories.TrainingCenterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingCenterService {

    /**
     * Service class handles the business logic related to training centers,
     * including saving new training centers and retrieving registered centers based
     * on location.
     */

    private final TrainingCenterRepository trainingCenterRepository;

    public TrainingCenterService(TrainingCenterRepository trainingCenterRepository) {
        this.trainingCenterRepository = trainingCenterRepository;
    }

    /**
     * Saves a new training center after checking for duplicates by center code.
     * Sets the creation timestamp in Epoch milliseconds based on the current
     * time in the Asia/Kolkata timezone.
     *
     * @param trainingCenterRequest the training center to be saved
     * @return the saved TrainingCenter object
     * @throws DuplicateTrainingCenterException if the center code already exists
     */

    public TrainingCenter saveTrainingCenter(TrainingCenter trainingCenterRequest) throws DuplicateTrainingCenterException {

        Optional<TrainingCenter> existingCenter = trainingCenterRepository.findByCenterCode(trainingCenterRequest.getCenterCode());
        if(existingCenter.isPresent()) {
            throw new DuplicateTrainingCenterException("Training Center already registered");
        }


        // Get the current LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();

        // Convert LocalDateTime to ZonedDateTime in Asia/Kolkata timezone
        ZoneId indiaTimeZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime zonedDateTime = localDateTime.atZone(indiaTimeZone);

        // Convert ZonedDateTime to Epoch milliseconds
        long epochTime = zonedDateTime.toInstant().toEpochMilli();

        // Set the createdOn field with the local Epoch time
        trainingCenterRequest.setCreatedOn(epochTime);


        return trainingCenterRepository.save(trainingCenterRequest);

    }

    /**
     * Retrieves a list of registered training centers based on the provided
     * city and state. If both are null, all registered training centers are
     * returned.
     *
     * @param city the city to filter by (optional)
     * @param state the state to filter by (optional)
     * @return a list of registered TrainingCenter objects
     */

    public List<TrainingCenter> getRegisteredTrainingCenters(String city, String state) {
        if (city != null || state != null) {
            return trainingCenterRepository.findTrainingCentersByLocation(city, state);
        }

        return trainingCenterRepository.findAll();
    }
}
