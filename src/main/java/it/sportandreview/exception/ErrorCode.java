package it.sportandreview.exception;

public enum ErrorCode {
    USER_ALREADY_EXISTS(1001, "L'indirizzo email è già in uso."),
    CREATE_ENTITY_ERROR(1002, "Errore nella creazione dell'entità."),
    INVALID_REFRESH_TOKEN(1003, "Token di refresh non valido.");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
