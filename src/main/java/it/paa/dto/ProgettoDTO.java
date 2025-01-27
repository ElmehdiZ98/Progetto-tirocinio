package it.paa.dto;

import it.paa.model.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProgettoDTO {

    public String codiceLocaleProgetto;

    public Boolean aiuto;

    public String responsabileProcedimento;

    public Date startDatetime;

    public Date endDatetime;

    public TipoAttoProcedura tipoAttoProcedura;

    public String numeroAtto;

    public Date dataAtto;

    public String descrizione;

    public BigDecimal importoTotale;

    public TipoNaturaCup tipoNaturaCup;

    public TipoCup tipoCup;

    public TipoAiuto tipoAiuto;

    public StepWorkflow currentState;


}
