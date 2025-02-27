package it.paa.dto;

import it.paa.model.TipoMetodologia;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DettaglioCorsoDTO {

    public String codiceCorso;

    public String titoloCorso;

    public String macroCategoriaModalitaFormativa;

    public String classeModalitaFormativa;

    public String tipoModalitaFormativa;

    public String tipoContenutoFormativo;

    public Date dataInizio;

    public Date dataFine;

    public String tipoCriterioSelezione;

    public String tipoAttestazioneFinale;

    public String qualifica;

    public Integer oreAulaFad;

    public Integer oreStageTirocinio;

    public Integer oreLaboratorio;

    public Integer durata;

    public Integer numeroDocentiFormatoriTutor;

    public Integer maxAllievi;

    public List<ModuloDTO> moduli;




}
