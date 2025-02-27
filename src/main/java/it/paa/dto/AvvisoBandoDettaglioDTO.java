package it.paa.dto;

import it.paa.model.TipoAiuto;
import it.paa.model.TipoAtto;
import it.paa.model.TipoAttoProcedura;
import it.paa.model.TipoNaturaCup;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AvvisoBandoDettaglioDTO {

    public String descrizione;

    public TipoNaturaCup tipoNaturaCup;

    public TipoAttoProcedura tipoAttoProcedura;

    public String numeroAtto;

    public Date dataAtto;

    public String responsabileProcedimento;

    public Date startDatetime;

    public Date endDatetime;

    public BigDecimal importoTotale;

    public Boolean aiuto;

    public TipoAiuto tipoAiuto;

    public boolean propostaProgettuale;


}
