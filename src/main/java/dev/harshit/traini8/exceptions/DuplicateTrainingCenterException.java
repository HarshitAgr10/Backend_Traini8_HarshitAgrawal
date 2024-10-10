package dev.harshit.traini8.exceptions;

public class DuplicateTrainingCenterException extends Exception {

    /**
     * Custom exception thrown when trying to register a training center
     * that already exists in the system, identified by its unique center code.
     */

    public DuplicateTrainingCenterException(String message) {
        super(message);
    }
}
