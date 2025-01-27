package it.paa.dto;

import it.paa.model.TipoProgramma;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ListaArticolazioneDTO {

    public String codice;

    public BigDecimal dotazioneFinanziaria;

    public String titolo;

    public TipoProgramma tipoProgramma;

    public String tipoFondo;

}
