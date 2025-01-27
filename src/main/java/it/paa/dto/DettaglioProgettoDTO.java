package it.paa.dto;

import it.paa.model.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DettaglioProgettoDTO {

    public String titolo;         //titolo

    public String sintesiProgetto;   //sintesi

    public Date startDatetime;              //Data di avvio

    public ProceduraAttivazione procedura;

    public String cupDefinitivo;         //CUP defenitivo

    public TipoNaturaCup tipoNaturaCup;  //Tipo Natura CUP

    public String codiceProceduraAttivazione;     //Codice IGRUE

    public AttoProceduraAttivazione atto;

    public BigDecimal costo ;

    public BigDecimal importoTotale;

    public Regione regione;

    public Provincia provincia;

    public Comune comune;

    public String denominazione;

    public TipoSoggettoDiritto tipoSoggettoDiritto;

    public String cfPiva;

    public String codiceIPA;         //codiceIPA

    public TipoFormaGiuridica tipoFormaGiuridica;        //Tipo forma giudiziaria

    public TipoCodiceAteco tipoCodiceAteco;

    public Articolazione articolazione;           //articolazione

    public List<TipoCampoIntervento> tipoCampoInterventoList;   //campo di intervento

    public List<TipoFormeFinanziamento> tipoFormeFinanziamentoList;    //forme finanziamento

    public List<TipoDimensioneTematicaSecondaria> tipoDimensioneTematicaSecondariaList;   // dimensione tematica secondaria

    public List<TipoRisultatoAtteso> tipoRisultatoAttesoList;   //risultato atteso

    public String nome;   // nome

    public String cognome;  //cognome
}
