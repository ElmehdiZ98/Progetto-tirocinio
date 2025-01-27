package it.paa.exception;

import jakarta.ws.rs.WebApplicationException;
import it.paa.error.ErrorJSON;
import it.paa.error.JSONExceptionable;


public class EntityException extends WebApplicationException implements JSONExceptionable {
    private final String internalMessage;
    private final String userMessage;
    public EntityException(String internalMessage, String userMessage) {

        this.internalMessage = internalMessage;
        this.userMessage = userMessage;
    }

    @Override
    public ErrorJSON makeErrorJSON() {
        ErrorJSON errorJSON = new ErrorJSON();
        errorJSON.setCode("DA_DEFINIRE");
        errorJSON.setInternalMessage(internalMessage);
        errorJSON.setUserMessage(userMessage);
        errorJSON.setMoreInfo("DA_DEFINIRE");
        errorJSON.setType("RUNTIME");
        errorJSON.setHttpCode(400);
        return errorJSON;
    }
}
