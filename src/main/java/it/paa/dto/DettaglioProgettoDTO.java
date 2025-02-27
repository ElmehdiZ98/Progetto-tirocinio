package it.paa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.paa.model.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DettaglioProgettoDTO {

    public String titolo;

    public String sintesiProgetto;

    public Date startDatetime;

    public String cupDefinitivo;

    public TipoNaturaCup tipoNaturaCup;

    public TipoCup tipoCup;

    public String codiceProceduraAttivazione;

    public AttoProceduraAttivazione atto;

    public BigDecimal costo ;

    public BigDecimal importoTotale;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<Localizzazione> localizzazioneList;

    public TipoCodiceAteco tipoCodiceAteco;

    public String codice;

   // public Articolazione articolazione;

    public String descrizione;

    public ProgettoProgramma progettoProgramma;

    public TipoCampoIntervento tipoCampoInterventoList;   //campo di intervento

    public TipoFormeFinanziamento tipoFormeFinanziamentoList;    //forme finanziamento

    public TipoDimensioneTematicaSecondaria tipoDimensioneTematicaSecondariaList;   // dimensione tematica secondaria

    public TipoRisultatoAtteso tipoRisultatoAttesoList;   //risultato atteso

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<SoggettoDTO> soggetti;


}
