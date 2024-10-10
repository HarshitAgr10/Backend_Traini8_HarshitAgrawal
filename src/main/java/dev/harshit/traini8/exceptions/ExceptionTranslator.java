package dev.harshit.traini8.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionTranslator {

    /**
     * Class serves as a model for translating exceptions into a standardized
     * response format, including a message and a corresponding HTTP status code.
     */

    private String message;
    private int statusCode;

}
