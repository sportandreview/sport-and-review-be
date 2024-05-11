package it.sportandreview.constants;

import java.net.URI;

public class ErrorConstants {

    public static final String PROBLEM_BASE_URL = "https://www.sportandreview.it/problem";
    public static final URI INVALID_TOKEN = URI.create(PROBLEM_BASE_URL + "/invalid-token");
    public static final URI NOT_FOUND = URI.create(PROBLEM_BASE_URL + "/not-found");
    public static final URI DECODE_EXCEPTION = URI.create(PROBLEM_BASE_URL + "/decode-exception");
    public static final URI INTERNAL_ERROR = URI.create(PROBLEM_BASE_URL + "/internal-error");

    private ErrorConstants() {
    }
}
