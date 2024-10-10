package dev.harshit.traini8.controllers;

import dev.harshit.traini8.exceptions.DuplicateTrainingCenterException;
import dev.harshit.traini8.models.TrainingCenter;
import dev.harshit.traini8.services.TrainingCenterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingCenterController {

    /**
     * Controller handles incoming HTTP requests related to training centers,
     * providing endpoints for registering new centers and retrieving existing centers.
     */

    private final TrainingCenterService trainingCenterService;

    public TrainingCenterController(TrainingCenterService trainingCenterService) {
        this.trainingCenterService = trainingCenterService;
    }

    /**
     * Registers a new training center
     *
     * @param trainingCenterRequest the training center details provided in the request
     * @return a ResponseEntity containing the registered TrainingCenter and a status code
     * @throws DuplicateTrainingCenterException if a training center with the same center code already exists
     */

    @PostMapping("/trainingcenters")
    public ResponseEntity<TrainingCenter> registerTrainingCenter(
            @Valid @RequestBody TrainingCenter trainingCenterRequest) throws DuplicateTrainingCenterException {

        TrainingCenter registeredTrainingCenter  = trainingCenterService.saveTrainingCenter(trainingCenterRequest);
        return new ResponseEntity<>(registeredTrainingCenter , HttpStatusCode.valueOf(200));
    }

    /**
     * Retrieves a list of registered training centers, optionally filtering by city and state.
     *
     * @param city the city to filter by (optional)
     * @param state the state to filter by (optional)
     * @return a ResponseEntity containing a list of TrainingCenter objects and an HTTP status
     */

    @GetMapping("/trainingcenters")
    public ResponseEntity<List<TrainingCenter>> getTrainingCenters(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state) {
        List<TrainingCenter> trainingCenters = trainingCenterService.getRegisteredTrainingCenters(city, state);
        return new ResponseEntity<>(trainingCenters, HttpStatus.OK);
    }
}
