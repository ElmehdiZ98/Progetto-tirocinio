package it.paa.error;

import jakarta.ws.rs.core.Response;
import lombok.Getter;
import lombok.Setter;
import it.paa.validation.dto.ValidationResponse;

@Getter
@Setter
public class ErrorJSON {

    protected String type;

    // TODO userMessage deve essere inteso come messaggio chiaro per il team di frontend in caso di errore applicativo
    //  oppure nella richiesta invalida o incompleta da parte del client.
    //  I messaggi di errore indirizzati all'utente finale (operatore, funzionario, etc...)  devo essere gestiti
    //  tramite il fronted, oppure userMessage deve contenere un messaggio generico di circostanza (questo implica
    //  rework in tutto il codice, in quanto questi userMessage spesso in realtà non sono utili per l'utente finale).
    //  Inoltre i messaggi devono contenere le chiavi delle label, le quali dovranno essere censite e centralizzate
    //  in un file di bundle o in una tabella sul database reiniziabile on demand tramite API di amministrazione.
    protected String userMessage;

    // TODO internalMessage dovrà contenere il message della cause exception, se esiste, altrimenti un messaggio che
    //  include la entity type, la entity ID e le operazioni richieste, per motivi di debug. E' un messaggio che deve apparire
    //  anche nei log.
    protected String internalMessage;

    // TODO code dovrebbe contenere un codice di errore ancora da definire
    protected String code;

    // TODO moreInfo dovrebbe contenere lo stack trace dell'eccezione sollevata, per motivi di debug.
    protected String moreInfo;
    protected Integer httpCode;

    /* Dato presente solo se il messaggio viene generato da un {ValidationException} */
    protected ValidationResponse validationResponse;

    public ErrorJSON() {
    }

    public ErrorJSON(String userMessage,
                     Response.Status httpCode) {
        type = "RUNTIME";
        this.userMessage = userMessage;
        internalMessage = userMessage;
        code = "DA_DEFINIRE";
        moreInfo = "DA_DEFINIRE";
        this.httpCode = httpCode.getStatusCode();
    }

    public ErrorJSON(ValidationResponse validationResponse) {
        this(validationResponse, null);
    }

    public ErrorJSON(ValidationResponse validationResponse, String internalMessage) {
        type = ErrorType.VALIDATION.name();
        this.validationResponse = validationResponse;
        userMessage = validationResponse.getUserMessage();
        this.internalMessage = null != internalMessage ? internalMessage : "Validation error";
        code = "DA_DEFINIRE";
        moreInfo = "DA_DEFINIRE";
        httpCode = Response.Status.BAD_REQUEST.getStatusCode();
    }
}

