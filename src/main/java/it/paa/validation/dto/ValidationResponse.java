package it.paa.validation.dto;

import it.paa.validation.exception.ValidationException;

import java.util.ArrayList;
import java.util.List;

public final class ValidationResponse {

    public static final String DEFAULT_MESSAGE = "Alcuni dati risultano mancanti o non validi";

    public static final String SQL_EXCEPTION_UNIQUE_VIOLATION = "23505";

    public static final String UNIQUE_VIOLATION_MESSAGE = "Elemento già esistente";

    private String userMessage;
    private List<ValidationMessage> messageList;
    private Result result;

    private ValidationResponse() {
    }

    public static ValidationResponse make() {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.userMessage = DEFAULT_MESSAGE;
        validationResponse.result = Result.OK;
        validationResponse.messageList = new ArrayList<>();
        return validationResponse;
    }

    public static ValidationResponse make(String userMessage) {
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.userMessage = userMessage;
        validationResponse.result = Result.OK;
        validationResponse.messageList = new ArrayList<>();
        return validationResponse;
    }

    public static ValidationResponse make(org.hibernate.exception.ConstraintViolationException exception) {
        ValidationResponse validationResponse;
        if (!exception.getSQLException()
                .getSQLState()
                .equals(SQL_EXCEPTION_UNIQUE_VIOLATION)) {
            validationResponse = make();
        } else {
            validationResponse = new ValidationResponse();
            validationResponse.userMessage = UNIQUE_VIOLATION_MESSAGE;
            validationResponse.result = Result.KO;
            validationResponse.messageList = new ArrayList<>();
        }
        return validationResponse;
    }

    public ValidationResponse add(ValidationMessage validationMessage) {
        if (validationMessage.hasError()) {
            result = Result.KO;
        }
        messageList.add(validationMessage);
        return this;
    }

    public List<ValidationMessage> getMessageList() {
        return messageList;
    }

    public Result getResult() {
        return result;
    }

    public boolean hasError() {
        return Result.KO == result;
    }

    public boolean isOk() {
        return Result.OK == result;
    }

    public ValidationResponse orElseThrow() {
        if (Result.KO == result) {
            throw new ValidationException(this);
        }
        return this;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String toString() {
        return messageList.toString();
    }

}

