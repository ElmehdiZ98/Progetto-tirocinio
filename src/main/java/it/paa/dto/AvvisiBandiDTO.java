package it.paa.dto;


import it.paa.model.StepWorkflow;
import it.paa.model.TipoProceduraAttivazione;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AvvisiBandiDTO {

    public String codiceProceduraAttivazione;

    public String descrizione;

    public TipoProceduraAttivazione tipoProceduraAttivazione;

    public BigDecimal importoTotale;

    public StepWorkflow currentState;

    public String codice;

    public String denominazione;

    public String topLevel;



    public AvvisiBandiDTO(String codiceProceduraAttivazione, String descrizione, TipoProceduraAttivazione tipoProceduraAttivazione,
                          BigDecimal importoTotale, StepWorkflow currentState, String codice,
                           String denominazione, String topLevel) {
        this.codiceProceduraAttivazione = codiceProceduraAttivazione;
        this.descrizione = descrizione;
        this.tipoProceduraAttivazione = tipoProceduraAttivazione;
        this.importoTotale = importoTotale;
        this.currentState = currentState;
        this.codice = codice;
        this.denominazione = denominazione;
        this.topLevel = topLevel;
    }
}
