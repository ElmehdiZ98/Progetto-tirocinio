package it.paa.validation.dto;

import lombok.ToString;

@ToString
public final class ValidationMessage {

    private StringBuilder message;

    private StringBuilder field;

    private Severity severity;

    private ValidationMessage() {
    }

    public static ValidationMessage build() {
        ValidationMessage response = new ValidationMessage();
        response.message = new StringBuilder();
        response.field = new StringBuilder();
        response.severity = Severity.INFO;
        return response;
    }

    public static ValidationMessage build(Severity severity, String message) {
        ValidationMessage response = new ValidationMessage();
        response.message = new StringBuilder(message);
        response.field = new StringBuilder();
        response.severity = severity;
        return response;
    }

    public static ValidationMessage build(Severity severity, String message, Object... parameters) {
        ValidationMessage response = new ValidationMessage();
        response.message = new StringBuilder(String.format(message, parameters));
        response.field = new StringBuilder();
        response.severity = severity;
        return response;
    }

    public ValidationMessage append(String message) {
        this.message.append(message);
        return this;
    }

    public ValidationMessage field(String field) {
        this.field.append(field);
        return this;
    }

    public ValidationMessage append(String message, Object... parameters) {
        this.message.append(String.format(message, parameters));
        return this;
    }

    public ValidationMessage info() {
        severity = Severity.INFO;
        return this;
    }

    public ValidationMessage warning() {
        severity = Severity.WARNING;
        return this;
    }

    public ValidationMessage error() {
        severity = Severity.ERROR;
        return this;
    }

    public String getMessage() {
        return message.toString();
    }

    public String getField() {
        return field.toString();
    }

    public Severity getSeverity() {
        return severity;
    }

    protected boolean hasError() {
        return Severity.ERROR == severity;
    }
}
