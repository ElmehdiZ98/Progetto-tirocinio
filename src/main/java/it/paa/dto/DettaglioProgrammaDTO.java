package it.paa.dto;

import it.paa.model.Articolazione;
import it.paa.model.Atto;
import it.paa.model.TipoAtto;
import it.paa.model.TipoProgramma;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DettaglioProgrammaDTO {

    public String codice;

    public String descrizione;

    public Atto atto;

    public TipoAtto tipoAtto;

    public String numeroAtto;

    public Date dataAtto;

    public String titolo;

    public TipoProgramma tipoProgramma;

    public String tipoFondo;

    public BigDecimal dotazioneFinanziaria;

    public BigDecimal dotazioneFinanziariaTotale;

    public Articolazione articolazione;

}
