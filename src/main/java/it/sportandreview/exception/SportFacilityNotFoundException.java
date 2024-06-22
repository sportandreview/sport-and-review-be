package it.sportandreview.exception;

public class SportFacilityNotFoundException extends RuntimeException {
    public SportFacilityNotFoundException(Long id) {
        super("Struttura sportiva non trovata con ID: " + id);
    }
}
