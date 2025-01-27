package it.paa.validation.exception;


import jakarta.ws.rs.WebApplicationException;
import it.paa.error.ErrorJSON;
import it.paa.error.JSONExceptionable;
import it.paa.validation.dto.ValidationResponse;
import it.paa.validation.dto.ValidationMessage;
/**
 * Gestione del messaggio di validazione come eccezione bloccante
 */
public class ValidationException extends WebApplicationException implements JSONExceptionable {

    private final ValidationResponse validationResponse;

    public ValidationException(ValidationResponse validationResponse) {
        super(null !=
                validationResponse.getUserMessage() ? validationResponse.getUserMessage() : ValidationResponse.DEFAULT_MESSAGE);
        this.validationResponse = validationResponse;
    }

    public ValidationException(String message) {
        super(message);
        validationResponse = ValidationResponse.make()
                .add(ValidationMessage.build()
                        .append(message)
                        .error()
                );
    }

    @Override
    public ErrorJSON makeErrorJSON() {
        return new ErrorJSON(validationResponse);
    }

    public String toString() {
        return getClass().getSimpleName() + ':' + validationResponse.toString();
    }
}
