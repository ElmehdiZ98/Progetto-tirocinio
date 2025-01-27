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
@Table(name = "tipo_campo_intervento")
@NamedQuery(name = "TipoCampoIntervento.findAll", query = "SELECT t FROM TipoCampoIntervento t")
public class TipoCampoIntervento extends TipoClassificazione {

    @Column(name = "descrizione")
    public String descrizione;


    @Override
    public String getCategoria() {
        return "";
    }

    @Override
    public String getSiglaCategoria() {
        return "";
    }
}
