package it.paa.dto;


import it.paa.model.MacroCategoriaModalitaFormativa;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CorsoDTO {

    public String codiceCorso;

    public String titoloCorso;

    public String macroCategoriaModalitaFormativa;

    public Date dataInizio;

    public Date dataFine;

    public Integer durata;


}
