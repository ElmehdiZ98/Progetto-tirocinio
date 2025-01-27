package it.paa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_risultato_atteso")
@NamedQuery(name = "TipoRisultatoAtteso.findAll", query = "SELECT t FROM TipoRisultatoAtteso t")
public class TipoRisultatoAtteso extends TipoClassificazione {



    @Column(name = "codice_obiettivo_tematico")
    private String codiceObiettivoTematico;

    @Column(name = "descrizione_obiettivo_tematico")
    private String descrizioneObiettivoTematico;

    @Column(name = "origine_dato")
    private String origineDato;


    @Override
    public String getCategoria() {
        return "";
    }
    @Override
    public String getSiglaCategoria() {
        return "";
    }

}
